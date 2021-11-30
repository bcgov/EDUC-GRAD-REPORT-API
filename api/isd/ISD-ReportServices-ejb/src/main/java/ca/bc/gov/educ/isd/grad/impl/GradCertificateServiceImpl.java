/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.grad.impl;

import ca.bc.gov.educ.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.dao.GradToIsdDataConvertBean;
import ca.bc.gov.educ.grad.dto.ReportData;
import ca.bc.gov.educ.grad.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.service.GradReportCodeService;
import ca.bc.gov.educ.isd.adaptor.dao.utils.TRAXThreadDataUtility;
import ca.bc.gov.educ.isd.cert.Certificate;
import ca.bc.gov.educ.isd.codes.SignatureBlockType;
import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.eis.trax.db.StudentDemographic;
import ca.bc.gov.educ.isd.grad.GradCertificateReport;
import ca.bc.gov.educ.isd.grad.GradCertificateService;
import ca.bc.gov.educ.isd.reports.*;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.student.impl.SchoolImpl;
import ca.bc.gov.educ.isd.student.impl.StudentImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.Constants.DATE_ISO_8601_FULL;
import static ca.bc.gov.educ.isd.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.isd.reports.ReportFormat.PDF;
import static ca.bc.gov.educ.isd.transcript.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static java.util.Locale.CANADA;
import static java.util.Locale.CANADA_FRENCH;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class GradCertificateServiceImpl
        implements GradCertificateService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = GradCertificateServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    private ReportService reportService;

    @Autowired
    private GradReportCodeService codeService;

    @Autowired
    GradToIsdDataConvertBean gradtoIsdDataConvertBean;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public List<BusinessReport> buildReport() throws DomainServiceException {
        final String _m = "buildReport()";
        LOG.entering(CLASSNAME, _m);

        // access student demographics to obtain PEN
        PersonalEducationNumber penObj = null;
        String penId = null;

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        penObj = reportData.getStudent().getPen();
        penId = penObj.getValue();

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        // access TRAX adaptor to obtain required data for PEN
        final StudentDemographic studentData = gradtoIsdDataConvertBean.getSingleStudentDemog(reportData); //validated
        if (studentData == null) {
            final String msg = "Failed to find student demographic information for PEN: " + penId;
            final DomainServiceException dse = new DomainServiceException(msg);
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        final CertificateImpl certificate = (CertificateImpl)reportData.getCertificate();
        if (certificate == null) {
            final String msg = "Failed to find student certificate for PEN: " + penId;
            final DomainServiceException dse = new DomainServiceException(msg);
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        final Map<String, SignatureBlockTypeCode> signatureBlockTypeCodes = codeService.getSignatureBlockTypeCodesMap();
        final Map<String, SignatureBlockType> signatureBlockTypes = new HashMap<>();
        signatureBlockTypes.putAll(signatureBlockTypeCodes);
        certificate.setSignatureBlockTypes(signatureBlockTypes);

        // transfer TRAX data to other objects for reporting
        final Student student = transferStudentData(penObj, studentData); //validated
        final School school = transferSchoolData(studentData); //validated
        final String penEntityId = penObj.getEntityId();

        final List<BusinessReport> certificates = new ArrayList<>();
        final String englishCert = studentData.getEnglishCertificate().trim();
        final String frenchCert = studentData.getFrenchCertificate().trim();

        LOG.log(Level.FINE, "EnglishCert flag: {0}", englishCert);
        LOG.log(Level.FINE, "FrenchCert flag: {0}", frenchCert);

        if (!englishCert.isEmpty()) {
            final String certType = certificate.getCertificateType().getReportName();

            final GradCertificateReport gradCert = englishCertificate(
                    certType, student, school, penEntityId, certificate);

            certificates.add(gradCert);
        }

        if (!frenchCert.isEmpty()) {
            final String certType = certificate.getCertificateType().getReportName();

            final GradCertificateReport gradCert = frenchCertificate(
                    certType, student, school, penEntityId, certificate);

            certificates.add(gradCert);
        }

        LOG.exiting(CLASSNAME, _m);
        return certificates;
    }

    /**
     * Transfer the TRAX data from the data value object into a Student object.
     *
     * @param penObj
     * @param traxStudent
     * @return Student
     */
    private Student transferStudentData(
            final PersonalEducationNumber penObj,
            final StudentDemographic traxStudent) {
        final String _m = "transferStudentData(PersonalEducationNumber, StudentDemographic)";
        final Object[] params = {penObj, traxStudent};
        LOG.entering(CLASSNAME, _m, params);

        final StudentImpl student = new StudentImpl();
        student.setPen(penObj);
        student.setFirstName(traxStudent.getFirstName());
        student.setMiddleName(traxStudent.getMiddleName());
        student.setLastName(traxStudent.getLastName());
        student.setBirthdate(traxStudent.getBirthDate());

        LOG.exiting(CLASSNAME, _m);
        return student;
    }

    /**
     * Transfer the TRAX data from the data value object into a School object.
     *
     * @param traxStudent
     * @return School
     */
    private School transferSchoolData(final StudentDemographic traxStudent) {
        final String _m = "transferSchoolData(StudentDemographic)";
        LOG.entering(CLASSNAME, _m, traxStudent);

        String schoolId = traxStudent.getGradMincode();

        if (schoolId.isEmpty()) {
            schoolId = traxStudent.getMincode();
        }

        final SchoolImpl school = new SchoolImpl();

        if(traxStudent.getSchool() != null) {
            school.setMincode(traxStudent.getSchool().getMincode());
            school.setName(traxStudent.getSchool().getSchlName());
        } else {
            school.setMincode(schoolId);
            school.setName(traxStudent.getSchoolName());
        }

        school.setSignatureCode(traxStudent.getCertificateSignature());
        school.setTypeIndicator(traxStudent.getSchoolTypeIndicator());

        LOG.exiting(CLASSNAME, _m);
        return school;
    }

    /**
     * @param certType
     * @param student
     * @param school
     * @param entityId
     * @param certificate
     *
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport englishCertificate(
            final String certType,
            final Student student,
            final School school,
            final String entityId,
            final Certificate certificate) throws DomainServiceException {

        final CertificateType rsRptType;
        final CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, "Cert Type: {0}", certType);

        switch(certType) {
            case "E":
                rsRptType = CertificateType.E;
                break;
            case "A":
                rsRptType = CertificateType.A;
                break;
            case "EI":
                rsRptType = CertificateType.EI;
                break;
            case "AI":
                rsRptType = CertificateType.AI;
                break;
            case "S":
                rsRptType = CertificateType.S;
                break;
            case "SC":
                rsRptType = CertificateType.SC;
                break;
            case "SCF":
                rsRptType = CertificateType.SCF;
                break;
            case "F":
                rsRptType = CertificateType.F;
                break;
            case "O":
                rsRptType = CertificateType.O;
                break;
            default:
                rsRptType = CertificateType.E;
        }

        LOG.log(Level.FINE, "Type: {0}; Subtype: {1}",
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        final GradCertificateReport gradCert = createReport(
                student, school, entityId, certificate,
                CANADA, rsRptType, rsRptSubType);

        return gradCert;
    }

    /**
     * @param certType
     * @param student
     * @param school
     * @param entityId
     * @param certificate
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport frenchCertificate(
            final String certType,
            final Student student,
            final School school,
            final String entityId,
            final Certificate certificate) throws DomainServiceException {

        final CertificateType rsRptType;
        final CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, "Cert Type: {0}", certType);

        switch(certType) {
            case "E":
                rsRptType = CertificateType.E;
                break;
            case "A":
                rsRptType = CertificateType.A;
                break;
            case "EI":
                rsRptType = CertificateType.EI;
                break;
            case "AI":
                rsRptType = CertificateType.AI;
                break;
            case "S":
                rsRptType = CertificateType.S;
                break;
            case "SC":
                rsRptType = CertificateType.SC;
                break;
            case "SCF":
                rsRptType = CertificateType.SCF;
                break;
            case "F":
                rsRptType = CertificateType.F;
                break;
            case "O":
                rsRptType = CertificateType.O;
                break;
            default:
                rsRptType = CertificateType.E;
        }

        LOG.log(Level.FINE, "Type: {0}; Subtype: {1}",
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        final GradCertificateReport gradCert = createReport(
                student, school, entityId, certificate,
                CANADA_FRENCH, rsRptType, rsRptSubType);

        return gradCert;
    }

    /**
     * @param student
     * @param school
     * @param entityId
     * @param certificate
     * @param location
     * @param rsRptType
     * @param rsRptSubType
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport createReport(
            final Student student,
            final School school,
            final String entityId,
            final Certificate certificate,
            final Locale location,
            final CertificateType rsRptType,
            final CertificateSubType rsRptSubType) throws DomainServiceException {

        final String methodName = "createReport(Student, School, String, Certificate, Locale, CertificateReportType, CertificateReportSubtype)";
        LOG.entering(CLASSNAME, methodName);

        String timestamp = new SimpleDateFormat(DATE_ISO_8601_FULL).format(new Date());

        CertificateReport certificateReport = reportService.createCertificateReport();
        certificateReport.setReportType(rsRptType);
        certificateReport.setReportSubtype(rsRptSubType);
        certificateReport.setLocale(location);
        certificateReport.setStudent(student);
        certificateReport.setCertificate(certificate);
        certificateReport.setSchool(school);

        GradCertificateReport report = null;
        try {
            final ReportDocument rptDoc = reportService.export(certificateReport);

            StringBuilder sb = new StringBuilder("certificate_");
            sb.append(location.toLanguageTag());
            sb.append("_");
            sb.append(entityId);
            sb.append("_");
            sb.append(timestamp);
            sb.append(".");
            sb.append(PDF.getFilenameExtension());
            final String filename = certificateReport.getFilename();

            byte[] inData = rptDoc.asBytes();
            inData = ArrayUtils.nullToEmpty(inData);
            if (ArrayUtils.isEmpty(inData)) {
                String msg = "The generated report output is empty.";
                DomainServiceException dse = new DomainServiceException(null,
                        msg);
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }
            byte[] rptData = inData;

            // TODO: Use a constant for the name.
            report = new GradCertificateReportImpl(rptData, PDF, filename, createReportTypeName(rsRptType, rsRptSubType, location));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the provincial examination report.", ex);
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    private String createReportTypeName(
            final CertificateType type,
            final CertificateSubType subtype,
            final Locale locale) {
        final String reportTypeName
                = type.toString()
                + " "
                + subtype.toString()
                + " "
                + locale.getISO3Language();
        return reportTypeName;
    }
}

/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.TraxSchool;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.dto.impl.CertificateImpl;
import ca.bc.gov.educ.grad.report.dto.impl.GradCertificateReportImpl;
import ca.bc.gov.educ.grad.report.dto.impl.SchoolImpl;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.exception.InvalidParameterException;
import ca.bc.gov.educ.grad.report.model.cert.Certificate;
import ca.bc.gov.educ.grad.report.model.cert.CertificateSubType;
import ca.bc.gov.educ.grad.report.model.cert.CertificateType;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.graduation.GradCertificateReport;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import ca.bc.gov.educ.grad.report.model.reports.CertificateReport;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.utils.SerializableMap;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_ISO_8601_FULL;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_EXITING;
import static java.util.Locale.CANADA;
import static java.util.Locale.CANADA_FRENCH;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class StudentCertificateServiceImpl extends GradReportServiceImpl
        implements StudentCertificateService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = StudentCertificateServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String CERTIFICATE_TYPE = "Cert Type: {0}";
    private static final String TYPE_SUB_TYPE = "Type: {0}; Subtype: {1}";

    @Autowired ReportService reportService;
    @Autowired GradReportCodeService codeService;

    @Autowired GradDataConvertionBean gradDataConvertionBean;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public List<BusinessReport> buildReport() throws DomainServiceException {
        final String methodName = "buildReport()";
        log.trace(LOG_TRACE_ENTERING, methodName);

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        String accessToken = reportData.getAccessToken();

        final Certificate certificate = getCertificate(reportData);
        if (certificate == null) {
            final String msg = "Failed to find student certificate";
            final DomainServiceException dse = new DomainServiceException(msg);
            log.error(msg, dse);
            throw dse;
        }

        // validate incoming data for reporting
        final Student student = getStudent(reportData); //validated
        final School school = getSchool(reportData); //validated

        if(school != null && !StringUtils.isBlank(school.getSchoolId())) {
            TraxSchool traxSchool = getSchool(school.getSchoolId(), accessToken);
            if(traxSchool != null) {
                if ("N".equalsIgnoreCase(traxSchool.getCertificateEligibility())) {
                    EntityNotFoundException dse = new EntityNotFoundException(
                            getClass(),
                            REPORT_DATA_VALIDATION,
                            "School is not eligible for certificates");
                    log.error(dse.getMessage(), dse);
                    throw dse;
                }
                if(StringUtils.isBlank(school.getSchoolCategoryCode())) {
                    ((SchoolImpl) school).setSchoolCategoryCode(traxSchool.getSchoolCategoryLegacyCode());
                }
            }
        }

        final String englishCert;
        final String frenchCert;

        //adapt student certificate flags to requested certificate type
        final String certType = certificate.getCertificateType().getReportName();
        switch (certType) {
            case "F":
            case "SCF":
            case "S":
                frenchCert = (certType);
                englishCert = ("");
                break;
            default:
                englishCert = (certType);
                frenchCert = ("");
                break;
        }

        LOG.log(Level.FINE, "EnglishCert flag: {0}", englishCert);
        LOG.log(Level.FINE, "FrenchCert flag: {0}", frenchCert);

        final SerializableMap<String, SignatureBlockTypeCode> signatureBlockTypeCodes = codeService.getSignatureBlockTypeCodesMap();
        final Map<String, SignatureBlockType> signatureBlockTypes = new HashMap<>(SerializationUtils.clone(signatureBlockTypeCodes));
        ((CertificateImpl)certificate).setSignatureBlockTypes(signatureBlockTypes);

        final List<BusinessReport> certificates = new ArrayList<>();

        if (!englishCert.isEmpty()) {
            final GradCertificateReport gradCert = englishCertificate(
                    certType, student, school, certificate);

            certificates.add(gradCert);
        }

        if (!frenchCert.isEmpty()) {
            final GradCertificateReport gradCert = frenchCertificate(
                    certType, student, school, certificate);

            certificates.add(gradCert);
        }

        log.trace(LOG_TRACE_EXITING, methodName);
        return certificates;
    }

    /**
     * @param certType
     * @param student
     * @param school
     * @param certificate
     *
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport englishCertificate(
            final String certType,
            final Student student,
            final School school,
            final Certificate certificate) throws DomainServiceException {

        final CertificateType rsRptType = adaptCertificateType(certType);
        final CertificateSubType rsRptSubType;
        if (Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()))
            rsRptSubType = CertificateSubType.ORIGINAL;
        else
            rsRptSubType = Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, CERTIFICATE_TYPE, certType);

        LOG.log(Level.FINE, TYPE_SUB_TYPE,
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        return createReport(student, school, certificate, CANADA, rsRptType, rsRptSubType);
    }

    /**
     * @param certType
     * @param student
     * @param school
     * @param certificate
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport frenchCertificate(
            final String certType,
            final Student student,
            final School school,
            final Certificate certificate) throws DomainServiceException {

        final CertificateType rsRptType = adaptCertificateType(certType);
        CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : null;
        if(rsRptSubType == null)
            rsRptSubType = Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, CERTIFICATE_TYPE, certType);

        LOG.log(Level.FINE, TYPE_SUB_TYPE,
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        return createReport(student, school, certificate, CANADA_FRENCH, rsRptType, rsRptSubType);
    }

    private CertificateType adaptCertificateType(String certType) {
        final CertificateType rsRptType;
        switch(certType) {
            case "A":
                rsRptType = CertificateType.A;
                break;
            case "AI":
                rsRptType = CertificateType.AI;
                break;
            case "E":
                rsRptType = CertificateType.E;
                break;
            case "EI":
                rsRptType = CertificateType.EI;
                break;
            case "S":
                rsRptType = CertificateType.S;
                break;
            case "SC":
                rsRptType = CertificateType.SC;
                break;
            case "SCI":
                rsRptType = CertificateType.SCI;
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
            case "FN":
                rsRptType = CertificateType.FN;
                break;
            case "FNA":
                rsRptType = CertificateType.FNA;
                break;
            case "SCFN":
                rsRptType = CertificateType.SCFN;
                break;
            default:
                throw new InvalidParameterException(certType);
        }
        return rsRptType;
    }

    /**
     * @param student
     * @param school
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
            final Certificate certificate,
            final Locale location,
            final CertificateType rsRptType,
            final CertificateSubType rsRptSubType) throws DomainServiceException {

        final String methodName = "createReport(Student, School, String, Certificate, Locale, CertificateReportType, CertificateReportSubtype)";
        log.trace(LOG_TRACE_ENTERING, methodName);

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
            final String filename = certificateReport.getFilename();

            byte[] inData = rptDoc.asBytes();
            inData = ArrayUtils.nullToEmpty(inData);
            if (ArrayUtils.isEmpty(inData)) {
                String msg = "The generated report output is empty.";
                DomainServiceException dse = new DomainServiceException(null,
                        msg);
                log.error(msg, dse);
                throw dse;
            }
            byte[] rptData = inData;

            report = new GradCertificateReportImpl(rptData, PDF, filename, createReportTypeName(rsRptType, rsRptSubType, location));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the provincial examination report.", ex);
        }

        log.trace(LOG_TRACE_EXITING, methodName);
        return report;
    }

    private String createReportTypeName(
            final CertificateType type,
            final CertificateSubType subtype,
            final Locale locale) {
        return type.toString() + " " + subtype.toString() + " " + locale.getISO3Language();
    }

    @Override
    GraduationReport createGraduationReport() {
        throw new NotImplementedException("Method createGraduationReport() not implemented");
    }
}

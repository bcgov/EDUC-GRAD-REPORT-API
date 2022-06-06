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
package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.dto.impl.CertificateImpl;
import ca.bc.gov.educ.grad.report.dto.impl.GradCertificateReportImpl;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.model.cert.Certificate;
import ca.bc.gov.educ.grad.report.model.cert.CertificateSubType;
import ca.bc.gov.educ.grad.report.model.cert.CertificateType;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.graduation.GradCertificateReport;
import ca.bc.gov.educ.grad.report.model.graduation.GradCertificateService;
import ca.bc.gov.educ.grad.report.model.reports.CertificateReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
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

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_ISO_8601_FULL;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;
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
    private static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";
    private static final String CERTIFICATE_TYPE = "Cert Type: {0}";
    private static final String TYPE_SUB_TYPE = "Type: {0}; Subtype: {1}";

    @Autowired ReportService reportService;
    @Autowired GradReportCodeService codeService;

    @Autowired GradDataConvertionBean gradDataConvertionBean;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public List<BusinessReport> buildReport() throws DomainServiceException {
        final String methodName = "buildReport()";
        LOG.entering(CLASSNAME, methodName);


        ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        // validate incoming data for reporting
        final Student student = gradDataConvertionBean.getStudent(reportData); //validated
        final School school = gradDataConvertionBean.getSchool(reportData); //validated

        final CertificateImpl certificate = (CertificateImpl)gradDataConvertionBean.getCertificate(reportData.getCertificate());
        if (certificate == null) {
            final String msg = "Failed to find student certificate";
            final DomainServiceException dse = new DomainServiceException(msg);
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        final Map<String, SignatureBlockTypeCode> signatureBlockTypeCodes = codeService.getSignatureBlockTypeCodesMap();
        final Map<String, SignatureBlockType> signatureBlockTypes = new HashMap<>(signatureBlockTypeCodes);
        certificate.setSignatureBlockTypes(signatureBlockTypes);

        final List<BusinessReport> certificates = new ArrayList<>();
        final String englishCert = student.getEnglishCert().trim();
        final String frenchCert = student.getFrenchCert().trim();

        LOG.log(Level.FINE, "EnglishCert flag: {0}", englishCert);
        LOG.log(Level.FINE, "FrenchCert flag: {0}", frenchCert);

        if (!englishCert.isEmpty()) {
            final String certType = certificate.getCertificateType().getReportName();

            final GradCertificateReport gradCert = englishCertificate(
                    certType, student, school, certificate);

            certificates.add(gradCert);
        }

        if (!frenchCert.isEmpty()) {
            final String certType = certificate.getCertificateType().getReportName();

            final GradCertificateReport gradCert = frenchCertificate(
                    certType, student, school, null, certificate);

            certificates.add(gradCert);
        }

        LOG.exiting(CLASSNAME, methodName);
        return certificates;
    }

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public List<BusinessReport> buildBlankReport() throws DomainServiceException {
        final String methodName = "buildBlankReport()";
        LOG.entering(CLASSNAME, methodName);


        ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        final CertificateImpl certificate = (CertificateImpl)gradDataConvertionBean.getCertificate(reportData.getCertificate());
        if (certificate == null) {
            final String msg = "Failed to find student certificate";
            final DomainServiceException dse = new DomainServiceException(msg);
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        final Map<String, SignatureBlockTypeCode> signatureBlockTypeCodes = codeService.getSignatureBlockTypeCodesMap();
        final Map<String, SignatureBlockType> signatureBlockTypes = new HashMap<>(signatureBlockTypeCodes);
        certificate.setSignatureBlockTypes(signatureBlockTypes);

        final List<BusinessReport> certificates = new ArrayList<>();
        final String englishCert = reportData.getStudent().getEnglishCert() != null? reportData.getStudent().getEnglishCert().trim():"";
        final String frenchCert = reportData.getStudent().getFrenchCert() != null ?reportData.getStudent().getFrenchCert().trim():"";

        LOG.log(Level.FINE, "EnglishCert flag: {0}", englishCert);
        LOG.log(Level.FINE, "FrenchCert flag: {0}", frenchCert);

        if (!englishCert.isEmpty()) {
            final String certType = certificate.getCertificateType().getReportName();

            final GradCertificateReport gradCert = blankEnglishCertificate(
                    certType,certificate);

            certificates.add(gradCert);
        }

        if (!frenchCert.isEmpty()) {
            final String certType = certificate.getCertificateType().getReportName();

            final GradCertificateReport gradCert = blankFrenchCertificate(
                    certType,certificate);

            certificates.add(gradCert);
        }

        LOG.exiting(CLASSNAME, methodName);
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
        final CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, CERTIFICATE_TYPE, certType);

        LOG.log(Level.FINE, TYPE_SUB_TYPE,
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        return createReport(student, school, certificate,CANADA, rsRptType, rsRptSubType);
    }

    private GradCertificateReport blankEnglishCertificate(
            final String certType,
            final Certificate certificate) throws DomainServiceException {

        final CertificateType rsRptType = adaptCertificateType(certType);
        CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : null;
        if(rsRptSubType == null)
            rsRptSubType = Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;
        LOG.log(Level.FINE, CERTIFICATE_TYPE, certType);

        LOG.log(Level.FINE, TYPE_SUB_TYPE,new Object[]{rsRptType, rsRptSubType});

        return createBlankReport(certificate,CANADA, rsRptType, rsRptSubType);
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

        final CertificateType rsRptType = adaptCertificateType(certType);
        CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : null;
        if(rsRptSubType == null)
            rsRptSubType = Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, CERTIFICATE_TYPE, certType);

        LOG.log(Level.FINE, TYPE_SUB_TYPE,
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        return createReport(student, school, certificate,CANADA_FRENCH, rsRptType, rsRptSubType);
    }

    private GradCertificateReport blankFrenchCertificate(
            final String certType,
            final Certificate certificate) throws DomainServiceException {

        final CertificateType rsRptType = adaptCertificateType(certType);
        CertificateSubType rsRptSubType = Certificate.CERT_STYLE_ORIGINAL.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.ORIGINAL : null;
        if(rsRptSubType == null)
            rsRptSubType = Certificate.CERT_STYLE_REPRINT.equalsIgnoreCase(certificate.getCertStyle()) ? CertificateSubType.REPRINT : CertificateSubType.BLANK;

        LOG.log(Level.FINE, CERTIFICATE_TYPE, certType);

        LOG.log(Level.FINE, TYPE_SUB_TYPE,new Object[]{rsRptType, rsRptSubType});

        return createBlankReport(certificate,CANADA_FRENCH, rsRptType, rsRptSubType);
    }

    private CertificateType adaptCertificateType(String certType) {
        final CertificateType rsRptType;
        switch(certType) {
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

    private GradCertificateReport createBlankReport(
            final Certificate certificate,
            final Locale location,
            final CertificateType rsRptType,
            final CertificateSubType rsRptSubType) throws DomainServiceException {

        final String methodName = "createBlankReport(String, Certificate, Locale, CertificateReportType, CertificateReportSubtype)";
        LOG.entering(CLASSNAME, methodName);

        String timestamp = new SimpleDateFormat(DATE_ISO_8601_FULL).format(new Date());

        CertificateReport certificateReport = reportService.createCertificateReport();
        certificateReport.setReportType(rsRptType);
        certificateReport.setReportSubtype(rsRptSubType);
        certificateReport.setLocale(location);
        certificateReport.setCertificate(certificate);

        GradCertificateReport report = null;
        try {
            final ReportDocument rptDoc = reportService.export(certificateReport);

            StringBuilder sb = new StringBuilder("certificate_");
            sb.append(location.toLanguageTag());
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
        return type.toString() + " " + subtype.toString() + " " + locale.getISO3Language();
    }
}

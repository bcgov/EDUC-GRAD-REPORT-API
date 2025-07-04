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
package ca.bc.gov.educ.grad.report.dto.reports.impl;

import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.JasperReportImpl;
import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.ParameterPredicateImpl;
import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.ReportDocumentImpl;
import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.TranscriptJasperReportImpl;
import ca.bc.gov.educ.grad.report.dto.reports.service.AbstractReportService;
import ca.bc.gov.educ.grad.report.model.graduation.GradProgram;
import ca.bc.gov.educ.grad.report.model.reports.*;
import ca.bc.gov.educ.grad.report.model.transcript.ParameterPredicate;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptTypeCode;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.reports.impl.constants.Roles.*;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;

/**
 * Provides a mechanism to create reports to fill out and produce a specific
 * format.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({
    USER,
    USER_REPORTS_EXPORT,
    USER_REPORTS_PEAR,
    USER_REPORTS_SCHOLARSHIPS,
    USER_REPORTS_TRANSCRIPT,
    USER_REPORTS_PACKINGSLIP,
        USER_REPORTS_SCHOOL_GRADUATION,
    USER_REPORTS_CERTIFICATES,
    FULFILLMENT_SERVICES_USER})
public class ReportServiceImpl extends AbstractReportService implements ReportService {

    private static final String CLASSNAME = ReportServiceImpl.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final long serialVersionUID = 2L;
    private final GradReportSignatureService gradReportSignatureService;

    public ReportServiceImpl(GradReportSignatureService gradReportSignatureService) {
        this.gradReportSignatureService = gradReportSignatureService;
    }
    /**
     * @throws IOException Could not read resources required for filling the
     * report (e.g., resource bundle or report template).
     * @param report The report to fill and export.
     */
    @Override
    @PermitAll
    public ReportDocument export(final Report report) throws IOException {
        return super.export(report);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_TRANSCRIPT, FULFILLMENT_SERVICES_USER})
    public TranscriptReport createTranscriptReport(TranscriptTypeCode transcriptTypeCode, GradProgram program) {
        return new TranscriptReportImpl(transcriptTypeCode, program, gradReportSignatureService);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_CERTIFICATES, FULFILLMENT_SERVICES_USER})
    public CertificateReport createCertificateReport() {
        return new CertificateReportImpl(gradReportSignatureService);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createSchoolDistributionReport() {
        return new SchoolGraduationReportImpl("SchoolDistribution");
    }

    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createDistrictDistributionYearEndCredentialsReport() {
        return new SchoolGraduationReportImpl("DistrictDistYearEndCred");
    }

    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createDistrictDistributionYearEndNonGradCredentialsReport() {
        return new SchoolGraduationReportImpl("DistrictDistYearEndNonGradCred");
    }

    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createSchoolDistributionYearEndNewCredentialsReport() {
        return new SchoolGraduationReportImpl("SchoolDistYearEndCred");
    }

    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createSchoolDistributionYearEndIssuedTranscriptsReport() {
        return new SchoolGraduationReportImpl("SchoolDistYearEndTran");
    }

    @Override
    public GraduationReport createSchoolLabelReport() {
        return new SchoolGraduationReportImpl("SchoolLabel");
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createSchoolGraduationReport() {
        return new SchoolGraduationReportImpl("SchoolGraduation");
    }

    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_GRADUATION})
    public GraduationReport createSchoolNonGraduationReport() {
        return new SchoolNonGraduationReportImpl("SchoolNonGraduation");
    }

    @Override
    @RolesAllowed({USER_REPORTS_NON_GRAD})
    public GraduationReport createStudentNonGradProjectedReport() {
        return new StudentNonGradProjectedReportImpl();
    }

    @Override
    @RolesAllowed({USER_REPORTS_NON_GRAD})
    public GraduationReport createStudentNonGradReport() {
        return new StudentNonGradReportImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER, FULFILLMENT_SERVICES_USER})
    public ReportDocument createReportDocument(final byte[] bytes) {
        return new ReportDocumentImpl(bytes);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER, FULFILLMENT_SERVICES_USER})
    public ParameterPredicate createParameterPredicate() {
        final String methodName = "createParameterPredicate()";
        LOG.entering(CLASSNAME, methodName);

        final ParameterPredicate pp = new ParameterPredicateImpl();

        LOG.exiting(CLASSNAME, methodName);
        return pp;
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER, FULFILLMENT_SERVICES_USER})
    public Parameters<String, Object> createParameters() {
        final String methodName = "createParameters()";
        LOG.entering(CLASSNAME, methodName);

        final Parameters<String, Object> parameters = new LinkedParameters<>();

        LOG.exiting(CLASSNAME, methodName);
        return parameters;
    }

    @Override
    @RolesAllowed({USER_REPORTS_TRANSCRIPT, FULFILLMENT_SERVICES_USER})
    public AchievementReport createAchievementReport() {
        return new AchievementReportImpl();
    }

    @Override
    @RolesAllowed({USER_REPORTS_TRANSCRIPT, FULFILLMENT_SERVICES_USER})
    public AchievementReport createAchievementReport(String reportName) {
        return new AchievementReportImpl(reportName);
    }

    /**
     * Based on the type of report, this will return an internal representation
     * of the given report instance that can use JasperReports without exposing
     * said library to the calling client. (Prevents marshaling of JasperReports
     * library classes, which won't be found on the client side.)
     *
     * @param report The report to coerce to a JasperReport implementation.
     * @return The given report coerced into a corresponding JasperReport
     * implementation (e.g., a TranscriptReport becomes a
     * TranscriptJasperReportImpl).
     */
    @Override
    protected JasperReportImpl createJasperReportImpl(final Report report) {
        final JasperReportImpl result;

        if (report instanceof TranscriptReport) {
            result = new TranscriptJasperReportImpl(report);
        } else {
            result = new JasperReportImpl(report);
        }

        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_PACKINGSLIP, FULFILLMENT_SERVICES_USER})
    public PackingSlipReport createPackingSlipReport() {
        return new PackingSlipReportImpl();
    }
}

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
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
    USER_REPORTS_SCHOOL_DISTRIBUTION,
    USER_REPORTS_CERTIFICATES,
    FULFILLMENT_SERVICES_USER})
public class ReportServiceImpl extends AbstractReportService implements ReportService {

    private static final String CLASSNAME = ReportServiceImpl.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final long serialVersionUID = 2L;

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
        return new TranscriptReportImpl(transcriptTypeCode, program);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_CERTIFICATES, FULFILLMENT_SERVICES_USER})
    public CertificateReport createCertificateReport() {
        return new CertificateReportImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_SCHOOL_DISTRIBUTION})
    public DistributionReport createSchoolDistributionReport() {
        return new DistributionReportImpl();
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
    public Parameters createParameters() {
        final String methodName = "createParameters()";
        LOG.entering(CLASSNAME, methodName);

        final Parameters parameters = new LinkedParameters();

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

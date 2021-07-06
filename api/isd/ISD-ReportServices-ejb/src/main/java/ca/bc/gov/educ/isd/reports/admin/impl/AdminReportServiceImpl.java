/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: AdminReportServiceImpl.java 11078 2018-08-07 17#$
 *  Date of Last Commit: $Date:: 2018-08-07 10:04:34 -0700 (Tue, 07 Aug 2018)  $
 *  Revision Number:     $Rev:: 11078                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.Report;
import ca.bc.gov.educ.isd.reports.ReportDocument;
import ca.bc.gov.educ.isd.reports.admin.AdminReport;
import ca.bc.gov.educ.isd.reports.admin.AdminReportService;
import ca.bc.gov.educ.isd.reports.admin.data.*;
import ca.bc.gov.educ.isd.reports.common.impl.AbstractReportService;
import ca.bc.gov.educ.isd.reports.impl.constants.Roles;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;

import static ca.bc.gov.educ.isd.reports.impl.constants.Roles.*;

/**
 * Provides a mechanism to create reports to fill out and produce a specific
 * format.
 *
 * @author CGI Information Management Consultants Inc.
 */
@DeclareRoles({
    ADMIN_REPORTS_SERVICE,
    ADMIN_REPORTS_EXPORT,
    ADMIN_REPORTS_CREATE_DATA,
    ADMIN_REPORTS_CREATE_REPORT,})
public class AdminReportServiceImpl extends AbstractReportService
        implements AdminReportService {

    /**
     * @throws IOException
     * @inheritDoc
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_EXPORT)
    public ReportDocument export(final Report report) throws IOException {
        return super.export(report);
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<ManualInterventionOrder> createManualInterventionOrderReport() {
        return new ManualInterventionOrderReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<NonPENUserOrder> createNonPENUserOrderReport() {
        return new NonPENUserOrderReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<PENUserOrder> createPENUserOrderReport() {
        return new PENUserOrderReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<PSIChoice> createPSIChoiceReport() {
        return new PSIChoiceReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<RegistrationIssue> createRegistrationIssueReport() {
        return new RegistrationIssueReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<SelfServeOrder> createSelfServeOrderReport() {
        return new SelfServeOrderReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<StudentProfile> createStudentProfileReport() {
        return new StudentProfileReportImpl();
    }

    /**
     * Creates a report instance that can be exported into a final report
     * document.
     *
     * @return A specific report that must be initialized with data.
     */
    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_REPORT)
    public AdminReport<IdleTranscript> createIdleTranscriptReport() {
        return new IdleTranscriptReportImpl();
    }

    @Override
    @RolesAllowed(Roles.ADMIN_REPORTS_CREATE_DATA)
    public AdminReportDAO createReportOfType(final Class reportClass) {
        final AdminReportDAO response;

        if (reportClass == PSIChoice.class) {
            response = new PSIChoiceImpl();
        } else if (reportClass == NonPENUserOrder.class) {
            response = new NonPENUserOrderImpl();
        } else if (reportClass == StudentProfile.class) {
            response = new StudentProfileImpl();
        } else if (reportClass == RegistrationIssue.class) {
            response = new RegistrationIssueImpl();
        } else if (reportClass == SelfServeOrder.class) {
            response = new SelfServeOrderImpl();
        } else if (reportClass == PENUserOrder.class) {
            response = new PENUserOrderImpl();
        } else if (reportClass == ManualInterventionOrder.class) {
            response = new ManualInterventionOrderImpl();
        } else if (reportClass == IdleTranscript.class) {
            response = new IdleTranscriptImpl();
        } else {
            response = null;
        }

        return response;
    }
}

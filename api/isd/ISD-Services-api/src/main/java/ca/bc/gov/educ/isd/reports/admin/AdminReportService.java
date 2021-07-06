/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: AdminReportService.java 7153 2017-05-16 20:36:3#$
 *  Date of Last Commit: $Date:: 2017-05-16 13:36:34 -0700 (Tue, 16 May 2017)  $
 *  Revision Number:     $Rev:: 7153                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports.admin;

import ca.bc.gov.educ.isd.reports.ReportExportService;
import ca.bc.gov.educ.isd.reports.admin.data.AdminReportDAO;
import ca.bc.gov.educ.isd.reports.admin.data.IdleTranscript;
import ca.bc.gov.educ.isd.reports.admin.data.ManualInterventionOrder;
import ca.bc.gov.educ.isd.reports.admin.data.NonPENUserOrder;
import ca.bc.gov.educ.isd.reports.admin.data.PENUserOrder;
import ca.bc.gov.educ.isd.reports.admin.data.PSIChoice;
import ca.bc.gov.educ.isd.reports.admin.data.RegistrationIssue;
import ca.bc.gov.educ.isd.reports.admin.data.SelfServeOrder;
import ca.bc.gov.educ.isd.reports.admin.data.StudentProfile;

/**
 * Responsible for creating various administrative reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AdminReportService extends ReportExportService {

    /**
     * Creates an unfilled AdminReport for ManualInterventionOrder instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<ManualInterventionOrder> createManualInterventionOrderReport();

    /**
     * Creates an unfilled AdminReport for NonPENUserOrder instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<NonPENUserOrder> createNonPENUserOrderReport();

    /**
     * Creates an unfilled AdminReport for PENUserOrder instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<PENUserOrder> createPENUserOrderReport();

    /**
     * Creates an unfilled AdminReport for PSIChoice instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<PSIChoice> createPSIChoiceReport();

    /**
     * Creates an unfilled AdminReport for RegistrationIssue instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<RegistrationIssue> createRegistrationIssueReport();

    /**
     * Creates an unfilled AdminReport for SelfServeOrder instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<SelfServeOrder> createSelfServeOrderReport();

    /**
     * Creates an unfilled AdminReport for StudentProfile instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<StudentProfile> createStudentProfileReport();

    /**
     * Creates an unfilled AdminReport for IdleTranscript instances.
     *
     * @return An instance to populate with data.
     */
    public AdminReport<IdleTranscript> createIdleTranscriptReport();

    /**
     * Creates an unfilled Admin report.
     *
     * @param reportType type of the report to be created.
     * @return An instance to populate with data.
     */
    AdminReportDAO createReportOfType(Class reportType);
}

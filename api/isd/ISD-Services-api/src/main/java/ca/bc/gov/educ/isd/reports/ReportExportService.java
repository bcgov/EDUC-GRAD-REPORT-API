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
 *  File:                $Id:: ReportExportService.java 11078 2018-08-07 17:04#$
 *  Date of Last Commit: $Date:: 2018-08-07 10:04:34 -0700 (Tue, 07 Aug 2018)  $
 *  Revision Number:     $Rev:: 11078                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.common.BusinessService;

import java.io.IOException;

/**
 * Common interface used by different report services.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ReportExportService extends BusinessService {

    /**
     * Generates a report document based on the report instance. The report
     * instance must be configured but not filled prior to calling this method.
     * This executes the report, fills the template with data, and produces a
     * final document.
     *
     * @param report The unfilled report to fill for producing a final document.
     * @return A document in the final format specified by the report itself.
     * @throws IOException An error occurred during filling the report (such as
     * the report template is missing or a mismatch exists between the report
     * template and the data used to fill the report).
     */
    ReportDocument export(Report report) throws IOException;
}

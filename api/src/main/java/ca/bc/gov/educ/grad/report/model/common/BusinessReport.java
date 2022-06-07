/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: BusinessReport.java 7057 2017-05-11 17:50:55Z c#$
 *  Date of Last Commit: $Date:: 2017-05-11 10:50:55 -0700 (Thu, 11 May 2017)  $
 *  Revision Number:     $Rev:: 7057                                           $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.common;

import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;

/**
 * A common structure for domain objects which are report containers. Report
 * objects have a common page-like structure in addition to any data access
 * fields that may be needed for report rendering.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BusinessReport extends DomainEntity {

    /**
     * Retrieve Report Type Unique identifier.
     * <p>
     * Each type of report has a unique name or key which is used when calling
     * report rendering services to identify the templates and formats that can
     * be used.
     * <p>
     * This value only need to be unique for the type of report. It should be
     * aligned with the report name in any report server used.
     *
     * @return Unique identifier for the report type.
     */
    String getReportTypeName();

    /**
     * Retrieve format used for generating the report.
     *
     * @return Report format used when exporting.
     */
    ReportFormat getReportFormat();

    /**
     * Return the report data as an array of Bytes.
     *
     * @return Raw data which represents the rendered report.
     */
    byte[] getReportData();

    /**
     * Retrieve the unique report filename used to represent the report instance
     * and which is used if the report is saved locally to a workstation.
     *
     * @return Generated filename.
     */
    String getFilename();

}

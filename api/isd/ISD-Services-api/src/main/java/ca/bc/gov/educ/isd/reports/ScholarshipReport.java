/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: ScholarshipReport.java 10969 2018-07-25 16:13:3#$
 *  Date of Last Commit: $Date:: 2018-07-25 09:13:39 -0700 (Wed, 25 Jul 2018)  $
 *  Revision Number:     $Rev:: 10969                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.scholarship.Scholarship;

import java.util.List;

/**
 * Represents information required to generate a student scholarship report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ScholarshipReport extends StudentReport {

    /**
     * Sets the scholarship (or award) information associated with a student.
     *
     * @param scholarships The scholarships (or award) that are available to,
     * and possibly redeemed by, a student.
     */
    void setScholarships(List<Scholarship> scholarships);
}

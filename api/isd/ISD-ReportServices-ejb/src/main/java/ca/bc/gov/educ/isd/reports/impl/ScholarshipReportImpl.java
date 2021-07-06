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
 *  File:                $Id:: ScholarshipReportImpl.java 11067 2018-08-03 20:#$
 *  Date of Last Commit: $Date:: 2018-08-03 13:55:33 -0700 (Fri, 03 Aug 2018)  $
 *  Revision Number:     $Rev:: 11067                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.reports.ScholarshipReport;
import ca.bc.gov.educ.isd.scholarship.Scholarship;

import java.util.List;

import static ca.bc.gov.educ.isd.reports.data.adapter.BusinessEntityAdapter.adapt;

/**
 * Represents a student's scholarship report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ScholarshipReportImpl extends StudentReportImpl implements ScholarshipReport {

    private static final long serialVersionUID = 6288402695216314104L;

    public static final String REPORT_NAME = "Scholarship";

    /**
     * Constructs a new report using the default report template.
     */
    public ScholarshipReportImpl() {
        super(REPORT_NAME);
    }

    /**
     * Assigns the given list of scholarships to the student associated with
     * this report.
     *
     * @param scholarships
     */
    @Override
    public void setScholarships(final List<Scholarship> scholarships) {
        ensureValidStudent("setScholarships");

        adapt(scholarships, getStudent());
    }
}

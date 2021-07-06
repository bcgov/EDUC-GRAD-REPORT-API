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
 *  File:                $Id:: ProvincialExaminationReportImpl.java 3740 2016-#$
 *  Date of Last Commit: $Date:: 2016-09-21 17:12:30 -0700 (Wed, 21 Sep 2016)  $
 *  Revision Number:     $Rev:: 3740                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.exam.Exam;
import ca.bc.gov.educ.isd.reports.ProvincialExaminationReport;
import ca.bc.gov.educ.isd.reports.data.adapter.BusinessEntityAdapter;

/**
 * Represents a student's provincial examination report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ProvincialExaminationReportImpl
        extends StudentReportImpl implements ProvincialExaminationReport {

    private static final long serialVersionUID = 2L;

    public static final String REPORT_NAME = "PEAR";

    /**
     * Constructs a new report using the default report template.
     */
    public ProvincialExaminationReportImpl() {
        super(REPORT_NAME);
    }

    /**
     * Sets the student's examination results based on the given container for
     * examination results.
     *
     * @param examination The examination object that contains a list of
     * examinations.
     */
    @Override
    public void setExamination(final Exam examination) {
        ensureValidStudent("setExamination");

        BusinessEntityAdapter.adapt(examination, getStudent());
    }

    /**
     * Returns the portion of the file name to add after the prefix but before
     * the filename extension. This is useful for when the same report type is
     * used but differs by some other element (such as the logo or school
     * district).
     *
     * @return Various report parameter values separated by hyphens.
     */
    @Override
    protected String getFilenameSuffix() {
        return "-" + getLogoCode();
    }
}

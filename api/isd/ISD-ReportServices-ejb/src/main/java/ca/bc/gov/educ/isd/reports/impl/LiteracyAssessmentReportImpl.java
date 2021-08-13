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
 *  File:                $Id:: LiteracyAssessmentReportImpl_1.java 10957 2018-#$
 *  Date of Last Commit: $Date:: 2018-07-24 10:18:30 -0700 (Tue, 24 Jul 2018)  $
 *  Revision Number:     $Rev:: 10957                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.assessment.LiteracyAssessmentReport;
import ca.bc.gov.educ.isd.assessment.LiteracyAssessmentResult;

import static ca.bc.gov.educ.isd.reports.data.adapter.BusinessEntityAdapter.adapt;

/**
 * Represents a student's scholarship report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class LiteracyAssessmentReportImpl extends StudentReportImpl implements LiteracyAssessmentReport {

    private static final long serialVersionUID = 1L;

    public static final String ENGLISH = "GLA";
    public static final String FRENCH = "GLA_french";

    /**
     * Constructs a new report using the default report template.
     */
    public LiteracyAssessmentReportImpl() {
        super("GLA");
    }

    @Override
    public void setLiteracyAssessmentResult(final LiteracyAssessmentResult literacyAssessmentResult) {
        ensureValidStudent("setLiteracyAssessmentResult");

        adapt(literacyAssessmentResult, getStudent());
    }
}

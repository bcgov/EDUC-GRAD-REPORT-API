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
 *  File:                $Id:: NumeracyAssessmentReportImpl.java 10957 2018-07#$
 *  Date of Last Commit: $Date:: 2018-07-24 10:18:30 -0700 (Tue, 24 Jul 2018)  $
 *  Revision Number:     $Rev:: 10957                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.assessment.NumeracyAssessmentReport;
import ca.bc.gov.educ.isd.assessment.NumeracyAssessmentResult;

import static ca.bc.gov.educ.isd.reports.data.adapter.BusinessEntityAdapter.adapt;

/**
 * Represents a student's scholarship report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class NumeracyAssessmentReportImpl extends StudentReportImpl implements NumeracyAssessmentReport {

    private static final long serialVersionUID = 1L;

    public static final String REPORT_NAME = "GNA";

    /**
     * Constructs a new report using the default report template.
     */
    public NumeracyAssessmentReportImpl() {
        super(REPORT_NAME);
    }

    @Override
    public void setNumeracyAssessmentResult(final NumeracyAssessmentResult numeracyAssessmentResult) {
        ensureValidStudent("setNumeracyAssessmentResult");

        adapt(numeracyAssessmentResult, getStudent());
    }
}

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
 *  File:                $Id:: NumeracyAssessmentReport.java 10954 2018-07-24 #$
 *  Date of Last Commit: $Date:: 2018-07-23 17:11:11 -0700 (Mon, 23 Jul 2018)  $
 *  Revision Number:     $Rev:: 10954                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.model.assessment;

import ca.bc.gov.educ.grad.dto.reports.StudentReport;

/**
 * Represents information required to generate a student scholarship report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NumeracyAssessmentReport extends StudentReport {

    /**
     * Sets the assessment results associated with a student.
     *
     * @param nar The student's results for the numeracy assessment report.
     */
    void setNumeracyAssessmentResult(final NumeracyAssessmentResult nar);
}

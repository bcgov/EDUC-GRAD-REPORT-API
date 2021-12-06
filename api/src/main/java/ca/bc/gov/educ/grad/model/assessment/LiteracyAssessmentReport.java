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
 *  File:                $Id::                                          $
 *  Date of Last Commit: $Date::                                        $
 *  Revision Number:     $Rev::                                         $
 *  Last Commit by:      $Author::                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.model.assessment;

import ca.bc.gov.educ.grad.model.reports.StudentReport;

/**
 * Represents information required to generate a student scholarship report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface LiteracyAssessmentReport extends StudentReport {

    /**
     * Sets the assessment results associated with a student.
     *
     * @param nar The student's results for the literacy assessment report.
     */
    void setLiteracyAssessmentResult(final LiteracyAssessmentResult nar);
}

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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.model.assessment;

import ca.bc.gov.educ.grad.model.common.DomainEntity;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AssessmentScore extends DomainEntity {

    /**
     * Returns the look-up code for textual descriptions pertaining to the
     * student's assessment score category.
     *
     * @return A non-null instance (PLAN_AND_DESIGN by default).
     */
    AssessmentCode getAssessmentCode();

    /**
     * Returns student's actual score.
     *
     * @return -1 means "no response" (as "NR" on the report), 0 should never be
     * returned; otherwise, a small non-null value.
     */
    Integer getStudentScore();

    /**
     * Returns the maximum possible score for the assessment category defined by
     * the assessment code.
     *
     * @return Never null, possibly zero if not initialized.
     */
    Integer getMaximumScore();
}

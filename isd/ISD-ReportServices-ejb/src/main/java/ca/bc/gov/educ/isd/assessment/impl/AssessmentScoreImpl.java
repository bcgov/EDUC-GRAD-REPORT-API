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
package ca.bc.gov.educ.isd.assessment.impl;

import ca.bc.gov.educ.isd.assessment.AssessmentCode;
import ca.bc.gov.educ.isd.assessment.AssessmentScore;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;

import static ca.bc.gov.educ.isd.assessment.AssessmentCode.NO_RESPONSE;

/**
 * Contains the assessment results for tasks a student completes as part of the
 * numeracy (or literacy?) assessment.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AssessmentScoreImpl extends AbstractDomainEntity implements AssessmentScore {

    /**
     * Look-up code for textual descriptions pertaining to the student's
     * assessment score category.
     */
    private AssessmentCode assessmentCode = NO_RESPONSE;

    /**
     * The student's actual score, which must be less than or equal to the
     * maximum score. -1 means no response.
     */
    private Integer studentScore = -1;

    /**
     * Maximum possible score for the assessment category defined by the
     * assessment code.
     */
    private Integer maximumScore = -1;

    protected AssessmentScoreImpl() {

    }

    /**
     * Returns the look-up code for textual descriptions pertaining to the
     * student's assessment score category.
     *
     * @return A non-null instance (PLAN_AND_DESIGN by default).
     */
    @Override
    public AssessmentCode getAssessmentCode() {
        return this.assessmentCode;
    }

    /**
     * Changes the look-up code for textual descriptions pertaining to the
     * student's assessment score category.
     *
     * @param assessmentCode A non-null value.
     */
    public void setAssessmentCode(final AssessmentCode assessmentCode) {
        this.assessmentCode = assessmentCode;
    }

    /**
     * Returns student's actual score.
     *
     * @return -1 means "no response" (as "NR" on the report), 0 should never be
     * returned; otherwise, a small non-null value.
     */
    @Override
    public Integer getStudentScore() {
        return this.studentScore;
    }

    /**
     * Sets the student's actual score.
     *
     * @param studentScore -1, or a positive integer, null values will display blank on report.
     */
    public void setStudentScore(final Integer studentScore) {
        this.studentScore = studentScore;
    }

    /**
     * Returns the maximum possible score for the assessment category defined by
     * the assessment code.
     *
     * @return Never null, possibly zero if not initialized.
     */
    @Override
    public Integer getMaximumScore() {
        return this.maximumScore;
    }

    /**
     * Sets the maximum possible score for the assessment category defined by
     * the assessment code.
     *
     * @param maximumScore Always a positive integer, null values will display blank on report.
     */
    public void setMaximumScore(final Integer maximumScore) {
        this.maximumScore = maximumScore;
    }

    @Override
    public Long getId() { 
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

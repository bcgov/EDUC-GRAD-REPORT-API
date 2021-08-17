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

import ca.bc.gov.educ.isd.assessment.AssessmentScore;
import ca.bc.gov.educ.isd.assessment.RawScore;
import ca.bc.gov.educ.isd.assessment.RawScoreCategory;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the raw score results for a student's proficiency assessment.
 * Typically these are "online" (selected) and "written response" (extended).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class RawScoreImpl extends AbstractDomainEntity implements RawScore {

    /**
     * Usually "online" or "written response."
     */
    private RawScoreCategory rawScoreCategory;

    /**
     * A student's assessment score results.
     */
    private List<AssessmentScore> assessmentScores;

    /**
     * Corresponds to "Your Score" on the GNA report (meaning the student's
     * score).
     */
    private Integer totalStudentScore;

    /**
     * Corresponds to "Out Of" on the GNA report (meaning the highest score
     * possible for the total raw scores).
     */
    private Integer maximumScore;

    protected RawScoreImpl() {
    }

    @Override
    public RawScoreCategory getRawScoreCategory() {
        return this.rawScoreCategory;
    }

    public void setRawScoreCategory(final RawScoreCategory rawScoreCategory) {
        this.rawScoreCategory = rawScoreCategory;
    }

    /**
     * Lazily initialized.
     *
     * @return A non-null, possibly empty list.
     */
    @Override
    public synchronized List<AssessmentScore> getAssessmentScores() {
        return this.assessmentScores == null
                ? new ArrayList<AssessmentScore>()
                : this.assessmentScores;
    }

    public void setAssessmentScores(final List<AssessmentScore> assessmentScores) {
        this.assessmentScores = assessmentScores;
    }

    @Override
    public Integer getTotalStudentScore() {
        return this.totalStudentScore;
    }

    public void setTotalStudentScore(final Integer totalStudentScore) {
        this.totalStudentScore = totalStudentScore;
    }

    @Override
    public Integer getTotalScore() {
        return this.maximumScore;
    }

    public void setTotalScore(final Integer totalScore) {
        this.maximumScore = totalScore;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

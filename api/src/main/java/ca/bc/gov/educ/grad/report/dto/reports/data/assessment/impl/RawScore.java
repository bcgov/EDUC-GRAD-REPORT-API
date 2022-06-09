/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.dto.reports.data.assessment.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;
import ca.bc.gov.educ.grad.report.model.assessment.RawScoreCategory;

import java.util.List;

/**
 * Represents raw student scores as part of a GNA report. These are typically
 * "online" and "written" collections of assessment scores.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class RawScore extends BusinessEntity {

    /**
     * A student's assessment score results.
     */
    private List<AssessmentScore> assessmentScores;

    /**
     * Usually "online" or "written response."
     */
    private RawScoreCategory rawScoreCategory;

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

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<RawScore, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New instance.
         */
        @Override
        protected RawScore createObject() {
            return new RawScore();
        }

        /**
         * Sets the assessment scores.
         *
         * @param assessmentScores Non-null instances.
         * @return thisBuilder
         */
        public RawScore.Builder withAssessmentScores(
                final List<AssessmentScore> assessmentScores) {
            getObject().setAssessmentScores(assessmentScores);
            return thisBuilder();
        }

        /**
         * Sets the raw score category (e.g., online or written response).
         *
         * @param rawScoreCategory Non-null instance.
         * @return thisBuilder
         */
        public RawScore.Builder withRawScoreCategory(
                final RawScoreCategory rawScoreCategory) {
            getObject().setRawScoreCategory(rawScoreCategory);
            return thisBuilder();
        }

        /**
         * Sets the total student score for the raw scores category.
         *
         * @param totalStudentScore Non-null instance.
         * @return thisBuilder
         */
        public RawScore.Builder withTotalStudentScore(
                final Integer totalStudentScore) {
            getObject().setTotalStudentScore(totalStudentScore);
            return thisBuilder();
        }

        /**
         * Sets the total student score for the raw scores category.
         *
         * @param totalScore Non-null instance.
         * @return thisBuilder
         */
        public RawScore.Builder withTotalScore(
                final Integer totalScore) {
            getObject().setTotalScore(totalScore);
            return thisBuilder();
        }
    }

    public List<AssessmentScore> getAssessmentScores() {
        return this.assessmentScores;
    }

    public void setAssessmentScores(List<AssessmentScore> assessmentScores) {
        this.assessmentScores = assessmentScores;
    }

    public RawScoreCategory getRawScoreCategory() {
        return this.rawScoreCategory;
    }

    public void setRawScoreCategory(final RawScoreCategory rawScoreCategory) {
        this.rawScoreCategory = rawScoreCategory;
    }

    public Integer getTotalStudentScore() {
        return this.totalStudentScore;
    }

    public void setTotalStudentScore(final Integer totalStudentScore) {
        this.totalStudentScore = totalStudentScore;
    }

    public Integer getTotalScore() {
        return this.maximumScore;
    }

    public void setTotalScore(final Integer totalScore) {
        this.maximumScore = totalScore;
    }
}

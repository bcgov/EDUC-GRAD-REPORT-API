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
package ca.bc.gov.educ.grad.dto.reports.data.assessment.impl;

import ca.bc.gov.educ.grad.dto.reports.data.BusinessEntity;

import java.util.List;
import java.util.logging.Logger;

/**
 * Represents literacy assessment data that constitutes a GLA result, including
 * overall proficiency and task scores.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class LiteracyAssessmentResult extends BusinessEntity {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = LiteracyAssessmentResult.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Assessment proficiency score (the literacy assessment result).
     */
    private Integer proficiencyScore;

    /**
     * School year assessments took place.
     */
    private String sessionDate;

    /**
     * Student scores for a particular assessment category.
     */
    private List<RawScore> rawScores;

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<LiteracyAssessmentResult, Builder> {

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
         * @return New LiteracyAssessmentResult instance.
         */
        @Override
        protected LiteracyAssessmentResult createObject() {
            return new LiteracyAssessmentResult();
        }

        /**
         * Sets the proficiency score.
         *
         * @param proficiencyScore 1, 2, etc.
         * @return thisBuilder
         */
        public LiteracyAssessmentResult.Builder withProficiencyScore(
                final Integer proficiencyScore) {
            getObject().setProficiencyScore(proficiencyScore);
            return thisBuilder();
        }

        /**
         * Sets the session date.
         *
         * @param sessionDate TRAX-formatted (yyyyMM) date.
         * @return thisBuilder
         */
        public LiteracyAssessmentResult.Builder withSessionDate(
                final String sessionDate) {
            getObject().setSessionDate(sessionDate);
            return thisBuilder();
        }

        /**
         * Sets the raw scores for the student.
         *
         * @param rawScores Non-null, non-empty list.
         * @return thisBuilder
         */
        public LiteracyAssessmentResult.Builder withRawScores(
                final List<RawScore> rawScores) {
            getObject().setRawScores(rawScores);
            return thisBuilder();
        }
    }

    /**
     * @return
     * @inheritDoc
     */
    public Integer getProficiencyScore() {
        return nullSafe(this.proficiencyScore, 1);
    }

    public void setProficiencyScore(final Integer proficiencyScore) {
        this.proficiencyScore = proficiencyScore;
    }

    /**
     * @return
     * @inheritDoc
     */
    public String getSessionDate() {
        return this.sessionDate;
    }

    public void setSessionDate(final String sessionDate) {
        this.sessionDate = formatSessionDate(sessionDate);
    }

    /**
     * @return
     * @inheritDoc
     */
    public List<RawScore> getRawScores() {
        return this.rawScores;
    }

    public void setRawScores(final List<RawScore> rawScores) {
        this.rawScores = rawScores;
    }
}

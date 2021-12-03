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
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.assessment.LiteracyAssessmentResult;
import ca.bc.gov.educ.grad.model.assessment.RawScore;
import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;

import java.util.List;

/**
 * High-level container class for literacy assessment result report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class LiteracyAssessmentResultImpl extends AbstractDomainEntity
        implements LiteracyAssessmentResult {

    /**
     * Assessment proficiency score (the literacy assessment result).
     */
    private final Integer proficiencyScore;

    /**
     * School year assessments took place.
     */
    private final String sessionDate;

    /**
     * Student scores for a particular assessment category.
     */
    private final List<RawScore> rawScores;

    public LiteracyAssessmentResultImpl(
            final Integer proficiencyScore,
            final String sessionDate,
            final List<RawScore> rawScores) {
        this.proficiencyScore = proficiencyScore;
        this.sessionDate = sessionDate;
        this.rawScores = rawScores;
    }

    @Override
    public Integer getProficiencyScore() {
        return this.proficiencyScore;
    }

    @Override
    public String getSessionDate() {
        return this.sessionDate;
    }

    @Override
    public List<RawScore> getRawScores() {
        return this.rawScores;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

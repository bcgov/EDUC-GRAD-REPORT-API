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

import ca.bc.gov.educ.isd.assessment.NumeracyAssessmentResult;
import ca.bc.gov.educ.isd.assessment.RawScore;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;

import java.util.List;

/**
 * High-level container class for numeracy assessment result report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class NumeracyAssessmentResultImpl extends AbstractDomainEntity
        implements NumeracyAssessmentResult {

    /**
     * Assessment proficiency score (the numeracy assessment result).
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

    public NumeracyAssessmentResultImpl(
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

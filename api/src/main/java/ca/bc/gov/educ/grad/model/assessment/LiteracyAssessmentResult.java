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

import java.util.List;

/**
 * Represents literacy assessment data that constitutes a GNA result, including
 * overall proficiency and task scores.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface LiteracyAssessmentResult extends DomainEntity {

    /**
     * Returns the assessment proficiency score (the literacy assessment
     * result).
     *
     * @return A non-null value that corresponds to a student proficiency level.
     */
    Integer getProficiencyScore();

    /**
     * Returns the school year the student assessments took place.
     *
     * @return A non-null date in yyyyMM (TRAX_DATE_YM) format.
     */
    String getSessionDate();

    /**
     * Returns the student scores for a particular assessment category.
     *
     * @return A non-null, possibly empty list.
     */
    List<RawScore> getRawScores();
}

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
 * Represents raw student scores as part of a GNA report. These are typically
 * "online" and "written" collections of assessment scores.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface RawScore extends DomainEntity {

    /**
     * A student's assessment score results.
     *
     * @return A non-null, possibly empty list.
     */
    List<AssessmentScore> getAssessmentScores();

    /**
     * Usually "online" or "written response."
     *
     * @return A non-null instance.
     */
    RawScoreCategory getRawScoreCategory();

    /**
     * Corresponds to "Your Score" on the GNA report (meaning the student's
     * score).
     *
     * @return A non-null value or -1 to indicate no result.
     */
    Integer getTotalStudentScore();

    /**
     * Corresponds to "Out Of" on the GNA report (meaning the highest score
     * possible for the total raw scores).
     *
     * @return A non-null value greater than 1, usually in the double digits.
     */
    Integer getTotalScore();
}

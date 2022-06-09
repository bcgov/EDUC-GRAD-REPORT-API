/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: TranscriptResult.java 9387 2018-02-07 23:24:03Z#$
 *  Date of Last Commit: $Date:: 2018-02-07 15:24:03 -0800 (Wed, 07 Feb 2018)  $
 *  Revision Number:     $Rev:: 9387                                           $
 *  Last Commit by:      $Author:: SSURATI                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;

/**
 * Represents additional information about a course grade that constitutes a
 * transcript result.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TranscriptResult extends DomainEntity {

    /**
     * Returns a course the student attended.
     *
     * @return A course associated with a mark
     */
    Course getCourse();

    /**
     * Returns the mark the student received for the course.
     *
     * @return A non-null Mark instance.
     */
    Mark getMark();

    /**
     * Indicates whether the course met the graduation requirements.
     *
     * @return Graduation requirement, if any, that the course met.
     */
    String getRequirementMet();

    /**
     * If course met the graduation requirements then return course name.
     *
     * @return Graduation requirement, if any, that the course met.
     */
    String getRequirementMetName();

    /**
     * Returns whether course credit was granted through the Equivalency ("E")
     * or Challenge ("C") process.
     *
     * @return Either "C" or "E", or empty string.
     */
    String getEquivalencyChallenge();

    /**
     * TODO: Change to Boolean.
     *
     * Indicates whether the course is used for graduation.
     *
     * @return Graduation requirement, if any, that the course met.
     */
    String getUsedForGrad();

}

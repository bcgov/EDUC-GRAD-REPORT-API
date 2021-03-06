/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.model.achievement;

/**
 * Defines the public methods to access the TranscriptCourse object which
 * contains data pertaining to a course which a student has taken.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AchievementCourse {

    /**
     * get the PEN which the data corresponds to.
     *
     * @return PEN pen
     */
    String getPen();

    /**
     * get the course name.
     *
     * @return course name
     */
    String getCourseName();

    /**
     * get the course code.
     *
     * @return course code
     */
    String getCourseCode();

    /**
     * get the course grade level.
     *
     * @return course level
     */
    String getCourseLevel();

    /**
     * get the graduation requirements the course has met.
     *
     * @return requirement requirement
     */
    String getRequirement();

    /**
     * indicates if the course was granted through the equivalency or challenge
     * process. Possible values are E or C.
     *
     * @return equivalency equivalency
     */
    String getEquivalency();

    /**
     * get the date the exam was written for an examinable course, or the date
     * the course was completed if it was non-examinable. Format is YYYYMM.
     *
     * @return session date, possibly blank (indicates no session date).
     */
    String getSessionDate();

    /**
     * get the student's final school percent.
     *
     * @return school percent
     */
    String getSchoolPercent();

    /**
     * get the student's final exam percent.
     *
     * @return exam percent
     */
    String getExamPercent();

    /**
     * get the students final percent in the course.
     *
     * @return final percent
     */
    String getFinalPercent();

    /**
     * get the final letter grade in the course.
     *
     * @return letter grade
     */
    String getFinalLetterGrade();

    /**
     * get the interim mark.
     *
     * @return interim mark
     */
    String getInterimMark();

    /**
     * get the interim letter grade.
     *
     * @return interim letter grade
     */
    String getInterimLetterGrade();

    /**
     * get the number of credits the student earned for the course.
     *
     * @return credits credits
     */
    String getCredits();

    /**
     * Get the course type used for reporting.
     * <p>
     * 1 means examinable course; and 2 means non-examinable course.
     *
     * @return course type
     */
    String getCourseType();

    /**
     * get the related course code.
     *
     * @return related course
     */
    String getRelatedCourse();

    /**
     * get the related course level.
     *
     * @return related level
     */
    String getRelatedLevel();

    /**
     * Gets grad req met.
     *
     * @return the grad req met
     */
    String getGradReqMet();

    /**
     * Gets completed course percentage.
     *
     * @return the completed course percentage
     */
    String getCompletedCoursePercentage();

    /**
     * Gets completed course letter grade.
     *
     * @return the completed course letter grade
     */
    String getCompletedCourseLetterGrade();

    /**
     * Gets interim percent.
     *
     * @return the interim percent
     */
    String getInterimPercent();

    /**
     * Gets equiv or challenge.
     *
     * @return the equiv or challenge
     */
    String getEquivOrChallenge();

    /**
     * Gets projected.
     *
     * @return the projected
     */
    Boolean getProjected();

}

/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.eis.trax.db;

/**
 * Defines the public methods for data access to an ExamResult object which
 * contains student exam data for a course they taken.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ExamResult extends TRAXData {

    /**
     * get the PEN which the data corresponds to
     *
     * @return PEN
     */
    String getPen();

    /**
     * get the course code for the exam
     *
     * @return course code
     */
    String getCourseCode();

    /**
     * get the course level for the exam
     *
     * @return course level
     */
    String getCourseLevel();

    /**
     * get the course name for the exam
     *
     * @return course name
     */
    String getCourseName();

    /**
     * get the session in which the course was taken
     *
     * @return course session
     */
    String getCourseSession();

    /**
     * get the school percent
     *
     * @return school percent
     */
    String getSchoolPercent();

    /**
     * get the best school percent
     *
     * @return best school percent
     */
    String getBestSchoolPercent();

    /**
     * get the student's exam percent
     *
     * @return exam percent
     */
    String getExamPercent();

    /**
     * get the best student's exam percent
     *
     * @return best exam percent
     */
    String getBestExamPercent();

    /**
     * get the student's final percent grade for the course
     *
     * @return final percent
     */
    String getFinalPercent();

    /**
     * get the student's final letter grade for the course
     *
     * @return final letter grade
     */
    String getFinalLetterGrade();

}

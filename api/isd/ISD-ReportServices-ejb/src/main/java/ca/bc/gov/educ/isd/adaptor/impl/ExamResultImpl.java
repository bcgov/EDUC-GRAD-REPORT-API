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
package ca.bc.gov.educ.isd.adaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.ExamResult;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.trimSafe;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is for an examinable course the student has taken.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ExamResultImpl implements ExamResult, Serializable {

    private static final long serialVersionUID = 3L;

    private static final String CLASSNAME = ExamResultImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String pen;
    private String courseCode = "";
    private String courseLevel = "";
    private String courseName = "";
    private String courseSession = "";
    private String schoolPercent = "";
    private String bestSchoolPercent = "";
    private String examPercent = "";
    private String bestExamPercent = "";
    private String finalPercent = "";
    private String finalLetterGrade = "";

    /**
     */
    public ExamResultImpl() {
    }

    /**
     * Constructor method. Used by the JPQL to create an object from the
     * database entities.
     *
     * @param pen
     * @param courseCode
     * @param courseLevel
     * @param courseName
     * @param courseSession
     * @param schoolPercent
     * @param bestSchoolPercent
     * @param examPercent
     * @param bestExamPercent
     * @param finalPercent
     * @param finalLetterGrade
     */
    public ExamResultImpl(
            final String pen,
            final String courseCode,
            final String courseLevel,
            final String courseName,
            final String courseSession,
            final String schoolPercent,
            final String bestSchoolPercent,
            final String examPercent,
            final String bestExamPercent,
            final String finalPercent,
            final String finalLetterGrade) {
        this.pen = pen;
        this.courseCode = trimSafe(courseCode);
        this.courseLevel = trimSafe(courseLevel);
        this.courseName = trimSafe(courseName);
        this.courseSession = trimSafe(courseSession);
        this.schoolPercent = trimSafe(schoolPercent);
        this.bestSchoolPercent = trimSafe(bestSchoolPercent);
        this.examPercent = trimSafe(examPercent);
        this.bestExamPercent = trimSafe(bestExamPercent);
        this.finalPercent = trimSafe(finalPercent);
        this.finalLetterGrade = trimSafe(finalLetterGrade);
    }

    /**
     * get the PEN which the data corresponds to.
     *
     * @return PEN
     */
    @Override
    public String getPen() {
        return this.pen;
    }

    /**
     * get the course code for the exam.
     *
     * @return course code
     */
    @Override
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * get the course level for the exam.
     *
     * @return course level
     */
    @Override
    public String getCourseLevel() {
        return this.courseLevel;
    }

    /**
     * get the course name for the exam.
     *
     * @return course name
     */
    @Override
    public String getCourseName() {
        return this.courseName;
    }

    /**
     * get the session in which the course was taken. Format YYYYMM.
     *
     * @return course session
     */
    @Override
    public String getCourseSession() {
        return this.courseSession;
    }

    /**
     * get the school percent.
     *
     * @return school percent
     */
    @Override
    public String getSchoolPercent() {
        return this.schoolPercent;
    }

    /**
     * get the best school percent.
     *
     * @return best school percent
     */
    @Override
    public String getBestSchoolPercent() {
        return this.bestSchoolPercent;
    }

    /**
     * get the student's exam percent.
     *
     * @return exam percent
     */
    @Override
    public String getExamPercent() {
        return this.examPercent;
    }

    /**
     * get the best student's exam percent.
     *
     * @return best exam percent
     */
    @Override
    public String getBestExamPercent() {
        return this.bestExamPercent;
    }

    /**
     * get the student's final percent grade for the course.
     *
     * @return final percent
     */
    @Override
    public String getFinalPercent() {
        return this.finalPercent;
    }

    /**
     * get the student's final letter grade for the course.
     *
     * @return final letter grade
     */
    @Override
    public String getFinalLetterGrade() {
        return this.finalLetterGrade;
    }

    /**
     * Generate a unique hashcode value for this object based on an algorithm
     * using PEN, courseCode, courseLEvel, and courseSession.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.pen);
        hash = 97 * hash + Objects.hashCode(this.courseCode);
        hash = 97 * hash + Objects.hashCode(this.courseLevel);
        hash = 97 * hash + Objects.hashCode(this.courseSession);
        return hash;
    }

    /**
     * Determine if two ExamResultImpl objects are equal by comparing the
     * values: PEN, courseCode, courseLevel and courseSession.
     *
     * @param obj
     * @return True if the specified values are all equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExamResultImpl other = (ExamResultImpl) obj;
        if ((this.pen == null) ? (other.pen != null) : !this.pen.equals(other.pen)) {
            return false;
        }
        if ((this.courseCode == null) ? (other.courseCode != null) : !this.courseCode.equals(other.courseCode)) {
            return false;
        }
        if ((this.courseLevel == null) ? (other.courseLevel != null) : !this.courseLevel.equals(other.courseLevel)) {
            return false;
        }
        return !((this.courseSession == null) ? (other.courseSession != null) : !this.courseSession.equals(other.courseSession));
    }

}

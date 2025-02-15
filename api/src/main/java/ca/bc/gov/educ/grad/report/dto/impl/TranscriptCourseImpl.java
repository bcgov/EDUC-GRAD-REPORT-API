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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.transcript.TranscriptCourse;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is for a course the student has taken
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptCourseImpl implements TranscriptCourse {

    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TranscriptCourseImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String pen;
    private String courseName = "";
    private String courseCode = "";
    private String courseLevel = "";
    private String requirement = "";
    private String equivalency = "";
    private String sessionDate = "";
    private String schoolPercent = "";
    private String examPercent = "";
    private String finalPercent = "";
    private String credits = "";
    private String finalLetterGrade = "";
    private String courseType = "";
    private String interimMark = "";
    private String interimLetterGrade = "";
    private String relatedCourse = "";
    private String relatedLevel = "";
    private String usedForGrad = "";

    /**
     * Constructor method.
     *
     */
    public TranscriptCourseImpl() {
    }

    /**
     * Constructor method. Used by the JPQL to create an object from the
     * database entities.
     *  @param pen
     * @param courseName
     * @param crseCode
     * @param crseLevel
     * @param sessionDate
     * @param credits
     * @param examPercent
     * @param schoolPercent
     * @param finalPercent
     * @param finalLetterGrade
     * @param interimMark
     * @param requirement
     * @param courseType
     */
    public TranscriptCourseImpl(
            final String pen,
            final String courseName,
            final String crseCode,
            final String crseLevel,
            final String sessionDate,
            final String credits,
            final String examPercent,
            final String schoolPercent,
            final String finalPercent,
            final String finalLetterGrade,
            final String interimMark,
            final String interimLetterGrade,
            final String requirement,
            final String equivalency,
            final String courseType) {
        this.pen = pen;
        this.courseName = nullSafe(courseName);
        this.courseCode = nullSafe(crseCode);
        this.courseLevel = nullSafe(crseLevel);
        this.sessionDate = nullSafe(sessionDate);
        this.credits = nullSafe(credits);
        this.examPercent = nullSafe(examPercent);
        this.schoolPercent = nullSafe(schoolPercent);
        this.finalLetterGrade = nullSafe(finalLetterGrade);
        this.interimMark = nullSafe(interimMark);
        this.interimLetterGrade = nullSafe(interimLetterGrade);
        this.requirement = nullSafe(requirement);
        this.equivalency = nullSafe(equivalency);
        this.courseType = nullSafe(courseType);
        this.finalPercent = nullSafe(finalPercent).trim();
    }

    @Override
    public String getPen() {
        return pen;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String getRequirement() {
        return requirement;
    }

    @Override
    public String getEquivalency() {
        return equivalency;
    }

    @Override
    public String getSessionDate() {
        return sessionDate;
    }

    @Override
    public String getSchoolPercent() {
        return schoolPercent;
    }

    @Override
    public String getExamPercent() {
        return examPercent;
    }

    @Override
    public String getFinalPercent() {
        return finalPercent;
    }

    @Override
    public String getCredits() {
        return credits;
    }

    @Override
    public String getFinalLetterGrade() {
        return finalLetterGrade;
    }

    @Override
    public String getCourseType() {
        return courseType;
    }

    @Override
    public Boolean isExaminable() {
        final String ct = getCourseType();
        final Boolean result = "1".equals(ct);

        return result;
    }

    @Override
    public String getCourseLevel() {
        return courseLevel;
    }

    @Override
    public String getInterimMark() {
        return interimMark;
    }

    @Override
    public String getInterimLetterGrade() {
        return interimLetterGrade;
    }

    @Override
    public String getRelatedCourse() {
        return relatedCourse;
    }

    /**
     * set the related course value.
     * <p>
     * @param relatedCourse
     */
    public void setRelatedCourse(String relatedCourse) {
        this.relatedCourse = relatedCourse;
    }

    @Override
    public String getRelatedLevel() {
        return relatedLevel;
    }

    /**
     * set the related course level.
     * <p>
     * @param relatedLevel
     */
    public void setRelatedLevel(String relatedLevel) {
        this.relatedLevel = relatedLevel;
    }

    @Override
    public String getUsedForGrad() {
        return usedForGrad;
    }

    @Override
    public boolean courseEquals(final TranscriptCourse compareCourse) {

        boolean isEqual = ((!this.equals(compareCourse))
                && this.getCourseCode().equals(compareCourse.getCourseCode())
                && this.getCourseLevel().equals(compareCourse.getCourseLevel()));
        return isEqual;
    }

    @Override
    public boolean compareCourse(final TranscriptCourse compareCourse) {

        // Interim % should only be looked at if the courses do not have a final LG
        final int percentage = StringUtils.isBlank(this.getFinalLetterGrade())? getInt(this.getInterimMark()) : getInt(this.getFinalPercent());
        final int comparePercentage = StringUtils.isBlank(compareCourse.getFinalLetterGrade())? getInt(compareCourse.getInterimMark()) : getInt(compareCourse.getFinalPercent());

        // Removes duplication of courses by comparing and finding course with
        // highest percentage.
        // GRAD2-2905
        if (this.isCompletedCourseUsedForGrad() && !compareCourse.isCompletedCourseUsedForGrad()) {
            return percentage < comparePercentage && comparePercentage != 0;
        } else {
            return percentage <= comparePercentage && comparePercentage != 0;
        }
    }

    public boolean isCompletedCourseUsedForGrad() {
        return StringUtils.isNotBlank(this.finalLetterGrade) && StringUtils.isNotBlank(this.requirement);
    }

    /**
     * Returns the integer value of the given string.
     *
     * @param s The string that contains an integer.
     * @return The integer value from the string, or 0 if no value found.
     */
    private int getInt(final String s) {
        int value = 0;
        if (s.matches("^-?\\d+$")) {
            value = parseInt(s);
        }
        return value;
    }

    /**
     * Set the code value which indicates if this course is used for graduation
     * requirements.
     *
     * @param usedForGrad
     */
    public void setUsedForGrad(final String usedForGrad) {
        this.usedForGrad = usedForGrad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.pen != null ? this.pen.hashCode() : 0);
        hash = 59 * hash + (this.courseCode != null ? this.courseCode.hashCode() : 0);
        hash = 59 * hash + (this.sessionDate != null ? this.sessionDate.hashCode() : 0);
        return hash;
    }

    /**
     * Returns a trimmed version of the given string.
     *
     * @param s The string to trim.
     * @return The empty string if s is null, otherwise s.trim().
     */
    private String nullSafe(final String s) {
        return s == null ? "" : s.trim();
    }

    /**
     * Returns c or an empty space if c is null.
     *
     * @param c The character to ensure is not null.
     * @return A space or the given character, never null.
     */
    private String nullSafe(final Character c) {
        return c == null ? " " : c.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TranscriptCourseImpl other = (TranscriptCourseImpl) obj;
        if (!Objects.equals(this.pen, other.pen)) {
            return false;
        }
        if (!Objects.equals(this.courseName, other.courseName)) {
            return false;
        }
        if (!Objects.equals(this.courseCode, other.courseCode)) {
            return false;
        }
        if (!Objects.equals(this.courseLevel, other.courseLevel)) {
            return false;
        }
        if (!Objects.equals(this.requirement, other.requirement)) {
            return false;
        }
        if (!Objects.equals(this.equivalency, other.equivalency)) {
            return false;
        }
        if (!Objects.equals(this.sessionDate, other.sessionDate)) {
            return false;
        }
        if (!Objects.equals(this.schoolPercent, other.schoolPercent)) {
            return false;
        }
        if (!Objects.equals(this.examPercent, other.examPercent)) {
            return false;
        }
        if (!Objects.equals(this.finalPercent, other.finalPercent)) {
            return false;
        }
        if (!Objects.equals(this.credits, other.credits)) {
            return false;
        }
        if (!Objects.equals(this.finalLetterGrade, other.finalLetterGrade)) {
            return false;
        }
        if (!Objects.equals(this.courseType, other.courseType)) {
            return false;
        }
        if (!Objects.equals(this.interimMark, other.interimMark)) {
            return false;
        }
        if (!Objects.equals(this.interimLetterGrade, other.interimLetterGrade)) {
            return false;
        }
        if (!Objects.equals(this.relatedCourse, other.relatedCourse)) {
            return false;
        }
        if (!Objects.equals(this.relatedLevel, other.relatedLevel)) {
            return false;
        }
        return Objects.equals(this.usedForGrad, other.usedForGrad);
    }

}

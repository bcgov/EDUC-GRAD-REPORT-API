/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
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
package ca.bc.gov.educ.grad.dto.reports.data.impl;

import ca.bc.gov.educ.grad.dto.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.logging.Logger;

/**
 * Mark grades are used for both examination results and transcript results.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Mark extends BusinessEntity {

    private static final long serialVersionUID = 2L;

    private static final String CLASSNAME = Mark.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Name of the course that has an associated grade.
     *
     * For example, "LD Computer Network Management(APLUS)12B".
     */
    private String courseName;

    /**
     * Code for the given course courseName.
     *
     * For example, "UAWPM".
     */
    private String courseCode;

    /**
     * Level associated with the course.
     *
     * For example, "11A", "112", "12", etc.
     */
    private String courseLevel;

    /**
     * Two-digit level that associates same-level courses (e.g., "11A", "112",
     * and "11" should be grouped together).
     */
    private String courseLevelGroup;

    /**
     * Date the course was offered.
     */
    private String sessionDate;

    /**
     * Final marks (percentage and letter).
     */
    private Grade finalGrade;

    /**
     * Interim marks (percentage and letter).
     */
    private Grade interimGrade;

    /**
     * Best mark the student achieved across all attempts.
     */
    private String bestSchoolPercent;

    /**
     * Best examination mark the student achieved across all attempts.
     */
    private String bestExamPercent;

    /**
     * Computed from sessionDate.
     */
    private String formattedSessionDate;

    /**
     * Also known as examination title in ExaminationResult.
     *
     * @return A non-null string that contains the courseName of the course
     * taken.
     */
    public String getCourseName() {
        return nullSafe(this.courseName);
    }

    /**
     * The course courseCode associated with the course courseName for the
     * examination title.
     *
     * @return A non-null string that contains the course courseCode (5 digits
     * max).
     */
    public String getCourseCode() {
        return nullSafe(this.courseCode);
    }

    /**
     * Returns the courseCode that represents the course courseLevel. For
     * example, in "MATH 10", the "10" is the course.
     *
     * @return A non-null string that contains the course courseLevel (3 digits
     * max).
     */
    public String getCourseLevel() {
        return nullSafe(this.courseLevel);
    }

    /**
     * Returns the course level as a groupable term. The course level can be one
     * of: "", 1[0-3]+[A-F,2]
     *
     * @return A non-null value that contains the course courseLevelGroup.
     */
    public String getCourseLevelGroup() {
        return this.courseLevelGroup;
    }

    /**
     * When the course was offered.
     *
     * @return The date the course was offered (or taken?).
     */
    public String getSessionDate() {
        return nullSafe(this.sessionDate);
    }

    /**
     * Used by the exam and transcript reports to get a preformatted session
     * date.
     *
     * @see Constants.DATE_REPORT_SESSION
     * @return A non-null, possibly empty String.
     */
    public String getFormattedSessionDate() {
        return this.formattedSessionDate;
    }

    /**
     * Final grade as a percentage, but also includes status codes. Delegates to
     * the final grade instance.
     *
     * @return A number from 0 - 100 or a three-letter courseCode.
     */
    public String getFinalPercent() {
        return nullSafe(getFinalGrade().getPercent());
    }

    /**
     * Final grade percentage translated into a letter grade.
     *
     * @return A letter, typically between A through F with + and - symbols.
     */
    public String getFinalLetterGrade() {
        return nullSafe(getFinalGrade().getLetter());
    }

    /**
     * Returns the interim grade as a percentage, but also includes status
     * codes. Delegates to the interim grade instance.
     *
     * @return A number from 0 - 100 or a three-letter courseCode.
     */
    public String getInterimPercent() {
        return nullSafe(getInterimGrade().getPercent());
    }

    /**
     * Returns the interim grade percentage translated into a letter grade.
     * Delegates to the interim grade instance.
     *
     * @return A letter, typically between A through F with + and - symbols.
     */
    public String getInterimLetterGrade() {
        return nullSafe(getInterimGrade().getLetter());
    }

    /**
     * This is "Best School %" for ExaminationResult and this is "School %" for
     * TranscriptResult.
     *
     * @return A number from 0 - 100, or a three-letter courseCode.
     */
    public String getBestSchoolPercent() {
        return nullSafe(this.bestSchoolPercent);
    }

    /**
     * This is "Best Exam %" for ExaminationResult and this is "Exam %" for
     * TranscriptResult.
     *
     * @return A number from 0 - 100, or a three-letter courseCode.
     */
    public String getBestExamPercent() {
        return nullSafe(this.bestExamPercent);
    }

    /**
     * Subclasses override to return the appropriate percentage for the context.
     *
     * @return A number from 0 - 100, or a three-letter courseCode.
     */
    public abstract String getSchoolPercent();

    /**
     * Subclasses override to return the appropriate percentage for the context.
     *
     * @return A number from 0 - 100, or a three-letter courseCode.
     */
    public abstract String getExamPercent();

    /**
     * Returns the final grade for this course mark.
     *
     * @return The final grade, including percentage and letter.
     */
    private Grade getFinalGrade() {
        return this.finalGrade == null ? new Grade("", "") : this.finalGrade;
    }

    /**
     * Returns the interim grade for this course mark.
     *
     * @return The interim grade, including percentage and letter.
     */
    private Grade getInterimGrade() {
        return this.interimGrade == null ? new Grade("", "") : this.interimGrade;
    }

    /**
     * Used by the builder to set the course courseName.
     *
     * @param courseName Passed in by the builder.
     */
    protected void setCourseName(final String courseName) {
        this.courseName = courseName;
    }

    /**
     * Used by the builder to set the course courseCode.
     *
     * @param courseCode Passed in by the builder.
     */
    protected void setCourseCode(final String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Used by the builder to set the course courseLevel.
     *
     * @param courseLevel Passed in by the builder.
     */
    protected void setCourseLevel(final String courseLevel) {
        this.courseLevel = courseLevel;
    }

    /**
     * Sets the course level group so that the report can group by course level,
     * ungraded courses, and assessments.
     *
     * @param level The course level to make groupable.
     */
    protected void setCourseLevelGroup(String level) {
        if (level.isEmpty()) {
            level = "0";
        }

        // Shouldn't happen as all the course levels are two/three digits in
        // the database. But, since there are blanks, it could happen that a
        // single-digit course level appears. In that case, the substring will
        // throw an index out of bounds, which is undesirable.
        if (level.length() < 2) {
            level = "0" + level;
        }

        // Group by the grade levels.
        this.courseLevelGroup = level.substring(0, 2);
    }

    /**
     * Used by the builder to set the session date.
     *
     * @param sessionDate Passed in by the builder.
     */
    protected void setSessionDate(final String sessionDate) {
        this.sessionDate = sessionDate;

        setFormattedSessionDate(sessionDate);
    }

    /**
     * Sets the formatted session date; called by setSessionDate.
     *
     * @see setFormattedSessionDate()
     *
     * @param sessionDate An empty or formatted string, never null.
     */
    private void setFormattedSessionDate(final String sessionDate) {
        final String _m = "setFormattedSessionDate(String)";
        LOG.entering(CLASSNAME, _m);

        final String session = getSessionDate();
        this.formattedSessionDate = sessionDate;

        if (!session.isEmpty()) {
            this.formattedSessionDate = formatSessionDate(session);
        }

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * Used by the builder to set the final grade.
     *
     * @param finalGrade Passed in by the builder.
     */
    protected void setFinalGrade(final Grade finalGrade) {
        this.finalGrade = finalGrade;
    }

    /**
     * Used by the builder to set the interim grade.
     *
     * @param interimGrade Passed in by the builder.
     */
    protected void setInterimGrade(final Grade interimGrade) {
        this.interimGrade = interimGrade;
    }

    /**
     * Used by the builder to set the best exam percent.
     *
     * @param bestExamPercent Passed in by the builder.
     */
    protected void setBestExamPercent(final String bestExamPercent) {
        this.bestExamPercent = bestExamPercent;
    }

    /**
     * Used by the builder to set the best school percent.
     *
     * @param bestSchoolPercent Passed in by the builder.
     */
    protected void setBestSchoolPercent(final String bestSchoolPercent) {
        this.bestSchoolPercent = bestSchoolPercent;
    }

    /**
     * Used to create instances of the outer class.
     *
     * @param <T> The class type that the builder creates.
     * @param <B> The builder subclass for creating instances of T.
     */
    protected static abstract class Builder<T extends Mark, B extends Builder<T, B>> extends
            BusinessEntity.Builder<T, B> {

        /**
         * Sets the courseName for a course a student took.
         *
         * @param courseName The courseName of the course for a particular
         * grade.
         * @return thisBuilder
         */
        public B withCourseName(final String courseName) {
            getObject().setCourseName(courseName);
            return thisBuilder();
        }

        /**
         * Sets the courseCode for a course a student took.
         *
         * @param courseCode The courseCode of the course for a particular
         * grade.
         * @return thisBuilder
         */
        public B withCourseCode(final String courseCode) {
            getObject().setCourseCode(courseCode);
            return thisBuilder();
        }

        /**
         * Sets the courseLevel for a course a student took.
         *
         * @param courseLevel The courseLevel of the course for a particular
         * grade.
         * @return thisBuilder
         */
        public B withCourseLevel(final String courseLevel) {
            getObject().setCourseLevel(courseLevel);
            return thisBuilder();
        }

        /**
         * Sets the session date for a course a student took.
         *
         * @param sessionDate The session date of the course for a particular
         * grade.
         * @return thisBuilder
         */
        public B withSessionDate(final String sessionDate) {
            getObject().setSessionDate(sessionDate);
            return thisBuilder();
        }

        /**
         * Sets the final percent and letter grade for a course a student took.
         *
         * @param grade The final course grade.
         * @return thisBuilder
         */
        public B withFinalGrade(final Grade grade) {
            getObject().setFinalGrade(grade);
            return thisBuilder();
        }

        /**
         * Sets the interim percent and letter grade for a course a student
         * took.
         *
         * @param grade The final course grade.
         * @return thisBuilder
         */
        public B withInterimGrade(final Grade grade) {
            getObject().setInterimGrade(grade);
            return thisBuilder();
        }

        /**
         * Sets the best school percent for a course a student took (multiple
         * times).
         *
         * @param bestSchoolPercent The best school percent of the student's
         * course.
         * @return thisBuilder
         */
        public B withBestSchoolPercent(final String bestSchoolPercent) {
            getObject().setBestSchoolPercent(bestSchoolPercent);
            return thisBuilder();
        }

        /**
         * Sets the best exam percent for a course a student took (multiple
         * times).
         *
         * @param bestExamPercent The best exam percent of the student's course.
         * @return thisBuilder
         */
        public B withBestExamPercent(final String bestExamPercent) {
            getObject().setBestExamPercent(bestExamPercent);
            return thisBuilder();
        }

        /**
         * Convenience method to set the best school percent value.
         *
         * @param bestSchoolPercent Value to convert to a String.
         * @return thisBuilder
         */
        public B withBestSchoolPercent(final Integer bestSchoolPercent) {
            final String sp = nullSafe(bestSchoolPercent).toString();
            return withBestSchoolPercent(sp);
        }

        /**
         * Convenience method to set the best exam percent value.
         *
         * @param bestExamPercent Value to convert to a String.
         * @return thisBuilder
         */
        public B withBestExamPercent(final Integer bestExamPercent) {
            final String bep = nullSafe(bestExamPercent).toString();
            return withBestExamPercent(bep);
        }
    }

    @Override
    public String toString() {
        return CLASSNAME + "{" + "courseName=" + courseName + ", courseCode=" + courseCode + ", courseLevel=" + courseLevel + ", courseLevelGroup=" + courseLevelGroup + ", sessionDate=" + sessionDate + ", finalGrade=" + finalGrade + ", interimGrade=" + interimGrade + ", bestSchoolPercent=" + bestSchoolPercent + ", bestExamPercent=" + bestExamPercent + ", formattedSessionDate=" + formattedSessionDate + '}';
    }
}

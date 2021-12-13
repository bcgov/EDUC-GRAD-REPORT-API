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
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents additional information about a course grade that constitutes a
 * transcript result.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public final class TranscriptResult extends Mark {

    private static final long serialVersionUID = 2L;

    public final static String COURSE_TYPE_PROVINCIAL = "1";
    public final static String COURSE_TYPE_NONPROVINCIAL = "2";
    public final static String COURSE_TYPE_ASSESSMENT = "3";

    private final static String GROUP_ASSESSMENT = "99";
    private final static String GROUP_UNGRADED = "98";

    /**
     * Display credits attained for the course ("2p", "(4)", 4, 2, etc.).
     */
    private String credits;

    /**
     * Typically "C" or "E", or empty.
     */
    private String equivalencyChallenge;

    /**
     * Indicates what grade requirement was met with this course.
     */
    private String requirementMet;

    /**
     * Indicates what grade requirement was met with this course by name.
     */
    private String requirementMetName;

    /**
     * Indicates if the course is used for graduation.
     */
    private String usedForGrad;

    /**
     * Stores the examinable course state used for differentiating provincially
     * examinable courses, non-provincially examinable courses, and assessments.
     * Takes on the value of any COURSE_TYPE_* constant.
     */
    private String reportCourseType;

    /**
     * This value represents a previous course that the current course is
     * related to.
     */
    @XmlElement(name = "originalCourseID")
    private String originalCourseId;

    /**
     * Default (empty) constructor.
     */
    public TranscriptResult() {
    }

    /**
     * Number of credits earned by a student for completing this course that is
     * suitable for displaying.
     *
     * @return A non-null instance, possibly empty.
     */
    public String getCredits() {
        return nullSafe(this.credits);
    }

    /**
     * Indicates whether the course met the graduation requirements.
     *
     * @return Graduation requirement, if any, that the course met.
     */
    public String getRequirementMet() {
        return nullSafe(this.requirementMet);
    }

    /**
     * Indicates the name of the course met the graduation requirements.
     *
     * @return Graduation requirement, if any, that the course met.
     */
    public String getRequirementMetName() {
        return nullSafe(this.requirementMetName);
    }

    /**
     * TODO: Make this a boolean. Clarify method name (how is it used?).
     *
     * Indicates whether the course is used for graduation.
     *
     * @return Course required for graduation.
     */
    public String getUsedForGrad() {
        return nullSafe(this.usedForGrad);
    }

    /**
     * Returns whether course credit was granted through the Equivalency ("E")
     * or Challenge ("C") process.
     *
     * @return Either "C" or "E", or empty string.
     */
    public String getEquivalencyChallenge() {
        return nullSafe(this.equivalencyChallenge);
    }

    /**
     * Returns the best school percentage for this transcript instance.
     *
     * @return A number from 0 - 100, a three-letter code, or empty string.
     */
    @Override
    public String getSchoolPercent() {
        return getBestSchoolPercent();
    }

    /**
     * Returns the best exam percentage for this transcript instance.
     *
     * @return A number from 0 - 100, a three-letter code, or empty string.
     */
    @Override
    public String getExamPercent() {
        return getBestExamPercent();
    }

    /**
     * Returns a numeric indicator for whether this is a provincially examinable
     * course or not.
     *
     * @return One of the COURSE_TYPE_* constants, or the empty string, but
     * never null.
     */
    public String getReportCourseType() {
        return nullSafe(this.reportCourseType);
    }

    /**
     * Returns a course code and level if the course is linked to a previous
     * code.
     *
     * @return The original course id.
     */
    public String getOriginalCourseId() {
        return originalCourseId;
    }

    /**
     * Answers whether the report course type is of the given type.
     *
     * @param reportCourseType One of the COURSE_TYPE_* constants.
     *
     * @return true Given course type matches report course type.
     */
    public boolean isTranscriptReportCourseType(final String reportCourseType) {
        return getReportCourseType().equalsIgnoreCase(reportCourseType);
    }

    /**
     * Used by the builder to set the credits value.
     *
     * @param credits Passed in by the builder.
     */
    protected void setCredits(final String credits) {
        this.credits = credits;
    }

    /**
     * Used by the builder to set the equivalency challenge value.
     *
     * @param equivalencyChallenge Passed in by the builder.
     */
    protected void setEquivalencyChallenge(final String equivalencyChallenge) {
        this.equivalencyChallenge = equivalencyChallenge;
    }

    /**
     * Used by the builder to set the requirement met value.
     *
     * @param requirementMet Passed in by the builder.
     */
    protected void setRequirementMet(final String requirementMet) {
        this.requirementMet = requirementMet;
    }

    /**
     * Used by the builder to set the name of requirement met value.
     *
     * @param requirementMetName Passed in by the builder.
     */
    protected void setRequirementMetName(final String requirementMetName) {
        this.requirementMetName = requirementMetName;
    }

    /**
     * TODO: Clean up comment, add explanation for how value is used.
     *
     * TODO: Convert from String to boolean.
     *
     * Used by the builder to set the usedForGrad value.
     *
     * @param usedForGrad Passed in by the builder.
     */
    protected void setUsedForGrad(final String usedForGrad) {
        this.usedForGrad = usedForGrad;
    }

    /**
     * Allows differentiation between examinable or assessment course types. A
     * null parameter will cause an assertion error to be thrown.
     *
     * @param reportCourseType A COURSE_TYPE_* constant, or empty.
     */
    protected void setReportCourseType(final String reportCourseType) {
        assert COURSE_TYPE_PROVINCIAL.equals(reportCourseType)
                || COURSE_TYPE_NONPROVINCIAL.equals(reportCourseType)
                || COURSE_TYPE_ASSESSMENT.equals(reportCourseType)
                || "".equals(reportCourseType);

        this.reportCourseType = reportCourseType;
    }

    /**
     * Sets the previous course code if present.
     *
     * @param originalCourseId
     */
    public void setOriginalCourseId(String originalCourseId) {
        this.originalCourseId = originalCourseId;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder
            extends Mark.Builder<TranscriptResult, Builder> {

        /**
         * Returns the object to be built with its instance variables set via
         * the fluent interface.
         *
         * @return The initialized outer object.
         */
        @Override
        public TranscriptResult build() {
            final TranscriptResult result = super.build();
            final String courseLevel = result.getCourseLevel().trim();

            // If the course level is empty, it could be either an assessment
            // or an ungraded course. Set the grouping accordingly so that
            // the report can delineate between the two.
            if (courseLevel.isEmpty()) {
                final String courseType = result.getReportCourseType();

                if (COURSE_TYPE_ASSESSMENT.equals(courseType)) {
                    result.setCourseLevelGroup(GROUP_ASSESSMENT);
                } else {
                    result.setCourseLevelGroup(GROUP_UNGRADED);
                }
            } else {
                result.setCourseLevelGroup(courseLevel);
            }

            return result;
        }

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New TranscriptResult instance.
         */
        @Override
        protected TranscriptResult createObject() {
            return new TranscriptResult();
        }

        /**
         * Sets the credits for a course a student took.
         *
         * @param credits The credits of the student's course.
         * @return thisBuilder
         */
        public Builder withCredits(final String credits) {
            getObject().setCredits(credits);
            return thisBuilder();
        }

        /**
         * Sets the equivalency challenge value for a course a student took.
         *
         * @param equivalencyChallenge The equivalency challenge value of the
         * student's course.
         * @return thisBuilder
         */
        public Builder withEquivalencyChallenge(final String equivalencyChallenge) {
            getObject().setEquivalencyChallenge(equivalencyChallenge);
            return thisBuilder();
        }

        /**
         * Sets the requirement met value for a course a student took.
         *
         * @param requirementMet The requirement met value of the student's
         * course.
         * @return thisBuilder
         */
        public Builder withRequirementMet(
                final String requirementMet) {
            getObject().setRequirementMet(requirementMet);
            return thisBuilder();
        }

        /**
         * Sets the name of requirement met value for a course a student took.
         *
         * @param requirementMetName
         * @return thisBuilder
         */
        public Builder withRequirementMetName(
                final String requirementMetName) {
            getObject().setRequirementMetName(requirementMetName);
            return thisBuilder();
        }

        /**
         * Sets the used for graduation for a course a student took.
         *
         * @param usedForGrad The used for graduation value of the student's
         * course.
         * @return thisBuilder
         */
        public Builder withUsedForGrad(final String usedForGrad) {
            final String ufg;

            if (usedForGrad == null || usedForGrad.equalsIgnoreCase("0")) {
                ufg = "";
            } else {
                ufg = usedForGrad;
            }

            getObject().setUsedForGrad(ufg);
            return thisBuilder();
        }

        /**
         * Sets the transcript's report course type.
         *
         * @param reportCourseType A valid report course type.
         * @return thisBuilder
         */
        public Builder withReportCourseType(final String reportCourseType) {
            getObject().setReportCourseType(reportCourseType);
            return thisBuilder();
        }

        /**
         * TODO: "previous course" and "original course" appear to be separate
         * concepts; comment needs revision to explain a bit more.
         *
         * Sets the previous course code if present.
         *
         * @param originalCourseId The original course ID.
         * @return thisBuilder
         */
        public Builder withOriginalCourseId(final String originalCourseId) {
            getObject().setOriginalCourseId(originalCourseId);
            return thisBuilder();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "credits=" + credits + ", equivalencyChallenge=" + equivalencyChallenge + ", requirementMet=" + requirementMet + ", requirementMetName=" + requirementMetName + ", usedForGrad=" + usedForGrad + ", reportCourseType=" + reportCourseType + ", originalCourseId=" + originalCourseId + '}';
    }
}

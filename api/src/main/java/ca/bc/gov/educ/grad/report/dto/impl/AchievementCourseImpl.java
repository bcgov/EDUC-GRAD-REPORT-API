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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is for a course the student has taken
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AchievementCourseImpl implements AchievementCourse {

    private static final long serialVersionUID = 1L;

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
    private Boolean projected;

    private String gradReqMet;
    private String completedCoursePercentage;
    private String completedCourseLetterGrade;
    private String interimPercent = "";
    private String equivOrChallenge;

    /**
     * Constructor method.
     *
     */
    public AchievementCourseImpl() {
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

    @Override
    public String getGradReqMet() {
        return gradReqMet;
    }

    @Override
    public String getCompletedCoursePercentage() {
        return completedCoursePercentage;
    }

    @Override
    public String getCompletedCourseLetterGrade() {
        return completedCourseLetterGrade;
    }

    @Override
    public String getInterimPercent() {
        return interimPercent;
    }

    @Override
    public String getEquivOrChallenge() {
        return equivOrChallenge;
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

    @Override
    public Boolean getProjected() {
        return projected;
    }

    public void setProjected(Boolean projected) {
        this.projected = projected;
    }

    /**
     * set the related course level.
     * <p>
     * @param relatedLevel
     */
    public void setRelatedLevel(String relatedLevel) {
        this.relatedLevel = relatedLevel;
    }

    /**
     * Set the code value which indicates if this course is used for graduation
     * requirements.
     *
     * @param creditsUsedForGrad
     */
    public void setCreditsUsedForGrad(final String creditsUsedForGrad) {
        this.usedForGrad = creditsUsedForGrad;
    }

    public void setPen(String pen) {
        this.pen = pen;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public void setEquivalency(String equivalency) {
        this.equivalency = equivalency;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setSchoolPercent(String schoolPercent) {
        this.schoolPercent = schoolPercent;
    }

    public void setExamPercent(String examPercent) {
        this.examPercent = examPercent;
    }

    public void setFinalPercent(String finalPercent) {
        this.finalPercent = finalPercent;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setFinalLetterGrade(String finalLetterGrade) {
        this.finalLetterGrade = finalLetterGrade;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public void setInterimMark(String interimMark) {
        this.interimMark = interimMark;
    }

    public void setInterimLetterGrade(String interimLetterGrade) {
        this.interimLetterGrade = interimLetterGrade;
    }

    public String getUsedForGrad() {
        return usedForGrad;
    }

    public void setUsedForGrad(String usedForGrad) {
        this.usedForGrad = usedForGrad;
    }

    public void setGradReqMet(String gradReqMet) {
        this.gradReqMet = gradReqMet;
    }

    public void setCompletedCoursePercentage(String completedCoursePercentage) {
        this.completedCoursePercentage = completedCoursePercentage;
    }

    public void setCompletedCourseLetterGrade(String completedCourseLetterGrade) {
        this.completedCourseLetterGrade = completedCourseLetterGrade;
    }

    public void setInterimPercent(String interimPercent) {
        this.interimPercent = interimPercent;
    }

    public void setEquivOrChallenge(String equivOrChallenge) {
        this.equivOrChallenge = equivOrChallenge;
    }
}

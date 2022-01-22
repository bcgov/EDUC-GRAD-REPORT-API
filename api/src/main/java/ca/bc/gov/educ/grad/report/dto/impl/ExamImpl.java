package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.graduation.Exam;

public class ExamImpl implements Exam {

    private String courseCode;
    private String courseName;
    private String courseLevel;
    private String sessionDate;
    private String gradReqMet;
    private String completedCoursePercentage;
    private String completedCourseLetterGrade;
    private String bestSchoolPercent;
    private String bestExamPercent;
    private String interimPercent;
    private String equivOrChallenge;
    private String metLitNumRequirement;
    private String credits;
    private Integer creditsUsedForGrad;

    @Override
    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String getCourseLevel() {
        return courseLevel;
    }

    @Override
    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    @Override
    public String getSessionDate() {
        return sessionDate;
    }

    @Override
    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public String getGradReqMet() {
        return gradReqMet;
    }

    @Override
    public void setGradReqMet(String gradReqMet) {
        this.gradReqMet = gradReqMet;
    }

    @Override
    public String getCompletedCoursePercentage() {
        return completedCoursePercentage;
    }

    @Override
    public void setCompletedCoursePercentage(String completedCoursePercentage) {
        this.completedCoursePercentage = completedCoursePercentage;
    }

    @Override
    public String getCompletedCourseLetterGrade() {
        return completedCourseLetterGrade;
    }

    @Override
    public void setCompletedCourseLetterGrade(String completedCourseLetterGrade) {
        this.completedCourseLetterGrade = completedCourseLetterGrade;
    }

    @Override
    public String getBestSchoolPercent() {
        return bestSchoolPercent;
    }

    @Override
    public void setBestSchoolPercent(String bestSchoolPercent) {
        this.bestSchoolPercent = bestSchoolPercent;
    }

    @Override
    public String getBestExamPercent() {
        return bestExamPercent;
    }

    @Override
    public void setBestExamPercent(String bestExamPercent) {
        this.bestExamPercent = bestExamPercent;
    }

    @Override
    public String getInterimPercent() {
        return interimPercent;
    }

    public void setInterimPercent(String interimPercent) {
        this.interimPercent = interimPercent;
    }

    @Override
    public String getEquivOrChallenge() {
        return equivOrChallenge;
    }

    @Override
    public void setEquivOrChallenge(String equivOrChallenge) {
        this.equivOrChallenge = equivOrChallenge;
    }

    @Override
    public String getMetLitNumRequirement() {
        return metLitNumRequirement;
    }

    @Override
    public void setMetLitNumRequirement(String metLitNumRequirement) {
        this.metLitNumRequirement = metLitNumRequirement;
    }

    @Override
    public String getCredits() {
        return credits;
    }

    @Override
    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public Integer getCreditsUsedForGrad() {
        return creditsUsedForGrad;
    }

    @Override
    public void setCreditsUsedForGrad(Integer creditsUsedForGrad) {
        this.creditsUsedForGrad = creditsUsedForGrad;
    }
}

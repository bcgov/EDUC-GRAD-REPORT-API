package ca.bc.gov.educ.grad.report.model.graduation;

public interface Exam {
    String getCourseCode();

    String getCourseName();

    String getCourseLevel();

    String getSessionDate();

    String getGradReqMet();

    String getCompletedCoursePercentage();

    String getCompletedCourseLetterGrade();

    String getBestSchoolPercent();

    String getBestExamPercent();

    String getInterimPercent();

    String getEquivOrChallenge();

    String getMetLitNumRequirement();

    String getCredits();

    Integer getCreditsUsedForGrad();

    void setCourseCode(String courseCode);

    void setCourseName(String courseName);

    void setCourseLevel(String courseLevel);

    void setSessionDate(String sessionDate);

    void setGradReqMet(String gradReqMet);

    void setCompletedCoursePercentage(String completedCoursePercentage);

    void setCompletedCourseLetterGrade(String completedCourseLetterGrade);

    void setBestSchoolPercent(String bestSchoolPercent);

    void setBestExamPercent(String bestExamPercent);

    void setEquivOrChallenge(String equivOrChallenge);

    void setMetLitNumRequirement(String metLitNumRequirement);

    void setCredits(String credits);

    void setCreditsUsedForGrad(Integer creditsUsedForGrad);
}

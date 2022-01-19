package ca.bc.gov.educ.grad.report.model.graduation;

public interface GraduationStatus {
    String getProgramCompletionDate();

    String getHonours();

    String getGpa();

    String getStudentGrade();

    String getStudentStatus();

    String getStudentStatusName();

    String getSchoolAtGrad();

    String getSchoolOfRecord();

    String getCertificates();

    String getGraduationMessage();

    String getProgramName();

    void setProgramCompletionDate(String programCompletionDate);

    void setHonours(String honours);

    void setGpa(String gpa);

    void setStudentGrade(String studentGrade);

    void setStudentStatus(String studentStatus);

    void setStudentStatusName(String studentStatusName);

    void setSchoolAtGrad(String schoolAtGrad);

    void setSchoolOfRecord(String schoolOfRecord);

    void setCertificates(String certificates);

    void setGraduationMessage(String graduationMessage);

    void setProgramName(String programName);
}

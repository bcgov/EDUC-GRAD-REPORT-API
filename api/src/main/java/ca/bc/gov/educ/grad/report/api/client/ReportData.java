package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class),
        @JsonSubTypes.Type(value = School.class),
        @JsonSubTypes.Type(value = Transcript.class),
        @JsonSubTypes.Type(value = GradProgram.class),
        @JsonSubTypes.Type(value = NonGradReason.class),
        @JsonSubTypes.Type(value = Certificate.class),
        @JsonSubTypes.Type(value = OptionalProgram.class),
        @JsonSubTypes.Type(value = GradRequirement.class),
        @JsonSubTypes.Type(value = Exam.class),
        @JsonSubTypes.Type(value = Assessment.class),
        @JsonSubTypes.Type(value = AssessmentResult.class),
        @JsonSubTypes.Type(value = AchievementCourse.class)
})
public class ReportData {
    private Student student;
    private School school;
    private String logo;
    private Transcript transcript;
    private Assessment assessment;
    private GradProgram gradProgram;
    private GraduationData graduationData;
    private String gradMessage;
    private String updateDate;
    private Certificate certificate;
    private GraduationStatus graduationStatus;
    private String orgCode;
    private String issueDate;

    private List<NonGradReason> nonGradReasons;
    private List<AchievementCourse> achievementCourses;
    private List<Exam> exams;
    private List<OptionalProgram> optionalPrograms;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student value) {
        this.student = value;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School value) {
        this.school = value;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String value) {
        this.logo = value;
    }

    public List<NonGradReason> getNonGradReasons() {
        return nonGradReasons;
    }

    public void setNonGradReasons(List<NonGradReason> value) {
        this.nonGradReasons = value;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript value) {
        this.transcript = value;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment value) {
        this.assessment = value;
    }

    public GradProgram getGradProgram() {
        return gradProgram;
    }

    public void setGradProgram(GradProgram value) {
        this.gradProgram = value;
    }

    public GraduationData getGraduationData() {
        return graduationData;
    }

    public void setGraduationData(GraduationData value) {
        this.graduationData = value;
    }

    public String getGradMessage() {
        return gradMessage;
    }

    public void setGradMessage(String value) {
        this.gradMessage = value;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String value) {
        this.updateDate = value;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate value) {
        this.certificate = value;
    }

    public GraduationStatus getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(GraduationStatus graduationStatus) {
        this.graduationStatus = graduationStatus;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public List<AchievementCourse> getAchievementCourses() {
        return achievementCourses;
    }

    public void setAchievementCourses(List<AchievementCourse> achievementCourses) {
        this.achievementCourses = achievementCourses;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<OptionalProgram> getOptionalPrograms() {
        return optionalPrograms;
    }

    public void setOptionalPrograms(List<OptionalProgram> optionalPrograms) {
        this.optionalPrograms = optionalPrograms;
    }
}

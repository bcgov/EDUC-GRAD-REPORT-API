package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.api.client.utils.AchievementCourseListDeserializer;
import ca.bc.gov.educ.grad.report.api.client.utils.ExamListDeserializer;
import ca.bc.gov.educ.grad.report.api.client.utils.NonGradReasonListDeserializer;
import ca.bc.gov.educ.grad.report.api.client.utils.OptionalProgramListDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.*;

@Component
@XmlType(name = "")
@XmlRootElement(name = "generateReport")
@XmlSeeAlso({
		Student.class,
		School.class,
		Transcript.class,
		GradProgram.class,
		NonGradReason.class,
		Certificate.class
})
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
public class ReportData implements Serializable {

	@JsonDeserialize(as = Student.class)
	private Student student = new Student();
	@JsonDeserialize(as = School.class)
	private School school = new School();
	@JsonDeserialize(as = Transcript.class)
	private Transcript transcript = new Transcript();
	@JsonDeserialize(as = Assessment.class)
	private Assessment assessment = new Assessment();
	@JsonDeserialize(as = GradProgram.class)
	private GradProgram gradProgram = new GradProgram();
	@JsonDeserialize(as = GraduationData.class)
	private GraduationData graduationData = new GraduationData();
	@JsonFormat(pattern="yyyy-MM-dd")
	private String updateDate;
	@JsonDeserialize(as = Certificate.class)
	private Certificate certificate = new Certificate();
	@JsonDeserialize(as = GraduationStatus.class)
	private GraduationStatus graduationStatus = new GraduationStatus();
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date issueDate;
	@JsonDeserialize(as = PackingSlip.class)
	private PackingSlip packingSlip = new PackingSlip();

	private String logo;
	private String orgCode;
	private String gradMessage;
	private String reportNumber;
	private String reportTitle;
	private String reportSubTitle;

	@JsonDeserialize(using = NonGradReasonListDeserializer.class)
	private List<NonGradReason> nonGradReasons = new ArrayList<>();
	@JsonDeserialize(using = AchievementCourseListDeserializer.class)
	private List<AchievementCourse> studentCourses = new ArrayList<>();
	@JsonDeserialize(using = ExamListDeserializer.class)
	private List<Exam> studentExams = new ArrayList<>();
	@JsonDeserialize(using = OptionalProgramListDeserializer.class)
	private List<OptionalProgram> optionalPrograms = new ArrayList<>();

	private List<School> schools = new ArrayList<>();

	@JsonIgnore
	private Map<String, String> parameters = new HashMap<>();
	@JsonIgnore
	private String accessToken = "accessToken";

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public GradProgram getGradProgram() {
		return gradProgram;
	}

	public void setGradProgram(GradProgram gradProgram) {
		this.gradProgram = gradProgram;
	}

	public GraduationData getGraduationData() {
		return graduationData;
	}

	public void setGraduationData(GraduationData graduationData) {
		this.graduationData = graduationData;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public GraduationStatus getGraduationStatus() {
		return graduationStatus;
	}

	public void setGraduationStatus(GraduationStatus graduationStatus) {
		this.graduationStatus = graduationStatus;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public PackingSlip getPackingSlip() {
		return packingSlip;
	}

	public void setPackingSlip(PackingSlip packingSlip) {
		this.packingSlip = packingSlip;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getGradMessage() {
		return gradMessage;
	}

	public void setGradMessage(String gradMessage) {
		this.gradMessage = gradMessage;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportSubTitle() {
		return reportSubTitle;
	}

	public void setReportSubTitle(String reportSubTitle) {
		this.reportSubTitle = reportSubTitle;
	}

	public List<NonGradReason> getNonGradReasons() {
		return nonGradReasons;
	}

	public void setNonGradReasons(List<NonGradReason> nonGradReasons) {
		this.nonGradReasons = nonGradReasons;
	}

	public List<AchievementCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<AchievementCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public List<Exam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(List<Exam> studentExams) {
		this.studentExams = studentExams;
	}

	public List<OptionalProgram> getOptionalPrograms() {
		return optionalPrograms;
	}

	public void setOptionalPrograms(List<OptionalProgram> optionalPrograms) {
		this.optionalPrograms = optionalPrograms;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}

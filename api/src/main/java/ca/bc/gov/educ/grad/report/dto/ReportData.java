package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.dto.impl.*;
import ca.bc.gov.educ.grad.report.model.achievement.Achievement;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.model.assessment.Assessment;
import ca.bc.gov.educ.grad.report.model.cert.Certificate;
import ca.bc.gov.educ.grad.report.model.graduation.*;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.transcript.GraduationData;
import ca.bc.gov.educ.grad.report.model.transcript.Transcript;
import ca.bc.gov.educ.grad.report.utils.AchievementCourseListDeserializer;
import ca.bc.gov.educ.grad.report.utils.ExamListDeserializer;
import ca.bc.gov.educ.grad.report.utils.NonGradReasonListDeserializer;
import ca.bc.gov.educ.grad.report.utils.OptionalProgramListDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Component
@XmlType(name = "")
@XmlRootElement(name = "generateReport")
@XmlSeeAlso({
		StudentImpl.class,
		SchoolImpl.class,
		TranscriptImpl.class,
		GradProgramImpl.class,
		NonGradReasonImpl.class,
		CertificateImpl.class
})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
//@JsonPropertyOrder(alphabetic = true)
//@JsonRootName("generateReport")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = StudentImpl.class),
		@JsonSubTypes.Type(value = SchoolImpl.class),
		@JsonSubTypes.Type(value = TranscriptImpl.class),
		@JsonSubTypes.Type(value = GradProgramImpl.class),
		@JsonSubTypes.Type(value = NonGradReasonImpl.class),
		@JsonSubTypes.Type(value = CertificateImpl.class),
		@JsonSubTypes.Type(value = StudentInfoImpl.class),
		@JsonSubTypes.Type(value = OptionalProgramImpl.class),
		@JsonSubTypes.Type(value = GradRequirementImpl.class),
		@JsonSubTypes.Type(value = ExamImpl.class),
		@JsonSubTypes.Type(value = AssessmentImpl.class),
		@JsonSubTypes.Type(value = AssessmentResultImpl.class),
		@JsonSubTypes.Type(value = AchievementCourseImpl.class)
})
public class ReportData implements Serializable {

	@JsonDeserialize(as = StudentImpl.class)
	private Student student;
	@JsonDeserialize(as = SchoolImpl.class)
	private School school;
	private String logo;
	@JsonDeserialize(as = TranscriptImpl.class)
	private Transcript transcript;
	@JsonDeserialize(as = AchievementImpl.class)
	private Achievement achievement;
	@JsonDeserialize(as = AssessmentImpl.class)
	private Assessment assessment;
	@JsonDeserialize(as = GradProgramImpl.class)
	private GradProgram gradProgram;
	@JsonDeserialize(using = NonGradReasonListDeserializer.class)
	private List<NonGradReason> nonGradReasons;
	private String gradMessage;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	private Map<String, String> parameters;
	@JsonDeserialize(as = CertificateImpl.class)
	private Certificate certificate;
	@JsonDeserialize(as = GraduationDataImpl.class)
	private GraduationData graduationData;
	@JsonDeserialize(using = ExamListDeserializer.class)
	private List<Exam> studentExams;
	@JsonDeserialize(using = AchievementCourseListDeserializer.class)
	private List<AchievementCourse> studentCourses;
	@JsonDeserialize(using = OptionalProgramListDeserializer.class)
	private List<OptionalProgram> optionalPrograms;
	@JsonDeserialize(as = StudentInfoImpl.class)
	private ca.bc.gov.educ.grad.report.model.student.StudentInfo studentInfo;
	@JsonDeserialize(as = GraduationStatusImpl.class)
	private GraduationStatus graduationStatus;
	private String orgCode;
}

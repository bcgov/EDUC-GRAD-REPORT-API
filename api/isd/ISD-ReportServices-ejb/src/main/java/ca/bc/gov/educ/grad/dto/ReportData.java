package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.grad.utils.NonGradReasonListDeserializer;
import ca.bc.gov.educ.isd.cert.Certificate;
import ca.bc.gov.educ.isd.grad.GradProgram;
import ca.bc.gov.educ.isd.grad.NonGradReason;
import ca.bc.gov.educ.isd.grad.impl.CertificateImpl;
import ca.bc.gov.educ.isd.grad.impl.GradProgramImpl;
import ca.bc.gov.educ.isd.grad.impl.NonGradReasonImpl;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.student.impl.SchoolImpl;
import ca.bc.gov.educ.isd.student.impl.StudentImpl;
import ca.bc.gov.educ.isd.transcript.GraduationData;
import ca.bc.gov.educ.isd.transcript.Transcript;
import ca.bc.gov.educ.isd.transcript.impl.GraduationDataImpl;
import ca.bc.gov.educ.isd.transcript.impl.TranscriptImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ReportData implements Serializable {

	@JsonDeserialize(as = StudentImpl.class)
	private Student student;
	@JsonDeserialize(as = SchoolImpl.class)
	private School school;
	private String logo;
	@JsonDeserialize(as = TranscriptImpl.class)
	private Transcript transcript;
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

}

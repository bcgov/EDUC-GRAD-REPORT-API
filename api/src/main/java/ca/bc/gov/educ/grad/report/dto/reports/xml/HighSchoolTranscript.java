package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class HighSchoolTranscript {
	@JacksonXmlProperty(localName = "TransmissionData")
	private TransmissionData TransmissionData = new TransmissionData();
	@JacksonXmlProperty(localName = "Student")
	private Student Student;
	@JacksonXmlProperty(isAttribute = true, localName = "HSTrn")
	private String HSTrn = "urn:org:pesc:message:HighSchoolTranscript:v1.5.0";
	@JacksonXmlProperty(isAttribute = true)
	private String xsi = "http://www.w3.org/2001/XMLSchema";
	@JacksonXmlProperty(isAttribute = true)
	private String core = "urn:org:pesc:core:CoreMain:v1.14.0";

	public HighSchoolTranscript(Student student) {
		Student = student;
	}
}
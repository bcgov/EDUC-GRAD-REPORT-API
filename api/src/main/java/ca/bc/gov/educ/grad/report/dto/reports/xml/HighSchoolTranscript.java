package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighSchoolTranscript { 
	private TransmissionData TransmissionData;
	private Student Student;
	@XmlAttribute
	private String HSTrn = "urn:org:pesc:message:HighSchoolTranscript:v1.5.0";
	@XmlAttribute
	private String xsi = "http://www.w3.org/2001/XMLSchema";
	@XmlAttribute
	private String core = "urn:org:pesc:core:CoreMain:v1.14.0";
}
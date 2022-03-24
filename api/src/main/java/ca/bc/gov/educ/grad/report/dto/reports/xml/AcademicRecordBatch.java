package ca.bc.gov.educ.grad.report.dto.reports.xml;

import ca.bc.gov.educ.grad.report.utils.AcademicRecordBatchDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonDeserialize(using = AcademicRecordBatchDeserializer.class)
@JacksonXmlRootElement(localName = "AcademicRecordBatch")
public class AcademicRecordBatch {
	@JacksonXmlProperty(localName = "HighSchoolTranscript")
	private HighSchoolTranscript HighSchoolTranscript;
	@JacksonXmlProperty(isAttribute = true)
	private String AcRecBat = "urn:org:pesc:message:AcademicRecordBatch:v1.0.0";
	@JacksonXmlProperty(isAttribute = true)
	private String xsi = "http://www.w3.org/2001/XMLSchema-instance";
	@JacksonXmlProperty(isAttribute = true)
	private String schemaLocation = "urn:org:pesc:message:AcademicRecordBatch:v1.0.0 AcademicRecordBatch_v1.0.0.xsd";
}

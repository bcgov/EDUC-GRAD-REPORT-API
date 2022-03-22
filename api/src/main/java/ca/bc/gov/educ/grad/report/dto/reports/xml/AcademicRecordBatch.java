package ca.bc.gov.educ.grad.report.dto.reports.xml;

import ca.bc.gov.educ.grad.report.dto.reports.util.xml.AcademicRecordBatchDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonDeserialize(using = AcademicRecordBatchDeserializer.class)
public class AcademicRecordBatch {
	private HighSchoolTranscript HighSchoolTranscript;
	@JacksonXmlProperty(isAttribute = true)
	private String AcRecBat = "urn:org:pesc:message:AcademicRecordBatch:v1.0.0";
	@JacksonXmlProperty(isAttribute = true)
	private String xsi = "http://www.w3.org/2001/XMLSchema-instance";
	@JacksonXmlProperty(isAttribute = true)
	private String schemaLocation = "urn:org:pesc:message:AcademicRecordBatch:v1.0.0 AcademicRecordBatch_v1.0.0.xsd";
}

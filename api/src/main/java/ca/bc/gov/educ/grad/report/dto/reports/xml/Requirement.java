package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requirement {
	@JacksonXmlProperty(localName = "RAPCode")
	private String RAPCode;
	@JacksonXmlProperty(localName = "RAPName")
	private String RAPName;
	@JacksonXmlProperty(localName = "ConditionsMetCode")
	private String ConditionsMetCode;
}
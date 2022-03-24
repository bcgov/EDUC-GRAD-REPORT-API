package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
	@JacksonXmlProperty(localName = "FirstName")
	private String FirstName;
	@JacksonXmlProperty(localName = "MiddleName")
	private String MiddleName;
	@JacksonXmlProperty(localName = "LastName")
	private String LastName;
}
package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSessionDetail {
	@JacksonXmlProperty(localName = "SessionDesignator")
	private String SessionDesignator;
	@JacksonXmlProperty(localName = "SessionName")
	private String SessionName;
	@JacksonXmlProperty(localName = "SessionSchoolYear")
	private String SessionSchoolYear;
}

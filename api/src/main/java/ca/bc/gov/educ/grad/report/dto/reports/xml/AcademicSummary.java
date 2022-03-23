package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSummary { 
	private String AcademicSummaryType;
	private String AcademicSummaryLevel;
	@JacksonXmlProperty(localName = "GPA")
	private GPA GPA;
}

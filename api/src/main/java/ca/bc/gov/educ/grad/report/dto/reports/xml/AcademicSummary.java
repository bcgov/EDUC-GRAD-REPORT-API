package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSummary {
	@JacksonXmlProperty(localName = "AcademicSummaryType")
	private String AcademicSummaryType;
	@JacksonXmlProperty(localName = "AcademicSummaryLevel")
	private String AcademicSummaryLevel;
	@JacksonXmlProperty(localName = "NoteMessage")
	private String NoteMessage;
	@JacksonXmlProperty(localName = "GPA")
	private GPA GPA;
}

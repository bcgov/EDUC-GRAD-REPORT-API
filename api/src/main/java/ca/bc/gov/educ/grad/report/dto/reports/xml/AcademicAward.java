package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicAward {
	@JacksonXmlProperty(localName = "AcademicAwardLevel")
	private String AcademicAwardLevel;
	@JacksonXmlProperty(localName = "AcademicAwardDate")
	private String AcademicAwardDate;
	@JacksonXmlProperty(localName = "AcademicAwardTitle")
	private String AcademicAwardTitle;
	@JacksonXmlProperty(localName = "AcademicHonors")
	private AcademicHonors AcademicHonors;
	@JacksonXmlProperty(localName = "AcademicCompletionIndicator")
	private String AcademicCompletionIndicator;
	@JacksonXmlProperty(localName = "AcademicCompletionDate")
	private String AcademicCompletionDate;
	@JacksonXmlProperty(localName = "AcademicAwardProgram")
	private AcademicAwardProgram AcademicAwardProgram;
	@JacksonXmlProperty(localName = "AcademicSummary")
	private AcademicSummary AcademicSummary;
}

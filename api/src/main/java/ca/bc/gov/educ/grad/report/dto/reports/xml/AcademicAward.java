package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicAward { 
	private String AcademicAwardLevel;
	private String AcademicAwardDate;
	private String AcademicAwardTitle;
	@JacksonXmlProperty(localName = "AcademicHonors")
	private AcademicHonors AcademicHonors;
	private String AcademicCompletionIndicator;
	private String AcademicCompletionDate;
	@JacksonXmlProperty(localName = "AcademicSummary")
	private AcademicSummary AcademicSummary;
}

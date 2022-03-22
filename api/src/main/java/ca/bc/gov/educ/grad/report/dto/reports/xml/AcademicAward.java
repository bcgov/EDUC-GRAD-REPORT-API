package ca.bc.gov.educ.grad.report.dto.reports.xml;

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
	private AcademicHonors AcademicHonors;
	private String AcademicCompletionIndicator;
	private String AcademicCompletionDate;
	private AcademicSummary AcademicSummary;
}

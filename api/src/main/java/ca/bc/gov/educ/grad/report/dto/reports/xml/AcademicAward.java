package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicAward { 
	private String AcademicAwardLevel;
	private Date AcademicAwardDate;
	private String AcademicAwardTitle;
	private AcademicHonors AcademicHonors;
	private boolean AcademicCompletionIndicator;
	private Date AcademicCompletionDate;
	private AcademicSummary AcademicSummary;
}

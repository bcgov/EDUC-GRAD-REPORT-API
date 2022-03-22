package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSessionDetail { 
	private String SessionDesignator;
	private String SessionName;
	private String SessionSchoolYear;
}

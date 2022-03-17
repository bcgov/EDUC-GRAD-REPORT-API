package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicSessionDetail { 
	private Date SessionDesignator;
	private Date SessionName;
	private String SessionSchoolYear;
}

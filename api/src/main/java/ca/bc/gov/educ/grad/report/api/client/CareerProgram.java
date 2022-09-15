package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CareerProgram {

	private String careerProgramCode;	
	private String careerProgramName;
	
}

package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class CareerProgram {

	private String careerProgramCode;	
	private String careerProgramName;
	
}

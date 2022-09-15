package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.program.CareerProgram;
import lombok.Data;

@Data
public class CareerProgramImpl implements CareerProgram {

    private String careerProgramCode;
    private String careerProgramName;

}

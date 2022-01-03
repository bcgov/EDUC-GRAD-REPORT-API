package ca.bc.gov.educ.grad.report.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherProgram {

    private String programCode;
    private String programName;

}

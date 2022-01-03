package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.model.graduation.GradRequirement;
import ca.bc.gov.educ.grad.report.utils.GradRequirementListDeserializer;
import ca.bc.gov.educ.grad.report.utils.NonGradReasonListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionalProgram {

    private String optionalProgramCode;
    private String optionalProgramName;
    private String programCompletionDate;
    private String hasRequirementMet;
    @JsonDeserialize(using = GradRequirementListDeserializer.class)
    private List<GradRequirement> requirementMet;
    @JsonDeserialize(using = NonGradReasonListDeserializer.class)
    private List<ca.bc.gov.educ.grad.report.model.graduation.NonGradReason> nonGradReasons;

}

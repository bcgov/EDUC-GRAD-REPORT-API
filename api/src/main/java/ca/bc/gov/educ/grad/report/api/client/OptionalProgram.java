package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.api.client.utils.GradRequirementListDeserializer;
import ca.bc.gov.educ.grad.report.api.client.utils.NonGradReasonListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OptionalProgram implements Serializable {

    private String optionalProgramCode = "";
    private String optionalProgramName = "";
    private String programCompletionDate = "";
    private String hasRequirementMet = "";
    private List<GradRequirement> requirementMet = new ArrayList<>();
    private List<NonGradReason> nonGradReasons = new ArrayList<>();

    public String getOptionalProgramCode() {
        return optionalProgramCode;
    }

    public void setOptionalProgramCode(String optionalProgramCode) {
        this.optionalProgramCode = optionalProgramCode;
    }

    public String getOptionalProgramName() {
        return optionalProgramName;
    }

    public void setOptionalProgramName(String optionalProgramName) {
        this.optionalProgramName = optionalProgramName;
    }

    public String getProgramCompletionDate() {
        return programCompletionDate;
    }

    public void setProgramCompletionDate(String programCompletionDate) {
        this.programCompletionDate = programCompletionDate;
    }

    public String getHasRequirementMet() {
        return hasRequirementMet;
    }

    public void setHasRequirementMet(String hasRequirementMet) {
        this.hasRequirementMet = hasRequirementMet;
    }

    @JsonDeserialize(using = GradRequirementListDeserializer.class)
    public List<GradRequirement> getRequirementMet() {
        return requirementMet;
    }

    public void setRequirementMet(List<GradRequirement> requirementMet) {
        this.requirementMet = requirementMet;
    }

    @JsonDeserialize(using = NonGradReasonListDeserializer.class)
    public List<NonGradReason> getNonGradReasons() {
        return nonGradReasons;
    }

    public void setNonGradReasons(List<NonGradReason> nonGradReasons) {
        this.nonGradReasons = nonGradReasons;
    }
}

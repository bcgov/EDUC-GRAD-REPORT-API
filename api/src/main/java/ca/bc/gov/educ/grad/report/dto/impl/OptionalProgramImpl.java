package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.graduation.GradRequirement;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
import ca.bc.gov.educ.grad.report.utils.DateUtils;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

public class OptionalProgramImpl implements OptionalProgram {

    private String optionalProgramCode;
    private String optionalProgramName;
    private String programCompletionDate;
    private String hasRequirementMet;
    private List<GradRequirement> requirementMet;
    private List<NonGradReason> nonGradReasons;

    public OptionalProgramImpl() {}

    @Override
    public String getOptionalProgramCode() {
        return optionalProgramCode;
    }

    @Override
    public void setOptionalProgramCode(String optionalProgramCode) {
        this.optionalProgramCode = optionalProgramCode;
    }

    @Override
    public String getOptionalProgramName() {
        return optionalProgramName;
    }

    @Override
    public void setOptionalProgramName(String optionalProgramName) {
        this.optionalProgramName = optionalProgramName;
    }

    @Override
    public String getProgramCompletionDate() {
        return DateUtils.formatProgramCompletionDate(programCompletionDate);
    }

    @Override
    public void setProgramCompletionDate(String programCompletionDate) {
        this.programCompletionDate = programCompletionDate;
    }

    @Override
    public String getHasRequirementMet() {
        return hasRequirementMet;
    }

    @Override
    public void setHasRequirementMet(String hasRequirementMet) {
        this.hasRequirementMet = hasRequirementMet;
    }

    @Override
    public List<GradRequirement> getRequirementMet() {
        return requirementMet;
    }

    @Override
    public void setRequirementMet(List<GradRequirement> requirementMet) {
        this.requirementMet = requirementMet;
    }

    @Override
    public List<NonGradReason> getNonGradReasons() {
        return nonGradReasons;
    }

    @Override
    public void setNonGradReasons(List<NonGradReason> nonGradReasons) {
        this.nonGradReasons = nonGradReasons;
    }

    @Override
    public JRBeanCollectionDataSource getRequirementMetdataSource() {
        return new JRBeanCollectionDataSource(requirementMet, false);
    }

    @Override
    public JRBeanCollectionDataSource getNonGradReasonsdataSource() {
        return new JRBeanCollectionDataSource(nonGradReasons, false);
    }

}

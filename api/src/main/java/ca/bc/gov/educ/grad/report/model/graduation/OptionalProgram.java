package ca.bc.gov.educ.grad.report.model.graduation;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public interface OptionalProgram {
    JRBeanCollectionDataSource getRequirementMetdataSource();

    JRBeanCollectionDataSource getNonGradReasonsdataSource();

    String getOptionalProgramCode();

    String getOptionalProgramName();

    String getProgramCompletionDate();

    String getHasRequirementMet();

    java.util.List<GradRequirement> getRequirementMet();

    java.util.List<NonGradReason> getNonGradReasons();

    void setOptionalProgramCode(String optionalProgramCode);

    void setOptionalProgramName(String optionalProgramName);

    void setProgramCompletionDate(String programCompletionDate);

    void setHasRequirementMet(String hasRequirementMet);

    void setRequirementMet(java.util.List<GradRequirement> requirementMet);

    void setRequirementMetdataSource(JRBeanCollectionDataSource requirementMetdataSource);

    void setNonGradReasons(java.util.List<NonGradReason> nonGradReasons);

    void setNonGradReasonsdataSource(JRBeanCollectionDataSource nonGradReasonsdataSource);
}

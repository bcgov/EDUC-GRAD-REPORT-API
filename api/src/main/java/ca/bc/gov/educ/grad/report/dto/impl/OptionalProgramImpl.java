package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.graduation.GradRequirement;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
import ca.bc.gov.educ.grad.report.utils.GradRequirementListDeserializer;
import ca.bc.gov.educ.grad.report.utils.NonGradReasonListDeserializer;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = NonGradReasonImpl.class),
        @JsonSubTypes.Type(value = GradRequirementImpl.class),
        @JsonSubTypes.Type(value = AchievementCourseImpl.class)
})
public class OptionalProgramImpl implements OptionalProgram {

    private String optionalProgramCode;
    private String optionalProgramName;
    private String programCompletionDate;
    private String hasRequirementMet;
    @JsonDeserialize(using = GradRequirementListDeserializer.class)
    private List<GradRequirement> requirementMet;
    private JRBeanCollectionDataSource requirementMetdataSource;
    @JsonDeserialize(using = NonGradReasonListDeserializer.class)
    private List<ca.bc.gov.educ.grad.report.model.graduation.NonGradReason> nonGradReasons;
    private JRBeanCollectionDataSource nonGradReasonsdataSource;

    public OptionalProgramImpl() {}

    @Override
    public JRBeanCollectionDataSource getRequirementMetdataSource() {
        return new JRBeanCollectionDataSource(requirementMet, false);
    }

    @Override
    public JRBeanCollectionDataSource getNonGradReasonsdataSource() {
        return new JRBeanCollectionDataSource(nonGradReasons, false);
    }

}

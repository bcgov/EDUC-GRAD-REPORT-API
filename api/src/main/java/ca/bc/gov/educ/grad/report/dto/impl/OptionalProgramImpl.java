package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.graduation.GradRequirement;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
import com.fasterxml.jackson.annotation.JsonSubTypes;
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
    private List<GradRequirement> requirementMet;
    private List<NonGradReason> nonGradReasons;

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

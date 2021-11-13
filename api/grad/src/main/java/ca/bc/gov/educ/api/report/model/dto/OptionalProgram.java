package ca.bc.gov.educ.api.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    private List<GradRequirement> requirementMet;
    private JRBeanCollectionDataSource requirementMetdataSource;
    private List<GradRequirement> nonGradReasons;
    private JRBeanCollectionDataSource nonGradReasonsdataSource;


    public void setHasRequirementMet(String hasRequirementMet) {
        if (this.requirementMet.isEmpty()) {
            this.hasRequirementMet = "false";
        }
    }

    public JRBeanCollectionDataSource getRequirementMetdataSource() {
        return new JRBeanCollectionDataSource(requirementMet, false);
    }

    public JRBeanCollectionDataSource getNonGradReasonsdataSource() {
        return new JRBeanCollectionDataSource(nonGradReasons, false);
    }

}

package ca.bc.gov.educ.grad.report.api.dto;

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
    public JRBeanCollectionDataSource getRequirementMetdataSource() {
        return new JRBeanCollectionDataSource(requirementMet, false);
    }

    public JRBeanCollectionDataSource getNonGradReasonsdataSource() {
        return new JRBeanCollectionDataSource(nonGradReasons, false);
    }

}

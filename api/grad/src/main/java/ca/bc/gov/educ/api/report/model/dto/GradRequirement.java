package ca.bc.gov.educ.api.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradRequirement {
    private String rule;
    private String description;
    private List<CourseDetails> courseDetails;
    private JRBeanCollectionDataSource courseDetailsdataSource;

    public JRBeanCollectionDataSource getCourseDetailsdataSource() {
        return new JRBeanCollectionDataSource(courseDetails, false);
    }
}

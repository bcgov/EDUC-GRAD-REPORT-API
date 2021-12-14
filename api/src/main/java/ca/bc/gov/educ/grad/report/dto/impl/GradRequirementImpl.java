package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.model.graduation.GradRequirement;
import ca.bc.gov.educ.grad.report.utils.AchievementCourseListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class GradRequirementImpl implements GradRequirement {
    private String code;
    private String description;
    @JsonDeserialize(using = AchievementCourseListDeserializer.class)
    private List<AchievementCourse> courseDetails;
    private JRBeanCollectionDataSource courseDetailsdataSource;

    @Override
    public JRBeanCollectionDataSource getCourseDetailsdataSource() {
        return new JRBeanCollectionDataSource(courseDetails, false);
    }
}

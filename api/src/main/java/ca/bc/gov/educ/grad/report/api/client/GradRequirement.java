package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = AchievementCourse.class)
})
public class GradRequirement {
    private String code;
    private String description;
    private List<AchievementCourse> courseDetails;

}

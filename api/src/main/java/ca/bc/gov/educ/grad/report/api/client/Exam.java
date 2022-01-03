package ca.bc.gov.educ.grad.report.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    private String courseCode;
    private String courseName;
    private String courseLevel;
    private String sessionDate;
    private String gradReqMet;
    private String completedCoursePercentage;
    private String completedCourseLetterGrade;
    private String bestSchoolPercent;
    private String bestExamPercent;
    private String interimPercent;
    private String equivOrChallenge;
    private String metLitNumRequirement;
    private String credits;
    private Integer creditsUsedForGrad;
}

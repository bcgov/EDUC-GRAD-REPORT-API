package ca.bc.gov.educ.api.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentExam {

    private String pen;
    private String courseCode;
    private String courseName;
    private String courseLevel;
    private String sessionDate;
    private String gradReqMet;
    private Double completedCoursePercentage;
    private String completedCourseLetterGrade;
    private Double bestSchoolPercent;
    private Double bestExamPercent;
    private String equivOrChallenge;
    private String metLitNumRequirement;
    private Integer credits;
    private Integer creditsUsedForGrad;
}

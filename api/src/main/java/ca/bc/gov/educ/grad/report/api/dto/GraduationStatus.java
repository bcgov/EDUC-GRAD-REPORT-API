package ca.bc.gov.educ.grad.report.api.dto;

import lombok.Data;

@Data
public class GraduationStatus {
    private String programCompletionDate;
    private String honours;
    private String gpa;
    private String studentGrade;
    private String studentStatus;
    private String studentStatusName;
    private String schoolAtGrad;
    private String schoolOfRecord;
    private String certificates;
    private String graduationMessage;
    private String programName;
}

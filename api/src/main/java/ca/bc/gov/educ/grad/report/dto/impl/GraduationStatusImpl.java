package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.graduation.GraduationStatus;
import lombok.Data;

@Data
public class GraduationStatusImpl implements GraduationStatus {
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

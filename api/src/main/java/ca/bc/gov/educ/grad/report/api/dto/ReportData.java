package ca.bc.gov.educ.grad.report.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportData {
    private List<StudentExam> studentExams;
    private List<StudentCourse> studentCourses;
    private List<StudentAssessment> studentAssessments;
    private List<NonGradReason> nonGradReasons;
    private List<OptionalProgram> optionalPrograms;
    private School school;
    private Student student;
    private GraduationStatus graduationStatus;
    private String orgCode;

}

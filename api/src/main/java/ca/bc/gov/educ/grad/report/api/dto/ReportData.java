package ca.bc.gov.educ.grad.report.api.dto;

import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.utils.AchievementCourseListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
public class ReportData {
    private List<StudentExam> studentExams;
    @JsonDeserialize(using = AchievementCourseListDeserializer.class)
    private List<AchievementCourse> studentCourses;
    private List<StudentAssessment> studentAssessments;
    private List<NonGradReason> nonGradReasons;
    private List<OptionalProgram> optionalPrograms;
    private School school;
    private Student student;
    private GraduationStatus graduationStatus;
    private String orgCode;

}

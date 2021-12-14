package ca.bc.gov.educ.grad.report.api.dto;

import ca.bc.gov.educ.grad.report.dto.impl.OptionalProgramImpl;
import ca.bc.gov.educ.grad.report.dto.impl.SchoolImpl;
import ca.bc.gov.educ.grad.report.dto.impl.StudentInfoImpl;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.utils.AchievementCourseListDeserializer;
import ca.bc.gov.educ.grad.report.utils.NonGradReasonListDeserializer;
import ca.bc.gov.educ.grad.report.utils.OptionalProgramListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
public class ReportData {
    private List<StudentExam> studentExams;
    @JsonDeserialize(using = AchievementCourseListDeserializer.class)
    private List<AchievementCourse> studentCourses;
    private List<StudentAssessment> studentAssessments;
    @JsonDeserialize(using = NonGradReasonListDeserializer.class)
    private List<ca.bc.gov.educ.grad.report.model.graduation.NonGradReason> nonGradReasons;
    @JsonDeserialize(using = OptionalProgramListDeserializer.class)
    private List<OptionalProgramImpl> optionalPrograms;
    @JsonDeserialize(as = SchoolImpl.class)
    private ca.bc.gov.educ.grad.report.model.school.School school;
    @JsonDeserialize(as = StudentInfoImpl.class)
    private ca.bc.gov.educ.grad.report.model.student.StudentInfo student;
    private GraduationStatus graduationStatus;
    private String orgCode;

}

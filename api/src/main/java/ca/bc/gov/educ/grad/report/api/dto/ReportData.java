package ca.bc.gov.educ.grad.report.api.dto;

import ca.bc.gov.educ.grad.report.dto.impl.GraduationStatusImpl;
import ca.bc.gov.educ.grad.report.dto.impl.SchoolImpl;
import ca.bc.gov.educ.grad.report.dto.impl.StudentInfoImpl;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationStatus;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
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
    private List<OptionalProgram> optionalPrograms;
    @JsonDeserialize(as = SchoolImpl.class)
    private ca.bc.gov.educ.grad.report.model.school.School school;
    @JsonDeserialize(as = StudentInfoImpl.class)
    private ca.bc.gov.educ.grad.report.model.student.StudentInfo student;
    @JsonDeserialize(as = GraduationStatusImpl.class)
    private GraduationStatus graduationStatus;
    private String orgCode;

}

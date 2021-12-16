package ca.bc.gov.educ.grad.report.api.dto;

import ca.bc.gov.educ.grad.report.dto.impl.*;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.model.graduation.Exam;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationStatus;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
import ca.bc.gov.educ.grad.report.utils.AchievementCourseListDeserializer;
import ca.bc.gov.educ.grad.report.utils.ExamListDeserializer;
import ca.bc.gov.educ.grad.report.utils.NonGradReasonListDeserializer;
import ca.bc.gov.educ.grad.report.utils.OptionalProgramListDeserializer;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonSubTypes({
        @JsonSubTypes.Type(value = StudentInfoImpl.class),
        @JsonSubTypes.Type(value = SchoolImpl.class),
        @JsonSubTypes.Type(value = NonGradReasonImpl.class),
        @JsonSubTypes.Type(value = OptionalProgramImpl.class),
        @JsonSubTypes.Type(value = GradRequirementImpl.class),
        @JsonSubTypes.Type(value = ExamImpl.class),
        @JsonSubTypes.Type(value = AchievementCourseImpl.class)
})
public class ReportData {
    @JsonDeserialize(using = ExamListDeserializer.class)
    private List<Exam> studentExams;
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

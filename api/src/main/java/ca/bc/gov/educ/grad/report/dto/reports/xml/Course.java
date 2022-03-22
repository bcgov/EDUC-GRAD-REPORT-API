package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Value("${xml.transcript.Student.AcademicRecord.AcademicSession.Course.CourseCreditBasis}")
	private String CourseCreditBasis;
	private String CourseCreditLevel;
	private int CourseCreditValue;
	private int CourseAcademicGradeScaleCode;
	private String CourseAcademicGrade;
	private CourseSupplementalAcademicGrade CourseSupplementalAcademicGrade;
	private String CourseAcademicGradeStatusCode;
	private String CourseSubjectAbbreviation;
	private String CourseNumber;
	private String CourseTitle;
	private Requirement Requirement;
}
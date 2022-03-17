package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course { 
	private String CourseCreditBasis;
	private String CourseCreditLevel;
	private int CourseCreditValue;
	private int CourseAcademicGradeScaleCode;
	private String CourseAcademicGrade;
	private CourseSupplementalAcademicGrade CourseSupplementalAcademicGrade;
	private String CourseAcademicGradeStatusCode;
	private String CourseSubjectAbbreviation;
	private int CourseNumber;
	private String CourseTitle;
	private Requirement Requirement;
}
package ca.bc.gov.educ.grad.report.dto.reports.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSupplementalGrade { 
	private String SupplementalGradeCode;
	private int CourseAcademicSupplementalGradeScaleCode;
	private String CourseAcademicSupplementalGradeStatusCode;
	private int CourseAcademicSupplementalGrade;
}
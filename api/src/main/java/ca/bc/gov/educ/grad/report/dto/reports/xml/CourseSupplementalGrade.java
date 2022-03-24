package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSupplementalGrade {
	@JacksonXmlProperty(localName = "SupplementalGradeCode")
	private String SupplementalGradeCode;
	@JacksonXmlProperty(localName = "CourseAcademicSupplementalGradeScaleCode")
	private String CourseAcademicSupplementalGradeScaleCode;
	@JacksonXmlProperty(localName = "CourseAcademicSupplementalGradeStatusCode")
	private String CourseAcademicSupplementalGradeStatusCode;
	@JacksonXmlProperty(localName = "CourseAcademicSupplementalGrade")
	private String CourseAcademicSupplementalGrade;
}
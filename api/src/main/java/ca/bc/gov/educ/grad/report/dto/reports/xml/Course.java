package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
	@JacksonXmlProperty(localName = "CourseCreditBasis")
	private String CourseCreditBasis;
	@JacksonXmlProperty(localName = "CourseCreditLevel")
	private String CourseCreditLevel;
	@JacksonXmlProperty(localName = "CourseCreditValue")
	private int CourseCreditValue;
	@JacksonXmlProperty(localName = "CourseAcademicGradeScaleCode")
	private int CourseAcademicGradeScaleCode;
	@JacksonXmlProperty(localName = "CourseAcademicGrade")
	private String CourseAcademicGrade;
	@JacksonXmlProperty(localName = "CourseSupplementalAcademicGrade")
	private CourseSupplementalAcademicGrade CourseSupplementalAcademicGrade;
	@JacksonXmlProperty(localName = "CourseAcademicGradeStatusCode")
	private String CourseAcademicGradeStatusCode;
	@JacksonXmlProperty(localName = "CourseSubjectAbbreviation")
	private String CourseSubjectAbbreviation;
	@JacksonXmlProperty(localName = "CourseNumber")
	private String CourseNumber;
	@JacksonXmlProperty(localName = "CourseTitle")
	private String CourseTitle;
	@JacksonXmlProperty(localName = "Requirement")
	private Requirement Requirement;
}
package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.beans.factory.annotation.Value;

public class Course {
	@Value("${xml.transcript.Student.AcademicRecord.AcademicSession.Course.CourseCreditBasis}")
	private String CourseCreditBasis;
	private String CourseCreditLevel;
	private int CourseCreditValue;
	private int CourseAcademicGradeScaleCode;
	private String CourseAcademicGrade;
	@JacksonXmlProperty(localName = "CourseSupplementalAcademicGrade")
	private CourseSupplementalAcademicGrade CourseSupplementalAcademicGrade;
	private String CourseAcademicGradeStatusCode;
	private String CourseSubjectAbbreviation;
	private String CourseNumber;
	private String CourseTitle;
	@JacksonXmlProperty(localName = "Requirement")
	private Requirement Requirement;

	public Course(String courseCreditLevel, int courseCreditValue, int courseAcademicGradeScaleCode, String courseAcademicGrade, CourseSupplementalAcademicGrade courseSupplementalAcademicGrade, String courseAcademicGradeStatusCode, String courseSubjectAbbreviation, String courseNumber, String courseTitle, Requirement requirement) {
		CourseCreditLevel = courseCreditLevel;
		CourseCreditValue = courseCreditValue;
		CourseAcademicGradeScaleCode = courseAcademicGradeScaleCode;
		CourseAcademicGrade = courseAcademicGrade;
		CourseSupplementalAcademicGrade = courseSupplementalAcademicGrade;
		CourseAcademicGradeStatusCode = courseAcademicGradeStatusCode;
		CourseSubjectAbbreviation = courseSubjectAbbreviation;
		CourseNumber = courseNumber;
		CourseTitle = courseTitle;
		Requirement = requirement;
	}

	public String getCourseCreditBasis() {
		return CourseCreditBasis;
	}

	public void setCourseCreditBasis(String courseCreditBasis) {
		CourseCreditBasis = courseCreditBasis;
	}

	public String getCourseCreditLevel() {
		return CourseCreditLevel;
	}

	public void setCourseCreditLevel(String courseCreditLevel) {
		CourseCreditLevel = courseCreditLevel;
	}

	public int getCourseCreditValue() {
		return CourseCreditValue;
	}

	public void setCourseCreditValue(int courseCreditValue) {
		CourseCreditValue = courseCreditValue;
	}

	public int getCourseAcademicGradeScaleCode() {
		return CourseAcademicGradeScaleCode;
	}

	public void setCourseAcademicGradeScaleCode(int courseAcademicGradeScaleCode) {
		CourseAcademicGradeScaleCode = courseAcademicGradeScaleCode;
	}

	public String getCourseAcademicGrade() {
		return CourseAcademicGrade;
	}

	public void setCourseAcademicGrade(String courseAcademicGrade) {
		CourseAcademicGrade = courseAcademicGrade;
	}

	public CourseSupplementalAcademicGrade getCourseSupplementalAcademicGrade() {
		return CourseSupplementalAcademicGrade;
	}

	public void setCourseSupplementalAcademicGrade(CourseSupplementalAcademicGrade courseSupplementalAcademicGrade) {
		CourseSupplementalAcademicGrade = courseSupplementalAcademicGrade;
	}

	public String getCourseAcademicGradeStatusCode() {
		return CourseAcademicGradeStatusCode;
	}

	public void setCourseAcademicGradeStatusCode(String courseAcademicGradeStatusCode) {
		CourseAcademicGradeStatusCode = courseAcademicGradeStatusCode;
	}

	public String getCourseSubjectAbbreviation() {
		return CourseSubjectAbbreviation;
	}

	public void setCourseSubjectAbbreviation(String courseSubjectAbbreviation) {
		CourseSubjectAbbreviation = courseSubjectAbbreviation;
	}

	public String getCourseNumber() {
		return CourseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		CourseNumber = courseNumber;
	}

	public String getCourseTitle() {
		return CourseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		CourseTitle = courseTitle;
	}

	public Requirement getRequirement() {
		return Requirement;
	}

	public void setRequirement(Requirement requirement) {
		Requirement = requirement;
	}
}
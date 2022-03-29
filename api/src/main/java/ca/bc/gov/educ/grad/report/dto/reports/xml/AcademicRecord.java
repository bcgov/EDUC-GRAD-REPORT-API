package ca.bc.gov.educ.grad.report.dto.reports.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicRecord {

	private School School;
	private StudentLevel StudentLevel;
	private AcademicAward AcademicAward;
	private Map<String, AcademicSession> academicSessionMap = new HashMap<>();

	public void addAcademicSessionCourse(String sessionDate, Course course) {
		Integer sessionMonth = Integer.parseInt(StringUtils.substringAfter(sessionDate, "/"));
		Integer sessionYear = Integer.parseInt(StringUtils.substringBefore(sessionDate, "/"));
		String sessionName = sessionYear + "-" + sessionMonth;
		String sessionSchoolYear = sessionMonth < 9 ? (sessionYear - 1 + "-" + sessionYear) : (sessionYear + "-" + sessionYear + 1);
		AcademicSession session = academicSessionMap.get(sessionName);
		if(session == null) {
			session = new AcademicSession();
			session.getAcademicSessionDetail().setSessionName(sessionName);
			session.getAcademicSessionDetail().setSessionDesignator(sessionName);
			session.getAcademicSessionDetail().setSessionSchoolYear(sessionSchoolYear);
			academicSessionMap.put(sessionName, session);
		}
		session.getCourse().add(course);
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "AcademicSession")
	public List<AcademicSession> getAcademicSession() {
		List<AcademicSession> academicSession = new ArrayList<>();
		for(String key: academicSessionMap.keySet()) {
			AcademicSession session = academicSessionMap.get(key);
			academicSession.add(session);
		}
		return academicSession;
	}

	@JacksonXmlProperty(localName = "School")
	public School getSchool() {
		return School;
	}

	public void setSchool(School school) {
		School = school;
	}

	@JacksonXmlProperty(localName = "StudentLevel")
	public StudentLevel getStudentLevel() {
		return StudentLevel;
	}

	public void setStudentLevel(StudentLevel studentLevel) {
		StudentLevel = studentLevel;
	}

	@JacksonXmlProperty(localName = "AcademicAward")
	public AcademicAward getAcademicAward() {
		return AcademicAward;
	}

	public void setAcademicAward(AcademicAward academicAward) {
		AcademicAward = academicAward;
	}
}

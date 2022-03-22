package ca.bc.gov.educ.grad.report.dto.reports.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicRecord {
	private School School;
	private StudentLevel StudentLevel;
	private AcademicAward AcademicAward;
	private Map<String, AcademicSession> academicSessionMap = new HashMap<>();

	public void addAcademicSessionCourse(String sessionName, Course course) {
		AcademicSession session = academicSessionMap.get(sessionName);
		if(session == null) {
			session = new AcademicSession();
			session.getAcademicSessionDetail().setSessionName(sessionName);
			academicSessionMap.put(sessionName, session);
		}
		session.getCourse().add(course);
	}

	public List<AcademicSession> getAcademicSession() {
		List<AcademicSession> academicSession = new ArrayList<>();
		for(String key: academicSessionMap.keySet()) {
			AcademicSession session = academicSessionMap.get(key);
			academicSession.add(session);
		}
		return academicSession;
	}

	public School getSchool() {
		return School;
	}

	public void setSchool(School school) {
		School = school;
	}

	public StudentLevel getStudentLevel() {
		return StudentLevel;
	}

	public void setStudentLevel(StudentLevel studentLevel) {
		StudentLevel = studentLevel;
	}

	public AcademicAward getAcademicAward() {
		return AcademicAward;
	}

	public void setAcademicAward(AcademicAward academicAward) {
		AcademicAward = academicAward;
	}
}

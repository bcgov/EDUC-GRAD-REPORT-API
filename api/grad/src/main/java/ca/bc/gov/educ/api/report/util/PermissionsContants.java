package ca.bc.gov.educ.api.report.util;

public interface PermissionsContants {
	String _PREFIX = "#oauth2.hasAnyScope('";
	String _SUFFIX = "')";

	String STUDENT_ACHIEVEMENT_REPORT = _PREFIX + "CREATE_STUDENT_ACHIEVEMENT_REPORT" + _SUFFIX;
	String STUDENT_TRANSCRIPT_REPORT = _PREFIX + "CREATE_STUDENT_TRANSCRIPT_REPORT" + _SUFFIX;
	String STUDENT_CERTIFICATE = _PREFIX + "CREATE_STUDENT_CERTIFICATE" + _SUFFIX;
}

package ca.bc.gov.educ.grad.utils;

public interface PermissionsContants {
	String _PREFIX = "#oauth2.hasAnyScope('";
	String _SUFFIX = "')";

	String CREATE_OR_UPDATE_SIGNATURE_IMAGE = _PREFIX + "UPDATE_GRAD_GRADUATION_STATUS" + _SUFFIX;
	String READ_SIGNATURE_IMAGE_BY_CODE = _PREFIX + "READ_GRAD_STUDENT_CAREER_DATA" + _SUFFIX;
}

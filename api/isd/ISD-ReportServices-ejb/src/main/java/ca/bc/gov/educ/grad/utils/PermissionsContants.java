package ca.bc.gov.educ.grad.utils;

public interface PermissionsContants {
	String _PREFIX = "#oauth2.hasAnyScope('";
	String _SUFFIX = "')";

	String CREATE_OR_UPDATE_SIGNATURE_IMAGE = _PREFIX + "CREATE_OR_UPDATE_SIGNATURE_IMAGE" + _SUFFIX;
	String READ_SIGNATURE_IMAGE_BY_CODE = _PREFIX + "READ_SIGNATURE_IMAGE_BY_CODE" + _SUFFIX;
}

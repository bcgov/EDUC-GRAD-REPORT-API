package ca.bc.gov.educ.grad.report.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHelper {

	@Value("${validation.value.missing}")
	String missingValueString;

	@Value("${validation.value.unfound}")
	String unfoundValueString;

	@Autowired
	MessageSource messageSource;

	public String missingValue(String missingValue) {
		return String.format(missingValueString, missingValue);
	}
	
	public String unfoundValue(String unfoundType, String value) {
		return String.format(unfoundValueString, unfoundType, value);
	}

	public String getDefaultValue(String messageKey) {
		return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
	}
}

package ca.bc.gov.educ.grad.report.exception;

public class GradBusinessRuleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GradBusinessRuleException() {
		super("Unable to process request, due to validation errors.");
	}
	
	public GradBusinessRuleException(String errorMessage) {
		super(errorMessage);
	}

}

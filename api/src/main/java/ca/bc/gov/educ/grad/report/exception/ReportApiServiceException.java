package ca.bc.gov.educ.grad.report.exception;

public class ReportApiServiceException extends RuntimeException {
    private static final long serialVersionUID = -6418563091242776474L;

    public ReportApiServiceException(String msg, Throwable t) {
        super(msg, t);
    }
}

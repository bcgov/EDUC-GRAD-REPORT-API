package ca.bc.gov.educ.grad.report.exception;

import ca.bc.gov.educ.grad.report.exception.errors.ApiError;
import lombok.Getter;

/**
 * ConfirmationRequiredException
 */
public class ConfirmationRequiredException extends RuntimeException {
  /**
   * The Error.
   */
  @Getter
  private final ApiError error;

  public ConfirmationRequiredException(final ApiError error) {
    super(error.getMessage());
    this.error = error;
  }

}

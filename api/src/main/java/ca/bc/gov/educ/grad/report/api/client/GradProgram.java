package ca.bc.gov.educ.grad.report.api.client;

import java.io.Serializable;


public class GradProgram implements Serializable {
    private Code code = new Code();

    public Code getCode() {
        return code;
    }

    public void setCode(Code value) {
        this.code = value;
    }
}

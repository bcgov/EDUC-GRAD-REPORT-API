package ca.bc.gov.educ.grad.report.api.client;

import java.io.Serializable;


public class PaperType implements Serializable {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }
}

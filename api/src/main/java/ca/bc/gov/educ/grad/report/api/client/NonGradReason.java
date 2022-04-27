package ca.bc.gov.educ.grad.report.api.client;

import java.io.Serializable;


public class NonGradReason implements Serializable {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }
}

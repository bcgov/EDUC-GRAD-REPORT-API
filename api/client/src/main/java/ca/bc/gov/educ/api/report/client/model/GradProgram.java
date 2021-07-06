package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class GradProgram {
    private Code code;

    public Code getCode() {
        return code;
    }

    public void setCode(Code value) {
        this.code = value;
    }
}

package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pen {

    private String pen;
    private Object entityID;

    public String getPen() {
        return pen;
    }

    public void setPen(String value) {
        this.pen = value;
    }

    @JsonProperty("studentID")
    public Object getEntityID() {
        return entityID;
    }

    public void setEntityID(Object value) {
        this.entityID = value;
    }
}

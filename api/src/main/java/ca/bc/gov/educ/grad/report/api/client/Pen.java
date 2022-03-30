package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pen {

    private String pen;
    private String entityID;

    public String getPen() {
        return pen;
    }

    public void setPen(String value) {
        this.pen = value;
    }

    @JsonProperty("studentID")
    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String value) {
        this.entityID = value;
    }
}

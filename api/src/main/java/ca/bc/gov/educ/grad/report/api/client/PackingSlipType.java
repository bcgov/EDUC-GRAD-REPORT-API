package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;


public class PackingSlipType implements Serializable {

    private String name;
    private PaperType paperType;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @JsonDeserialize(as = PaperType.class)
    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType value) {
        this.paperType = value;
    }
}

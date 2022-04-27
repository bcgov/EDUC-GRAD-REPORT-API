package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;


public class PackingSlipOrderType implements Serializable {

    private String name;
    private PackingSlipType packingSlipType;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @JsonDeserialize(as = PackingSlipType.class)
    public PackingSlipType getPackingSlipType() {
        return packingSlipType;
    }

    public void setPackingSlipType(PackingSlipType value) {
        this.packingSlipType = value;
    }
}

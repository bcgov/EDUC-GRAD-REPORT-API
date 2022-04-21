package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;


public class OrderType implements Serializable {

    private String name;
    private CertificateType certificateType;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @JsonDeserialize(as = CertificateType.class)
    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType value) {
        this.certificateType = value;
    }
}

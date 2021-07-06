package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class OrderType {
    private String name;
    private CertificateType certificateType;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType value) {
        this.certificateType = value;
    }
}

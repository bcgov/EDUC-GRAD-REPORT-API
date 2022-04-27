package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;


public class CertificateType implements Serializable {

    private String reportName;
    private PaperType paperType;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String value) {
        this.reportName = value;
    }

    @JsonDeserialize(as = PaperType.class)
    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType value) {
        this.paperType = value;
    }
}

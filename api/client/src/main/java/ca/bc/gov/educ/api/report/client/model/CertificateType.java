package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class CertificateType {
    private String reportName;
    private PaperType paperType;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String value) {
        this.reportName = value;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType value) {
        this.paperType = value;
    }
}

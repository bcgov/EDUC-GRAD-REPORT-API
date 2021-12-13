package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class ReportRequest {
    private ReportOptions options;
    private ReportData reportData;

    @JsonProperty("options")
    public ReportOptions getOptions() { return options; }
    public void setOptions(ReportOptions value) { this.options = value; }

    @JsonProperty("data")
    public ReportData getData() { return reportData; }
    public void setData(ReportData value) { this.reportData = value; }
}



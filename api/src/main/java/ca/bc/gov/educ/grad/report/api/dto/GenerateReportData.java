package ca.bc.gov.educ.grad.report.api.dto;

import lombok.Data;

@Data
public class GenerateReportData {
    private ReportData data;
    private ReportOptions options;
}

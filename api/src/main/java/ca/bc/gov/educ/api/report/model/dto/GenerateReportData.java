package ca.bc.gov.educ.api.report.model.dto;

import lombok.Data;

@Data
public class GenerateReportData {
    private ReportData data;
    private ReportOptions options;
}

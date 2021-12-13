package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.dto.ReportData;

public class ReportRequestDataThreadLocal {

    private static final InheritableThreadLocal<ReportData> thread = new InheritableThreadLocal<ReportData>();

    public static ReportData getGenerateReportData() {
        return thread.get();
    }

    public static void setGenerateReportData(ReportData request) {
        thread.set(request);
    }

    public static void removeGenerateReportData() {
        thread.remove();
    }
}

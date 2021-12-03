package ca.bc.gov.educ.grad.dto.adaptor.dao.utils;

import ca.bc.gov.educ.grad.dto.ReportData;

public class TRAXThreadDataUtility {

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

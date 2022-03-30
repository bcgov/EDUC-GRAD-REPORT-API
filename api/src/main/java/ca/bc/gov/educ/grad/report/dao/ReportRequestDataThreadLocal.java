package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.XmlReportData;

public class ReportRequestDataThreadLocal {

    private static final InheritableThreadLocal<ReportData> reportDataThread = new InheritableThreadLocal<ReportData>();
    private static final InheritableThreadLocal<XmlReportData> xmlReportDataThread = new InheritableThreadLocal<XmlReportData>();

    public static ReportData getGenerateReportData() {
        return reportDataThread.get();
    }

    public static void setGenerateReportData(ReportData request) {
        reportDataThread.set(request);
    }

    public static void removeGenerateReportData() {
        reportDataThread.remove();
    }

    public static XmlReportData getXmlReportData() {
        return xmlReportDataThread.get();
    }

    public static void setXmlReportData(XmlReportData request) {
        xmlReportDataThread.set(request);
    }

    public static void removeXmlReportData() {
        xmlReportDataThread.remove();
    }
}

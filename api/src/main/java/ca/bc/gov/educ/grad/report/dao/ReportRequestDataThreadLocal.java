package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.XmlReportData;

public class ReportRequestDataThreadLocal {

    private static final InheritableThreadLocal<ReportData> reportDataThread = new InheritableThreadLocal<ReportData>();
    private static final InheritableThreadLocal<XmlReportData> xmlReportDataThread = new InheritableThreadLocal<XmlReportData>();
    private static final InheritableThreadLocal<String> currentUserThread = new InheritableThreadLocal<String>();
    private static final InheritableThreadLocal<String> transactionThread = new InheritableThreadLocal<String>();
    private static final ThreadLocal<String> signatureImageUrlThreadLocal = new ThreadLocal<String>();

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

    public static String getCurrentUser() {
        return currentUserThread.get();
    }

    public static void setCurrentUser(String username) {
        currentUserThread.set(username);
    }

    public static void removeCurrentUser() {
        currentUserThread.remove();
    }

    public static void setCorrelationID(String correlationID){
        transactionThread.set(correlationID);
    }

    public static String getCorrelationID() {
        return transactionThread.get();
    }

    public static void removeCorrelationID() {
        transactionThread.remove();
    }

    public static String getSignatureImageUrl() {
        return signatureImageUrlThreadLocal.get();
    }

    public static void setSignatureImageUrl(String imageUrl) {
        if (imageUrl == null) {
            signatureImageUrlThreadLocal.remove();
        } else {
            signatureImageUrlThreadLocal.set(imageUrl);
        }
    }

    public static void clear() {
        removeGenerateReportData();
        removeXmlReportData();
        removeCurrentUser();
        setSignatureImageUrl(null);
    }
}

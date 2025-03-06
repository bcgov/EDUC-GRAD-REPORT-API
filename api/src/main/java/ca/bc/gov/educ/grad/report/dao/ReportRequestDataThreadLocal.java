package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.XmlReportData;

public class ReportRequestDataThreadLocal {

    private static final InheritableThreadLocal<ReportData> reportDataThread = new InheritableThreadLocal<ReportData>();
    private static final InheritableThreadLocal<XmlReportData> xmlReportDataThread = new InheritableThreadLocal<XmlReportData>();
    private static final InheritableThreadLocal<String> user = new InheritableThreadLocal<String>();
    private static final InheritableThreadLocal<String> transactionThread = new InheritableThreadLocal<String>();
    private static final ThreadLocal<String> signatureImageUrlThreadLocal = new ThreadLocal<String>();
    private static final InheritableThreadLocal<String> requestSource = new InheritableThreadLocal<String>();
    public static ReportData getReportData() {
        return reportDataThread.get();
    }

    public static void setReportData(ReportData request) {
        reportDataThread.set(request);
    }

    public static void removeReportData() {
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
        return user.get();
    }

    public static void setCurrentUser(String username) {
        user.set(username);
    }

    public static void setCorrelationID(String correlationID){
        transactionThread.set(correlationID);
    }

    public static String getCorrelationID() {
        return transactionThread.get();
    }

    /**
     * Set the requestSource for this thread
     *
     * @param reqSource
     */
    public static void setRequestSource(String reqSource){
        requestSource.set(reqSource);
    }
    /**
     * Get the requestSource for this thread
     *
     * @return the requestSource, or null if it is unknown.
     */
    public static String getRequestSource() {
        return requestSource.get();
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
        removeReportData();
        removeXmlReportData();
        user.remove();
        transactionThread.remove();
        requestSource.remove();
        setSignatureImageUrl(null);
    }
}

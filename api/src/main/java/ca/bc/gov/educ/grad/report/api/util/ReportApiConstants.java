package ca.bc.gov.educ.grad.report.api.util;

import java.util.Date;

public class ReportApiConstants {
    //API end-point Mapping constants
    public static final String API_ROOT_MAPPING = "";
    public static final String API_VERSION = "v1";
    public static final String REPORT_API_ROOT_MAPPING = "/api/" + API_VERSION + "/reports";
    public static final String STUDENT_ACHIEVEMENT_REPORT = "/achievementreport";
    public static final String STUDENT_TRANSCRIPT_REPORT = "/transcriptreport";
    public static final String STUDENT_CERTIFICATE = "/certificate";

    //Attribute Constants
    public static final String PEN_ATTRIBUTE = "pen";

    //Default Attribute value constants
    public static final String DEFAULT_CREATED_BY = "ReportAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "ReportAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    //Default Date format constants
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

    //Application Properties Constants
    public static final String ENDPOINT_GET_PDF_FROM_HTML_URL = "${endpoint.weasyprint.getPDFfromHTML}";
    public static final String ENDPOINT_GET_PDF_URL = "${endpoint.cdogs.getPDF}";
    public static final String ENDPOINT_GET_TOKEN_URL = "${endpoint.cdogs.getToken}";
}

package ca.bc.gov.educ.grad.report.api.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class ReportApiConstants {
    //API end-point Mapping constants
    public static final String API_ROOT_MAPPING = "";
    public static final String API_VERSION = "v1";
    public static final String REPORT_API_ROOT_MAPPING = "/api/" + API_VERSION + "/reports";
    public static final String STUDENT_ACHIEVEMENT_REPORT = "/achievementreport";
    public static final String STUDENT_TRANSCRIPT_REPORT = "/transcriptreport";
    public static final String STUDENT_XML_TRANSCRIPT_REPORT = "/xmltranscriptreport";
    public static final String STUDENT_CERTIFICATE = "/certificate";
    public static final String PACKING_SLIP = "/packingslip";
    public static final String SCHOOL_DISTRIBUTION = "/schooldistribution";
    public static final String SCHOOL_GRADUATION = "/schoolgraduation";
    public static final String STUDENT_NON_GRAD = "/studentnongrad";

    //Attribute Constants
    public static final String PEN_ATTRIBUTE = "pen";

    //Default Attribute value constants
    public static final String DEFAULT_CREATED_BY = "ReportAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "ReportAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    //Default Date format constants
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

    @Value("${endpoint.grad-student-api.read-grad-student-record.url}")
    private String readGradStudentRecord;

    @Value("${endpoint.grad-student-api.read-grad-student-record-pen.url}")
    private String readGradStudentRecordPen;

    @Value("${endpoint.pen-student-api.by-pen.url}")
    private String penStudentApiByPenUrl;
}

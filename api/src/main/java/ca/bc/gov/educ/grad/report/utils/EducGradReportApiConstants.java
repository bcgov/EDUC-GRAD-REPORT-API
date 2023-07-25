package ca.bc.gov.educ.grad.report.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EducGradReportApiConstants {

    public static final String CORRELATION_ID = "correlationID";
    //API end-point Mapping constants
    public static final String API_ROOT_MAPPING = "";
    public static final String API_VERSION = "v1";
    public static final String API_ROOT_CONTEXT_MAPPING = "/api/" + API_VERSION;
    public static final String GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING = API_ROOT_CONTEXT_MAPPING + "/reports/signatures" ;
    public static final String GET_SIGNATURE_IMAGE_BY_CODE = "/{signCode}";
    public static final String SAVE_SIGNATURE_IMAGE = "/save";
    public static final String SAVE_SIGNATURE_BLOCK_TYPE_CODE = "/saveSignatureBlockTypeCode";
    public static final String GET_SIGNATURE_IMAGE = "/get" + GET_SIGNATURE_IMAGE_BY_CODE;
    public static final String GET_SIGNATURE_IMAGES = "/get/all";
    public static final String GET_SIGNATURE_BLOCK_TYPE_CODES = "/getSignatureBlockTypeCodes";
    public static final String GET_SIGNATURE_BLOCK_TYPE_CODE = "/getSignatureBlockTypeCode/{signBlockTypeCode}";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TRAX_DATE_FORMAT = "yyyyMM";

    @Value("${endpoint.educ-grad-trax-api.get-district-details.url}")
    private String districtDetails;

    @Value("${endpoint.educ-grad-trax-api.school-by-min-code.url}")
    private String schoolDetails;

    @Value("${endpoint.educ-grad-trax-api.country-by-code.url}")
    private String countryDetails;

    @Value("${endpoint.grad-program-api.program-name-by-program_code.url}")
    private String graduationProgram;

    // Splunk LogHelper Enabled
    @Value("${splunk.log-helper.enabled}")
    private boolean splunkLogHelperEnabled;

}

package ca.bc.gov.educ.grad.report.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class EducGradSignatureImageApiConstants {
    //API end-point Mapping constants
    public static final String API_ROOT_MAPPING = "";
    public static final String API_VERSION = "v1";
    public static final String API_ROOT_CONTEXT_MAPPING = "/api/" + API_VERSION;
    public static final String GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING = API_ROOT_CONTEXT_MAPPING + "/signatures" ;
    public static final String GET_SIGNATURE_IMAGE_BY_CODE = "/{signCode}";
    public static final String SAVE_SIGNATURE_IMAGE = "/save";
    public static final String SAVE_SIGNATURE_BLOCK_TYPE_CODE = "/saveSignatureBlockTypeCode";
    public static final String GET_SIGNATURE_IMAGE = "/get" + GET_SIGNATURE_IMAGE_BY_CODE;
    public static final String GET_SIGNATURE_IMAGES = "/get/all";
    public static final String GET_SIGNATURE_BLOCK_TYPE_CODES = "/getSignatureBlockTypeCodes";
    public static final String GET_SIGNATURE_BLOCK_TYPE_CODE = "/getSignatureBlockTypeCode";

    //Default Date format constants
    public static final String DEFAULT_CREATED_BY = "GradSignatureImagesAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "GradSignatureImagesAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TRAX_DATE_FORMAT = "yyyyMM";

}

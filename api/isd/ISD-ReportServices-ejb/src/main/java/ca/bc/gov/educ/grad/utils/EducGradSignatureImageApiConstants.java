package ca.bc.gov.educ.grad.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class EducGradSignatureImageApiConstants {
    //API end-point Mapping constants
    public static final String API_ROOT_MAPPING = "";
    public static final String API_VERSION = "v1";
    public static final String GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING = "/api/" + API_VERSION + "/signatures" ;
    public static final String GET_SIGNATURE_IMAGE_BY_CODE = "/{signCode}";
    public static final String SAVE_SIGNATURE_IMAGE = "/save";

    //Default Date format constants
    public static final String DEFAULT_CREATED_BY = "GradSignatureImagesAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "GradSignatureImagesAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TRAX_DATE_FORMAT = "yyyyMM";

    public static String GRAD_SIGNATURE_IMAGE_BASE_URL;
    public static String GRAD_SIGNATURE_IMAGE_BY_CODE_URL;

    //Endpoints
    @Value("${endpoint.grad-report-api.signature-image-base.url}")
    public void setSignatureImageBaseUrl(String signatureImageBAseUrl) {
        GRAD_SIGNATURE_IMAGE_BASE_URL = signatureImageBAseUrl;
    }
    @Value("${endpoint.grad-report-api.signature-image-by-code.url}")
    public void setSignatureImageByCodeUrl(String signatureImageByCodeUrl) {
        GRAD_SIGNATURE_IMAGE_BY_CODE_URL = signatureImageByCodeUrl;
    }

}

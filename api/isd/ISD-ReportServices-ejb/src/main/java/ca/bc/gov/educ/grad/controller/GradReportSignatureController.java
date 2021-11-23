package ca.bc.gov.educ.grad.controller;

import ca.bc.gov.educ.grad.dto.GragReportSignatureImage;
import ca.bc.gov.educ.grad.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.service.GradReportCodeService;
import ca.bc.gov.educ.grad.service.GradReportSignatureService;
import ca.bc.gov.educ.grad.utils.EducGradSignatureImageApiConstants;
import ca.bc.gov.educ.grad.utils.GradValidation;
import ca.bc.gov.educ.grad.utils.PermissionsContants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(EducGradSignatureImageApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING)
@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "API for Certificate Signatures endpoints.", description = "This API is for Certificate Signatures endpoints.", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_STUDENT_UNGRAD_REASONS_DATA","READ_GRAD_STUDENT_CAREER_DATA"})})
public class GradReportSignatureController extends BaseController {

    private static final String CLASS_NAME = GradReportSignatureController.class.getName();
    private static final Logger logger = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    GradReportSignatureService gradReportSignatureService;
    @Autowired
    GradReportCodeService gradReportCodeService;
    
    @Autowired
    GradValidation validation;
    
    @GetMapping(EducGradSignatureImageApiConstants.GET_SIGNATURE_IMAGE_BY_CODE)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_IMAGE_BY_CODE)
    @Operation(summary = "Return Signature Image binary", description = "Retrieve Signature Image binary by signature code", tags = { "Signature Image" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public byte[] extractSignatureImageByCode(@PathVariable String signCode) {
        String _m = String.format("extractSignatureImageByCode(String %s)", signCode);
        logger.debug("<{}.{}", _m, CLASS_NAME);
    	logRequest();
        GragReportSignatureImage signatureImage = gradReportSignatureService.getSignatureImageByCode(signCode);
        return signatureImage.getSignatureContent();
    }

    @GetMapping(EducGradSignatureImageApiConstants.GET_SIGNATURE_IMAGE)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_IMAGE_BY_CODE)
    @Operation(summary = "Return Signature Image Object", description = "Retrieve Signature Object by signature code", tags = { "Signature Image" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public GragReportSignatureImage getSignatureImageByCode(@PathVariable String signCode) {
        String _m = String.format("getSignatureImageByCode(String %s)", signCode);
        logger.debug("<{}.{}", _m, CLASS_NAME);
        logRequest();
        return gradReportSignatureService.getSignatureImageByCode(signCode);
    }

    @GetMapping(EducGradSignatureImageApiConstants.GET_SIGNATURE_IMAGES)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_IMAGE_BY_CODE)
    @Operation(summary = "Return Signature Images", description = "Retrieve Signature Objects", tags = { "Signature Images" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<GragReportSignatureImage> getSignatureImages() {
        String _m = String.format("getSignatureImages()");
        logger.debug("<{}.{}", _m, CLASS_NAME);
        logRequest();
        return gradReportSignatureService.getSignatureImages();
    }

    @PostMapping (EducGradSignatureImageApiConstants.SAVE_SIGNATURE_IMAGE)
    @PreAuthorize(PermissionsContants.CREATE_OR_UPDATE_SIGNATURE_IMAGE)
    @Operation(summary = "Create Signature", description = "Create Signature Image File", tags = { "Signature Image" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public GragReportSignatureImage saveGragReportSignatureImage(@RequestBody GragReportSignatureImage signatureImage) {
        String _m = String.format("saveGragReportSignatureImage(String %s)", signatureImage.getGradReportSignatureCode());
        logger.debug("<{}.{}", _m, CLASS_NAME);
        logRequest();
        validation.requiredField(signatureImage.getGradReportSignatureCode(), "Signature Code");
        validation.requiredField(signatureImage.getSignatureContent(), "Signature Content");
        return gradReportSignatureService.saveSignatureImage(signatureImage);
    }

    @GetMapping(EducGradSignatureImageApiConstants.GET_SIGNATURE_BLOCK_TYPE_CODE)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_BLOCK_TYPE_CODE)
    @Operation(summary = "Return Signature Block Types", description = "Retrieve Signature Block Types", tags = { "Signature Block Types" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public SignatureBlockTypeCode getSignatureBlockTypeCode(@PathVariable String signBlockTypeCode) {
        String _m = String.format("getSignatureBlockTypeCode(%s)", signBlockTypeCode);
        logger.debug("<{}.{}", _m, CLASS_NAME);
        logRequest();
        return gradReportCodeService.getSignatureBlockTypeCode(signBlockTypeCode);
    }

    @GetMapping(EducGradSignatureImageApiConstants.GET_SIGNATURE_BLOCK_TYPE_CODES)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_BLOCK_TYPE_CODE)
    @Operation(summary = "Return Signature Block Type", description = "Retrieve Signature Block Type", tags = { "Signature Block Type" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<SignatureBlockTypeCode> getSignatureBlockTypeCodes() {
        String _m = String.format("getSignatureBlockTypeCodes()");
        logger.debug("<{}.{}", _m, CLASS_NAME);
        logRequest();
        return gradReportCodeService.getSignatureBlockTypeCodes();
    }

    @PostMapping (EducGradSignatureImageApiConstants.SAVE_SIGNATURE_BLOCK_TYPE_CODE)
    @PreAuthorize(PermissionsContants.CREATE_OR_UPDATE_SIGNATURE_BLOCK_TYPE_CODE)
    @Operation(summary = "Save Signature Block Type Code", description = "Save Signature Block Type Code", tags = { "Signature Block Type" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public SignatureBlockTypeCode saveSignatureBlockTypeCode(@RequestBody SignatureBlockTypeCode code) {
        String _m = String.format("saveSignatureBlockTypeCode(String %s)", code.getSignatureBlockTypeCode());
        logger.debug("<{}.{}", _m, CLASS_NAME);
        logRequest();
        validation.requiredField(code.getSignatureBlockTypeCode(), "Signature Block Type Code");
        validation.requiredField(code.getCode(), "Signature Block Type Code Code");
        validation.requiredField(code.getLabel(), "Signature Block Type Code Label");
        validation.requiredField(code.getDescription(), "Signature Block Type Code Description");
        return gradReportCodeService.saveSignatureBlockTypeCode(code);
    }

}

package ca.bc.gov.educ.grad.report.api.controller;

import ca.bc.gov.educ.grad.report.api.util.PermissionsContants;
import ca.bc.gov.educ.grad.report.dto.GradReportSignatureImage;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import ca.bc.gov.educ.grad.report.utils.GradValidation;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@CrossOrigin
@RestController
@RequestMapping(EducGradReportApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING)
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
    
    @GetMapping(EducGradReportApiConstants.GET_SIGNATURE_IMAGE_BY_CODE)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_IMAGE_BY_CODE)
    @Operation(summary = "Return Signature Image binary", description = "Retrieve Signature Image binary by signature code", tags = { "Signature Image" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public byte[] extractSignatureImageByCode(@PathVariable String signCode) {
        String methodName = String.format("extractSignatureImageByCode(String %s)", signCode);
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        GradReportSignatureImage signatureImage = gradReportSignatureService.getSignatureImageByCode(signCode);
        return signatureImage == null ? new byte[0] : signatureImage.getSignatureContent();
    }

    @GetMapping(EducGradReportApiConstants.GET_SIGNATURE_IMAGE)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_IMAGE_BY_CODE)
    @Operation(summary = "Return Signature Image Object", description = "Retrieve Signature Object by signature code", tags = { "Signature Image" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public GradReportSignatureImage getSignatureImageByCode(@PathVariable String signCode, @RequestHeader(name="Authorization") String accessToken) {
        String methodName = String.format("getSignatureImageByCode(String %s)", signCode);
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return gradReportSignatureService.getSignatureImageByCode(signCode, accessToken.replace("Bearer ", ""));
    }

    @GetMapping(EducGradReportApiConstants.GET_SIGNATURE_IMAGES)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_IMAGE_BY_CODE)
    @Operation(summary = "Return Signature Images", description = "Retrieve Signature Objects", tags = { "Signature Images" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<GradReportSignatureImage> getSignatureImages(@RequestHeader(name="Authorization") String accessToken) {
        String methodName = "getSignatureImages()";
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return gradReportSignatureService.getSignatureImages(accessToken.replace("Bearer ", ""));
    }

    @PostMapping (EducGradReportApiConstants.SAVE_SIGNATURE_IMAGE)
    @PreAuthorize(PermissionsContants.CREATE_OR_UPDATE_SIGNATURE_IMAGE)
    @Operation(summary = "Create Signature", description = "Create Signature Image File", tags = { "Signature Image" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public GradReportSignatureImage saveGragReportSignatureImage(@RequestBody GradReportSignatureImage signatureImage) {
        String methodName = String.format("saveGragReportSignatureImage(String %s)", signatureImage.getGradReportSignatureCode());
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        logRequest(signatureImage);
        validation.requiredField(signatureImage.getGradReportSignatureCode(), "Signature Code");
        validation.requiredField(signatureImage.getSignatureContent(), "Signature Content");
        return gradReportSignatureService.saveSignatureImage(signatureImage);
    }

    @GetMapping(EducGradReportApiConstants.GET_SIGNATURE_BLOCK_TYPE_CODE)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_BLOCK_TYPE_CODE)
    @Operation(summary = "Return Signature Block Types", description = "Retrieve Signature Block Types", tags = { "Signature Block Types" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public SignatureBlockTypeCode getSignatureBlockTypeCode(@PathVariable String signBlockTypeCode) {
        String methodName = String.format("getSignatureBlockTypeCode(%s)", signBlockTypeCode);
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return gradReportCodeService.getSignatureBlockTypeCode(signBlockTypeCode);
    }

    @GetMapping(EducGradReportApiConstants.GET_SIGNATURE_BLOCK_TYPE_CODES)
    @PreAuthorize(PermissionsContants.READ_SIGNATURE_BLOCK_TYPE_CODE)
    @Operation(summary = "Return Signature Block Type", description = "Retrieve Signature Block Type", tags = { "Signature Block Type" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public List<SignatureBlockTypeCode> getSignatureBlockTypeCodes() {
        String methodName = "getSignatureBlockTypeCodes()";
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return gradReportCodeService.getSignatureBlockTypeCodes();
    }

    @PostMapping (EducGradReportApiConstants.SAVE_SIGNATURE_BLOCK_TYPE_CODE)
    @PreAuthorize(PermissionsContants.CREATE_OR_UPDATE_SIGNATURE_BLOCK_TYPE_CODE)
    @Operation(summary = "Save Signature Block Type Code", description = "Save Signature Block Type Code", tags = { "Signature Block Type" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public SignatureBlockTypeCode saveSignatureBlockTypeCode(@RequestBody SignatureBlockTypeCode code) {
        String methodName = String.format("saveSignatureBlockTypeCode(String %s)", code.getSignatureBlockTypeCode());
        logger.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        logRequest(code);
        validation.requiredField(code.getSignatureBlockTypeCode(), "Signature Block Type Code");
        validation.requiredField(code.getCode(), "Signature Block Type Code Code");
        validation.requiredField(code.getLabel(), "Signature Block Type Code Label");
        validation.requiredField(code.getDescription(), "Signature Block Type Code Description");
        return gradReportCodeService.saveSignatureBlockTypeCode(code);
    }

}

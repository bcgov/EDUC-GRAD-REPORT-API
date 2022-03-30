package ca.bc.gov.educ.grad.report.api.controller;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.XmlReportRequest;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.util.PermissionsContants;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping (ReportApiConstants.REPORT_API_ROOT_MAPPING)
@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "API for Report Generation", description = "This API is for Report Generation", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_STUDENT_COURSE_DATA"})})
public class ReportController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    GradReportService reportService;
    
    @PostMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "Generate Student Achievement Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentAchievementReport(@RequestBody ReportRequest report) {
        logger.debug("getStudentAchievementReport");
        logRequest();
        return reportService.getStudentAchievementReport(report);
    }

    /**
     * @deprecated
     * The end point duplicates the one above and is used for compatibility only.
     * Please delete it after other APIs fix the URL
    **/
    @PostMapping (ReportApiConstants.STUDENT_ACHV_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "@deprecated -- Generate Student Achievement Report -- @deprecated", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentAchvReport(@RequestBody ReportRequest report) {
        logger.debug("getStudentAchvReport");
        logRequest();
        return reportService.getStudentAchievementReport(report);
    }

    @PostMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentTranscriptReport(@RequestBody ReportRequest report) {
        logger.debug("getStudentTranscriptReport");
        logRequest();
        return reportService.getStudentTranscriptReport(report);
    }

    @PostMapping (ReportApiConstants.STUDENT_XML_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_XML_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentXmlTranscriptReport(@RequestBody XmlReportRequest report) {
        logger.debug("getStudentTranscriptReport");
        logRequest();
        OAuth2AuthenticationDetails auth = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String accessToken = auth.getTokenValue();
        report.getData().setAccessToken(accessToken);
        return reportService.getStudentXmlTranscriptReport(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_CERTIFICATE)
    @PreAuthorize(PermissionsContants.STUDENT_CERTIFICATE)
    @Operation(summary = "Generate Student Certificate", description = "Generate Student Certificate", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentCertificate(@RequestBody ReportRequest report) {
        logger.debug("getStudentCertificate");
        logRequest();
        return reportService.getStudentCertificateReport(report);
    }

    @PostMapping (ReportApiConstants.PACKING_SLIP)
    @PreAuthorize(PermissionsContants.PACKING_SLIP)
    @Operation(summary = "Generate Packing Slip", description = "Generate Packing Slip", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getPackingSlip(@RequestBody ReportRequest report) {
        logger.debug("getPackingSlip");
        logRequest();
        return reportService.getPackingSlipReport(report);
    }

    @PostMapping (ReportApiConstants.SCHOOL_DISTRIBUTION)
    @PreAuthorize(PermissionsContants.SCHOOL_DISTRIBUTION)
    @Operation(summary = "Generate School Distribution Report", description = "Generate School Distribution Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolDistribution(@RequestBody ReportRequest report) {
        logger.debug("getSchoolDistribution");
        logRequest();
        return reportService.getSchoolDistributionReport(report);
    }
}

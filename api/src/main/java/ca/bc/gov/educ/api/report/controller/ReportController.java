package ca.bc.gov.educ.api.report.controller;

import ca.bc.gov.educ.api.report.model.dto.GenerateReportData;
import ca.bc.gov.educ.api.report.service.ReportService;
import ca.bc.gov.educ.api.report.util.PermissionsContants;
import ca.bc.gov.educ.api.report.util.ReportApiConstants;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
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
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping (ReportApiConstants.REPORT_API_ROOT_MAPPING)
@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "API for Report Generation", description = "This API is for Report Generation", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_STUDENT_COURSE_DATA"})})
public class ReportController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;
    
    @PostMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "Generate Student Achievement Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity getStudentAchievementReport(@RequestBody GenerateReportRequest report) {
        logger.debug("getStudentAchievementReport");
        logRequest();
        return reportService.getStudentAchievementReport(report);
    }

    @PostMapping (ReportApiConstants.STUDENT_ACHV_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "Generate Student Achievement Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity getStudentAchvReport(@RequestBody GenerateReportData reportData) {
        logger.debug("getStudentAchievementReport");
        logRequest();
        return reportService.getStudentAchvReport(reportData);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity getStudentTranscriptReport(@RequestBody GenerateReportRequest report) {
        logger.debug("getStudentTranscriptReport");
        logRequest();
        return reportService.getStudentTranscriptReport(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_CERTIFICATE)
    @PreAuthorize(PermissionsContants.STUDENT_CERTIFICATE)
    @Operation(summary = "Generate Student Certificate", description = "Generate Student Certificate", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity getStudentCertificate(@RequestBody GenerateReportRequest report) {
        logger.debug("getStudentCertificate");
        logRequest();
        return reportService.getStudentCertificateReport(report);
    }
}

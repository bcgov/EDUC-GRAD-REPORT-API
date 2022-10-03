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
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping (ReportApiConstants.REPORT_API_ROOT_MAPPING)
@OpenAPIDefinition(info = @Info(title = "API for Report Generation", description = "This API is for Report Generation", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_STUDENT_COURSE_DATA"})})
public class ReportController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    GradReportService reportService;
    
    @PostMapping (ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "Generate Student Achievement Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentAchievementReport(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getStudentAchievementReport");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getStudentAchievementReport(report);
    }

    @PostMapping (ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentTranscriptReport(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getStudentTranscriptReport");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getStudentTranscriptReport(report);
    }

    @PostMapping (ReportApiConstants.STUDENT_XML_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_XML_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentXmlTranscriptReport(@RequestBody XmlReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getStudentTranscriptReport");
        logRequest();
        report.getData().setAccessToken(accessToken.replace("Bearer ", ""));
        return reportService.getStudentXmlTranscriptReport(report);
    }
    
    @PostMapping (ReportApiConstants.STUDENT_CERTIFICATE)
    @PreAuthorize(PermissionsContants.STUDENT_CERTIFICATE)
    @Operation(summary = "Generate Student Certificate", description = "Generate Student Certificate", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentCertificate(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getStudentCertificate");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getStudentCertificateReport(report);
    }

    @PostMapping (ReportApiConstants.PACKING_SLIP)
    @PreAuthorize(PermissionsContants.PACKING_SLIP)
    @Operation(summary = "Generate Packing Slip", description = "Generate Packing Slip", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getPackingSlip(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getPackingSlip");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getPackingSlipReport(report);
    }

    @PostMapping (ReportApiConstants.SCHOOL_DISTRIBUTION)
    @PreAuthorize(PermissionsContants.SCHOOL_DISTRIBUTION)
    @Operation(summary = "Generate School Distribution Report", description = "Generate School Distribution Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolDistribution(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getSchoolDistribution");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getSchoolDistributionReport(report);
    }

    @PostMapping (ReportApiConstants.SCHOOL_GRADUATION)
    @PreAuthorize(PermissionsContants.SCHOOL_GRADUATION)
    @Operation(summary = "Generate School Graduation Report", description = "Generate School Graduation Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolGraduation(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getSchoolGraduation");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getSchoolGraduationReport(report);
    }

    @PostMapping (ReportApiConstants.SCHOOL_NON_GRADUATION)
    @PreAuthorize(PermissionsContants.SCHOOL_NON_GRADUATION)
    @Operation(summary = "Generate School Non-Graduates Report", description = "Generate School Non-Graduates Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolNonGraduation(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getSchoolNonGraduation");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getSchoolNonGraduationReport(report);
    }

    @PostMapping (ReportApiConstants.STUDENT_NON_GRAD)
    @PreAuthorize(PermissionsContants.STUDENT_NON_GRAD)
    @Operation(summary = "Generate Student NonGraduate Requirements Report", description = "Generate Student NonGraduate Requirements Report", tags = { "Report" })
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentNonGrad(@RequestBody ReportRequest report, @RequestHeader(name="Authorization") String accessToken) {
        logger.debug("getStudentNonGrad");
        logRequest();
        setAccessToken(report, accessToken);
        return reportService.getStudentNonGradReport(report);
    }

    private void setAccessToken(ReportRequest report, String accessToken) {
        report.getData().setAccessToken(accessToken.replace("Bearer ", ""));
    }
}

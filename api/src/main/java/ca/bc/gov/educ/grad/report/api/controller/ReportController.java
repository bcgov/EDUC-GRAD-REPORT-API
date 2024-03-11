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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(ReportApiConstants.REPORT_API_ROOT_MAPPING)
@OpenAPIDefinition(info = @Info(title = "API for Report Generation", description = "This API is for Report Generation", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_STUDENT_COURSE_DATA"})})
public class ReportController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    GradReportService reportService;

    @PostMapping(ReportApiConstants.STUDENT_ACHIEVEMENT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_ACHIEVEMENT_REPORT)
    @Operation(summary = "Generate Student Achievement Report", description = "Generate Student Achievement Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentAchievementReport(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getStudentAchievementReport");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getStudentAchievementReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.STUDENT_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentTranscriptReport(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getStudentTranscriptReport");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getStudentTranscriptReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.STUDENT_XML_TRANSCRIPT_REPORT)
    @PreAuthorize(PermissionsContants.STUDENT_XML_TRANSCRIPT_REPORT)
    @Operation(summary = "Generate Student Transcript Report", description = "Generate Student Transcript Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentXmlTranscriptReport(@RequestBody XmlReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getStudentTranscriptReport");
        logRequest(report);
        report.getData().setAccessToken(accessToken.replace("Bearer ", ""));
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getStudentXmlTranscriptReport(report);
            return handleBinaryResponse(resultBinary, reportFile, MediaType.APPLICATION_XML);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.STUDENT_CERTIFICATE)
    @PreAuthorize(PermissionsContants.STUDENT_CERTIFICATE)
    @Operation(summary = "Generate Student Certificate", description = "Generate Student Certificate", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentCertificate(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getStudentCertificate");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getStudentCertificateReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.PACKING_SLIP)
    @PreAuthorize(PermissionsContants.PACKING_SLIP)
    @Operation(summary = "Generate Packing Slip", description = "Generate Packing Slip", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getPackingSlip(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getPackingSlip");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getPackingSlipReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.SCHOOL_DISTRIBUTION)
    @PreAuthorize(PermissionsContants.SCHOOL_DISTRIBUTION)
    @Operation(summary = "Generate School Distribution Report", description = "Generate School Distribution Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolDistribution(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getSchoolDistribution");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getSchoolDistributionReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.SCHOOL_DISTRIBUTION_YEAR_END)
    @PreAuthorize(PermissionsContants.SCHOOL_DISTRIBUTION)
    @Operation(summary = "Generate School Distribution Report", description = "Generate School Distribution Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolDistributionYearEnd(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getSchoolDistributionYearEnd");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getSchoolDistributionReportYearEnd(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.DISTRICT_DISTRIBUTION_YEAR_END)
    @PreAuthorize(PermissionsContants.SCHOOL_DISTRIBUTION)
    @Operation(summary = "Generate District Distribution Report", description = "Generate District Distribution Year End Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getDistrictDistributionYearEnd(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getDistrictDistributionYearEnd");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getDistrictDistributionReportYearEnd(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.DISTRICT_DISTRIBUTION_YEAR_END_NONGRAD)
    @PreAuthorize(PermissionsContants.SCHOOL_DISTRIBUTION)
    @Operation(summary = "Generate District Distribution Report", description = "Generate District Distribution Year End Non Grad Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getDistrictDistributionYearEndNonGrad(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getDistrictDistributionYearEndNonGrad");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getDistrictDistributionReportYearEndNonGrad(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.SCHOOL_LABEL)
    @PreAuthorize(PermissionsContants.SCHOOL_LABEL)
    @Operation(summary = "Generate School Label Report", description = "Generate School Label Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolLabel(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getSchoolLabel");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getSchoolLabelReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.SCHOOL_GRADUATION)
    @PreAuthorize(PermissionsContants.SCHOOL_GRADUATION)
    @Operation(summary = "Generate School Graduation Report", description = "Generate School Graduation Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolGraduation(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getSchoolGraduation");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getSchoolGraduationReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.SCHOOL_NON_GRADUATION)
    @PreAuthorize(PermissionsContants.SCHOOL_NON_GRADUATION)
    @Operation(summary = "Generate School Non-Graduates Report", description = "Generate School Non-Graduates Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getSchoolNonGraduation(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getSchoolNonGraduation");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getSchoolNonGraduationReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.STUDENT_NON_GRAD_PROJECTED)
    @PreAuthorize(PermissionsContants.STUDENT_NON_GRAD)
    @Operation(summary = "Generate Student NonGraduate Projected Requirements Report", description = "Generate Student NonGraduate Projected Requirements Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentNonGradProjected(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getStudentNonGradProjected");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getStudentNonGradProjectedReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @PostMapping(ReportApiConstants.STUDENT_NON_GRAD)
    @PreAuthorize(PermissionsContants.STUDENT_NON_GRAD)
    @Operation(summary = "Generate Student NonGraduate Report", description = "Generate Student NonGraduate Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> getStudentNonGrad(@RequestBody ReportRequest report, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("getStudentNonGrad");
        logRequest(report);
        setAccessToken(report, accessToken);
        try {
            String reportFile = report.getOptions().getReportFile();
            byte[] resultBinary = reportService.getStudentNonGradReport(report);
            return handleBinaryResponse(resultBinary, reportFile);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
    }

    @DeleteMapping(ReportApiConstants.STUDENT_REPORTS_BY_ID)
    @PreAuthorize(PermissionsContants.DELETE_STUDENT_REPORTS)
    @Operation(summary = "Delete Student Achievement Report", description = "Delete Student Achievement Report", tags = {"Report"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<byte[]> deleteStudentAchievementReportByStudentID(@PathVariable String studentID) {
        logger.debug("deleteStudentAchievementReport");
        try {
            logger.debug("Student ID from Path: [{}]", UUID.fromString(studentID));
            reportService.deleteStudentReportsByStudentID(studentID);
        } catch (Exception e) {
            return getInternalServerErrorResponse(e);
        }
        return null;
    }


    private void setAccessToken(ReportRequest report, String accessToken) {
        report.getData().setAccessToken(accessToken.replace("Bearer ", ""));
    }
}

package ca.bc.gov.educ.api.report.controller;

import ca.bc.gov.educ.api.report.GradReportBaseTest;
import ca.bc.gov.educ.api.report.service.ReportService;
import ca.bc.gov.educ.grad.controller.GradReportSignatureController;
import ca.bc.gov.educ.grad.dao.GradRequestDataAdaptor;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import ca.bc.gov.educ.grad.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.model.common.BusinessReport;
import ca.bc.gov.educ.grad.model.graduation.GradCertificateService;
import ca.bc.gov.educ.grad.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.service.GradReportSignatureService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
public class StudentReportApiControllerTest extends GradReportBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiControllerTest.class);
    private static final String CLASS_NAME = StudentReportApiControllerTest.class.getSimpleName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    WebApplicationContext wac;

    @Autowired
    StudentTranscriptService transcriptService;

    @Autowired
    GradCertificateService gradCertificateService;

    @Autowired
    BCMPBundleService bcmpBundleService;

    @Mock
    ReportService reportService;

    @Mock
    GradReportSignatureService reportSignatureService;

    @InjectMocks
    private ReportController reportController;

    @InjectMocks
    private GradReportSignatureController reportSignatureController;

    @Test
    public void getStudentAchievementReportTest() throws Exception {
        LOG.debug("<{}.getStudentAchievementReportReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-2004.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        GradRequestDataAdaptor.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("Student Achievement Report.pdf");

        byte[] resultBinary = new byte[0];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());
        ResponseEntity response = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resultBinary);

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        //Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        //Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(reportService.getStudentAchievementReport(reportRequest)).thenReturn(response);
        reportController.getStudentAchievementReport(reportRequest);
        Mockito.verify(reportService).getStudentAchievementReport(reportRequest);

        LOG.debug(">getStudentAchievementReportTest");
    }

    @Test
    public void getStudentTranscriptReportTest() throws Exception {
        LOG.debug("<{}.getStudentTranscriptReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-2004.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        GradRequestDataAdaptor.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("Transcript 2004 Report.pdf");
        StudentTranscriptReport report = transcriptService.buildOfficialTranscriptReport();
        byte[] resultBinary = report.getReportData();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());
        ResponseEntity response = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resultBinary);

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        //Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        //Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(reportService.getStudentTranscriptReport(reportRequest)).thenReturn(response);
        reportController.getStudentTranscriptReport(reportRequest);
        Mockito.verify(reportService).getStudentTranscriptReport(reportRequest);

        LOG.debug(">getStudentTranscriptReportTest");
    }

    @Test
    public void getStudentCertificateTest() throws Exception {
        LOG.debug("<{}.getStudentCertificateTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-A.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        GradRequestDataAdaptor.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("Certificate 1950 Report.pdf");
        List<BusinessReport> gradCertificateReports = gradCertificateService.buildReport();

        DocumentBundle documentBundle = bcmpBundleService.createDocumentBundle(reportRequest.getData().getCertificate().getOrderType());
        documentBundle.appendBusinessReport(gradCertificateReports);
        byte[] resultBinary = documentBundle.asBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());
        ResponseEntity response = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resultBinary);

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        //Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        //Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(reportService.getStudentCertificateReport(reportRequest)).thenReturn(response);
        reportController.getStudentCertificate(reportRequest);
        Mockito.verify(reportService).getStudentCertificateReport(reportRequest);

        LOG.debug(">getStudentCertificateTest");
    }

}

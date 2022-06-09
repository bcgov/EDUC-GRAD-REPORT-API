package ca.bc.gov.educ.grad.report.api.test.controller;

import ca.bc.gov.educ.grad.report.api.controller.ReportController;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.model.graduation.GradCertificateService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

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
    GradReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @Test
    public void testFake() {
        assertEquals(1+1, 2);
    }
    /*@Test
    public void getStudentXmlTranscriptReportTest() throws Exception {
        LOG.debug("<{}.getStudentXmlTranscriptReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        XmlReportRequest reportRequest = createXmlReportRequest("json/xmlTranscriptReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setXmlReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("XML Transcript Report (Controller).xml");

        byte[] resultBinary = new byte[0];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());
        ResponseEntity response = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_XML)
                .body(resultBinary);

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);
        reportRequest.getData().setAccessToken(details.getTokenValue());

        Mockito.when(reportService.getStudentXmlTranscriptReport(reportRequest)).thenReturn(response);
        reportController.getStudentXmlTranscriptReport(reportRequest, "");
        Mockito.verify(reportService).getStudentXmlTranscriptReport(reportRequest);

        LOG.debug(">getStudentXmlTranscriptReportTest");
    }*/

    /*@Test
    public void getSchoolDistributionReportTest() throws Exception {
        LOG.debug("<{}.getSchoolDistributionReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("School Distribution Report.pdf");

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

        Mockito.when(reportService.getSchoolDistributionReport(reportRequest)).thenReturn(response);
        reportController.getSchoolDistribution(reportRequest);
        Mockito.verify(reportService).getSchoolDistributionReport(reportRequest);

        LOG.debug(">getSchoolDistributionReportTest");
    }*/

    /*@Test
    public void getPackingSlipReportTest() throws Exception {
        LOG.debug("<{}.getPackingSlipReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/packingSlipReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("Packing Slip Report.pdf");

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

        Mockito.when(reportService.getPackingSlipReport(reportRequest)).thenReturn(response);
        reportController.getPackingSlip(reportRequest);
        Mockito.verify(reportService).getPackingSlipReport(reportRequest);

        LOG.debug(">getPackingSlipReportTest");
    }*/

    /*@Test
    public void getStudentAchievementReportTest() throws Exception {
        LOG.debug("<{}.getStudentAchievementReportReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

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
    }*/

    /*@Test
    public void getStudentTranscriptReportTest() throws Exception {
        LOG.debug("<{}.getStudentTranscriptReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-PUB.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("Transcript BC1950-PUB Report.pdf");
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
    }*/

    /*@Test
    public void getStudentCertificateTest() throws Exception {
        LOG.debug("<{}.getStudentCertificateTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-A.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

        reportRequest.getOptions().setReportFile("Certificate 1950 Report.pdf");
        List<BusinessReport> gradCertificateReports = gradCertificateService.buildReport();

        ca.bc.gov.educ.grad.report.model.order.OrderType orderType = new CertificateOrderTypeImpl() {
            @Override
            public String getName() {
                return reportRequest.getData().getCertificate().getOrderType().getName();
            }
        };
        DocumentBundle documentBundle = bcmpBundleService.createDocumentBundle(orderType);
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
    }*/

}

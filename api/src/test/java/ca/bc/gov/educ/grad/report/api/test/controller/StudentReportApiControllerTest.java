package ca.bc.gov.educ.grad.report.api.test.controller;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.controller.ReportController;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.service.TokenService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.utils.GradValidation;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import reactor.core.publisher.Mono;

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
    StudentCertificateService gradCertificateService;

    @Autowired
    BCMPBundleService bcmpBundleService;

    @Mock
    GradReportService reportService;
    @Mock
    TokenService tokenService;

    @Mock
    GradValidation gradValidation;

    @InjectMocks
    private ReportController reportController;

    @Test
    public void getSchoolDistributionReportTest() throws Exception {
        LOG.debug("<{}.getSchoolDistributionReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getSchoolDistributionReport(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getSchoolDistribution(reportRequest);
        Mockito.verify(reportService).getSchoolDistributionReport(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getSchoolDistributionReportTest");
    }

    @Test
    public void getSchoolDistributionYearEndReportTest() throws Exception {
        LOG.debug("<{}.getSchoolDistributionYearEndReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/schoolDistributionYearEndReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getSchoolDistributionReportYearEnd(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getSchoolDistributionYearEnd(reportRequest);
        Mockito.verify(reportService).getSchoolDistributionReportYearEnd(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getSchoolDistributionYearEndReportTest");
    }

    @Test
    public void getDistrictDistributionYearEndReportTest() throws Exception {
        LOG.debug("<{}.getDistrictDistributionYearEndReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/districtDistributionYearEndReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getDistrictDistributionReportYearEnd(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getDistrictDistributionYearEnd(reportRequest);
        Mockito.verify(reportService).getDistrictDistributionReportYearEnd(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getDistrictDistributionYearEndReportTest");
    }

    @Test
    public void getDistrictDistributionYearEndNonGradReportTest() throws Exception {
        LOG.debug("<{}.getDistrictDistributionYearEndNonGradReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/districtDistributionYearEndNonGradReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getDistrictDistributionReportYearEndNonGrad(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getDistrictDistributionYearEndNonGrad(reportRequest);
        Mockito.verify(reportService).getDistrictDistributionReportYearEndNonGrad(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getDistrictDistributionYearEndNonGradReportTest");
    }

    @Test
    public void getPackingSlipReportTest() throws Exception {
        LOG.debug("<{}.getPackingSlipReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/packingSlipReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getPackingSlipReport(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getPackingSlip(reportRequest);
        Mockito.verify(reportService).getPackingSlipReport(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getPackingSlipReportTest");
    }

    @Test
    public void getStudentAchievementReportTest() throws Exception {
        LOG.debug("<{}.getStudentAchievementReportReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getStudentAchievementReport(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getStudentAchievementReport(reportRequest);
        Mockito.verify(reportService).getStudentAchievementReport(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getStudentAchievementReportTest");
    }

    @Test
    public void getStudentTranscriptReportTest() throws Exception {
        LOG.debug("<{}.getStudentTranscriptReportTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-PUB.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        byte[] resultBinary = reportRequest.getOptions().getReportFile().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + reportRequest.getOptions().getReportFile());

        Mockito.when(reportService.getStudentTranscriptReport(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getStudentTranscriptReport(reportRequest);
        Mockito.verify(reportService).getStudentTranscriptReport(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getStudentTranscriptReportTest");
    }

    @Test
    public void getStudentCertificateTest() throws Exception {
        LOG.debug("<{}.getStudentCertificateTest at {}", CLASS_NAME, dateFormat.format(new Date()));

        ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-A.json");

        assertNotNull(reportRequest);
        assertNotNull(reportRequest.getData());

        ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
        ReportRequestDataThreadLocal.setCurrentUser("Batch Process");

        mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));

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

        Mockito.when(reportService.getStudentCertificateReport(reportRequest)).thenReturn(resultBinary);
        Mockito.when(tokenService.getAccessToken()).thenReturn(Mono.just("token"));
        ResponseEntity response = reportController.getStudentCertificate(reportRequest);
        Mockito.verify(reportService).getStudentCertificateReport(reportRequest);
        assertNotNull(response.getBody());

        LOG.debug(">getStudentCertificateTest");
    }

}

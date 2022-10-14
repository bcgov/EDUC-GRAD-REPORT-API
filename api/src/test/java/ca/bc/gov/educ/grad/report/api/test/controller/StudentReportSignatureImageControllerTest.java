package ca.bc.gov.educ.grad.report.api.test.controller;

import ca.bc.gov.educ.grad.report.api.controller.GradReportSignatureController;
import ca.bc.gov.educ.grad.report.api.controller.ReportController;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
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

@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
public class StudentReportSignatureImageControllerTest extends GradReportBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(StudentReportSignatureImageControllerTest.class);
    private static final String CLASS_NAME = StudentReportSignatureImageControllerTest.class.getSimpleName();
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

    @Autowired
    GradReportCodeService gradReportCodeService;

    @Mock
    GradReportSignatureService reportSignatureService;

    @InjectMocks
    private ReportController reportController;

    @InjectMocks
    private GradReportSignatureController reportSignatureController;


    /*@Test
    public void extractSignatureImageTest() throws Exception {
        LOG.debug("<{}.extractSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
        byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
        assertNotNull(imageBinary);
        assertNotEquals(0, imageBinary.length);
        LOG.debug("Test image loaded {} bytes", imageBinary.length);

        String signatureCode = "MOE";
        GradReportSignatureImage signatureImage = new GradReportSignatureImage();
        signatureImage.setGradReportSignatureCode(signatureCode);
        signatureImage.setSignatureContent(imageBinary);
        signatureImage.setSignatureId(UUID.randomUUID());

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(reportSignatureService.getSignatureImageByCode(signatureCode, details.getTokenValue())).thenReturn(signatureImage);
        reportSignatureController.extractSignatureImageByCode(signatureCode, "");
        Mockito.verify(reportSignatureService).getSignatureImageByCode(signatureCode, details.getTokenValue());

        LOG.debug(">extractSignatureImageTest");
    }*/

    /*@Test
    public void getSignatureImagesTest() throws Exception {
        LOG.debug("<{}.getSignatureImagesTest at {}", CLASS_NAME, dateFormat.format(new Date()));
        byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
        assertNotNull(imageBinary);
        assertNotEquals(0, imageBinary.length);
        LOG.debug("Test image loaded {} bytes", imageBinary.length);

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);

        String signatureCode = "MOE";
        GradReportSignatureImage signatureImage = new GradReportSignatureImage();
        signatureImage.setGradReportSignatureCode(signatureCode);
        signatureImage.setSignatureContent(imageBinary);
        signatureImage.setSignatureId(UUID.randomUUID());

        List<GradReportSignatureImage> signatureImages = new ArrayList();
        signatureImages.add(signatureImage);

        Mockito.when(reportSignatureService.getSignatureImages(details.getTokenValue())).thenReturn(signatureImages);
        reportSignatureController.getSignatureImages("");
        Mockito.verify(reportSignatureService).getSignatureImages(details.getTokenValue());

        LOG.debug(">getSignatureImagesTest");
    }*/

    /*@Test
    public void getSignatureImageTest() throws Exception {
        LOG.debug("<{}.getSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
        byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
        assertNotNull(imageBinary);
        assertNotEquals(0, imageBinary.length);
        LOG.debug("Test image loaded {} bytes", imageBinary.length);

        String signatureCode = "MOE";
        GradReportSignatureImage signatureImage = new GradReportSignatureImage();
        signatureImage.setGradReportSignatureCode(signatureCode);
        signatureImage.setSignatureContent(imageBinary);
        signatureImage.setSignatureId(UUID.randomUUID());

        Authentication authentication = Mockito.mock(Authentication.class);
        OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getDetails()).thenReturn(details);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(reportSignatureService.getSignatureImageByCode(signatureCode, details.getTokenValue())).thenReturn(signatureImage);
        reportSignatureController.getSignatureImageByCode(signatureCode, "");
        Mockito.verify(reportSignatureService).getSignatureImageByCode(signatureCode, details.getTokenValue());

        LOG.debug(">getSignatureImageTest");
    }*/

}

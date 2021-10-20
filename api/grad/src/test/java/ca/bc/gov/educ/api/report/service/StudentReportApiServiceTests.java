package ca.bc.gov.educ.api.report.service;

import ca.bc.gov.educ.api.report.GradReportBaseTest;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import ca.bc.gov.educ.grad.dto.GragReportSignatureImage;
import ca.bc.gov.educ.grad.service.GradReportSignatureService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@WebAppConfiguration
public class StudentReportApiServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiServiceTests.class);
	private static final String CLASS_NAME = StudentReportApiServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportService reportService;
	@Autowired
	GradReportSignatureService reportSignatureService;

	@Before
	public void init() throws Exception {

	}

	@Test
	public void getSignatureImagesTest() throws Exception {
		LOG.debug("<{}.getSignatureImagesTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "MOE.png";
		GragReportSignatureImage signatureImage = new GragReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		reportSignatureService.saveSignatureImage(signatureImage);

		List<GragReportSignatureImage> signatureImages = reportSignatureService.getSignatureImages();
		assertNotNull(signatureImages);
		assertTrue(signatureImages.size() > 0);

		LOG.debug(">getSignatureImagesTest");
	}

	@Test
	public void getSignatureImageTest() throws Exception {
		LOG.debug("<{}.getSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "MOE.png";
		GragReportSignatureImage signatureImage = new GragReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		reportSignatureService.saveSignatureImage(signatureImage);
		signatureImage = reportSignatureService.getSignatureImageByCode(signatureCode);
		assertNotNull(signatureImage);

		LOG.debug(">getSignatureImageTest");
	}

	@Test
	public void saveSignatureImageTest() throws Exception {
		LOG.debug("<{}.saveSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "TEST.png";
		GragReportSignatureImage signatureImage = new GragReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		signatureImage = reportSignatureService.saveSignatureImage(signatureImage);
		assertNotNull(signatureImage);

		LOG.debug(">saveSignatureImageTest");
	}

	@Test
	public void createStudentAchievementReport() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Student Achievement Report.pdf");
		ResponseEntity response = reportService.getStudentAchievementReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createStudentAchievementReport");
	}

	@Test
	public void createTranscriptReport_2004() throws Exception {
		LOG.debug("<{}.createTranscriptReport_2004 at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-2004.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Transcript 2004 Report.pdf");
		ResponseEntity response = reportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_2004");
	}

	@Test
	public void createTranscriptReport_1950() throws Exception {
		LOG.debug("<{}.createTranscriptReport_1950 at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-1950.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Transcript 1950 Report.pdf");
		ResponseEntity response = reportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_1950");
	}

	@Test
	public void createTranscriptReport_2018() throws Exception {
		LOG.debug("<{}.createTranscriptReport_2018 at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-2018.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Transcript 2018 Report.pdf");
		ResponseEntity response = reportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_2018");
	}

	@Test
	public void createTranscriptReport_SCCP() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Transcript SCCP Report.pdf");
		ResponseEntity response = reportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_SCCP");
	}

	@Test
	public void createCertificateReport_E() throws Exception {
		LOG.debug("<{}.createCertificateReport_E at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate E Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_E");
	}

	@Test
	public void createCertificateReport_A() throws Exception {
		LOG.debug("<{}.createCertificateReport_A at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-A.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate A Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_A");
	}

	@Test
	public void createCertificateReport_EI() throws Exception {
		LOG.debug("<{}.createCertificateReport_EI at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EI.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate EI Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_EI");
	}

	@Test
	public void createCertificateReport_AI() throws Exception {
		LOG.debug("<{}.createCertificateReport_AI at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AI.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate AI Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_AI");
	}

	@Test
	public void createCertificateReport_SC() throws Exception {
		LOG.debug("<{}.createCertificateReport_SC at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SC.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SC Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SC");
	}

	@Test
	public void createCertificateReport_SCO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCO at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCO.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SCO Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SCO");
	}

	@Test
	public void createCertificateReport_S() throws Exception {
		LOG.debug("<{}.createCertificateReport_S at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-S.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate S Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_S");
	}

	@Test
	public void createCertificateReport_F() throws Exception {
		LOG.debug("<{}.createCertificateReport_S at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-F.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate F Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_F");
	}
}

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

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
	public void createCertificateReport() throws Exception {
		LOG.debug("<{}.createCertificateReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-2004.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate REGULAR Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport");
	}

	@Test
	public void createCertificateReport_1950() throws Exception {
		LOG.debug("<{}.createCertificateReport_1950 at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-1950.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate 1950 Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_1950");
	}

	@Test
	public void createCertificateReport_2018() throws Exception {
		LOG.debug("<{}.createCertificateReport_2018 at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-2018.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate 2018 Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_2018");
	}

	@Test
	public void createCertificateReport_SCCP() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCCP at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SCCP Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SCCP");
	}
}

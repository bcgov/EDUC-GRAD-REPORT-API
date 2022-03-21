package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.XmlReportRequest;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementReport;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.DestinationType;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.naming.NamingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ca.bc.gov.educ.grad.report.model.cert.CertificateType.E;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
public class StudentReportApiServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiServiceTests.class);
	private static final String CLASS_NAME = StudentReportApiServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	GradReportService apiReportService;
	@Autowired
	BCMPBundleService bcmpBundleService;
	@Autowired
	GradReportSignatureService reportSignatureService;
	@Autowired
	PackingSlipService packingSlipService;

	@Before
	public void init() throws Exception {

	}

	@Test
	public void createStudentAchievementReport() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Student Achievement Report (New).pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentAchievementReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createStudentAchievementReport");
	}

	@Test
	public void createTranscriptReport_BC1950_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC1950-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC1950_PUB");
	}

	@Test
	public void createTranscriptReport_BC1950_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC1950-IND Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC1950_IND");
	}

	@Test
	public void createTranscriptReport_YU1950_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1950_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1950-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript YU1950-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_YU1950_PUB");
	}

	@Test
	public void createTranscriptReport_BC1986_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC1986-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC1986_PUB");
	}

	@Test
	public void createTranscriptReport_BC1986_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC1986-IND Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC1986_IND");
	}

	@Test
	public void createTranscriptReport_YU1986_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1986_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1986-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript YU1986-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_YU1986_PUB");
	}

	@Test
	public void createTranscriptReport_BC1996_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC1996-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC1996_PUB");
	}

	@Test
	public void createTranscriptReport_BC1996_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC1996-IND Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC1996_IND");
	}

	@Test
	public void createTranscriptReport_YU1996_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1996_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1996-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript YU1996-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_YU1996_PUB");
	}

	@Test
	public void createTranscriptReport_BC2004_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC2004-IND Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC2004_IND");
	}

	@Test
	public void createTranscriptReport_BC2004_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC2004-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC2004_PUB");
	}

	@Test
	public void createTranscriptReport_YU2004_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU2004_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU2004-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript YU2004-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_YU2004_PUB");
	}

	@Test
	public void createTranscriptReport_BC2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC2018-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC2018_PUB");
	}

	@Test
	public void createTranscriptReport_BC2018_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC2018-IND Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC2018_IND");
	}

	@Test
	public void createTranscriptReport_BC2018_OFF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_OFF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-OFF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC2018-OFF Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC2018_OFF");
	}

	@Test
	public void createTranscriptReport_BC2018_PF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_PF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-PF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript BC2018-PF Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_BC2018_PF");
	}

	@Test
	public void createTranscriptReport_YU2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript YU2018-PUB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_YU2018_PUB");
	}

	@Test
	public void createTranscriptReport_SCCP_EN() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_EN at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript SCCP-EN Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_SCCP_EN");
	}

	@Test
	public void createTranscriptReport_SCCP_PF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_PF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-PF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript SCCP-PF Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_SCCP_PF");
	}

	@Test
	public void createTranscriptReport_NOPROG() throws Exception {
		LOG.debug("<{}.createTranscriptReport_NOPROG at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-NOPROG.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Transcript NOPROG Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createTranscriptReport_NOPROG");
	}

	@Test
	public void createCertificateReport_E() throws Exception {
		LOG.debug("<{}.createCertificateReport_E at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate E Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_E");
	}

	@Test
	public void createCertificateReport_EO() throws Exception {
		LOG.debug("<{}.createCertificateReport_EO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate EO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_EO");
	}

	@Test
	public void createCertificateReport_EB() throws Exception {
		LOG.debug("<{}.createCertificateReport_EB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate EB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_EB");
	}

	@Test
	public void createCertificateReport_A() throws Exception {
		LOG.debug("<{}.createCertificateReport_A at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-A.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate A Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_A");
	}

	@Test
	public void createCertificateReport_AB() throws Exception {
		LOG.debug("<{}.createCertificateReport_AB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate AB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_AB");
	}

	@Test
	public void createCertificateReport_AIB() throws Exception {
		LOG.debug("<{}.createCertificateReport_AIB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AIB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate AIB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_AIB");
	}

	@Test
	public void createCertificateReport_EI() throws Exception {
		LOG.debug("<{}.createCertificateReport_EI at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EI.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate EI Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_EI");
	}

	@Test
	public void createCertificateReport_EIB() throws Exception {
		LOG.debug("<{}.createCertificateReport_EIB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EIB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate EIB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_EIB");
	}

	@Test
	public void createCertificateReport_EIO() throws Exception {
		LOG.debug("<{}.createCertificateReport_EIO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EIO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate EIO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_EIO");
	}

	@Test
	public void createCertificateReport_AI() throws Exception {
		LOG.debug("<{}.createCertificateReport_AI at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AI.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate AI Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
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
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SC.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SC Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SC");
	}

	@Test
	public void createCertificateReport_SCB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SCB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SCB");
	}

	@Test
	public void createCertificateReport_SCO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SCO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
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
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-S.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate S Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_S");
	}

	@Test
	public void createCertificateReport_SB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SB");
	}

	@Test
	public void createCertificateReport_SO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SO");
	}

	@Test
	public void createCertificateReport_F() throws Exception {
		LOG.debug("<{}.createCertificateReport_F at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-F.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate F Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_F");
	}

	@Test
	public void createCertificateReport_FB() throws Exception {
		LOG.debug("<{}.createCertificateReport_FB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-FB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate FB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_FB");
	}

	@Test
	public void createCertificateReport_FO() throws Exception {
		LOG.debug("<{}.createCertificateReport_FO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-FO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate FO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_FO");
	}

	@Test
	public void createCertificateReport_SCF() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SCF Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SCF");
	}

	@Test
	public void createCertificateReport_SCFO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCFO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCFO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate SCFO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SCFO");
	}

	@Test
	public void createCertificateReport_O() throws Exception {
		LOG.debug("<{}.createCertificateReport_O at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-O.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate O Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_O");
	}

	@Test
	public void createCertificateReport_OB() throws Exception {
		LOG.debug("<{}.createCertificateReport_OB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-OB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate OB Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_OB");
	}

	@Test
	public void createCertificateReport_OO() throws Exception {
		LOG.debug("<{}.createCertificateReport_OO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-OO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Certificate OO Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_OO");
	}

	@Test
	public void createSchoolDistributionReport() throws Exception {
		LOG.debug("<{}.createSchoolDistributionReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("School Distribution Report.pdf");
		ResponseEntity<byte[]> response = apiReportService.getSchoolDistributionReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createSchoolDistributionReport");
	}

	//@Test
	public void createXmlTranscriptReport() throws Exception {
		LOG.debug("<{}.createXmlTranscriptReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		XmlReportRequest reportRequest = (XmlReportRequest)createReportRequest("json/xmlTranscriptReportRequest.json", XmlReportRequest.class);

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setXmlReportData(reportRequest.getData());

		reportRequest.getOptions().setReportFile("Xml Transcript Report.xml");
		ResponseEntity<byte[]> response = apiReportService.getStudentXmlTranscriptReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createXmlTranscriptReport");
	}

	@Test
	public void testPackingSlipReport() throws Exception {
		LOG.debug("<{}.testPackingSlipReport at {}", CLASS_NAME, dateFormat.format(new Date()));

		OrderType orderType;

		ReportRequest achievementReportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		assertNotNull(achievementReportRequest);
		assertNotNull(achievementReportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(achievementReportRequest.getData());
		achievementReportRequest.getOptions().setReportFile("Student Achievement Report (New).pdf");
		StudentAchievementReport achievementReport = apiReportService.getStudentAchievementReportDocument(achievementReportRequest);

		ReportRequest eCertificateReportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(eCertificateReportRequest);
		assertNotNull(eCertificateReportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(eCertificateReportRequest.getData());

		eCertificateReportRequest.getOptions().setReportFile("Certificate E Report.pdf");
		DocumentBundle eCertificateReport = apiReportService.getStudentCertificateReportDocument(eCertificateReportRequest);

		ReportRequest sccpTranscriptReportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN.json");

		assertNotNull(sccpTranscriptReportRequest);
		assertNotNull(sccpTranscriptReportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(sccpTranscriptReportRequest.getData());

		sccpTranscriptReportRequest.getOptions().setReportFile("Transcript SCCP-EN Report.pdf");
		StudentTranscriptReport sccpTranscriptReport = apiReportService.getStudentTranscriptReportDocument(sccpTranscriptReportRequest);

		ReportRequest packingSlipReportRequest = createReportRequest("json/packingSlipReportRequest.json");

		assertNotNull(packingSlipReportRequest);
		assertNotNull(packingSlipReportRequest.getData());

		packingSlipReportRequest.getOptions().setReportFile("Packing Slip Report.pdf");

		List<ReportDocument> rds = new ArrayList<>();
		rds.add(achievementReport);

		orderType = bcmpBundleService.createAchievementOrderType();
		packingSlipReportRequest.getData().getPackingSlip().getOrderType().setName("Achievement");
		ReportRequestDataThreadLocal.setGenerateReportData(packingSlipReportRequest.getData());
		testPackingSlipReport(
				rds,
				orderType,
				DestinationType.PSI,
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());
		rds.clear();

		rds.add(eCertificateReport);
		orderType = bcmpBundleService.createCertificateOrderType(E);
		packingSlipReportRequest.getData().getPackingSlip().getOrderType().setName("Certificate");
		ReportRequestDataThreadLocal.setGenerateReportData(packingSlipReportRequest.getData());
		testPackingSlipReport(
				rds,
				orderType,
				DestinationType.PSI,
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());
		rds.clear();

		rds.add(sccpTranscriptReport);
		orderType = bcmpBundleService.createTranscriptOrderType();
		packingSlipReportRequest.getData().getPackingSlip().getOrderType().setName("Transcript");
		ReportRequestDataThreadLocal.setGenerateReportData(packingSlipReportRequest.getData());
		testPackingSlipReport(
				rds,
				orderType,
				DestinationType.PSI,
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());

		LOG.debug(">testPackingSlipReport");
	}

	private void testPackingSlipReport(
			final List<ReportDocument> rds,
			final OrderType orderType,
			final DestinationType destinationType,
			final int quantity,
			final int current,
			final int total)
			throws DomainServiceException, IOException {

		ReportDocument packingSlip = packingSlipService.createPackingSlipReport(
			16895L,
			new Date(),
			"Test Case",
				quantity,
				current,
				total
		);

		//DocumentBundle documentBundle = createDocumentBundle(packingSlip, rds, orderType);
		byte[] bArrray = (byte[]) packingSlip.asBytes();
		try (OutputStream out = new FileOutputStream("target/PackingSlip" + orderType.getName() + ".pdf")) {
			out.write(bArrray);
		}
	}

	/**
	 * Bundles an arbitrary number of transcripts or certificates with a packing
	 * slip without XPIF information.
	 *
	 * @param orderType The type of report document to bundle (transcript or
	 * certificate).
	 *
	 * @throws NamingException Could not find service.
	 * @throws IOException Could not generate bundle.
	 */
	private DocumentBundle createDocumentBundle(
			final ReportDocument packingSlip,
			final List<ReportDocument> rds,
			final OrderType orderType)
			throws IOException {

		DocumentBundle bundle = bcmpBundleService.createDocumentBundle(orderType);
		bundle = bcmpBundleService.appendReportDocument(bundle, packingSlip);
		bundle = bcmpBundleService.appendReportDocument(bundle, rds);
		// Once the bundle has been created, decorate the page numbers.
		return bcmpBundleService.enumeratePages(bundle);
	}

}

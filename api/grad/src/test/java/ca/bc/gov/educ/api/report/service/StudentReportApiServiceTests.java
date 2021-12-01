package ca.bc.gov.educ.api.report.service;

import ca.bc.gov.educ.api.report.GradReportBaseTest;
import ca.bc.gov.educ.api.report.model.dto.GenerateReportData;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
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

import static org.junit.Assert.assertNotNull;

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
	public void createStudentAchievementReport() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Student Achievement Report (Legacy).pdf");
		ResponseEntity response = reportService.getStudentAchievementReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createStudentAchievementReport");
	}

	@Test
	public void createStudentAchvReport() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportData reportData = createReportRequestObj("json/studentAchvReportRequest.json");
		assertNotNull(reportData);
		reportData.getOptions().setReportFile("Student Achievement Report.pdf");

		ResponseEntity response = reportService.getStudentAchvReport(reportData);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportData.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createStudentAchievementReport");
	}

	@Test
	public void createStudentAchvReport2() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportData reportData = createReportRequestObj("json/studentAchvReportRequest2.json");
		assertNotNull(reportData);
		reportData.getOptions().setReportFile("Student Achievement Report 3.pdf");

		ResponseEntity response = reportService.getStudentAchvReport(reportData);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportData.getOptions().getReportFile())) {
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
	public void createCertificateReport_EO() throws Exception {
		LOG.debug("<{}.createCertificateReport_EO at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EO.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate EO Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate EB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
	public void createCertificateReport_AB() throws Exception {
		LOG.debug("<{}.createCertificateReport_AB at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate AB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AIB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate AIB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
	public void createCertificateReport_EIB() throws Exception {
		LOG.debug("<{}.createCertificateReport_EIB at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EIB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate EIB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EIO.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate EIO Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
	public void createCertificateReport_SCB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCB at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SCB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
	public void createCertificateReport_SB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SB at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SO.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SO Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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

	@Test
	public void createCertificateReport_FB() throws Exception {
		LOG.debug("<{}.createCertificateReport_FB at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-FB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate FB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-FO.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate FO Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCF.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate SCF Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_SCF");
	}

	@Test
	public void createCertificateReport_O() throws Exception {
		LOG.debug("<{}.createCertificateReport_O at {}", CLASS_NAME, dateFormat.format(new Date()));
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-O.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate O Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-OB.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate OB Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
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
		GenerateReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-OO.json");
		assertNotNull(reportRequest);
		reportRequest.getOptions().setReportFile("Certificate OO Report.pdf");
		ResponseEntity response = reportService.getStudentCertificateReport(reportRequest);
		assertNotNull(response.getBody());
		byte[] bArrray = (byte[]) response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArrray);
		}
		LOG.debug(">createCertificateReport_OO");
	}
}

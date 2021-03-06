package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.*;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.exception.InvalidParameterException;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementReport;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptCourse;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static ca.bc.gov.educ.grad.report.model.cert.CertificateType.E;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebAppConfiguration
public class StudentReportApiServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiServiceTests.class);
	private static final String CLASS_NAME = StudentReportApiServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportApiConstants reportApiConstants;
	@Autowired
	GradReportService apiReportService;
	@Autowired
	BCMPBundleService bcmpBundleService;
	@Autowired
	GradReportSignatureService reportSignatureService;
	@Autowired
	PackingSlipService packingSlipService;

	@Autowired
	StudentTranscriptService transcriptService;
	@Autowired
	GradDataConvertionBean gradDataConvertionBean;

	@MockBean WebClient webClient;
	@Mock WebClient.RequestHeadersSpec requestHeadersMock;
	@Mock WebClient.RequestHeadersUriSpec requestHeadersUriMock;
	@Mock WebClient.RequestBodySpec requestBodyMock;
	@Mock WebClient.RequestBodyUriSpec requestBodyUriMock;
	@Mock WebClient.ResponseSpec responseMock;

	@Autowired
	EducGradReportApiConstants constants;

	@Before
	public void init() {

	}

	@Test
	public void createStudentAchievementReport() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentAchievementReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createStudentAchievementReport");
	}

	@Test
	public void createTranscriptReport_BC1950_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1950_PUB");
	}

	@Test
	public void createTranscriptReport_BC1950_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1950_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC1950_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1950_IND");
	}

	@Test
	public void createTranscriptReport_YU1950_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1950_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1950-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_YU1950_PUB");
	}

	@Test
	public void createTranscriptReport_BC1986_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1986_PUB");
	}

	@Test
	public void createTranscriptReport_BC1986_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1986_IND");
	}

	@Test
	public void createTranscriptReport_BC1986_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1986_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_YU1986_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1986_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1986-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_YU1986_PUB");
	}

	@Test
	public void createTranscriptReport_BC1996_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1996_PUB");
	}

	@Test
	public void createTranscriptReport_BC1996_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1996_IND");
	}

	@Test
	public void createTranscriptReport_BC1996_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC1996_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_YU1996_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1996_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1996-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_YU1996_PUB");
	}

	@Test
	public void createTranscriptReport_BC2004_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2004_IND");
	}

	@Test
	public void createTranscriptReport_BC2004_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2004_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC2004_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2004_PUB");
	}

	@Test
	public void createTranscriptReport_YU2004_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU2004_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU2004-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_YU2004_PUB");
	}

	@Test
	public void createTranscriptReport_BC2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2018_PUB");
	}

	@Test
	public void createTranscriptReport_BC2018_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2018_IND");
	}

	@Test
	public void createTranscriptReport_BC2018_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2018_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC2018_OFF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_OFF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-OFF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2018_OFF");
	}

	@Test
	public void createTranscriptReport_BC2018_PF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_PF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-PF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_BC2018_PF");
	}

	@Test
	public void createTranscriptReport_YU2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_YU2018_PUB");
	}

	@Test
	public void createTranscriptReport_SCCP_EN() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_EN at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_SCCP_EN");
	}

	@Test
	public void createTranscriptReport_SCCP_EN_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_EN_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_SCCP_EN_BLANK");
	}

	@Test
	public void createTranscriptReport_SCCP_PF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_PF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-PF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_SCCP_PF");
	}

	@Test
	public void createTranscriptReport_NOPROG() throws Exception {
		LOG.debug("<{}.createTranscriptReport_NOPROG at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-NOPROG.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_NOPROG");
	}

	@Test
	public void createTranscriptReport_NOPROG_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_NOPROG at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-NOPROG-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createTranscriptReport_NOPROG");
	}

	@Test
	public void createTranscriptReport_NOTELIG_NOPROG() throws Exception {
		LOG.debug("<{}.createTranscriptReport_NOTELIG_NOPROG at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-NOTELIG_NOPROG.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		TraxSchool school = adaptTraxSchool(getReportDataSchool(reportRequest.getData()));
		school.setTranscriptEligibility("N");
		mockTraxSchool(school);
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentTranscriptReport(reportRequest);
		assertEquals(500, response.getStatusCode().value());
		assertNotNull(response.getBody());
		String bodyResponse = new String(response.getBody());
		LOG.debug(bodyResponse);
		assertTrue(StringUtils.contains(bodyResponse, "School is not eligible for transcripts"));
		LOG.debug(">createTranscriptReport_NOTELIG_NOPROG");
	}

	@Test
	public void createCertificateReport_NOTELIG_E() throws Exception {
		LOG.debug("<{}.createCertificateReport_NOTELIG_E at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		TraxSchool school = adaptTraxSchool(getReportDataSchool(reportRequest.getData()));
		school.setCertificateEligibility("N");
		mockTraxSchool(school);
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(500, response.getStatusCode().value());
		assertNotNull(response.getBody());
		String bodyResponse = new String(response.getBody());
		LOG.debug(bodyResponse);
		assertTrue(StringUtils.contains(bodyResponse, "School is not eligible for certificates"));
		LOG.debug(">createCertificateReport_NOTELIG_E");
	}

	@Test
	public void createCertificateReport_E() throws Exception {
		LOG.debug("<{}.createCertificateReport_E at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_E");
	}

	@Test
	public void createCertificateReport_EO() throws Exception {
		LOG.debug("<{}.createCertificateReport_EO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_EO");
	}

	@Test
	public void createCertificateReport_EB() throws Exception {
		LOG.debug("<{}.createCertificateReport_EB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_EB");
	}

	@Test
	public void createCertificateReport_A() throws Exception {
		LOG.debug("<{}.createCertificateReport_A at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-A.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_A");
	}

	@Test
	public void createCertificateReport_AB() throws Exception {
		LOG.debug("<{}.createCertificateReport_AB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_AB");
	}

	@Test
	public void createCertificateReport_AIB() throws Exception {
		LOG.debug("<{}.createCertificateReport_AIB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AIB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_AIB");
	}

	@Test
	public void createCertificateReport_EI() throws Exception {
		LOG.debug("<{}.createCertificateReport_EI at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EI.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_EI");
	}

	@Test
	public void createCertificateReport_EIB() throws Exception {
		LOG.debug("<{}.createCertificateReport_EIB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EIB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_EIB");
	}

	@Test
	public void createCertificateReport_EIO() throws Exception {
		LOG.debug("<{}.createCertificateReport_EIO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-EIO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_EIO");
	}

	@Test
	public void createCertificateReport_AI() throws Exception {
		LOG.debug("<{}.createCertificateReport_AI at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AI.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_AI");
	}

	@Test
	public void createCertificateReport_SC() throws Exception {
		LOG.debug("<{}.createCertificateReport_SC at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SC.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SC");
	}

	@Test
	public void createCertificateReport_SCB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SCB");
	}

	@Test
	public void createCertificateReport_SCO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SCO");
	}

	@Test
	public void createCertificateReport_S() throws Exception {
		LOG.debug("<{}.createCertificateReport_S at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-S.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_S");
	}

	@Test
	public void createCertificateReport_SB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SB");
	}

	@Test
	public void createCertificateReport_SO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SO");
	}

	@Test
	public void createCertificateReport_F() throws Exception {
		LOG.debug("<{}.createCertificateReport_F at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-F.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_F");
	}

	@Test
	public void createCertificateReport_FB() throws Exception {
		LOG.debug("<{}.createCertificateReport_FB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-FB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_FB");
	}

	@Test
	public void createCertificateReport_FO() throws Exception {
		LOG.debug("<{}.createCertificateReport_FO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-FO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_FO");
	}

	@Test
	public void createCertificateReport_SCF() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SCF");
	}

	@Test
	public void createCertificateReport_SCFB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCFB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCFB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SCF");
	}

	@Test
	public void createCertificateReport_SCFO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCFO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCFO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_SCFO");
	}

	@Test
	public void createCertificateReport_O() throws Exception {
		LOG.debug("<{}.createCertificateReport_O at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-O.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_O");
	}

	@Test
	public void createCertificateReport_OB() throws Exception {
		LOG.debug("<{}.createCertificateReport_OB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-OB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_OB");
	}

	@Test
	public void createCertificateReport_OO() throws Exception {
		LOG.debug("<{}.createCertificateReport_OO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-OO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentCertificateReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createCertificateReport_OO");
	}

	@Test
	public void createSchoolDistributionReport() throws Exception {
		LOG.debug("<{}.createSchoolDistributionReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getSchoolDistributionReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createSchoolDistributionReport");
	}

	@Test
	public void createSchoolGraduationReport() throws Exception {
		LOG.debug("<{}.createSchoolGraduationReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolGraduationReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getSchoolGraduationReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createSchoolGraduationReport");
	}

	@Test
	public void createStudentNonGradReport() throws Exception {
		LOG.debug("<{}.createStudentNonGradReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ResponseEntity<byte[]> response = apiReportService.getStudentNonGradReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
		}
		LOG.debug(">createStudentNonGradReport");
	}

	@Test
	public void createXmlTranscriptReport() throws Exception {
		LOG.debug("<{}.createXmlTranscriptReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		XmlReportRequest reportRequest = createXmlReportRequest("json/xmlTranscriptReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		reportRequest.getData().setAccessToken("accessToken");

		ReportRequestDataThreadLocal.setXmlReportData(reportRequest.getData());

		String pen = reportRequest.getData().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GradSearchStudent gradSearchStudent = new GradSearchStudent();
		gradSearchStudent.setPen(pen);
		gradSearchStudent.setStudentID(UUID.randomUUID().toString());

		final ParameterizedTypeReference<List<GradSearchStudent>> gradSearchStudentResponseType = new ParameterizedTypeReference<>() {
		};

		when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
		when(this.requestHeadersUriMock.uri(String.format(reportApiConstants.getPenStudentApiByPenUrl(),pen))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
		when(this.responseMock.bodyToMono(gradSearchStudentResponseType)).thenReturn(Mono.just(List.of(gradSearchStudent)));

		GraduationStudentRecord graduationStudentRecord = new GraduationStudentRecord();
		graduationStudentRecord.setPen(pen);
		graduationStudentRecord.setStudentID(UUID.fromString(gradSearchStudent.getStudentID()));

		String studentGradData = readFile("data/student_grad_data.json");
		assertNotNull(studentGradData);
		graduationStudentRecord.setStudentGradData(studentGradData);

		when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
		when(this.requestHeadersUriMock.uri(String.format(reportApiConstants.getReadGradStudentRecord(),graduationStudentRecord.getStudentID().toString()))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
		when(this.responseMock.bodyToMono(GraduationStudentRecord.class)).thenReturn(Mono.just(graduationStudentRecord));

		ResponseEntity<byte[]> response = apiReportService.getStudentXmlTranscriptReport(reportRequest);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody());
		byte[] bArray = response.getBody();
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(bArray);
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

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(eCertificateReportRequest.getData())));
		ReportRequestDataThreadLocal.setGenerateReportData(eCertificateReportRequest.getData());

		eCertificateReportRequest.getOptions().setReportFile("Certificate E Report.pdf");
		DocumentBundle eCertificateReport = apiReportService.getStudentCertificateReportDocument(eCertificateReportRequest);

		ReportRequest sccpTranscriptReportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN.json");

		assertNotNull(sccpTranscriptReportRequest);
		assertNotNull(sccpTranscriptReportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(sccpTranscriptReportRequest.getData())));
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
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());

		LOG.debug(">testPackingSlipReport");
	}

	@Test
	public void createTranscriptReportDuplicateInterimCourses_BC2018_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReportDuplicateInterimCourses_BC2018_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		ca.bc.gov.educ.grad.report.model.transcript.Transcript filteredTranscript = transcriptService.getTranscript(reportRequest.getData().getStudent().getPen().getPen());
		List<TranscriptCourse> originalCourses = gradDataConvertionBean.getTranscriptCourses(reportRequest.getData());

		assertTrue(originalCourses.size() > filteredTranscript.getResults().size());

		LOG.debug(">createTranscriptReportDuplicateInterimCourses_BC2018_IND");
	}

	private void testPackingSlipReport(
			final List<ReportDocument> rds,
			final OrderType orderType,
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
		byte[] bArray = packingSlip.asBytes();
		try (OutputStream out = new FileOutputStream("target/PackingSlip" + orderType.getName() + ".pdf")) {
			out.write(bArray);
		}
	}

	/**
	 * Bundles an arbitrary number of transcripts or certificates with a packing
	 * slip without XPIF information.
	 *
	 * @param orderType The type of report document to bundle (transcript or
	 * certificate).
	 *
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

	private School getReportDataSchool(ReportData reportData) {
		reportData.setAccessToken("accessToken");
		if(reportData.getSchool() == null || reportData.getSchool().getMincode() == null) {
			throw new InvalidParameterException("School and mincode can't be NULL");
		}
		return reportData.getSchool();
	}

	private TraxSchool adaptTraxSchool(School school) {
		TraxSchool traxSchool = new TraxSchool();
		traxSchool.setMinCode(school.getMincode());
		traxSchool.setSchoolName(school.getName());
		traxSchool.setAddress1(school.getAddress().getStreetLine1());
		traxSchool.setAddress2(school.getAddress().getStreetLine2());
		traxSchool.setCity(school.getAddress().getCity());
		traxSchool.setProvCode(school.getAddress().getRegion());
		traxSchool.setPostal(school.getAddress().getCode());
		traxSchool.setCountryCode(school.getAddress().getCountry());
		return traxSchool;
	}
	
	private void mockTraxSchool(TraxSchool traxSchool) {
		when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
		when(this.requestHeadersUriMock.uri(String.format(constants.getSchoolDetails(),traxSchool.getMinCode()))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
		when(this.responseMock.bodyToMono(TraxSchool.class)).thenReturn(Mono.just(traxSchool));

	}

}

package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.TraxSchool;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.exception.ServiceException;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
public class GradReportApiCertificateServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(GradReportApiCertificateServiceTests.class);
	private static final String CLASS_NAME = GradReportApiCertificateServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	GradReportService apiReportService;
	@Autowired
	StudentCertificateService certificateService;

	@Before
	public void init() {

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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		assertThrows("REPORT_DATA_NOT_VALID=School is not eligible for certificates", ServiceException.class, () -> {
			apiReportService.getStudentCertificateReport(reportRequest);
		});

		LOG.debug(">createCertificateReport_NOTELIG_E");
	}

	@Test
	public void createCertificateReport_E() throws Exception {
		LOG.debug("<{}.createCertificateReport_E at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_A");
	}

	@Test
	public void createCertificateReport_AO() throws Exception {
		LOG.debug("<{}.createCertificateReport_AO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_AO");
	}

	@Test
	public void createCertificateReport_AB() throws Exception {
		LOG.debug("<{}.createCertificateReport_AB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-AB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_SCO");
	}

	@Test
	public void createCertificateReport_SCI() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCI at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCI.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_SCI");
	}

	@Test
	public void createCertificateReport_SCIB() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCIB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCIB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_SCIB");
	}

	@Test
	public void createCertificateReport_SCIO() throws Exception {
		LOG.debug("<{}.createCertificateReport_SCIO at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-SCIO.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_SCIO");
	}

	@Test
	public void createCertificateReport_S() throws Exception {
		LOG.debug("<{}.createCertificateReport_S at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-S.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createCertificateReport_OO");
	}

	@Test
	public void createCertificateReport_BLANK() throws Exception {
		LOG.debug("<{}.createCertificateReport_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		List<BusinessReport> response = certificateService.buildReport();
		assertNotNull(response);
		assertFalse(response.isEmpty());

		for(BusinessReport report: response) {
			assertNotNull(report.getReportData());
			try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
				out.write(report.getReportData());
			}
		}
		LOG.debug(">createCertificateReport_BLANK");
	}

	@Test
	public void createCertificateReport_NOTBLANK() throws Exception {
		LOG.debug("<{}.createCertificateReport_NOTBLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-NOTBLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		List<BusinessReport> response = certificateService.buildReport();
		assertNotNull(response);
		assertFalse(response.isEmpty());

		for(BusinessReport report: response) {
			assertNotNull(report.getReportData());
			try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
				out.write(report.getReportData());
			}
		}
		LOG.debug(">createCertificateReport_NOTBLANK");
	}

	@Test
	public void createCertificateReportThrowDataException() throws Exception {
		LOG.debug("<{}.createCertificateReportThrowDataException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());
		assertNotNull(reportRequest.getData().getStudent());
		assertNotNull(reportRequest.getData().getStudent().getPen());
		assertNotNull(reportRequest.getData().getStudent().getPen().getPen());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));

		reportRequest.getData().setCertificate(null);
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
		assertThrows("Failed to find student certificate", DomainServiceException.class, () -> {
			certificateService.buildReport();
		});

		LOG.debug(">createCertificateReportThrowDataException");
	}

}

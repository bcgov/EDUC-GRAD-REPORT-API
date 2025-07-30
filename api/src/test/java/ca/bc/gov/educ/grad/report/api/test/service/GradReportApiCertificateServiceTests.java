package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.TraxSchool;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.exception.ReportApiServiceException;
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

		assertThrows("REPORT_DATA_NOT_VALID=School is not eligible for certificates", ReportApiServiceException.class, () -> {
			apiReportService.getStudentCertificateReport(reportRequest);
		});

		LOG.debug(">createCertificateReport_NOTELIG_E");
	}

	@Test
	public void createCertificateReport_E() throws Exception {
		createCertificateReport("createCertificateReport_E", "json/studentCertificateReportRequest-E.json");
	}

	@Test
	public void createCertificateReport_FN() throws Exception {
		createCertificateReport("createCertificateReport_FN", "json/studentCertificateReportRequest-FN.json");
	}

	@Test
	public void createCertificateReport_EO() throws Exception {
		createCertificateReport("createCertificateReport_EO", "json/studentCertificateReportRequest-EO.json");
	}

	@Test
	public void createCertificateReport_EB() throws Exception {
		createCertificateReport("createCertificateReport_EB", "json/studentCertificateReportRequest-EB.json");
	}

	@Test
	public void createCertificateReport_A() throws Exception {
		createCertificateReport("createCertificateReport_A", "json/studentCertificateReportRequest-A.json");
	}

	@Test
	public void createCertificateReport_FNA() throws Exception {
		createCertificateReport("createCertificateReport_FNA", "json/studentCertificateReportRequest-FNA.json");
	}

	@Test
	public void createCertificateReport_AO() throws Exception {
		createCertificateReport("createCertificateReport_AO", "json/studentCertificateReportRequest-AO.json");
	}

	@Test
	public void createCertificateReport_AB() throws Exception {
		createCertificateReport("createCertificateReport_AB", "json/studentCertificateReportRequest-AB.json");
	}

	@Test
	public void createCertificateReport_AIB() throws Exception {
		createCertificateReport("createCertificateReport_AIB", "json/studentCertificateReportRequest-AIB.json");
	}

	@Test
	public void createCertificateReport_EI() throws Exception {
		createCertificateReport("createCertificateReport_EI", "json/studentCertificateReportRequest-EI.json");
	}

	@Test
	public void createCertificateReport_EIB() throws Exception {
		createCertificateReport("createCertificateReport_EIB", "json/studentCertificateReportRequest-EIB.json");
	}

	@Test
	public void createCertificateReport_EIO() throws Exception {
		createCertificateReport("createCertificateReport_EIO", "json/studentCertificateReportRequest-EIO.json");
	}

	@Test
	public void createCertificateReport_AI() throws Exception {
		createCertificateReport("createCertificateReport_AI", "json/studentCertificateReportRequest-AI.json");
	}

	@Test
	public void createCertificateReport_SC() throws Exception {
		createCertificateReport("createCertificateReport_SC", "json/studentCertificateReportRequest-SC.json");
	}

	@Test
	public void createCertificateReport_SCFN() throws Exception {
		createCertificateReport("createCertificateReport_SCFN", "json/studentCertificateReportRequest-SCFN.json");
	}

	@Test
	public void createCertificateReport_SCB() throws Exception {
		createCertificateReport("createCertificateReport_SCB", "json/studentCertificateReportRequest-SCB.json");
	}

	@Test
	public void createCertificateReport_SCO() throws Exception {
		createCertificateReport("createCertificateReport_SCO", "json/studentCertificateReportRequest-SCO.json");
	}

	@Test
	public void createCertificateReport_SCI() throws Exception {
		createCertificateReport("createCertificateReport_SCI", "json/studentCertificateReportRequest-SCI.json");
	}

	@Test
	public void createCertificateReport_SCIB() throws Exception {
		createCertificateReport("createCertificateReport_SCIB", "json/studentCertificateReportRequest-SCIB.json");
	}

	@Test
	public void createCertificateReport_SCIO() throws Exception {
		createCertificateReport("createCertificateReport_SCIO", "json/studentCertificateReportRequest-SCIO.json");
	}

	@Test
	public void createCertificateReport_S() throws Exception {
		createCertificateReport("createCertificateReport_S", "json/studentCertificateReportRequest-S.json");
	}

	@Test
	public void createCertificateReport_SB() throws Exception {
		createCertificateReport("createCertificateReport_SB", "json/studentCertificateReportRequest-SB.json");
	}

	@Test
	public void createCertificateReport_SO() throws Exception {
		createCertificateReport("createCertificateReport_SO", "json/studentCertificateReportRequest-SO.json");
	}

	@Test
	public void createCertificateReport_F() throws Exception {
		createCertificateReport("createCertificateReport_F", "json/studentCertificateReportRequest-F.json");
	}

	@Test
	public void createCertificateReport_FB() throws Exception {
		createCertificateReport("createCertificateReport_FB", "json/studentCertificateReportRequest-FB.json");
	}

	@Test
	public void createCertificateReport_FO() throws Exception {
		createCertificateReport("createCertificateReport_FO", "json/studentCertificateReportRequest-FO.json");
	}

	@Test
	public void createCertificateReport_SCF() throws Exception {
		createCertificateReport("createCertificateReport_SCF", "json/studentCertificateReportRequest-SCF.json");
	}

	@Test
	public void createCertificateReport_SCFB() throws Exception {
		createCertificateReport("createCertificateReport_SCFB", "json/studentCertificateReportRequest-SCFB.json");
	}

	@Test
	public void createCertificateReport_SCFO() throws Exception {
		createCertificateReport("createCertificateReport_SCFO", "json/studentCertificateReportRequest-SCFO.json");
	}

	@Test
	public void createCertificateReport_O() throws Exception {
		createCertificateReport("createCertificateReport_O", "json/studentCertificateReportRequest-O.json");
	}

	@Test
	public void createCertificateReport_OB() throws Exception {
		createCertificateReport("createCertificateReport_OB", "json/studentCertificateReportRequest-OB.json");
		LOG.debug("<{}.createCertificateReport_OB at {}", CLASS_NAME, dateFormat.format(new Date()));
	}

	@Test
	public void createCertificateReport_OO() throws Exception {
		createCertificateReport("createCertificateReport_OO", "json/studentCertificateReportRequest-OO.json");
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

	void createCertificateReport(String methodName, String jsonPath) throws Exception {
		LOG.debug("<{}.{} at {}", CLASS_NAME, methodName, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest(jsonPath);

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentCertificateReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">".concat(methodName));
	}

}


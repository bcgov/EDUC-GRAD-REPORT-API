package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.Student;
import ca.bc.gov.educ.grad.report.api.client.TraxCountry;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.entity.CertificateTypeCodeEntity;
import ca.bc.gov.educ.grad.report.entity.TranscriptTypeCodeEntity;
import ca.bc.gov.educ.grad.report.exception.ReportApiServiceException;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@WebAppConfiguration
public class GradReportApiSchoolServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(GradReportApiSchoolServiceTests.class);
	private static final String CLASS_NAME = GradReportApiSchoolServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportApiConstants reportApiConstants;
	@Autowired
	GradReportService apiReportService;

	@Before
	public void init() {

	}

	@Test
	public void createSchoolDistributionReport() throws Exception {
		LOG.debug("<{}.createSchoolDistributionReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String entityId = "ac339d70-7649-1a2e-8176-4a2e693008cf";

		when(this.studentCertificateRepository.getCertificateDistributionDate(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.studentReportRepository.getReportUpdatedTimestamp(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.certificateTypeCodeRepository.getStudentCertificateTypes(UUID.fromString(entityId))).thenReturn(List.of(
				CertificateTypeCodeEntity.builder().label("Dogwood (Public)").build(),
				CertificateTypeCodeEntity.builder().label("diplôme (Programme francophone)").build()
		));
		when(this.transcriptTypeCodeRepository.getStudentTranscriptTypes(UUID.fromString(entityId))).thenReturn(List.of(
				TranscriptTypeCodeEntity.builder().label("Adult Graduation Program").build(),
				TranscriptTypeCodeEntity.builder().label("Graduation Program 2018").build()
		));

		byte[] response = apiReportService.getSchoolDistributionReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolDistributionReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createSchoolDistributionReportException() throws Exception {
		LOG.debug("<{}.createSchoolDistributionReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getSchoolDistributionReport(reportRequest);
		LOG.debug(">createSchoolDistributionReportException");
	}

	@Test
	public void createDistrictDistributionYearEndReport() throws Exception {
		LOG.debug("<{}.createDistrictDistributionYearEndReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/districtDistributionYearEndReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String entityId = "ac339d70-7649-1a2e-8176-4a2e693008cf";

		when(this.studentCertificateRepository.getCertificateDistributionDate(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.studentReportRepository.getReportUpdatedTimestamp(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.certificateTypeCodeRepository.getStudentCertificateTypes(UUID.fromString(entityId))).thenReturn(List.of(
				CertificateTypeCodeEntity.builder().label("Dogwood (Public)").build(),
				CertificateTypeCodeEntity.builder().label("diplôme (Programme francophone)").build()
		));
		when(this.transcriptTypeCodeRepository.getStudentTranscriptTypes(UUID.fromString(entityId))).thenReturn(List.of(
				TranscriptTypeCodeEntity.builder().label("Adult Graduation Program").build(),
				TranscriptTypeCodeEntity.builder().label("Graduation Program 2018").build()
		));

		byte[] response = apiReportService.getDistrictDistributionReportYearEnd(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createDistrictDistributionYearEndReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createDistrictDistributionYearEndReportException() throws Exception {
		LOG.debug("<{}.createDistrictDistributionYearEndReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/districtDistributionYearEndReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getDistrictDistributionReportYearEnd(reportRequest);

		LOG.debug(">createDistrictDistributionYearEndReport");
	}

	@Test
	public void createDistrictDistributionYearEndNonGradReport() throws Exception {
		LOG.debug("<{}.createDistrictDistributionYearEndNonGradReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/districtDistributionYearEndNonGradReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String entityId = "ac339d70-7649-1a2e-8176-4a2e693008cf";

		when(this.studentCertificateRepository.getCertificateDistributionDate(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.studentReportRepository.getReportUpdatedTimestamp(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.certificateTypeCodeRepository.getStudentCertificateTypes(UUID.fromString(entityId))).thenReturn(List.of(
				CertificateTypeCodeEntity.builder().label("Dogwood (Public)").build(),
				CertificateTypeCodeEntity.builder().label("diplôme (Programme francophone)").build()
		));
		when(this.transcriptTypeCodeRepository.getStudentTranscriptTypes(UUID.fromString(entityId))).thenReturn(List.of(
				TranscriptTypeCodeEntity.builder().label("Adult Graduation Program").build(),
				TranscriptTypeCodeEntity.builder().label("Graduation Program 2018").build()
		));

		byte[] response = apiReportService.getDistrictDistributionReportYearEndNonGrad(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createDistrictDistributionYearEndNonGradReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createDistrictDistributionYearEndNonGradReportException() throws Exception {
		LOG.debug("<{}.createDistrictDistributionYearEndNonGradReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/districtDistributionYearEndNonGradReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getDistrictDistributionReportYearEndNonGrad(reportRequest);

		LOG.debug(">createDistrictDistributionYearEndNonGradReportException");
	}

	@Test
	public void createSchoolDistributionYearEndReport() throws Exception {
		LOG.debug("<{}.createSchoolDistributionYearEndReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolDistributionYearEndReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String entityId = "ac339d70-7649-1a2e-8176-4a2e693008cf";

		when(this.studentCertificateRepository.getCertificateDistributionDate(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.studentReportRepository.getReportUpdatedTimestamp(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.certificateTypeCodeRepository.getStudentCertificateTypes(UUID.fromString(entityId))).thenReturn(List.of(
				CertificateTypeCodeEntity.builder().label("Dogwood (Public)").build(),
				CertificateTypeCodeEntity.builder().label("diplôme (Programme francophone)").build()
		));
		when(this.transcriptTypeCodeRepository.getStudentTranscriptTypes(UUID.fromString(entityId))).thenReturn(List.of(
				TranscriptTypeCodeEntity.builder().label("Adult Graduation Program").build(),
				TranscriptTypeCodeEntity.builder().label("Graduation Program 2018").build()
		));

		byte[] response = apiReportService.getSchoolDistributionReportYearEnd(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolDistributionYearEndReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createSchoolDistributionYearEndReportException() throws Exception {
		LOG.debug("<{}.createSchoolDistributionYearEndReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		apiReportService.getSchoolDistributionReportYearEnd(null);
		LOG.debug(">createSchoolDistributionYearEndReportException");
	}

	@Test
	public void createSchoolDistributionReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createSchoolDistributionReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolDistributionReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String entityId = "ac339d70-7649-1a2e-8176-4a2e693008cf";

		when(this.studentCertificateRepository.getCertificateDistributionDate(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.studentReportRepository.getReportUpdatedTimestamp(UUID.fromString(entityId))).thenReturn(Optional.of(new Date()));
		when(this.certificateTypeCodeRepository.getStudentCertificateTypes(UUID.fromString(entityId))).thenReturn(List.of(
				CertificateTypeCodeEntity.builder().label("Dogwood (Public)").build(),
				CertificateTypeCodeEntity.builder().label("diplôme (Programme francophone)").build()
		));
		when(this.transcriptTypeCodeRepository.getStudentTranscriptTypes(UUID.fromString(entityId))).thenReturn(List.of(
				TranscriptTypeCodeEntity.builder().label("Adult Graduation Program").build(),
				TranscriptTypeCodeEntity.builder().label("Graduation Program 2018").build()
		));

		byte[] response = apiReportService.getSchoolDistributionReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolDistributionReport_NOSTUDENTS");
	}

	@Test
	public void createSchoolLabelReport() throws Exception {
		LOG.debug("<{}.createSchoolLabelReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolLabelReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		TraxCountry traxCountry = new TraxCountry();
		traxCountry.setCountryCode("US");
		traxCountry.setCountryName("USA");
		mockTraxCountry(traxCountry);

		traxCountry = new TraxCountry();
		traxCountry.setCountryCode("AU");
		traxCountry.setCountryName("AUSTRIA");
		mockTraxCountry(traxCountry);

		traxCountry = new TraxCountry();
		traxCountry.setCountryCode("NL");
		traxCountry.setCountryName("NETHERLANDS");
		mockTraxCountry(traxCountry);

		byte[] response = apiReportService.getSchoolLabelReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolLabelReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createSchoolLabelReportException() throws Exception {
		LOG.debug("<{}.createSchoolLabelReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolLabelReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getSchoolLabelReport(reportRequest);

		LOG.debug(">createSchoolLabelReportException");
	}

	@Test
	public void createSchoolGraduationReport() throws Exception {
		LOG.debug("<{}.createSchoolGraduationReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolGraduationReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());
		assertNotNull(reportRequest.getData().getSchool());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		for(Student st: reportRequest.getData().getSchool().getStudents()) {
			if(!StringUtils.isBlank(st.getPen().getEntityID())) {
				mockGraduationStudentRecord(st.getPen().getPen(), st.getPen().getEntityID());
			}
		}
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getSchoolGraduationReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolGraduationReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createSchoolGraduationReportException() throws Exception {
		LOG.debug("<{}.createSchoolGraduationReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolGraduationReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getSchoolGraduationReport(reportRequest);

		LOG.debug(">createSchoolGraduationReportException");
	}

	@Test
	public void createSchoolGraduationReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createSchoolGraduationReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolGraduationReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());
		assertNotNull(reportRequest.getData().getSchool());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		for(Student st: reportRequest.getData().getSchool().getStudents()) {
			if(!StringUtils.isBlank(st.getPen().getEntityID())) {
				mockGraduationStudentRecord(st.getPen().getPen(), st.getPen().getEntityID());
			}
		}
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getSchoolGraduationReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolGraduationReport_NOSTUDENTS");
	}

	@Test
	public void createSchoolNonGraduationReport() throws Exception {
		LOG.debug("<{}.createSchoolNonGraduationReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolNonGraduationReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getSchoolNonGraduationReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolNonGraduationReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createSchoolNonGraduationReportException() throws Exception {
		LOG.debug("<{}.createSchoolNonGraduationReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolNonGraduationReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getSchoolNonGraduationReport(reportRequest);

		LOG.debug(">createSchoolNonGraduationReportException");
	}

	@Test
	public void createSchoolNonGraduationReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createSchoolNonGraduationReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/schoolNonGraduationReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getSchoolNonGraduationReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolNonGraduationReport_NOSTUDENTS");
	}

	@Test
	public void createStudentNonGradReport() throws Exception {
		LOG.debug("<{}.createStudentNonGradReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentNonGradReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentNonGradReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createStudentNonGradReportException() throws Exception {
		LOG.debug("<{}.createStudentNonGradReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getStudentNonGradReport(reportRequest);

		LOG.debug(">createStudentNonGradReportException");
	}

	@Test
	public void createStudentNonGradReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createStudentNonGradReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentNonGradProjectedReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentNonGradReport_NOSTUDENTS");
	}

	@Test
	public void createStudentNonGradProjectedReport() throws Exception {
		LOG.debug("<{}.createStudentNonGradProjectedReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradProjectedReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		for(Student st: reportRequest.getData().getSchool().getStudents()) {
			if(!StringUtils.isBlank(st.getPen().getEntityID())) {
				mockGraduationStudentRecord(st.getPen().getPen(), st.getPen().getEntityID());
			}
		}
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentNonGradProjectedReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentNonGradProjectedReport");
	}

	@Test
	public void createStudentNonGradProjectedReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createStudentNonGradProjectedReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradProjectedReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentNonGradProjectedReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentNonGradProjectedReport_NOSTUDENTS");
	}

	@Test
	public void createStudentGradProjectedReport() throws Exception {
		LOG.debug("<{}.createStudentGradProjectedReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentGradProjectedReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		for(Student st: reportRequest.getData().getSchool().getStudents()) {
			if(!StringUtils.isBlank(st.getPen().getEntityID())) {
				mockGraduationStudentRecord(st.getPen().getPen(), st.getPen().getEntityID());
			}
		}
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentGradProjectedReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentGradProjectedReport");
	}

	@Test
	public void createStudentGradProjectedReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createStudentGradProjectedReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentGradProjectedReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentGradProjectedReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentGradProjectedReport_NOSTUDENTS");
	}

}

package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.Student;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.entity.CertificateTypeCodeEntity;
import ca.bc.gov.educ.grad.report.entity.TranscriptTypeCodeEntity;
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

		byte[] response = apiReportService.getSchoolLabelReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createSchoolLabelReport");
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

	@Test
	public void createStudentNonGradReport_NOSTUDENTS() throws Exception {
		LOG.debug("<{}.createStudentNonGradReport_NOSTUDENTS at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentNonGradReportRequest-NOSTUDENTS.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		byte[] response = apiReportService.getStudentNonGradReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentNonGradReport_NOSTUDENTS");
	}

}
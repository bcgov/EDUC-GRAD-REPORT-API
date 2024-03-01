package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.*;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.impl.GradProgramImpl;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.exception.ReportApiServiceException;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptCourse;
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
import static org.mockito.Mockito.when;

@WebAppConfiguration
public class GradReportApiTranscriptServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(GradReportApiTranscriptServiceTests.class);
	private static final String CLASS_NAME = GradReportApiTranscriptServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportApiConstants reportApiConstants;
	@Autowired
	GradReportService apiReportService;
	@Autowired
	StudentTranscriptService transcriptService;
	@Autowired
	GradDataConvertionBean gradDataConvertionBean;

	@Before
	public void init() {

	}

	@Test
	public void createStudentAchievementReport() throws Exception {
		LOG.debug("<{}.createStudentAchievementReport at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		reportRequest.getData().setAccessToken("accessToken");

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentAchievementReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createStudentAchievementReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createStudentAchievementReportException() throws Exception {
		LOG.debug("<{}.createStudentAchievementReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		reportRequest.getData().setAccessToken("accessToken");

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		when(apiReportService.getStudentAchievementReportDocument(reportRequest)).thenThrow(new ReportApiServiceException(String.format("Unable to retrieve %s", "getStudentAchievementReport(ReportRequest reportRequest)"), new Exception()));
		apiReportService.getStudentAchievementReport(reportRequest);
		LOG.debug(">createStudentAchievementReportException");
	}

	@Test
	public void createStudentAchievementReportError() throws Exception {
		LOG.debug("<{}.createStudentAchievementReportError at {}", CLASS_NAME, dateFormat.format(new Date()));
		String pen = "12345678";
		ReportRequest reportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		reportRequest.getData().setAccessToken("accessToken");

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		reportRequest.getData().setGradProgram(null);
		assertThrows("Graduation Program not exists for the current report generation", EntityNotFoundException.class, () -> {
			apiReportService.getStudentAchievementReportDocument(reportRequest);
		});

		reportRequest.setData(null);
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
		ReportData reportData = ReportRequestDataThreadLocal.getReportData();
		assertNull(reportData);

		assertThrows("Report Data not exists for the current report generation", EntityNotFoundException.class, () -> {
			apiReportService.getStudentAchievementReportDocument(reportRequest);
		});

		LOG.debug(">createStudentAchievementReportError");
	}

	@Test
	public void createTranscriptReport_BC1950_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1950_PUB", "json/studentTranscriptReportRequest-BC1950-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC1950_IND_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1950_IND_BLANK", "json/studentTranscriptReportRequest-BC1950-IND-BLANK.json", GraduationProgramCode.PROGRAM_1950);
	}

	@Test
	public void createTranscriptReport_BC1950_IND() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1950_IND", "json/studentTranscriptReportRequest-BC1950-IND.json");
	}

	@Test
	public void createTranscriptReport_BC1950_IND_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1950_IND_PREVIEW", "json/studentTranscriptReportRequest-BC1950-IND-PREVIEW.json");
	}

	@Test
	public void createTranscriptReport_YU1950_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_YU1950_PUB", "json/studentTranscriptReportRequest-YU1950-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC1986_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1986_PUB", "json/studentTranscriptReportRequest-BC1986-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC1986_IND() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1986_IND", "json/studentTranscriptReportRequest-BC1986-IND.json");
	}

	@Test
	public void createTranscriptReport_BC1986_IND_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1986_IND_BLANK", "json/studentTranscriptReportRequest-BC1986-IND-BLANK.json", GraduationProgramCode.PROGRAM_1986_EN);
	}

	@Test
	public void createTranscriptReport_BC1986_IND_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1986_IND_PREVIEW", "json/studentTranscriptReportRequest-BC1986-IND-PREVIEW.json", GraduationProgramCode.PROGRAM_1986_EN);
	}

	@Test
	public void createTranscriptReport_YU1986_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_YU1986_PUB", "json/studentTranscriptReportRequest-YU1986-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC1996_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1996_PUB", "json/studentTranscriptReportRequest-BC1996-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC1996_IND() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1996_IND", "json/studentTranscriptReportRequest-BC1996-IND.json");
	}

	@Test
	public void createTranscriptReport_BC1996_IND_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1996_IND_BLANK", "json/studentTranscriptReportRequest-BC1996-IND-BLANK.json", GraduationProgramCode.PROGRAM_1996_EN);
	}

	@Test
	public void createTranscriptReport_BC1996_IND_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_BC1996_IND_PREVIEW", "json/studentTranscriptReportRequest-BC1996-IND-PREVIEW.json", GraduationProgramCode.PROGRAM_1996_EN);
	}

	@Test
	public void createTranscriptReport_YU1996_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_YU1996_PUB", "json/studentTranscriptReportRequest-YU1996-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC2004_IND() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2004_IND", "json/studentTranscriptReportRequest-BC2004-IND.json");
	}

	@Test
	public void createTranscriptReport_BC2004_IND_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2004_IND_BLANK", "json/studentTranscriptReportRequest-BC2004-IND-BLANK.json", GraduationProgramCode.PROGRAM_2004_EN);
	}

	@Test
	public void createTranscriptReport_BC2004_IND_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2004_IND_PREVIEW", "json/studentTranscriptReportRequest-BC2004-IND-PREVIEW.json", GraduationProgramCode.PROGRAM_2004_EN);
	}

	@Test
	public void createTranscriptReport_BC2004_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2004_PUB", "json/studentTranscriptReportRequest-BC2004-PUB.json");
	}

	@Test
	public void createTranscriptReport_YU2004_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_YU2004_PUB", "json/studentTranscriptReportRequest-YU2004-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC2023_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2023_PUB", "json/studentTranscriptReportRequest-BC2023-PUB.json");
	}

	@Test
	public void createTranscriptReport_YU2023_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_YU2023_PUB", "json/studentTranscriptReportRequest-YU2023-PUB.json");
	}

	@Test
	public void createTranscriptReport_YU2023_PUB_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_YU2023_PUB_PREVIEW", "json/studentTranscriptReportRequest-YU2023-PUB-PREVIEW.json");
	}

	@Test
	public void createTranscriptReport_BC2023_IND() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2023_IND", "json/studentTranscriptReportRequest-BC2023-IND.json");
	}

	@Test
	public void createTranscriptReport_BC2023_OFF() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2023_OFF", "json/studentTranscriptReportRequest-BC2023-OFF.json");
	}

	@Test
	public void createTranscriptReport_BC2023_PF() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2023_PF", "json/studentTranscriptReportRequest-BC2023-PF.json");
	}


	@Test
	public void createTranscriptReport_BC2018_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_PUB", "json/studentTranscriptReportRequest-BC2018-PUB.json");
	}

	@Test
	public void createTranscriptReport_BC2018_PUB_23() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_PUB", "json/studentTranscriptReportRequest-BC2018-PUB-23.json");
	}

	@Test
	public void createTranscriptReport_BC2018_IND() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_IND", "json/studentTranscriptReportRequest-BC2018-IND.json");
	}

	@Test
	public void createTranscriptReport_BC2018_IND_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_IND_BLANK", "json/studentTranscriptReportRequest-BC2018-IND-BLANK.json", GraduationProgramCode.PROGRAM_2018_EN);
	}

	@Test
	public void createTranscriptReport_BC2023_IND_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2023_IND_BLANK", "json/studentTranscriptReportRequest-BC2023-IND-BLANK.json", GraduationProgramCode.PROGRAM_2023_EN);
	}

	@Test
	public void createTranscriptReport_BC2018_IND_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_IND_PREVIEW", "json/studentTranscriptReportRequest-BC2018-IND-PREVIEW.json", GraduationProgramCode.PROGRAM_2018_EN);
	}

	@Test
	public void createTranscriptReport_BC2018_OFF() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_OFF", "json/studentTranscriptReportRequest-BC2018-OFF.json");
	}

	@Test
	public void createTranscriptReport_BC2018_PF() throws Exception {
		createTranscriptReport("createTranscriptReport_BC2018_PF", "json/studentTranscriptReportRequest-BC2018-PF.json");
	}

	@Test
	public void createTranscriptReport_YU2018_PUB() throws Exception {
		createTranscriptReport("createTranscriptReport_YU2018_PUB", "json/studentTranscriptReportRequest-YU2018-PUB.json");
	}

	@Test
	public void createTranscriptReport_SCCP_EN() throws Exception {
		createTranscriptReport("createTranscriptReport_SCCP_EN", "json/studentTranscriptReportRequest-SCCP-EN.json");
	}

	@Test
	public void createTranscriptReport_SCCP_EN_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_SCCP_EN_BLANK", "json/studentTranscriptReportRequest-SCCP-EN-BLANK.json", GraduationProgramCode.PROGRAM_SCCP);
	}

	@Test
	public void createTranscriptReport_SCCP_EN_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_SCCP_EN_PREVIEW", "json/studentTranscriptReportRequest-SCCP-EN-PREVIEW.json", GraduationProgramCode.PROGRAM_SCCP);
	}

	@Test
	public void createTranscriptReport_SCCP_PF() throws Exception {
		createTranscriptReport("createTranscriptReport_SCCP_PF", "json/studentTranscriptReportRequest-SCCP-PF.json");
	}

	@Test
	public void createTranscriptReport_SCCP_PF_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_SCCP_PF_BLANK", "json/studentTranscriptReportRequest-SCCP-PF-BLANK.json", GraduationProgramCode.PROGRAM_SCCP_PF);
	}

	@Test
	public void createTranscriptReport_SCCP_PF_PREVIEW() throws Exception {
		createTranscriptReport("createTranscriptReport_SCCP_PF_PREVIEW", "json/studentTranscriptReportRequest-SCCP-PF-PREVIEW.json");
	}

	@Test
	public void createTranscriptReport_NOPROG() throws Exception {
		createTranscriptReport("createTranscriptReport_NOPROG", "json/studentTranscriptReportRequest-NOPROG.json");
	}

	@Test
	public void createTranscriptReport_NOPROG_BLANK() throws Exception {
		createTranscriptReport("createTranscriptReport_NOPROG", "json/studentTranscriptReportRequest-NOPROG-BLANK.json", GraduationProgramCode.PROGRAM_NOPROG);
	}

	void createTranscriptReport(String methodName, String jsonPath) throws Exception {
		createTranscriptReport(methodName, jsonPath, null);
	}

	void createTranscriptReport(String methodName, String jsonPath, GraduationProgramCode graduationProgramCode) throws Exception {
		LOG.debug("<{}.{} at {}", CLASS_NAME, methodName, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest(jsonPath);

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		if(graduationProgramCode != null) {
			mockGradProgramEntity(graduationProgramCode.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
			GradProgramImpl gradProgram = new GradProgramImpl(graduationProgramCode, "");
			gradProgram.setProgramCode(graduationProgramCode.getCode());
			gradProgram.setProgramName(graduationProgramCode.getDescription());
			mockGradProgram(gradProgram);
		}

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">".concat(methodName));
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		assertThrows("REPORT_DATA_NOT_VALID=School is not eligible for transcripts", ReportApiServiceException.class, () -> {
			apiReportService.getStudentTranscriptReport(reportRequest);
		});

		LOG.debug(">createTranscriptReport_NOTELIG_NOPROG");
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

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentXmlTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createXmlTranscriptReport");
	}

	@Test(expected = ReportApiServiceException.class)
	public void createXmlTranscriptReportException() throws Exception {
		LOG.debug("<{}.createXmlTranscriptReportException at {}", CLASS_NAME, dateFormat.format(new Date()));
		XmlReportRequest reportRequest = createXmlReportRequest("json/xmlTranscriptReportRequest.json");

		reportRequest.setData(null);

		apiReportService.getStudentXmlTranscriptReport(reportRequest);

		LOG.debug(">createXmlTranscriptReportException");
	}

	@Test
	public void createTranscriptReportDuplicateInterimCourses_BC2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReportDuplicateInterimCourses_BC2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		ca.bc.gov.educ.grad.report.model.transcript.Transcript filteredTranscript = transcriptService.getTranscript(reportRequest.getData().getStudent().getPen().getPen());
		List<TranscriptCourse> originalCourses = gradDataConvertionBean.getTranscriptCourses(reportRequest.getData());

		assertTrue(originalCourses.size() > filteredTranscript.getResults().size());

		LOG.debug(">createTranscriptReportDuplicateInterimCourses_BC2018_PUB");
	}

	@Test
	public void createTranscriptReportThrowDataException() throws Exception {
		LOG.debug("<{}.createTranscriptReportThrowException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());
		assertNotNull(reportRequest.getData().getStudent());
		assertNotNull(reportRequest.getData().getStudent().getPen());
		assertNotNull(reportRequest.getData().getStudent().getPen().getPen());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		ReportRequestDataThreadLocal.setReportData(null);

		assertThrows("Report Data not exists for the current report generation", DataException.class, () -> {
			transcriptService.getTranscript(pen);
		});

		LOG.debug(">createTranscriptReportThrowException");
	}

	@Test
	public void createTranscriptReportThrowProgramException() throws Exception {
		LOG.debug("<{}.createTranscriptReportThrowException at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());
		assertNotNull(reportRequest.getData().getStudent());
		assertNotNull(reportRequest.getData().getStudent().getPen());
		assertNotNull(reportRequest.getData().getStudent().getPen().getPen());

		String pen = reportRequest.getData().getStudent().getPen().getPen();

		reportRequest.getData().setGradProgram(null);
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		assertThrows("Grad Program or Grad Program Code is null", DataException.class, () -> {
			transcriptService.getTranscript(pen);
		});

		LOG.debug(">createTranscriptReportThrowException");
	}
}

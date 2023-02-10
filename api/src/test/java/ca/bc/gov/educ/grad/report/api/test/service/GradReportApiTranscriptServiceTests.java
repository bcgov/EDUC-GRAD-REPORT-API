package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.*;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.impl.GradProgramImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.exception.ServiceException;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptCourse;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
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

import static ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode.PROGRAM_SCCP_PF;
import static org.junit.Assert.*;

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
	BCMPBundleService bcmpBundleService;
	@Autowired
	GradReportSignatureService reportSignatureService;
	@Autowired
	PackingSlipService packingSlipService;
	@Autowired
	StudentTranscriptService transcriptService;
	@Autowired
	StudentCertificateService certificateService;
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
		LOG.debug("<{}.createTranscriptReport_BC1950_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1950_PUB");
	}

	@Test
	public void createTranscriptReport_BC1950_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_1950.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_1950);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_1950.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_1950.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1950_IND");
	}

	@Test
	public void createTranscriptReport_BC1950_IND_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1950_IND_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1950-IND-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1950_IND_PREVIEW");
	}

	@Test
	public void createTranscriptReport_YU1950_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1950_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1950-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1986_IND");
	}

	@Test
	public void createTranscriptReport_BC1986_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_1986_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_1986_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_1986_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_1986_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1986_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC1986_IND_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1986_IND_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1986-IND-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_1986_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_1986_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_1986_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_1986_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1986_IND_PREVIEW");
	}

	@Test
	public void createTranscriptReport_YU1986_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1986_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1986-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1996_IND");
	}

	@Test
	public void createTranscriptReport_BC1996_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_1996_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_1996_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_1996_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_1996_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1996_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC1996_IND_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC1996_IND_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC1996-IND-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_1996_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_1996_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_1996_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_1996_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC1996_IND_PREVIEW");
	}

	@Test
	public void createTranscriptReport_YU1996_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU1996_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU1996-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2004_IND");
	}

	@Test
	public void createTranscriptReport_BC2004_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_2004_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_2004_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_2004_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_2004_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2004_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC2004_IND_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_IND_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-IND-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_2004_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_2004_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_2004_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_2004_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2004_IND_PREVIEW");
	}

	@Test
	public void createTranscriptReport_BC2004_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2004_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2004-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_YU2004_PUB");
	}

	@Test
	public void createTranscriptReport_BC2023_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2023_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2023-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2023_PUB");
	}

	@Test
	public void createTranscriptReport_YU2023_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU2023_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU2023-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_YU2023_PUB");
	}

	@Test
	public void createTranscriptReport_BC2023_IND() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2023_IND at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2023-IND.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2023_IND");
	}

	@Test
	public void createTranscriptReport_BC2023_OFF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2023_OFF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2023-OFF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2023_OFF");
	}

	@Test
	public void createTranscriptReport_BC2023_PF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2023_PF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2023-PF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2023_PF");
	}


	@Test
	public void createTranscriptReport_BC2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2018_IND");
	}

	@Test
	public void createTranscriptReport_BC2018_IND_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_IND_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_2018_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_2018_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_2018_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_2018_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2018_IND_BLANK");
	}

	@Test
	public void createTranscriptReport_BC2018_IND_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_IND_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-IND-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_2018_EN.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_2018_EN);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_2018_EN.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_2018_EN.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2018_IND_PREVIEW");
	}

	@Test
	public void createTranscriptReport_BC2018_OFF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_OFF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-BC2018-OFF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_BC2018_PF");
	}

	/** This method is for testing the transcript spacing and page breaks.
	 * */
	@Test
	public void createTranscriptReport_BC2018_SpaceTest() throws Exception {
		LOG.debug("<{}.createTranscriptReport_BC2018_PF_SpaceTest at {}", CLASS_NAME, dateFormat.format(new Date()));

		String[] files = {
				"json/studentTranscriptReportRequest-BC2018-PF-23.json",
				"json/studentTranscriptReportRequest-BC2018-PF-24.json",
				"json/studentTranscriptReportRequest-BC2018-PF-24-no-grad-reason.json"};

		for (int i = 0; i < files.length; i++) {
			ReportRequest reportRequest = createReportRequest(files[i]);
			assertNotNull(reportRequest);
			assertNotNull(reportRequest.getData());

			mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
			ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

			String pen = reportRequest.getData().getStudent().getPen().getPen();
			reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

			GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
			assertNotNull(graduationStudentRecord);
			assertNotNull(graduationStudentRecord.getLastUpdateDate());

			byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
			
			assertNotNull(response);
			
			try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
				out.write(response);
			}
			LOG.debug(">create " + files[i]);
		}
	}

	@Test
	public void createTranscriptReport_YU2018_PUB() throws Exception {
		LOG.debug("<{}.createTranscriptReport_YU2018_PUB at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-YU2018-PUB.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_SCCP_EN");
	}

	@Test
	public void createTranscriptReport_SCCP_EN_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_EN_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_SCCP.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_SCCP);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_SCCP.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_SCCP.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_SCCP_EN_BLANK");
	}

	@Test
	public void createTranscriptReport_SCCP_EN_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_EN_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_SCCP.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_SCCP);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_SCCP.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_SCCP.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_SCCP_EN_PREVIEW");
	}

	@Test
	public void createTranscriptReport_SCCP_PF() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_PF at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-PF.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_SCCP_PF");
	}

	@Test
	public void createTranscriptReport_SCCP_PF_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_PF_BLANK at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-PF-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(PROGRAM_SCCP_PF.getCode(), null);
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_SCCP_PF);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_SCCP_PF.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_SCCP_PF.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);

		assertNotNull(response);

		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_SCCP_PF_BLANK");
	}

	@Test
	public void createTranscriptReport_SCCP_PF_PREVIEW() throws Exception {
		LOG.debug("<{}.createTranscriptReport_SCCP_PF_PREVIEW at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-PF-PREVIEW.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_SCCP_PF_PREVIEW");
	}

	@Test
	public void createTranscriptReport_NOPROG() throws Exception {
		LOG.debug("<{}.createTranscriptReport_NOPROG at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-NOPROG.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(reportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
		}
		LOG.debug(">createTranscriptReport_NOPROG");
	}

	@Test
	public void createTranscriptReport_NOPROG_BLANK() throws Exception {
		LOG.debug("<{}.createTranscriptReport_NOPROG at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportRequest reportRequest = createReportRequest("json/studentTranscriptReportRequest-NOPROG-BLANK.json");

		assertNotNull(reportRequest);
		assertNotNull(reportRequest.getData());

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		mockGradProgramEntity(GraduationProgramCode.PROGRAM_NOPROG.getCode(), reportRequest.getData().getTranscript().getTranscriptTypeCode().getCode());
		GradProgramImpl gradProgram = new GradProgramImpl(GraduationProgramCode.PROGRAM_NOPROG);
		gradProgram.setProgramCode(GraduationProgramCode.PROGRAM_NOPROG.getCode());
		gradProgram.setProgramName(GraduationProgramCode.PROGRAM_NOPROG.getDescription());
		mockGradProgram(gradProgram);

		byte[] response = apiReportService.getStudentTranscriptReport(reportRequest);
		
		assertNotNull(response);
		
		try (OutputStream out = new FileOutputStream("target/"+reportRequest.getOptions().getReportFile())) {
			out.write(response);
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
		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		String pen = reportRequest.getData().getStudent().getPen().getPen();
		reportRequest.getOptions().setReportFile(String.format(reportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		assertThrows("REPORT_DATA_NOT_VALID=School is not eligible for transcripts", ServiceException.class, () -> {
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

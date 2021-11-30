package ca.bc.gov.educ.api.report.service;

import ca.bc.gov.educ.api.report.model.dto.*;
import ca.bc.gov.educ.api.report.util.ReportApiUtils;
import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import ca.bc.gov.educ.isd.achievement.StudentAchievementReport;
import ca.bc.gov.educ.isd.achievement.StudentAchievementService;
import ca.bc.gov.educ.isd.adaptor.dao.utils.TRAXThreadDataUtility;
import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.grad.GradCertificateService;
import ca.bc.gov.educ.isd.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.isd.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.isd.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.isd.transcript.StudentTranscriptService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportService {

	private static final String CLASS_NAME = ReportService.class.getName();
	private static Logger log = LoggerFactory.getLogger(CLASS_NAME);
	private static final String DIR_REPORT_BASE = "/reports/";
	private static final String DIR_IMAGE_BASE = "/reports/resources/images/";

	@Autowired
	StudentTranscriptService transcriptService;

	@Autowired
	StudentAchievementService achievementService;

	@Autowired
	GradCertificateService gradCertificateService;

	@Autowired
	BCMPBundleService bcmpBundleService;

    public ResponseEntity getStudentAchievementReport(GenerateReportRequest reportRequest) {
    	String _m = "getStudentAchievementReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		TRAXThreadDataUtility.setGenerateReportData(reportRequest.getData());

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			StudentAchievementReport report = achievementService.buildOfficialAchievementReport();
			byte[] resultBinary = report.getReportData();
			if(resultBinary.length > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=" + reportFile);
				response = ResponseEntity
						.ok()
						.headers(headers)
						.contentType(MediaType.APPLICATION_PDF)
						.body(resultBinary);
			} else {
				response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return response;
    	
    }

	public ResponseEntity getStudentTranscriptReport(GenerateReportRequest reportRequest) {
		String _m = "getStudentTranscriptReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		TRAXThreadDataUtility.setGenerateReportData(reportRequest.getData());

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			StudentTranscriptReport report = transcriptService.buildOfficialTranscriptReport();
			byte[] resultBinary = report.getReportData();
			if(resultBinary.length > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=" + reportFile);
				response = ResponseEntity
						.ok()
						.headers(headers)
						.contentType(MediaType.APPLICATION_PDF)
						.body(resultBinary);
			} else {
				response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return response;
	}
	
	public ResponseEntity getStudentCertificateReport(GenerateReportRequest reportRequest) {
		String _m = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		TRAXThreadDataUtility.setGenerateReportData(reportRequest.getData());

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			List<BusinessReport> gradCertificateReports = gradCertificateService.buildReport();

			DocumentBundle documentBundle = bcmpBundleService.createDocumentBundle(reportRequest.getData().getCertificate().getOrderType());
			documentBundle.appendBusinessReport(gradCertificateReports);
			byte[] resultBinary = documentBundle.asBytes();

			if(resultBinary.length > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=" + reportFile);
				response = ResponseEntity
						.ok()
						.headers(headers)
						.contentType(MediaType.APPLICATION_PDF)
						.body(resultBinary);
			} else {
				response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return response;
	}

	protected ResponseEntity getInternalServerErrorResponse(Throwable t) {
		ResponseEntity result = null;

		Throwable tmp = t;
		String message = null;
		if (tmp.getCause() != null) {
			tmp = tmp.getCause();
			message = tmp.getMessage();
		} else {
			message = tmp.getMessage();
		}
		if(message == null) {
			message = tmp.getClass().getName();
		}

		result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
		return result;
	}


	public ResponseEntity getStudentAchvReport(GenerateReportData generateReportData) {
		ResponseEntity response = null;
		Map<String,Object> parameters = new HashMap<>();
		String reportFile = generateReportData.getOptions().getReportFile();
		try {

			List<StudentExam> sExamObjList = generateReportData.getData().getStudentExams();
			parameters.put("hasStudentExam","false");
			if(!sExamObjList.isEmpty()) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(sExamObjList);
				parameters.put("studentExam", jrBeanCollectionDataSource);
				parameters.put("hasStudentExam","true");
			}

			List<StudentCourse> sCourseObjList = generateReportData.getData().getStudentCourses();
			parameters.put("hasStudentCourse","false");
			if(!sCourseObjList.isEmpty()) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(sCourseObjList);
				parameters.put("studentCourse", jrBeanCollectionDataSource);
				parameters.put("hasStudentCourse","true");
			}

			List<StudentAssessment> sAssessmentObjList = generateReportData.getData().getStudentAssessments();
			parameters.put("hasStudentAssessment","false");
			if(!sAssessmentObjList.isEmpty()) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(sAssessmentObjList);
				parameters.put("studentAssessment", jrBeanCollectionDataSource);
				parameters.put("hasStudentAssessment","true");
			}

			List<NonGradReason> nongradList = generateReportData.getData().getNonGradReasons();
			parameters.put("hasNonGradReasons","false");
			if(!nongradList.isEmpty()) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(nongradList);
				parameters.put("nonGradReason", jrBeanCollectionDataSource);
				parameters.put("hasNonGradReasons","true");
			}

			List<OptionalProgram> optionalProgramList = generateReportData.getData().getOptionalPrograms();
			parameters.put("hasOptionalPrograms","false");
			if(!optionalProgramList.isEmpty()) {
				JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(optionalProgramList);
				parameters.put("optionalProgram", jrBeanCollectionDataSource);
				parameters.put("hasOptionalPrograms","true");
			}

			School schoolObj = generateReportData.getData().getSchool();
			if(schoolObj != null) {
				parameters.put("schoolObj",schoolObj);
			}

			Student studentObj = generateReportData.getData().getStudent();
			if(studentObj != null) {
				parameters.put("studentObj",studentObj);
			}

			GraduationStatus graduationStatus = generateReportData.getData().getGraduationStatus();
			if(graduationStatus != null) {
				parameters.put("gradObj",graduationStatus);
			}

			InputStream inputLogo = openImageResource("logo_"+generateReportData.getData().getOrgCode().toLowerCase(Locale.ROOT)+".svg");
			parameters.put("orgImage",inputLogo);

			InputStream inputMainReport = openResource("StudentAchievementReport.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(JRXmlLoader.load(inputMainReport)),parameters,new JREmptyDataSource());

			byte[] resultBinary = JasperExportManager.exportReportToPdf(jasperPrint);
			if(resultBinary.length > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=" + reportFile);
				response = ResponseEntity
						.ok()
						.headers(headers)
						.contentType(MediaType.APPLICATION_PDF)
						.body(resultBinary);
			} else {
				response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			response = getInternalServerErrorResponse(e);
		}
		return response;

	}

	private InputStream openResource(final String resource) throws IOException {
		//final URL url = getReportResource(resource);
		URL url = this.getClass().getResource(DIR_REPORT_BASE + resource);
		return url.openStream();
	}
	private InputStream openImageResource(final String resource) throws IOException {
		//final URL url = getReportResource(resource);
		URL url = this.getClass().getResource(DIR_IMAGE_BASE + resource);
		return url.openStream();
	}

}

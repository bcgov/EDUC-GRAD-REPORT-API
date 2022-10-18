package ca.bc.gov.educ.grad.report.api.service;

import ca.bc.gov.educ.grad.report.api.client.PackingSlip;
import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.XmlReportRequest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementReport;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementService;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.school.*;
import ca.bc.gov.educ.grad.report.model.student.SchoolNonGraduationService;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradReport;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentXmlTranscriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@Service
public class GradReportService {

	private static final String CLASS_NAME = GradReportService.class.getName();
	private static final String EXCEPTION_MSG = "Unable to execute {}";

	private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);
	private static final String DIR_REPORT_BASE = "/reports/";
	private static final String DIR_IMAGE_BASE = "/reports/resources/images/";

	@Autowired
	StudentTranscriptService transcriptService;

	@Autowired
	StudentAchievementService achievementService;

	@Autowired
	StudentCertificateService certificateService;

	@Autowired
	BCMPBundleService bcmpBundleService;

	@Autowired
	PackingSlipService packingSlipService;

	@Autowired
	SchoolDistributionService schoolDistributionService;

	@Autowired
	SchoolGraduationService schoolGraduationService;

	@Autowired
	SchoolNonGraduationService schoolNonGraduationService;

	@Autowired
	StudentNonGradService studentNonGradService;

	@Autowired
	StudentXmlTranscriptService studentXmlTranscriptService;

	public ResponseEntity<byte[]> getPackingSlipReport(ReportRequest reportRequest) {
		String methodName = "getPackingSlipReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			ReportDocument report = getPackingSlipReportDocument(reportRequest);
			byte[] resultBinary = report.asBytes();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;

	}

	public ReportDocument getPackingSlipReportDocument(ReportRequest reportRequest) {

		String methodName = "getPackingSlipReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);


		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		PackingSlip packingSlip = reportRequest.getData().getPackingSlip();

		return packingSlipService.createPackingSlipReport(
				packingSlip.getOrderNumber(),
				packingSlip.getOrderDate(),
				packingSlip.getOrderedBy(),
				packingSlip.getQuantity(),
				packingSlip.getCurrent(),
				packingSlip.getTotal()
		);
	}

    public ResponseEntity<byte[]> getStudentAchievementReport(ReportRequest reportRequest) {
    	String methodName = "getStudentAchievementReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			StudentAchievementReport report = getStudentAchievementReportDocument(reportRequest);
			byte[] resultBinary = report.getReportData();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
    	
    }

	public StudentAchievementReport getStudentAchievementReportDocument(ReportRequest reportRequest) throws IOException {

		String methodName = "getStudentAchievementReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		return achievementService.buildOfficialAchievementReport();
	}

	public ResponseEntity<byte[]> getStudentTranscriptReport(ReportRequest reportRequest) {
		String methodName = "getStudentTranscriptReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			StudentTranscriptReport report = getStudentTranscriptReportDocument(reportRequest);
			byte[] resultBinary = report.getReportData();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public StudentTranscriptReport getStudentTranscriptReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getStudentTranscriptReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		return transcriptService.buildOfficialTranscriptReport();
	}

	public ResponseEntity<byte[]> getStudentXmlTranscriptReport(XmlReportRequest reportRequest) {
		String methodName = "getStudentXmlTranscriptReport(XmlReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			StudentTranscriptReport transcriptReport = getStudentXmlTranscriptReportDocument(reportRequest);
			byte[] resultBinary = transcriptReport.asBytes();
			response = handleBinaryResponse(resultBinary, String.format(reportFile, transcriptReport.getFilename()), MediaType.APPLICATION_XML);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public StudentTranscriptReport getStudentXmlTranscriptReportDocument(XmlReportRequest reportRequest) {
		String methodName = "getStudentXmlTranscriptReportDocument(XmlReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setXmlReportData(reportRequest.getData());

		return studentXmlTranscriptService.buildXmlTranscriptReport();
	}
	
	public ResponseEntity<byte[]> getStudentCertificateReport(ReportRequest reportRequest) {
		String methodName = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			DocumentBundle documentBundle = getStudentCertificateReportDocument(reportRequest);
			byte[] resultBinary = documentBundle.asBytes();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public DocumentBundle getStudentCertificateReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());
		List<BusinessReport> gradCertificateReports = certificateService.buildReport();

		ca.bc.gov.educ.grad.report.model.order.OrderType orderType = new CertificateOrderTypeImpl() {
			@Override
			public String getName() {
				return reportRequest.getData().getCertificate().getOrderType().getName();
			}
		};
		DocumentBundle documentBundle = bcmpBundleService.createDocumentBundle(orderType);
		documentBundle.appendBusinessReport(gradCertificateReports);
		return documentBundle;
	}

	public ResponseEntity<byte[]> getSchoolDistributionReport(ReportRequest reportRequest) {
		String methodName = "getSchoolDistributionReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			SchoolDistributionReport schoolDistributionReport = getSchoolDistributionReportDocument(reportRequest);
			byte[] resultBinary = schoolDistributionReport.asBytes();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public ResponseEntity<byte[]> getSchoolGraduationReport(ReportRequest reportRequest) {
		String methodName = "getSchoolGraduationReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			SchoolGraduationReport schoolGraduationReport = getSchoolGraduationReportDocument(reportRequest);
			byte[] resultBinary = schoolGraduationReport.asBytes();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public ResponseEntity<byte[]> getSchoolNonGraduationReport(ReportRequest reportRequest) {
		String methodName = "getSchoolNonGraduationReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			SchoolNonGraduationReport schoolNonGraduationReport = getSchoolNonGraduationReportDocument(reportRequest);
			byte[] resultBinary = schoolNonGraduationReport.asBytes();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public ResponseEntity<byte[]> getStudentNonGradReport(ReportRequest reportRequest) {
		String methodName = "getStudentNonGradReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity<byte[]> response = null;

		try {
			StudentNonGradReport studentNonGradReport = getStudentNonGradReportDocument(reportRequest);
			byte[] resultBinary = studentNonGradReport.asBytes();
			response = handleBinaryResponse(resultBinary, reportFile);
		} catch (Exception e) {
			log.error(EXCEPTION_MSG, methodName, e);
			response = getInternalServerErrorResponse(e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public SchoolDistributionReport getSchoolDistributionReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolDistributionReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		return schoolDistributionService.buildSchoolDistributionReport();
	}

	public SchoolGraduationReport getSchoolGraduationReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolGraduationReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		return schoolGraduationService.buildSchoolGraduationReport();
	}

	public SchoolNonGraduationReport getSchoolNonGraduationReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolNonGraduationReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		return schoolNonGraduationService.buildSchoolNonGraduationReport();
	}

	public StudentNonGradReport getStudentNonGradReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getStudentNonGradReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		return studentNonGradService.buildStudentNonGradReport();
	}

	protected ResponseEntity<byte[]> getInternalServerErrorResponse(Throwable t) {
		ResponseEntity<byte[]> result = null;

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

		result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.getBytes());
		return result;
	}

	private ResponseEntity<byte[]> handleBinaryResponse(byte[] resultBinary, String reportFile) {
		return handleBinaryResponse(resultBinary, reportFile, MediaType.APPLICATION_PDF);
	}

	private ResponseEntity<byte[]> handleBinaryResponse(byte[] resultBinary, String reportFile, MediaType contentType) {
		ResponseEntity<byte[]> response = null;

		if(resultBinary.length > 0) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + reportFile);
			response = ResponseEntity
					.ok()
					.headers(headers)
					.contentType(contentType)
					.body(resultBinary);
		} else {
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return response;
	}

}

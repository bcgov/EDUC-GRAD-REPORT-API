package ca.bc.gov.educ.grad.report.api.service;

import ca.bc.gov.educ.grad.report.api.client.PackingSlip;
import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementReport;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementService;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.graduation.GradCertificateService;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradReportService {

	private static final String CLASS_NAME = GradReportService.class.getName();
	private static final Logger log = LoggerFactory.getLogger(CLASS_NAME);
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

	@Autowired
	PackingSlipService packingSlipService;

	public ResponseEntity getPackingSlipReport(ReportRequest reportRequest) {
		String _m = "getPackingSlipReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			ReportDocument report = getPackingSlipReportDocument(reportRequest);
			byte[] resultBinary = report.asBytes();
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

	public ReportDocument getPackingSlipReportDocument(ReportRequest reportRequest) {
		String _m = "getPackingSlipReportDocument(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		PackingSlip packingSlip = reportRequest.getData().getPackingSlip();

		try {
			ReportDocument report = packingSlipService.createPackingSlipReport(
					packingSlip.getOrderNumber(),
					packingSlip.getOrderDate(),
					packingSlip.getOrderedBy(),
					packingSlip.getQuantity()
			);
			return report;
		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return null;
	}

    public ResponseEntity getStudentAchievementReport(ReportRequest reportRequest) {
    	String _m = "getStudentAchievementReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			StudentAchievementReport report = getStudentAchievementReportDocument(reportRequest);
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

	public StudentAchievementReport getStudentAchievementReportDocument(ReportRequest reportRequest) {
		String _m = "getStudentAchievementReportDocument(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		try {
			StudentAchievementReport report = achievementService.buildOfficialAchievementReport();
			return report;
		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return null;
	}

	public ResponseEntity getStudentTranscriptReport(ReportRequest reportRequest) {
		String _m = "getStudentTranscriptReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			StudentTranscriptReport report = getStudentTranscriptReportDocument(reportRequest);
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

	public StudentTranscriptReport getStudentTranscriptReportDocument(ReportRequest reportRequest) {
		String _m = "getStudentTranscriptReportDocument(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		try {
			StudentTranscriptReport report = transcriptService.buildOfficialTranscriptReport();
			return report;

		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return null;
	}
	
	public ResponseEntity getStudentCertificateReport(ReportRequest reportRequest) {
		String _m = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		String reportFile = reportRequest.getOptions().getReportFile();

		ResponseEntity response = null;

		try {
			DocumentBundle documentBundle = getStudentCertificateReportDocument(reportRequest);
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

	public DocumentBundle getStudentCertificateReportDocument(ReportRequest reportRequest) {
		String _m = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug("<{}.{}", _m, CLASS_NAME);

		ReportRequestDataThreadLocal.setGenerateReportData(reportRequest.getData());

		try {
			List<BusinessReport> gradCertificateReports = gradCertificateService.buildReport();
			ca.bc.gov.educ.grad.report.model.order.OrderType orderType = new CertificateOrderTypeImpl() {
				@Override
				public String getName() {
					return reportRequest.getData().getCertificate().getOrderType().getName();
				}
			};
			DocumentBundle documentBundle = bcmpBundleService.createDocumentBundle(orderType);
			documentBundle.appendBusinessReport(gradCertificateReports);
			return documentBundle;
		} catch (Exception e) {
			log.error("Unable to execute {}", _m, e);
		}
		log.debug(">{}.{}", _m, CLASS_NAME);
		return null;
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

}
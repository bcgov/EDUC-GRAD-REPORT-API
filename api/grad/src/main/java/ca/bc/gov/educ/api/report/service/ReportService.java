package ca.bc.gov.educ.api.report.service;

import ca.bc.gov.educ.grad.dto.GenerateReportRequest;
import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.grad.GradCertificateService;
import ca.bc.gov.educ.isd.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.isd.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.isd.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.isd.transcript.StudentTranscriptService;
import ca.bc.gov.educ.isd.traxadaptor.dao.utils.TRAXThreadDataUtility;
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
public class ReportService {

	private static final String CLASS_NAME = ReportService.class.getName();
	private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	StudentTranscriptService transcriptService;

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
			byte[] resultBinary = new byte[0];
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
		String _m = "getStudentTranscriptReport(GenerateReportRequest reportRequest)";
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
}

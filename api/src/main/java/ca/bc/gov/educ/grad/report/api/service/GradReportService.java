package ca.bc.gov.educ.grad.report.api.service;

import ca.bc.gov.educ.grad.report.api.client.PackingSlip;
import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.XmlReportRequest;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.SchoolOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.exception.ServiceException;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementReport;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementService;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.graduation.StudentCertificateService;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;
import ca.bc.gov.educ.grad.report.model.school.*;
import ca.bc.gov.educ.grad.report.model.student.SchoolNonGraduationService;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradReport;
import ca.bc.gov.educ.grad.report.model.student.StudentNonGradService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptService;
import ca.bc.gov.educ.grad.report.model.transcript.StudentXmlTranscriptService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@Service
public class GradReportService {

	private static final String CLASS_NAME = GradReportService.class.getName();
	private static final String EXCEPTION_MSG = "Unable to execute %s";

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

	@Qualifier("schoolDistributionServiceImpl")
	@Autowired
	SchoolDistributionService schoolDistributionService;

	@Qualifier("schoolDistributionYearEndServiceImpl")
	@Autowired
	SchoolDistributionService schoolDistributionEndYearService;

	@Qualifier("schoolDistributionYearEndNewCredentialsServiceImpl")
	@Autowired
	SchoolDistributionService schoolDistributionEndYearNewCredentialsService;

	@Qualifier("schoolDistributionYearEndIssuedTranscriptsServiceImpl")
	@Autowired
	SchoolDistributionService schoolDistributionEndYearIssuedTranscriptsService;

	@Autowired
	SchoolLabelService schoolLabelService;

	@Autowired
	SchoolGraduationService schoolGraduationService;

	@Autowired
	SchoolNonGraduationService schoolNonGraduationService;

	@Autowired
	StudentNonGradService studentNonGradService;

	@Autowired
	StudentXmlTranscriptService studentXmlTranscriptService;

	@SneakyThrows
	public byte[] getPackingSlipReport(ReportRequest reportRequest) {
		String methodName = "getPackingSlipReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			ReportDocument report = getPackingSlipReportDocument(reportRequest);
			response = report.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;

	}

	public ReportDocument getPackingSlipReportDocument(ReportRequest reportRequest) {

		String methodName = "getPackingSlipReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);


		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

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

	@SneakyThrows
    public byte[] getStudentAchievementReport(ReportRequest reportRequest) {
    	String methodName = "getStudentAchievementReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			StudentAchievementReport report = getStudentAchievementReportDocument(reportRequest);
			response = report.getReportData();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
    	
    }

	public StudentAchievementReport getStudentAchievementReportDocument(ReportRequest reportRequest) throws IOException {

		String methodName = "getStudentAchievementReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		return achievementService.buildOfficialAchievementReport();
	}

	@SneakyThrows
	public byte[] getStudentTranscriptReport(ReportRequest reportRequest) {
		String methodName = "getStudentTranscriptReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			StudentTranscriptReport report = getStudentTranscriptReportDocument(reportRequest);
			response = report.getReportData();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public StudentTranscriptReport getStudentTranscriptReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getStudentTranscriptReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
		if(reportRequest.getOptions().isPreview())
			return transcriptService.buildUnOfficialTranscriptReport(ReportFormat.PDF);
		else
			return transcriptService.buildOfficialTranscriptReport();
	}

	@SneakyThrows
	public byte[] getStudentXmlTranscriptReport(XmlReportRequest reportRequest) {
		String methodName = "getStudentXmlTranscriptReport(XmlReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			StudentTranscriptReport transcriptReport = getStudentXmlTranscriptReportDocument(reportRequest);
			response = transcriptReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
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

	@SneakyThrows
	public byte[] getStudentCertificateReport(ReportRequest reportRequest) {
		String methodName = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			DocumentBundle documentBundle = getStudentCertificateReportDocument(reportRequest);
			response = documentBundle.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public DocumentBundle getStudentCertificateReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getStudentCertificateReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());
		List<BusinessReport> gradCertificateReports = certificateService.buildReport();

		OrderType orderType = new CertificateOrderTypeImpl() {
			@Override
			public String getName() {
				return reportRequest.getData().getCertificate().getOrderType().getName();
			}
		};
		DocumentBundle documentBundle = bcmpBundleService.createDocumentBundle(orderType);
		documentBundle.appendBusinessReport(gradCertificateReports);
		return documentBundle;
	}

	@SneakyThrows
	public byte[] getSchoolDistributionReport(ReportRequest reportRequest) {
		String methodName = "getSchoolDistributionReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			SchoolDistributionReport schoolDistributionReport = getSchoolDistributionReportDocument(reportRequest);
			response = schoolDistributionReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	@SneakyThrows
	public byte[] getSchoolDistributionReportYearEnd(ReportRequest reportRequest) {
		String methodName = "getSchoolDistributionReportYearEnd(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			DocumentBundle schoolDistributionYearEndReport = getSchoolDistributionYearEndReportDocument(reportRequest);
			response = schoolDistributionYearEndReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	@SneakyThrows
	public byte[] getSchoolLabelReport(ReportRequest reportRequest) {
		String methodName = "getSchoolLabelReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			SchoolLabelReport schoolLabelReport = getSchoolLabelReportDocument(reportRequest);
			response = schoolLabelReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	@SneakyThrows
	public byte[] getSchoolGraduationReport(ReportRequest reportRequest) {
		String methodName = "getSchoolGraduationReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			SchoolGraduationReport schoolGraduationReport = getSchoolGraduationReportDocument(reportRequest);
			response = schoolGraduationReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	@SneakyThrows
	public byte[] getSchoolNonGraduationReport(ReportRequest reportRequest) {
		String methodName = "getSchoolNonGraduationReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			SchoolNonGraduationReport schoolNonGraduationReport = getSchoolNonGraduationReportDocument(reportRequest);
			response = schoolNonGraduationReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	@SneakyThrows
	public byte[] getStudentNonGradReport(ReportRequest reportRequest) {
		String methodName = "getStudentNonGradReport(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		byte[] response = null;

		try {
			StudentNonGradReport studentNonGradReport = getStudentNonGradReportDocument(reportRequest);
			response = studentNonGradReport.asBytes();
		} catch (Exception e) {
			throw new ServiceException(String.format(EXCEPTION_MSG, methodName), e);
		}
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
		return response;
	}

	public SchoolDistributionReport getSchoolDistributionReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolDistributionReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		return schoolDistributionService.buildSchoolDistributionReport();
	}

	public DocumentBundle getSchoolDistributionYearEndReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolDistributionYearEndReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		SchoolDistributionReport newCredentialsReport = null;
		SchoolDistributionReport issuedTranscriptsReport = null;

		Map<String, ReportData> reportDataMap = reportRequest.getDataMap();
		if(reportDataMap != null && reportDataMap.size() == 2) {
			{
				ReportData data = reportDataMap.get("newCredentialsReportData");
				if (data != null) {
					ReportRequestDataThreadLocal.setReportData(data);
					newCredentialsReport = this.schoolDistributionEndYearNewCredentialsService.buildSchoolDistributionReport();
				}
			}
			{
				ReportData data = reportDataMap.get("IssuedTranscriptsReportData");
				if (data != null) {
					ReportRequestDataThreadLocal.setReportData(data);
					issuedTranscriptsReport = this.schoolDistributionEndYearIssuedTranscriptsService.buildSchoolDistributionReport();
				}
			}
		}

		OrderType orderType = new SchoolOrderTypeImpl() {
			@Override
			public String getName() {
				return "School";
			}
		};

		DocumentBundle bundle = bcmpBundleService.createDocumentBundle(orderType);
		bundle = bcmpBundleService.appendReportDocument(bundle, newCredentialsReport);
		bundle = bcmpBundleService.appendReportDocument(bundle, issuedTranscriptsReport);
		// Once the bundle has been created, decorate the page numbers.
		return bcmpBundleService.enumeratePages(bundle);

	}

	public SchoolLabelReport getSchoolLabelReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolLabelReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		return schoolLabelService.buildSchoolLabelReport();
	}

	public SchoolGraduationReport getSchoolGraduationReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolGraduationReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		return schoolGraduationService.buildSchoolGraduationReport();
	}

	public SchoolNonGraduationReport getSchoolNonGraduationReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getSchoolNonGraduationReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		return schoolNonGraduationService.buildSchoolNonGraduationReport();
	}

	public StudentNonGradReport getStudentNonGradReportDocument(ReportRequest reportRequest) throws IOException {
		String methodName = "getStudentNonGradReportDocument(GenerateReportRequest reportRequest)";
		log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

		ReportRequestDataThreadLocal.setReportData(reportRequest.getData());

		return studentNonGradService.buildStudentNonGradReport();
	}
}

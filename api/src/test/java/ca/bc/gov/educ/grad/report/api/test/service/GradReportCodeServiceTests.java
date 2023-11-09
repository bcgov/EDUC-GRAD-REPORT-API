package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.entity.*;
import ca.bc.gov.educ.grad.report.exception.ReportApiServiceException;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@WebAppConfiguration
public class GradReportCodeServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(GradReportCodeServiceTests.class);
	private static final String CLASS_NAME = GradReportCodeServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	GradReportCodeService gradReportCodeService;

	@Before
	public void init() {

	}

	@Test
	public void getCertificateTypeCodes() throws Exception {
		LOG.debug("<{}.getCertificateTypeCodes at {}", CLASS_NAME, dateFormat.format(new Date()));
		CertificateTypeCodeEntity certificateTypeCodeEntity = new CertificateTypeCodeEntity();
		certificateTypeCodeEntity.setCertificateTypeCode("E");
		certificateTypeCodeEntity.setLabel("Dogwood (Public)");
		certificateTypeCodeEntity.setDescription("B.C. Certificate of Graduation - Public School");
		when(certificateTypeCodeRepository.findAll()).thenReturn(List.of(certificateTypeCodeEntity));
		var result = gradReportCodeService.getCertificateTypeCodes();
		assertNotNull(result);
		LOG.debug(">getCertificateTypeCodes");
	}

	@Test(expected = ReportApiServiceException.class)
	public void getCertificateTypeCodesException() {
		LOG.debug("<{}.getCertificateTypeCodes at {}", CLASS_NAME, dateFormat.format(new Date()));
		CertificateTypeCodeEntity certificateTypeCodeEntity = new CertificateTypeCodeEntity();
		certificateTypeCodeEntity.setCertificateTypeCode("E");
		certificateTypeCodeEntity.setLabel("Dogwood (Public)");
		certificateTypeCodeEntity.setDescription("B.C. Certificate of Graduation - Public School");
		when(certificateTypeCodeRepository.findAll()).thenThrow(new ReportApiServiceException(String.format("Unable to retrieve %s", "List<CertificateTypeCode>"), new Exception()));
		var result = gradReportCodeService.getCertificateTypeCodes();
		assertNotNull(result);
		LOG.debug(">getCertificateTypeCodes");
	}

	@Test
	public void getCertificateTypeCode() throws Exception {
		LOG.debug("<{}.getCertificateTypeCode at {}", CLASS_NAME, dateFormat.format(new Date()));
		CertificateTypeCodeEntity certificateTypeCodeEntity = new CertificateTypeCodeEntity();
		certificateTypeCodeEntity.setCertificateTypeCode("E");
		certificateTypeCodeEntity.setLabel("Dogwood (Public)");
		certificateTypeCodeEntity.setDescription("B.C. Certificate of Graduation - Public School");
		when(certificateTypeCodeRepository.findByCertificateCode(certificateTypeCodeEntity.getCertificateTypeCode())).thenReturn((certificateTypeCodeEntity));
		var result = gradReportCodeService.getCertificateTypeCode(certificateTypeCodeEntity.getCertificateTypeCode());
		assertNotNull(result);
		LOG.debug(">getCertificateTypeCode");
	}

	@Test
	public void getTranscriptTypeCodes() throws Exception {
		LOG.debug("<{}.getTranscriptTypeCodes at {}", CLASS_NAME, dateFormat.format(new Date()));
		TranscriptTypeCodeEntity transcriptTypeCodeEntity = new TranscriptTypeCodeEntity();
		transcriptTypeCodeEntity.setTranscriptTypeCode("BC2018-PUB");
		transcriptTypeCodeEntity.setLabel("Graduation Program 2018");
		transcriptTypeCodeEntity.setDescription("2018 Public School Transcript BC");
		when(transcriptTypeCodeRepository.findAll()).thenReturn(List.of(transcriptTypeCodeEntity));
		var result = gradReportCodeService.getTranscriptTypeCodes();
		assertNotNull(result);
		LOG.debug(">getTranscriptTypeCodes");
	}

	@Test
	public void getTranscriptTypeCode() throws Exception {
		LOG.debug("<{}.getTranscriptTypeCode at {}", CLASS_NAME, dateFormat.format(new Date()));
		TranscriptTypeCodeEntity transcriptTypeCodeEntity = new TranscriptTypeCodeEntity();
		transcriptTypeCodeEntity.setTranscriptTypeCode("BC2018-PUB");
		transcriptTypeCodeEntity.setLabel("Graduation Program 2018");
		transcriptTypeCodeEntity.setDescription("2018 Public School Transcript BC");
		when(transcriptTypeCodeRepository.findByTranscriptCode(transcriptTypeCodeEntity.getTranscriptTypeCode())).thenReturn((transcriptTypeCodeEntity));
		var result = gradReportCodeService.getTranscriptTypeCode(transcriptTypeCodeEntity.getTranscriptTypeCode());
		assertNotNull(result);
		LOG.debug(">getTranscriptTypeCode");
	}

	@Test
	public void getSignatureBlockTypeCodes() throws Exception {
		LOG.debug("<{}.getSignatureBlockTypeCodes at {}", CLASS_NAME, dateFormat.format(new Date()));
		SignatureBlockTypeCodeEntity signatureBlockTypeCodeEntity = new SignatureBlockTypeCodeEntity();
		signatureBlockTypeCodeEntity.setSignatureBlockType("MOE");
		signatureBlockTypeCodeEntity.setLabel("Minister of Education and Child Care");
		signatureBlockTypeCodeEntity.setDescription("Minister of Education and Child Care");
		when(signatureBlockTypeRepository.findAll()).thenReturn(List.of(signatureBlockTypeCodeEntity));
		var result = gradReportCodeService.getSignatureBlockTypeCodes();
		assertNotNull(result);
		LOG.debug(">getSignatureBlockTypeCodes");
	}

	@Test
	public void getSignatureBlockTypeCode() throws Exception {
		LOG.debug("<{}.getSignatureBlockTypeCode at {}", CLASS_NAME, dateFormat.format(new Date()));
		SignatureBlockTypeCodeEntity signatureBlockTypeCodeEntity = new SignatureBlockTypeCodeEntity();
		signatureBlockTypeCodeEntity.setSignatureBlockType("MOE");
		signatureBlockTypeCodeEntity.setLabel("Minister of Education and Child Care");
		signatureBlockTypeCodeEntity.setDescription("Minister of Education and Child Care");
		when(signatureBlockTypeRepository.findBySignatureBlockTypeCode(signatureBlockTypeCodeEntity.getSignatureBlockType())).thenReturn((signatureBlockTypeCodeEntity));
		var result = gradReportCodeService.getSignatureBlockTypeCode(signatureBlockTypeCodeEntity.getSignatureBlockType());
		assertNotNull(result);
		LOG.debug(">getSignatureBlockTypeCode");
	}

	@Test
	public void getDocumentStatusTypeCodes() throws Exception {
		LOG.debug("<{}.getDocumentStatusTypeCodes at {}", CLASS_NAME, dateFormat.format(new Date()));
		DocumentStatusCodeEntity documentStatusCodeEntity = new DocumentStatusCodeEntity();
		documentStatusCodeEntity.setDocumentStatusCode("COMPL");
		documentStatusCodeEntity.setLabel("Completed");
		documentStatusCodeEntity.setDescription("Student has met their program requirements");
		when(documentStatusCodeRepository.findAll()).thenReturn(List.of(documentStatusCodeEntity));
		var result = gradReportCodeService.getDocumentStatusCodes();
		assertNotNull(result);
		LOG.debug(">getDocumentStatusCodes");
	}

	@Test
	public void getDocumentStatusCode() throws Exception {
		LOG.debug("<{}.getDocumentStatusCode at {}", CLASS_NAME, dateFormat.format(new Date()));
		DocumentStatusCodeEntity documentStatusCodeEntity = new DocumentStatusCodeEntity();
		documentStatusCodeEntity.setDocumentStatusCode("COMPL");
		documentStatusCodeEntity.setLabel("Completed");
		documentStatusCodeEntity.setDescription("Student has met their program requirements");
		when(documentStatusCodeRepository.findByDocumentStatusCode(documentStatusCodeEntity.getDocumentStatusCode())).thenReturn((documentStatusCodeEntity));
		var result = gradReportCodeService.getDocumentStatusCode(documentStatusCodeEntity.getDocumentStatusCode());
		assertNotNull(result);
		LOG.debug(">getDocumentStatusCode");
	}

	@Test
	public void getReportTypeTypeCodes() throws Exception {
		LOG.debug("<{}.getReportTypeTypeCodes at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportTypeCodeEntity reportTypeCodeEntity = new ReportTypeCodeEntity();
		reportTypeCodeEntity.setReportTypeCode("GRADREG");
		reportTypeCodeEntity.setLabel("Graduated Students (MM YY to MM YY)");
		reportTypeCodeEntity.setDescription("A daily, cumulative list of student in the current cycle who have graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.");
		when(reportTypeCodeRepository.findAll()).thenReturn(List.of(reportTypeCodeEntity));
		var result = gradReportCodeService.getReportTypeCodes();
		assertNotNull(result);
		LOG.debug(">getReportTypeCodes");
	}

	@Test
	public void getReportTypeCode() throws Exception {
		LOG.debug("<{}.getReportTypeCode at {}", CLASS_NAME, dateFormat.format(new Date()));
		ReportTypeCodeEntity reportTypeCodeEntity = new ReportTypeCodeEntity();
		reportTypeCodeEntity.setReportTypeCode("GRADREG");
		reportTypeCodeEntity.setLabel("Graduated Students (MM YY to MM YY)");
		reportTypeCodeEntity.setDescription("A daily, cumulative list of student in the current cycle who have graduated, based on the latest information submitted by the school. Produced as part of the Batch Graduation Algorithm Run.");
		when(reportTypeCodeRepository.findByReportTypeCode(reportTypeCodeEntity.getReportTypeCode())).thenReturn((reportTypeCodeEntity));
		var result = gradReportCodeService.getReportTypeCode(reportTypeCodeEntity.getReportTypeCode());
		assertNotNull(result);
		LOG.debug(">getReportTypeCode");
	}
}

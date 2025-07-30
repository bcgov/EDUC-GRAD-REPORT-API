package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dto.GradReportSignatureImage;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.openMocks;

@WebAppConfiguration
public class GradReportSignatureImageServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(GradReportSignatureImageServiceTests.class);
	private static final String CLASS_NAME = GradReportSignatureImageServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired	GradReportSignatureService reportSignatureService;
	@Autowired	GradReportCodeService gradReportCodeService;

	@BeforeClass
	public static void setup() {

	}

	@After
	public void tearDown() {

	}

	@Before
	public void init() {
		openMocks(this);
	}

	@Test
	public void saveSignatureImageTest() throws Exception {
		LOG.debug("<{}.saveSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "TEST.png";
		GradReportSignatureImage signatureImage = new GradReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		GradReportSignatureImageEntity signatureImages = new GradReportSignatureImageEntity();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		Mockito.when(signatureImageRepository.findById(signatureImage.getSignatureId())).thenReturn(Optional.of(signatureImages));
		Mockito.when(signatureImageRepository.save(signatureImages)).thenReturn(signatureImages);
		signatureImage = reportSignatureService.saveSignatureImage(signatureImage);
		assertNotNull(signatureImage);

		LOG.debug(">saveSignatureImageTest");
	}

	//@Test
	public void getSignatureBlockTypesTest() {
		LOG.debug("<{}.getSignatureBlockTypesTest at {}", CLASS_NAME, dateFormat.format(new Date()));

		List<SignatureBlockTypeCode> signatureBlockTypeCodes = gradReportCodeService.getSignatureBlockTypeCodes();
		assertNotNull(signatureBlockTypeCodes);
		assertTrue(signatureBlockTypeCodes.size() > 0);

		LOG.debug(">getSignatureBlockTypesTest");
	}

	//@Test
	public void saveSignatureBlockTypesTest() {
		LOG.debug("<{}.saveSignatureBlockTypesTest at {}", CLASS_NAME, dateFormat.format(new Date()));

		List<SignatureBlockTypeCode> signatureBlockTypeCodes = gradReportCodeService.getSignatureBlockTypeCodes();
		assertNotNull(signatureBlockTypeCodes);
		assertTrue(signatureBlockTypeCodes.size() > 0);

		SignatureBlockTypeCode saveCode = signatureBlockTypeCodes.get(0);
		assertNotNull(saveCode);

		saveCode.setLabel("New Label");
		saveCode.setDescription("New Description");

		saveCode = gradReportCodeService.saveSignatureBlockTypeCode(saveCode);

		SignatureBlockTypeCode savedCode = gradReportCodeService.getSignatureBlockTypeCode(saveCode.getCode());
		assertEquals("New Label", savedCode.getLabel());
		assertEquals("New Description", savedCode.getDescription());

		LOG.debug(">saveSignatureBlockTypesTest");
	}

}

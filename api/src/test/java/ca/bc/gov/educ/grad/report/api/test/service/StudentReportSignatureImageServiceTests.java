package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dto.GragReportSignatureImage;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@WebAppConfiguration
public class StudentReportSignatureImageServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportSignatureImageServiceTests.class);
	private static final String CLASS_NAME = StudentReportSignatureImageServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	GradReportSignatureService reportSignatureService;
	@Autowired
	GradReportCodeService gradReportCodeService;

	@Before
	public void init() throws Exception {

	}

	@Test
	public void getSignatureImagesTest() throws Exception {
		LOG.debug("<{}.getSignatureImagesTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "MOE.png";
		GragReportSignatureImage signatureImage = new GragReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		reportSignatureService.saveSignatureImage(signatureImage);

		List<GragReportSignatureImage> signatureImages = reportSignatureService.getSignatureImages();
		assertNotNull(signatureImages);
		assertTrue(signatureImages.size() > 0);

		LOG.debug(">getSignatureImagesTest");
	}

	@Test
	public void getSignatureImageTest() throws Exception {
		LOG.debug("<{}.getSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "MOE.png";
		GragReportSignatureImage signatureImage = new GragReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		reportSignatureService.saveSignatureImage(signatureImage);
		signatureImage = reportSignatureService.getSignatureImageByCode(signatureCode);
		assertNotNull(signatureImage);

		LOG.debug(">getSignatureImageTest");
	}

	@Test
	public void saveSignatureImageTest() throws Exception {
		LOG.debug("<{}.saveSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "TEST.png";
		GragReportSignatureImage signatureImage = new GragReportSignatureImage();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());

		signatureImage = reportSignatureService.saveSignatureImage(signatureImage);
		assertNotNull(signatureImage);

		LOG.debug(">saveSignatureImageTest");
	}

	@Test
	public void getSignatureBlockTypesTest() {
		LOG.debug("<{}.getSignatureBlockTypesTest at {}", CLASS_NAME, dateFormat.format(new Date()));

		List<SignatureBlockTypeCode> signatureBlockTypeCodes = gradReportCodeService.getSignatureBlockTypeCodes();
		assertNotNull(signatureBlockTypeCodes);
		assertTrue(signatureBlockTypeCodes.size() > 0);

		LOG.debug(">getSignatureBlockTypesTest");
	}

	@Test
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
		assertEquals(savedCode.getLabel(), "New Label");
		assertEquals(savedCode.getDescription(), "New Description");

		LOG.debug(">saveSignatureBlockTypesTest");
	}

}

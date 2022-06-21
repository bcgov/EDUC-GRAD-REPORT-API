package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dao.SignatureImageRepository;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentReportSignatureImageServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportSignatureImageServiceTests.class);
	private static final String CLASS_NAME = StudentReportSignatureImageServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired	GradReportSignatureService reportSignatureService;
	@Autowired	GradReportCodeService gradReportCodeService;

	@MockBean SignatureImageRepository signatureImageRepository;

	@MockBean WebClient webClient;
	@Autowired
    EducGradReportApiConstants constants;

	@Mock WebClient.RequestHeadersSpec requestHeadersMock;
	@Mock WebClient.RequestHeadersUriSpec requestHeadersUriMock;
	@Mock WebClient.ResponseSpec responseMock;
	@Mock WebClient.RequestBodySpec requestBodyMock;
	@Mock WebClient.RequestBodyUriSpec requestBodyUriMock;

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

	/*@Test
	public void getSignatureImagesTest() throws Exception {
		LOG.debug("<{}.getSignatureImagesTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "023";
		List<GradReportSignatureImageEntity> list = new ArrayList<>();
		GradReportSignatureImageEntity signatureImage = new GradReportSignatureImageEntity();
		signatureImage.setGradReportSignatureCode(signatureCode);
		signatureImage.setSignatureContent(imageBinary);
		signatureImage.setSignatureId(UUID.randomUUID());
		list.add(signatureImage);

		Authentication authentication = Mockito.mock(Authentication.class);
		OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
		// Mockito.whens() for your authorization object
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		Mockito.when(authentication.getDetails()).thenReturn(details);
		SecurityContextHolder.setContext(securityContext);

		District dist = new District();
		dist.setDistrictNumber("023");
		dist.setDistrictName("blah");

		when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
		when(this.requestHeadersUriMock.uri(String.format(constants.getDistrictDetails(), "023"))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
		when(this.responseMock.bodyToMono(District.class)).thenReturn(Mono.just(dist));

		Mockito.when(signatureImageRepository.findAll()).thenReturn(list);

		List<GradReportSignatureImage> signatureImages = reportSignatureService.getSignatureImages(details.getTokenValue());
		assertNotNull(signatureImages);
		assertTrue(signatureImages.size() > 0);

		LOG.debug(">getSignatureImagesTest");
	}*/

	/*@Test
	public void getSignatureImageTest() throws Exception {
		LOG.debug("<{}.getSignatureImageTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		byte[] imageBinary = loadTestImage("reports/resources/images/signatures/MOE.png");
		assertNotNull(imageBinary);
		assertNotEquals(0, imageBinary.length);
		LOG.debug("Test image loaded {} bytes", imageBinary.length);

		String signatureCode = "023";
		UUID signatureId = UUID.randomUUID();

		GradReportSignatureImageEntity signatureImageEntity = new GradReportSignatureImageEntity();
		signatureImageEntity.setGradReportSignatureCode(signatureCode);
		signatureImageEntity.setSignatureContent(imageBinary);
		signatureImageEntity.setSignatureId(signatureId);

		Authentication authentication = Mockito.mock(Authentication.class);
		OAuth2AuthenticationDetails details = Mockito.mock(OAuth2AuthenticationDetails.class);
		// Mockito.whens() for your authorization object
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		Mockito.when(authentication.getDetails()).thenReturn(details);
		SecurityContextHolder.setContext(securityContext);

		District dist = new District();
		dist.setDistrictNumber("023");
		dist.setDistrictName("blah");

		when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
		when(this.requestHeadersUriMock.uri(String.format(constants.getDistrictDetails(), "023"))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
		when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
		when(this.responseMock.bodyToMono(District.class)).thenReturn(Mono.just(dist));

		Mockito.when(signatureImageRepository.findBySignatureCode(signatureCode)).thenReturn(signatureImageEntity);

		GradReportSignatureImage signatureImage = reportSignatureService.getSignatureImageByCode(signatureCode, details.getTokenValue());
		assertNotNull(signatureImage);

		LOG.debug(">getSignatureImageTest");
	}*/

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

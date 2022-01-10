package ca.bc.gov.educ.grad.report.api.test.repository;

import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.dao.SignatureBlockTypeRepository;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.entity.SignatureBlockTypeCodeEntity;
import ca.bc.gov.educ.grad.report.transformer.GradReportSignatureBlockTypeCodeTransformer;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
public class StudentReportApiRepositoryTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(StudentReportApiRepositoryTests.class);
	private static final String CLASS_NAME = StudentReportApiRepositoryTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	SignatureBlockTypeRepository signatureBlockTypeRepository;

	@Autowired
	GradReportSignatureBlockTypeCodeTransformer gradReportSignatureBlockTypeCodeTransformer;

	@Before
	public void init() throws Exception {

	}

	//@Test
	public void getSignatureBlockTypesTest() throws Exception {
		LOG.debug("<{}.getSignatureBlockTypesTest at {}", CLASS_NAME, dateFormat.format(new Date()));
		List<SignatureBlockTypeCodeEntity> dtos = signatureBlockTypeRepository.findAll();
		List<SignatureBlockTypeCode> _result = gradReportSignatureBlockTypeCodeTransformer.transformToDTO(dtos);
		assertNotNull(_result);
		LOG.debug(">getSignatureBlockTypesTest");
	}

}

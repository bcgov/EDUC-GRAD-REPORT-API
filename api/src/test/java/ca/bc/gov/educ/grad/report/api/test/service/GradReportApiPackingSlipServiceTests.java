package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.client.GraduationStudentRecord;
import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.service.GradReportService;
import ca.bc.gov.educ.grad.report.api.test.GradReportBaseTest;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.BCMPBundleService;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.model.achievement.StudentAchievementReport;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.packingslip.PackingSlipService;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ca.bc.gov.educ.grad.report.model.cert.CertificateType.E;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
public class GradReportApiPackingSlipServiceTests extends GradReportBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(GradReportApiPackingSlipServiceTests.class);
	private static final String CLASS_NAME = GradReportApiPackingSlipServiceTests.class.getSimpleName();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ReportApiConstants reportApiConstants;
	@Autowired
	GradReportService apiReportService;
	@Autowired
	BCMPBundleService bcmpBundleService;
	@Autowired
	PackingSlipService packingSlipService;

	@Before
	public void init() {

	}

	@Test
	public void testPackingSlipReport() throws Exception {
		LOG.debug("<{}.testPackingSlipReport at {}", CLASS_NAME, dateFormat.format(new Date()));

		OrderType orderType;

		ReportRequest achievementReportRequest = createReportRequest("json/studentAchievementReportRequest.json");

		assertNotNull(achievementReportRequest);
		assertNotNull(achievementReportRequest.getData());

		achievementReportRequest.getData().setAccessToken("accessToken");

		ReportRequestDataThreadLocal.setReportData(achievementReportRequest.getData());
		achievementReportRequest.getOptions().setReportFile("Student Achievement Report (New).pdf");

		String pen = achievementReportRequest.getData().getStudent().getPen().getPen();
		achievementReportRequest.getOptions().setReportFile(String.format(achievementReportRequest.getOptions().getReportFile(), pen));

		GraduationStudentRecord graduationStudentRecord = mockGraduationStudentRecord(pen, mockGradSearchStudent(pen).getStudentID());
		assertNotNull(graduationStudentRecord);
		assertNotNull(graduationStudentRecord.getLastUpdateDate());

		StudentAchievementReport achievementReport = apiReportService.getStudentAchievementReportDocument(achievementReportRequest);

		ReportRequest eCertificateReportRequest = createReportRequest("json/studentCertificateReportRequest-E.json");

		assertNotNull(eCertificateReportRequest);
		assertNotNull(eCertificateReportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(eCertificateReportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(eCertificateReportRequest.getData());

		eCertificateReportRequest.getOptions().setReportFile("Certificate E Report.pdf");
		DocumentBundle eCertificateReport = apiReportService.getStudentCertificateReportDocument(eCertificateReportRequest);

		ReportRequest sccpTranscriptReportRequest = createReportRequest("json/studentTranscriptReportRequest-SCCP-EN.json");

		assertNotNull(sccpTranscriptReportRequest);
		assertNotNull(sccpTranscriptReportRequest.getData());

		mockTraxSchool(adaptTraxSchool(getReportDataSchool(sccpTranscriptReportRequest.getData())));
		ReportRequestDataThreadLocal.setReportData(sccpTranscriptReportRequest.getData());

		sccpTranscriptReportRequest.getOptions().setReportFile("Transcript SCCP-EN Report.pdf");
		StudentTranscriptReport sccpTranscriptReport = apiReportService.getStudentTranscriptReportDocument(sccpTranscriptReportRequest);

		ReportRequest packingSlipReportRequest = createReportRequest("json/packingSlipReportRequest.json");

		assertNotNull(packingSlipReportRequest);
		assertNotNull(packingSlipReportRequest.getData());

		byte[] packingSlipResponse = apiReportService.getPackingSlipReport(packingSlipReportRequest);
		assertNotNull(packingSlipResponse);

		List<ReportDocument> rds = new ArrayList<>();
		rds.add(achievementReport);

		orderType = bcmpBundleService.createAchievementOrderType();
		packingSlipReportRequest.getData().getPackingSlip().getOrderType().setName("Achievement");
		ReportRequestDataThreadLocal.setReportData(packingSlipReportRequest.getData());
		testPackingSlipReport(
				rds,
				orderType,
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());
		rds.clear();

		rds.add(eCertificateReport);
		orderType = bcmpBundleService.createCertificateOrderType(E);
		packingSlipReportRequest.getData().getPackingSlip().getOrderType().setName("Certificate");
		ReportRequestDataThreadLocal.setReportData(packingSlipReportRequest.getData());
		testPackingSlipReport(
				rds,
				orderType,
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());
		rds.clear();

		rds.add(sccpTranscriptReport);
		orderType = bcmpBundleService.createTranscriptOrderType();
		packingSlipReportRequest.getData().getPackingSlip().getOrderType().setName("Transcript");
		ReportRequestDataThreadLocal.setReportData(packingSlipReportRequest.getData());
		testPackingSlipReport(
				rds,
				orderType,
				packingSlipReportRequest.getData().getPackingSlip().getQuantity(),
				packingSlipReportRequest.getData().getPackingSlip().getCurrent(),
				packingSlipReportRequest.getData().getPackingSlip().getTotal());

		LOG.debug(">testPackingSlipReport");
	}

	private void testPackingSlipReport(
			final List<ReportDocument> rds,
			final OrderType orderType,
			final int quantity,
			final int current,
			final int total)
			throws DomainServiceException, IOException {

		ReportDocument packingSlip = packingSlipService.createPackingSlipReport(
			16895L,
			new Date(),
			"Test Case",
				quantity,
				current,
				total
		);
		byte[] packingSlipByteArray = packingSlip.asBytes();
		try (OutputStream out = new FileOutputStream("target/PackingSlip" + orderType.getName() + ".pdf")) {
			out.write(packingSlipByteArray);
		}

		DocumentBundle documentBundle = createDocumentBundle(packingSlip, rds, orderType);
		byte[] documentBundleByteArray = documentBundle.asBytes();
		try (OutputStream out = new FileOutputStream("target/DocumentBundle" + orderType.getName() + ".pdf")) {
			out.write(documentBundleByteArray);
		}
	}

	/**
	 * Bundles an arbitrary number of transcripts or certificates with a packing
	 * slip without XPIF information.
	 *
	 * @param orderType The type of report document to bundle (transcript or
	 * certificate).
	 *
	 * @throws IOException Could not generate bundle.
	 */
	private DocumentBundle createDocumentBundle(
			final ReportDocument packingSlip,
			final List<ReportDocument> rds,
			final OrderType orderType)
			throws IOException {

		DocumentBundle bundle = bcmpBundleService.createDocumentBundle(orderType);
		bundle = bcmpBundleService.appendReportDocument(bundle, packingSlip);
		bundle = bcmpBundleService.appendReportDocument(bundle, rds);
		// Once the bundle has been created, decorate the page numbers.
		return bcmpBundleService.enumeratePages(bundle);
	}

}

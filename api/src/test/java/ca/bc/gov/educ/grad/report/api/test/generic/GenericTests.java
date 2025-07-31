package ca.bc.gov.educ.grad.report.api.test.generic;

import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.report.dto.impl.GradProgramImpl;
import ca.bc.gov.educ.grad.report.dto.reports.impl.ReportServiceImpl;
import ca.bc.gov.educ.grad.report.model.reports.*;
import ca.bc.gov.educ.grad.report.model.transcript.ParameterPredicate;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptTypeCode;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import ca.bc.gov.educ.grad.report.transformer.GradReportSignatureTransformer;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.Assert.assertNotNull;

public class GenericTests {

    @Autowired
    SignatureImageRepository signatureImageRepository;
    @Autowired
    GradReportSignatureTransformer gradReportSignatureTransformer;
    @Autowired
    @Qualifier("reportApiClient")
    WebClient webClient;
    @Autowired
    EducGradReportApiConstants constants;
    @Autowired
    RESTService restService;

    @Test
    public void reportServiceImplTest() {
        ReportServiceImpl service = new ReportServiceImpl(
                new GradReportSignatureService(signatureImageRepository, gradReportSignatureTransformer, webClient,
                        constants, restService));
        assertNotNull(service);
        TranscriptReport transcriptReport = service.createTranscriptReport(TranscriptTypeCode.BC1950_IND, new GradProgramImpl());
        assertNotNull(transcriptReport);
        CertificateReport certificateReport = service.createCertificateReport();
        assertNotNull(certificateReport);
        GraduationReport schoolDistributionReport = service.createSchoolDistributionReport();
        assertNotNull(schoolDistributionReport);
        GraduationReport schoolGraduationReport = service.createSchoolGraduationReport();
        assertNotNull(schoolGraduationReport);
        GraduationReport nonGradReport = service.createSchoolNonGraduationReport();
        assertNotNull(nonGradReport);
        ParameterPredicate predicate = service.createParameterPredicate();
        assertNotNull(predicate);
        AchievementReport achievementReport = service.createAchievementReport();
        assertNotNull(achievementReport);
        PackingSlipReport packingSlipReport = service.createPackingSlipReport();
        assertNotNull(packingSlipReport);
        Parameters<String, Object> parameters = service.createParameters();
        assertNotNull(parameters);
    }
}

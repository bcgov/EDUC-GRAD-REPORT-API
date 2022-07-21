package ca.bc.gov.educ.grad.report.api.test.generic;

import ca.bc.gov.educ.grad.report.dto.impl.GradProgramImpl;
import ca.bc.gov.educ.grad.report.dto.reports.impl.ReportServiceImpl;
import ca.bc.gov.educ.grad.report.model.reports.*;
import ca.bc.gov.educ.grad.report.model.transcript.ParameterPredicate;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptTypeCode;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GenericTests {

    @Test
    public void reportServiceImplTest() {
        ReportServiceImpl service = new ReportServiceImpl();
        assertNotNull(service);
        TranscriptReport transcriptReport = service.createTranscriptReport(TranscriptTypeCode.BC1950_IND, new GradProgramImpl());
        assertNotNull(transcriptReport);
        CertificateReport certificateReport = service.createCertificateReport();
        assertNotNull(certificateReport);
        GraduationReport graduationReport1 = service.createSchoolDistributionReport();
        assertNotNull(graduationReport1);
        GraduationReport graduationReport2 = service.createSchoolGraduationReport();
        assertNotNull(graduationReport2);
        NonGradReport nonGradReport = service.createNonGradReport();
        assertNotNull(nonGradReport);
        ParameterPredicate predicate = service.createParameterPredicate();
        assertNotNull(predicate);
        AchievementReport achievementReport = service.createAchievementReport();
        assertNotNull(achievementReport);
        PackingSlipReport packingSlipReport = service.createPackingSlipReport();
        assertNotNull(packingSlipReport);
    }
}

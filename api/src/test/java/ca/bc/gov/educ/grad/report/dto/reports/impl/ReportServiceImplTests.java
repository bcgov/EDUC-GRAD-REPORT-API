package ca.bc.gov.educ.grad.report.dto.reports.impl;

import ca.bc.gov.educ.grad.report.dto.impl.GradProgramImpl;
import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.JasperReportImpl;
import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.TranscriptJasperReportImpl;
import ca.bc.gov.educ.grad.report.model.reports.TranscriptReport;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptTypeCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportServiceImplTests {

    ReportServiceImpl reportService;

    @Before
    public void init() {
        this.reportService = new ReportServiceImpl();
    }

    @Test
    public void createJasperReportImpl_WithTranscriptReport_ExpectTranscriptJasperReportImpl(){
        TranscriptReport report = reportService.createTranscriptReport(TranscriptTypeCode.BC1950_IND, new GradProgramImpl());
        JasperReportImpl jasperReport = reportService.createJasperReportImpl(report);
        assertEquals(jasperReport.getClass(), TranscriptJasperReportImpl.class);
    }

}

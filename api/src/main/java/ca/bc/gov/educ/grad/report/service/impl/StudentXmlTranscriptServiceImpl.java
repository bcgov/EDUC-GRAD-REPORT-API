package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.GradSearchStudent;
import ca.bc.gov.educ.grad.report.api.client.GraduationStudentRecord;
import ca.bc.gov.educ.grad.report.api.client.XmlReportData;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.api.service.utils.XmlTransformer;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.impl.StudentTranscriptReportImpl;
import ca.bc.gov.educ.grad.report.dto.reports.xml.AcademicRecordBatch;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;
import ca.bc.gov.educ.grad.report.model.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.grad.report.model.transcript.StudentXmlTranscriptService;
import ca.bc.gov.educ.grad.report.utils.MessageHelper;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.security.DeclareRoles;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_TRANSCRIPT_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;

@Service
@DeclareRoles({STUDENT_TRANSCRIPT_REPORT, USER, FULFILLMENT_SERVICES_USER})
public class StudentXmlTranscriptServiceImpl implements StudentXmlTranscriptService {

    private static final String CLASSNAME = StudentXmlTranscriptServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    WebClient webClient;

    @Autowired
    ReportApiConstants reportApiConstants;

    @Autowired
    XmlTransformer xmlTransformer;

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    MessageHelper messageHelper;

    @Override
    public StudentTranscriptReport buildXmlTranscriptReport() {
        final String methodName = "buildXmlTranscript()";
        LOG.entering(CLASSNAME, methodName);

        String pen = null;

        try {
            XmlReportData reportData = ReportRequestDataThreadLocal.getXmlReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Xml Report Data not exists for the current report generation");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }

            if (reportData.getPen() == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Pen value must not be null");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }

            pen = reportData.getPen().getPen();

            GradSearchStudent student = getStudentByPenFromStudentApi(pen, reportData.getAccessToken());
            if(student == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Student with PEN " + pen + " value not exists in PEN system");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }
            GraduationStudentRecord graduationStudentRecord = getGradStatusFromGradStudentApi(student.getStudentID(), reportData.getAccessToken());
            if(graduationStudentRecord == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Student with PEN " + pen + " value not exists in GRAD Student system");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }
            if(graduationStudentRecord.getStudentGradData() == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        null,
                        "Student with PEN " + pen + " doesn't have graduation data in GRAD Student system");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }
            AcademicRecordBatch academicRecordBatch = (AcademicRecordBatch)jsonTransformer.unmarshall(graduationStudentRecord.getStudentGradData(), AcademicRecordBatch.class);
            setDefaultValues(academicRecordBatch);
            StudentTranscriptReportImpl transcriptReport = new StudentTranscriptReportImpl(
                    xmlTransformer.marshall(academicRecordBatch).getBytes(),
                    ReportFormat.XML,
                    reportData.getPen().getPen(),
                    reportData.getPen().getPen()
            );
            return transcriptReport;

        } catch (Exception ex) {
            String msg = ex.getMessage() == null ? "Failed to access xml transcript data for student with PEN: ".concat(pen) : ex.getMessage();
            final DataException dex = new DataException(null, null, msg, ex);
            LOG.throwing(CLASSNAME, methodName, dex);
            throw dex;
        }

    }

    private GraduationStudentRecord getGradStatusFromGradStudentApi(String studentID, String accessToken) {
        final String methodName = "getGradStatusFromGradStudentApi(String studentID, String accessToken)";
        LOG.entering(CLASSNAME, methodName);
        try
        {
            return webClient.get().uri(String.format(reportApiConstants.getReadGradStudentRecord(),studentID)).headers(h -> h.setBearerAuth(accessToken)).retrieve().bodyToMono(GraduationStudentRecord.class).block();
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

    private GradSearchStudent getStudentByPenFromStudentApi(String pen, String accessToken) {
        final String methodName = "getStudentByPenFromStudentApi(String pen, String accessToken)";
        LOG.entering(CLASSNAME, methodName);
        try {
            List<GradSearchStudent> stuDataList = webClient.get().uri(String.format(reportApiConstants.getPenStudentApiByPenUrl(),pen)).headers(h -> h.setBearerAuth(accessToken)).retrieve().bodyToMono(new ParameterizedTypeReference<List<GradSearchStudent>>() {}).block();
            if(stuDataList != null && !stuDataList.isEmpty()) {
                return stuDataList.get(0);
            }
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

    private void setDefaultValues(AcademicRecordBatch academicRecordBatch) {
        String courseCreditBasis = messageHelper.getDefaultValue("xml.transcript.HighSchoolTranscript.Student.AcademicRecord.AcademicSession.Course.CourseCreditBasis");
        Log.debug(courseCreditBasis);
    }
}

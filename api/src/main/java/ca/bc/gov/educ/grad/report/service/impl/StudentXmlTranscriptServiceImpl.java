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
import jakarta.annotation.security.DeclareRoles;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static ca.bc.gov.educ.grad.report.dto.impl.constants.Roles.STUDENT_TRANSCRIPT_REPORT;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;

@Slf4j
@Service
@DeclareRoles({STUDENT_TRANSCRIPT_REPORT, USER, FULFILLMENT_SERVICES_USER})
public class StudentXmlTranscriptServiceImpl extends BaseServiceImpl implements StudentXmlTranscriptService {

    private static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";
    private static final String STUDENT_MISSING = "STUDENT_MISSING";

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
        log.trace(LOG_TRACE_ENTERING, methodName);

        String pen = null;

        try {
            XmlReportData reportData = ReportRequestDataThreadLocal.getXmlReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        REPORT_DATA_MISSING,
                        "Xml Report Data not exists for the current report generation");
                log.error(methodName, dse);
                throw dse;
            }

            if (reportData.getPen() == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        "PEN_MISSING",
                        "Pen value must not be null");
                log.error(methodName, dse);
                throw dse;
            }

            pen = reportData.getPen().getPen();

            GradSearchStudent student = getStudentByPenFromStudentApi(pen, reportData.getAccessToken());
            if(student == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        STUDENT_MISSING,
                        "Student with PEN " + pen + " value not exists in PEN system");
                log.error(dse.getMessage(), dse);
                throw dse;
            }
            GraduationStudentRecord graduationStudentRecord = getGradStatusFromGradStudentApi(student.getStudentID(), reportData.getAccessToken());
            if(graduationStudentRecord == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        STUDENT_MISSING,
                        "Student with PEN " + pen + " value not exists in GRAD Student system");
                log.error(dse.getMessage(), dse);
                throw dse;
            }
            if(graduationStudentRecord.getStudentGradData() == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        STUDENT_MISSING,
                        "Student with PEN " + pen + " doesn't have graduation data in GRAD Student system");
                log.error(dse.getMessage(), dse);
                throw dse;
            }
            AcademicRecordBatch academicRecordBatch = (AcademicRecordBatch)jsonTransformer.unmarshall(graduationStudentRecord.getStudentGradData(), AcademicRecordBatch.class);
            setDefaultValues(academicRecordBatch);
            StudentTranscriptReportImpl transcriptReport = new StudentTranscriptReportImpl(
                    xmlTransformer.marshallPrettyPrinter(academicRecordBatch).getBytes(),
                    ReportFormat.XML,
                    reportData.getPen().getPen(),
                    reportData.getPen().getPen()
            );
            return transcriptReport;

        } catch (Exception ex) {
            String msg = ex.getMessage() == null ? "Failed to access xml transcript data for student with PEN: ".concat(pen) : ex.getMessage();
            final DataException dex = new DataException(null, null, msg, ex);
            log.error(msg, dex);
            throw dex;
        }

    }

    private void setDefaultValues(AcademicRecordBatch academicRecordBatch) {
        XmlReportData reportData = ReportRequestDataThreadLocal.getXmlReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Xml Report Data not exists for the current report generation");
            log.error(dse.getMessage(), dse);
            throw dse;
        }
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().setRequestTrackingID(reportData.getRequestTrackId());
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().setTransmissionType(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.TransmissionType")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().setDocumentTypeCode(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.DocumentTypeCode")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().setDocumentProcessCode(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.DocumentProcessCode")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().setDocumentOfficialCode(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.DocumentOfficialCode")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().setDocumentCompleteCode(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.DocumentCompleteCode")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getSource().getOrganization().setOrganizationName(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.Source.Organization.OrganizationName")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getSource().getOrganization().setMutuallyDefined(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.Source.Organization.MutuallyDefined")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getSource().getOrganization().getContacts().getAddress().setAddressLine(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.Source.Organization.Contacts.Address.AddressLine")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getSource().getOrganization().getContacts().getAddress().setCity(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.Source.Organization.Contacts.Address.City")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getSource().getOrganization().getContacts().getAddress().setStateProvinceCode(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.Source.Organization.Contacts.Address.StateProvinceCode")
        );
        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getSource().getOrganization().getContacts().getAddress().setPostalCode(
                messageHelper.getDefaultValue("xml.transcript.TransmissionData.Source.Organization.Contacts.Address.PostalCode")
        );

        academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getDestination().getOrganization().setPSIS(reportData.getPsis());

        if(StringUtils.trimToNull(reportData.getOrganizationName())  != null) {
            academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getDestination().getOrganization().setOrganizationName(reportData.getOrganizationName());
            academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getDestination().getOrganization().getContacts().getAddress().setAddressLine(reportData.getAddressLine1());
            academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getDestination().getOrganization().getContacts().getAddress().setCity(reportData.getCity());
            academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getDestination().getOrganization().getContacts().getAddress().setStateProvinceCode(reportData.getStateProvince());
            academicRecordBatch.getHighSchoolTranscript().getTransmissionData().getDestination().getOrganization().getContacts().getAddress().setPostalCode(reportData.getPostalCode());
        }

        academicRecordBatch.getHighSchoolTranscript().getStudent().getPerson().setSchoolAssignedPersonID(reportData.getPen().getEntityID());
        academicRecordBatch.getHighSchoolTranscript().getStudent().getPerson().setNoteMessage("BC: Auth " + reportData.getAuthorizeDate());

    }
}

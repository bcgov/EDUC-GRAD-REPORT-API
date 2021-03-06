package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.TraxSchool;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.dto.impl.*;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.PersonalEducationNumber;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.student.StudentInfo;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static java.lang.Integer.parseInt;

public abstract class GradReportServiceImpl implements Serializable {

    private static final long serialVersionUID = 5L;

    private static final String CLASSNAME = StudentAchievementServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String DIR_IMAGE_BASE = "/reports/resources/images/";

    static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";
    static final String REPORT_DATA_VALIDATION = "REPORT_DATA_NOT_VALID";

    @Autowired
    private ReportService reportService;
    @Autowired
    private GradReportCodeService codeService;
    @Autowired
    private GradDataConvertionBean gradDataConvertionBean;
    @Autowired
    private transient WebClient webClient;
    @Autowired
    private transient EducGradReportApiConstants constants;

    @RolesAllowed({FULFILLMENT_SERVICES_USER})
    public Parameters<String, Object> createParameters() {
        final String methodName = "createParameters()";
        LOG.entering(CLASSNAME, methodName);

        Parameters<String, Object> parameters = reportService.createParameters();

        LOG.exiting(CLASSNAME, methodName);
        return parameters;
    }

    InputStream openImageResource(final String resource) throws IOException {
        //final URL url = getReportResource(resource);
        URL url = this.getClass().getResource(DIR_IMAGE_BASE + resource);
        return url.openStream();
    }

    String getAccessToken() throws DomainServiceException {
        final String methodName = "getAccessToken()";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        String accessToken = reportData.getAccessToken();
        assert accessToken != null;
        LOG.exiting(CLASSNAME, methodName);
        return accessToken;
    }

    PersonalEducationNumber getStudentPEN() throws DomainServiceException {
        final String methodName = "getStudentPEN()";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        PersonalEducationNumberObject pen = new PersonalEducationNumberObject();
        pen.setPen(reportData.getStudent().getPen().getPen());

        LOG.log(Level.FINE, "Confirmed the user is a student and retrieved the PEN: {0}.", pen);
        LOG.exiting(CLASSNAME, methodName);
        return pen;
    }

    Date getIssueDate() throws DataException, DomainServiceException {
        final String methodName = "getIssueDate()";
        LOG.entering(CLASSNAME, methodName);

        final Date issueDate;

        try {

            ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        REPORT_DATA_MISSING,
                        "Report Data not exists for the current report generation");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }

            LOG.log(Level.FINER,
                    "Retrieved issue date: {0}", reportData.getIssueDate());

            issueDate = reportData.getIssueDate();

        } catch (Exception ex) {
            String msg = "Failed to obtain issue date: ".concat(ex.getMessage());
            final DataException dex = new DataException(null, null, msg, ex);
            LOG.throwing(CLASSNAME, methodName, dex);
            throw dex;
        }

        LOG.exiting(CLASSNAME, methodName);
        return issueDate;
    }

    /**
     * Read the static student data from TRAX which is needed for the achievement
     * service.
     *
     * @param pen
     * @return
     */
    StudentInfo getStudentInfo(final String pen) throws DataException, DomainServiceException {
        final String methodName = "getStudentInfo(String)";
        LOG.entering(CLASSNAME, methodName);

        final StudentInfo studentInfo;

        try {

            ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

            if (reportData == null) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        REPORT_DATA_MISSING,
                        "Report Data not exists for the current report generation");
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }

            LOG.log(Level.FINER,
                    "Retrieved student info for PEN: {0}", pen);

            StudentInfoImpl student = (StudentInfoImpl) gradDataConvertionBean.getStudentInfo(reportData);

            if (student == null) {
                final String msg = "Failed to find results for PEN: ".concat(pen);
                final DomainServiceException dse = new DomainServiceException(null, msg);
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            } else {
                LOG.log(Level.FINEST, "Retrieved student from achievement:");
                final HashMap<String, String> reasons = gradDataConvertionBean.getNongradReasons(reportData);
                student.setNonGradReasons(reasons);
                studentInfo = student;
                LOG.log(Level.FINEST, "{0} {1} {2}",
                        new Object[]{studentInfo.getPen(), studentInfo.getFirstName(), studentInfo.getLastName()});
            }

        } catch (Exception ex) {
            String msg = "Failed to access data for student with PEN: ".concat(pen);
            final DataException dex = new DataException(null, null, msg, ex);
            LOG.throwing(CLASSNAME, methodName, dex);
            throw dex;
        }

        LOG.log(Level.FINE, "Completed call to TRAX.");
        LOG.exiting(CLASSNAME, methodName);
        return studentInfo;
    }

    /**
     * Adapt the TRAX data from the data value object into a Student object.
     *
     * @param pen
     * @param studentInfo
     */
    Student adaptStudent(
            final PersonalEducationNumber pen,
            final StudentInfo studentInfo) {

        final String methodName = "adaptStudent(PersonalEducationNumber, StudentInfo)";
        final Object[] params = {pen, studentInfo};
        LOG.entering(CLASSNAME, methodName, params);

        final StudentImpl student = new StudentImpl();
        student.setPen(pen);
        student.setFirstName(studentInfo.getFirstName());
        student.setMiddleName(studentInfo.getMiddleName());
        student.setLastName(studentInfo.getLastName());
        student.setBirthdate(studentInfo.getBirthdate());
        student.setGrade(studentInfo.getGrade());
        student.setGender(studentInfo.getGender());
        student.setHasOtherProgram(studentInfo.getHasOtherProgram());
        student.setGradProgram(studentInfo.getGradProgram());
        student.setOtherProgramParticipation(studentInfo.getOtherProgramParticipation());

        final PostalAddressImpl address = new PostalAddressImpl();
        address.setStreetLine1(studentInfo.getStudentAddress1());
        address.setStreetLine2(studentInfo.getStudentAddress2());
        address.setCity(studentInfo.getStudentCity());
        address.setCode(studentInfo.getStudentPostalCode());
        address.setRegion(studentInfo.getStudentProv());
        address.setCountry(studentInfo.getCountryCode());
        student.setCurrentMailingAddress(address);

        final Map<String, SignatureBlockTypeCode> signatureBlockTypeCodes = codeService.getSignatureBlockTypeCodesMap();
        final Map<String, SignatureBlockType> signatureBlockTypes = new HashMap<>();
        signatureBlockTypes.putAll(signatureBlockTypeCodes);
        student.setSignatureBlockTypes(signatureBlockTypes);

        LOG.exiting(CLASSNAME, methodName);
        return student;
    }

    /**
     * Adapt the TRAX data from the data value object into a School object.
     *
     * @param studentInfo
     */
    School adaptSchool(final StudentInfo studentInfo, String accessToken, boolean checkEligibility) {
        final String m_ = "adaptSchool(StudentInfo)";
        LOG.entering(CLASSNAME, m_, studentInfo);

        SchoolImpl school = new SchoolImpl();
        if(checkEligibility) {
            TraxSchool traxSchool = getSchool(studentInfo.getMincode(), accessToken);
            if (traxSchool != null && "N".equalsIgnoreCase(traxSchool.getTranscriptEligibility())) {
                EntityNotFoundException dse = new EntityNotFoundException(
                        getClass(),
                        REPORT_DATA_VALIDATION,
                        "School is not eligible for transcripts");
                LOG.throwing(CLASSNAME, m_, dse);
                throw dse;
            }
            if (traxSchool != null) {
                populateSchoolFromTraxSchool(school, traxSchool);
                school.setTypeIndicator(studentInfo.getSchoolTypeIndicator());
                school.setTypeBanner(studentInfo.getSchoolTypeBanner());
            } else {
                populateSchoolFromStudentInfo(school, studentInfo);
            }
        } else {
            populateSchoolFromStudentInfo(school, studentInfo);
        }

        LOG.exiting(CLASSNAME, m_);
        return school;
    }

    void populateSchoolFromStudentInfo(SchoolImpl school, StudentInfo studentInfo) {
        school.setMincode(studentInfo.getMincode());
        school.setName(studentInfo.getSchoolName());

        school.setTypeIndicator(studentInfo.getSchoolTypeIndicator());
        school.setTypeBanner(studentInfo.getSchoolTypeBanner());

        final CanadianPostalAddressImpl address = new CanadianPostalAddressImpl();
        address.setStreetLine1(studentInfo.getSchoolStreet());
        address.setStreetLine2(studentInfo.getSchoolStreet2());
        address.setCity(studentInfo.getSchoolCity());
        address.setPostalCode(studentInfo.getSchoolPostalCode());
        address.setProvince(studentInfo.getSchoolProv());
        address.setCountry(studentInfo.getCountryCode());
        school.setAddress(address);
    }

    void populateSchoolFromTraxSchool(SchoolImpl school, TraxSchool traxSchool) {
        school.setMincode(traxSchool.getMinCode());
        school.setName(traxSchool.getSchoolName());
        final CanadianPostalAddressImpl address = new CanadianPostalAddressImpl();
        address.setStreetLine1(traxSchool.getAddress1());
        address.setStreetLine2(traxSchool.getAddress2());
        address.setCity(traxSchool.getCity());
        address.setPostalCode(traxSchool.getPostal());
        address.setProvince(traxSchool.getProvCode());
        address.setCountry(traxSchool.getCountryCode());
        school.setAddress(address);
    }

    /**
     * The number of credits can be a pure numeric value or adorned with extra
     * characters (e.g., 2, 2p, (4)). This parses the numeric value regardless
     * of whether there are non-numeric characters present.
     *
     * @param credits The number of credits to parse.
     * @return The parsed value, or 0 if there were no digits present.
     */
    int parseCredits(final String credits) {
        final String methodName = "parseCredits(String)";
        LOG.entering(CLASSNAME, methodName);

        // Strip out any non-digits.
        final String numericCredits = credits.replaceAll("[^\\d.]", "");
        int result = 0;

        try {
            if (!numericCredits.isEmpty()) {
                result = parseInt(numericCredits);
            }
        } catch (final Exception ex) {
            LOG.log(Level.WARNING, String.format("Could not parse credits: %s", credits), ex);
        }

        LOG.exiting(CLASSNAME, methodName);
        return result;

    }

    TraxSchool getSchool(String minCode, String accessToken) {
        try {
            return webClient.get()
                    .uri(String.format(constants.getSchoolDetails(), minCode))
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(TraxSchool.class)
                    .block();
        } catch (Exception ex) {
            LOG.log(Level.WARNING, String.format("Could not retrieve school with mincode: %s", minCode));
            return null;
        }
    }

    String createReportTypeName(
            final String reportName,
            final Locale locale) {
        return reportName
                + " "
                + locale.getISO3Language();
    }
}

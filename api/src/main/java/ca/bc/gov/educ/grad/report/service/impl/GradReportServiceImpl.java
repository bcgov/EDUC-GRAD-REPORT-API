package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.TraxSchool;
import ca.bc.gov.educ.grad.report.dao.GradDataConvertionBean;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.dto.impl.*;
import ca.bc.gov.educ.grad.report.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.report.model.cert.Certificate;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.PersonalEducationNumber;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.student.StudentInfo;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static java.lang.Integer.parseInt;
import static java.util.Locale.CANADA;

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

    List<Student> getStudents(ReportData reportData) {
        final List<Student> students = gradDataConvertionBean.getStudents(reportData); //validated
        students.removeIf(p -> "SCCP".equalsIgnoreCase(p.getGradProgram()));
        sortStudentsByLastUpdateDateAndNames(students);
        return students;
    }

    void sortStudentsByLastUpdateDateAndNames(List<Student> students) {
        students.sort(Comparator
                .comparing(Student::getLastUpdateDate, Comparator.nullsFirst(Comparator.naturalOrder())).reversed()
                .thenComparing(Student::getLastName, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Student::getFirstName, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Student::getMiddleName, Comparator.nullsLast(Comparator.naturalOrder())));
    }

    School getSchool(ReportData reportData) {
        return gradDataConvertionBean.getSchool(reportData);
    }

    Student getStudent(ReportData reportData) {
        return gradDataConvertionBean.getStudent(reportData);
    }

    ReportData getReportData(String methodName) {
        ReportData reportData = ReportRequestDataThreadLocal.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }
        return reportData;
    }

    Certificate getCertificate(ReportData reportData) {
        return gradDataConvertionBean.getCertificate(reportData.getCertificate());
    }


    InputStream openImageResource(final String resource) throws IOException {
        //final URL url = getReportResource(resource);
        URL url = this.getClass().getResource(DIR_IMAGE_BASE + resource);
        assert url != null;
        return url.openStream();
    }

    GraduationReport getGraduationReport(String methodName) throws IOException {
        Parameters<String, Object> parameters = createParameters();

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        // validate incoming data for reporting
        final List<Student> students = getStudents(reportData);
        final School school = getSchool(reportData);

        if(!students.isEmpty()) {
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(students);
            parameters.put("students", jrBeanCollectionDataSource);
            parameters.put("hasStudents", "true");
        }

        if (school != null) {
            parameters.put("school", school);
        }

        InputStream inputLogo = openImageResource("logo_" + reportData.getOrgCode().toLowerCase(Locale.ROOT) + ".svg");
        parameters.put("orgImage", inputLogo);

        parameters.put("reportNumber", reportData.getReportNumber());

        GraduationReport nonGraduationReport = createGraduationReport();
        nonGraduationReport.setLocale(CANADA);
        nonGraduationReport.setStudents(students);
        nonGraduationReport.setSchool(school);
        nonGraduationReport.setParameters(parameters);

        return nonGraduationReport;
    }

    abstract GraduationReport createGraduationReport();

    String getAccessToken() throws DomainServiceException {
        final String methodName = "getAccessToken()";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = getReportData(methodName);
        String accessToken = reportData.getAccessToken();

        assert accessToken != null;
        LOG.exiting(CLASSNAME, methodName);
        return accessToken;
    }

    PersonalEducationNumber getStudentPEN() throws DomainServiceException {
        final String methodName = "getStudentPEN()";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = getReportData(methodName);

        LOG.exiting(CLASSNAME, methodName);

        return gradDataConvertionBean.getStudent(reportData).getPen();
    }

    Date getIssueDate() throws DomainServiceException {
        final String methodName = "getIssueDate()";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINER,
                "Retrieved issue date: {0}", reportData.getIssueDate());

        LOG.exiting(CLASSNAME, methodName);
        return reportData.getIssueDate();
    }

    /**
     * Read the static student data from TRAX which is needed for the achievement
     * service.
     *
     * @param pen Student Pen
     * @return StudentInfo
     */
    StudentInfo getStudentInfo(final String pen) throws DomainServiceException {
        final String methodName = "getStudentInfo(String)";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINER,
                "Retrieved student info for PEN: {0}", pen);

        StudentInfoImpl student = (StudentInfoImpl) gradDataConvertionBean.getStudentInfo(reportData);
        final HashMap<String, String> reasons = gradDataConvertionBean.getNongradReasons(reportData);
        student.setNonGradReasons(reasons);

        LOG.log(Level.FINE, "Completed call to TRAX.");
        LOG.exiting(CLASSNAME, methodName);
        return student;
    }

    /**
     * Adapt the TRAX data from the data value object into a Student object.
     *
     * @param pen Student pen
     * @param studentInfo Student info
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
        student.setGradReqYear(studentInfo.getGradReqYear());
        student.setOtherProgramParticipation(studentInfo.getOtherProgramParticipation());
        student.setLocalId(studentInfo.getLocalId());

        final PostalAddressImpl address = new PostalAddressImpl();
        address.setStreetLine1(studentInfo.getStudentAddress1());
        address.setStreetLine2(studentInfo.getStudentAddress2());
        address.setCity(studentInfo.getStudentCity());
        address.setCode(studentInfo.getStudentPostalCode());
        address.setRegion(studentInfo.getStudentProv());
        address.setCountry(studentInfo.getCountryCode());
        student.setCurrentMailingAddress(address);

        final Map<String, SignatureBlockTypeCode> signatureBlockTypeCodes = codeService.getSignatureBlockTypeCodesMap();
        final Map<String, SignatureBlockType> signatureBlockTypes = new HashMap<>(signatureBlockTypeCodes);
        student.setSignatureBlockTypes(signatureBlockTypes);

        LOG.exiting(CLASSNAME, methodName);
        return student;
    }

    /**
     * Adapt the TRAX data from the data value object into a School object.
     *
     * @param studentInfo Student Info
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
        school.setSchoolCategoryCode(traxSchool.getSchoolCategoryCode());
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

        if (!numericCredits.isEmpty()) {
            result = parseInt(numericCredits);
        }

        LOG.exiting(CLASSNAME, methodName);
        return result;

    }

    TraxSchool getSchool(String minCode, String accessToken) {
        if(!StringUtils.isBlank(minCode)) {
            return webClient.get()
                    .uri(String.format(constants.getSchoolDetails(), minCode))
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(TraxSchool.class)
                    .block();
        }
        return null;
    }

    GradProgramImpl getGraduationProgram(String programCode, String accessToken) {
        if(!StringUtils.isBlank(programCode)) {
            return webClient.get()
                    .uri(String.format(constants.getGraduationProgram(), programCode))
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(GradProgramImpl.class)
                    .block();
        }
        return null;
    }

    String createReportTypeName(
            final String reportName,
            final Locale locale) {
        return reportName
                + " "
                + locale.getISO3Language();
    }
}

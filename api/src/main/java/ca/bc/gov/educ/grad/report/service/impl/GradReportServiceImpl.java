package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.ReportData;
import ca.bc.gov.educ.grad.report.api.client.TraxSchool;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
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
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportService;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.PersonalEducationNumber;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.student.StudentInfo;
import ca.bc.gov.educ.grad.report.service.GradReportCodeService;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import ca.bc.gov.educ.grad.report.utils.TotalCounts;
import jakarta.annotation.security.RolesAllowed;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.dto.reports.data.adapter.BusinessEntityAdapter.validate;
import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_ISO_8601_FULL;
import static ca.bc.gov.educ.grad.report.model.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;
import static java.lang.Integer.parseInt;
import static java.util.Locale.CANADA;

public abstract class GradReportServiceImpl {

    private static final long serialVersionUID = 5L;

    static final String CLASSNAME = GradReportServiceImpl.class.getName();
    static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String DIR_IMAGE_BASE = "/reports/resources/images/%s";

    static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";
    static final String REPORT_DATA_VALIDATION = "REPORT_DATA_NOT_VALID";

    @Autowired
    ReportService reportService;
    @Autowired
    GradReportCodeService codeService;
    @Autowired
    GradDataConvertionBean gradDataConvertionBean;
    @Autowired
    JsonTransformer jsonTransformer;
    @Autowired
    WebClient webClient;
    @Autowired
    EducGradReportApiConstants constants;

    @RolesAllowed({FULFILLMENT_SERVICES_USER})
    public Parameters<String, Object> createParameters() {
        final String methodName = "createParameters()";
        LOG.entering(CLASSNAME, methodName);

        Parameters<String, Object> parameters = reportService.createParameters();

        LOG.exiting(CLASSNAME, methodName);
        return parameters;
    }

    Pair<List<Student>, TotalCounts> getStudents(ReportData reportData, List<String> excludePrograms) {
        final Pair<List<Student>, TotalCounts> students = gradDataConvertionBean.getStudents(reportData); //validated
        for(String program: excludePrograms) {
            students.getFirst().removeIf(p -> program.equalsIgnoreCase(p.getGradProgram()));
        }
        return students;
    }

    void sortStudentsByNames(List<Student> students) {
        students.sort(Comparator
                .comparing(Student::getLastName, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Student::getFirstName, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Student::getMiddleName, Comparator.nullsLast(Comparator.naturalOrder())));
    }

    void sortStudentsByLastUpdateDateAndNames(List<Student> students) {
        students.sort(Comparator
                .comparing(Student::getStringLastUpdateDate, Comparator.nullsFirst(Comparator.naturalOrder())).reversed()
                .thenComparing(Student::getLastName, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Student::getFirstName, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Student::getMiddleName, Comparator.nullsLast(Comparator.naturalOrder())));
    }

    void sortStudentsByProgramCompletionDateAndNames(List<Student> students) {
        students.sort(Comparator
                //.comparing(Student::getProgramCompletionDate, Comparator.nullsFirst(Comparator.naturalOrder())).reversed()
                .comparing(Student::getLastName, Comparator.nullsLast(Comparator.naturalOrder()))
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
        ReportData reportData = ReportRequestDataThreadLocal.getReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    getClass(),
                    REPORT_DATA_MISSING,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        reportData.setReportIdentity(methodName);
        return reportData;
    }

    Certificate getCertificate(ReportData reportData) {
        return gradDataConvertionBean.getCertificate(reportData.getCertificate());
    }


    InputStream openImageResource(final String resource) throws IOException {
        /** final URL url = getReportResource(resource); **/
        validateResourcePath(resource);
        URL url = this.getClass().getResource(String.format(DIR_IMAGE_BASE, resource));
        assert url != null;
        return url.openStream();
    }

    void validateResourcePath(String resource) {
        if(StringUtils.isBlank(resource) || resource.contains("..") || resource.contains("/") || resource.contains("\\")) {
            throw new IllegalArgumentException("Invalid resource path");
        }
    }

    GraduationReport getGraduationReport(String methodName, List<String> excludePrograms) throws IOException {
        Parameters<String, Object> parameters = createParameters();

        ReportData reportData = getReportData(methodName);

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        // validate incoming data for reporting
        final Pair<List<Student>, TotalCounts> studentsResult = getStudents(reportData, excludePrograms);
        final List<Student> students = studentsResult.getFirst();
        final TotalCounts counts = studentsResult.getSecond();

        switch (methodName) {
            case "buildSchoolDistributionReport()":
                sortStudentsByProgramCompletionDateAndNames(students);
                break;
            case "buildSchoolGraduationReport()","buildSchoolNonGraduationReport()","buildStudentNonGradProjectedReport()","buildStudentGradProjectedReport()","buildStudentNonGradReport()":
                sortStudentsByNames(students);
                break;
            default:
                sortStudentsByLastUpdateDateAndNames(students);
                break;
        }

        final School school = getSchool(reportData);

        if(!students.isEmpty()) {
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(students);
            parameters.put("students", jrBeanCollectionDataSource);
            parameters.put("hasStudents", "true");
        }

        parameters.put("counts", counts);

        if (school != null) {
            parameters.put("school", school);
        }

        addReportLogo(parameters, reportData);

        parameters.put("reportNumber", reportData.getReportNumber());
        parameters.put("reportTitle", reportData.getReportTitle());
        parameters.put("reportSubTitle", reportData.getReportSubTitle());

        GraduationReport graduationReport = createGraduationReport();
        graduationReport.setLocale(CANADA);
        graduationReport.setStudents(students);
        graduationReport.setSchool(school);
        graduationReport.setParameters(parameters);

        return graduationReport;
    }

    protected void addReportLogo(Parameters<String, Object> parameters, ReportData reportData) throws IOException {
        if(StringUtils.isNotBlank(reportData.getOrgCode())) {
            InputStream inputLogo = openImageResource("logo_" + reportData.getOrgCode().toLowerCase(Locale.ROOT) + ".svg");
            parameters.put("orgImage", inputLogo);
        }
    }

    protected List<School> getSchools(ReportData reportData) {
        final String methodName = "getSchools()";
        LOG.entering(CLASSNAME, methodName);

        return gradDataConvertionBean.getSchools(reportData);
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
        if(student.getNonGradReasons() == null || student.getNonGradReasons().isEmpty()) {
            final HashMap<String, String> reasons = gradDataConvertionBean.getNongradReasons(reportData);
            student.setNonGradReasons(reasons);
        }

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
        student.setCitizenship(studentInfo.getCitizenship());
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

        validate(student, "student");

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
            TraxSchool traxSchool = getSchool(studentInfo.getSchoolId(), accessToken);
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
        school.setSchoolId(studentInfo.getSchoolId());
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
        school.setSchoolCategoryCode(traxSchool.getSchoolCategoryLegacyCode());
        school.setSchoolId(traxSchool.getSchoolId());
        school.setMincode(traxSchool.getMinCode());
        school.setName(traxSchool.getSchoolName());
        school.setSchoolCategoryCode(traxSchool.getSchoolCategoryLegacyCode());
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

    TraxSchool getSchool(String schoolId, String accessToken) {
        TraxSchool traxSchool = null;
        if(!StringUtils.isBlank(schoolId)) {
            try {
                traxSchool = webClient.get()
                    .uri(String.format(constants.getSchoolDetails(), schoolId))
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(TraxSchool.class)
                    .block();
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Unable to get TRAX school by schoolId={0}. Reason {1}", new String[]{schoolId, e.getMessage()});
            }
        }
        return traxSchool;
    }

    GradProgramImpl getGraduationProgram(String programCode, String accessToken) {
        GradProgramImpl result = null;
        if(!StringUtils.isBlank(programCode)) {
            try {
                result = webClient.get()
                        .uri(String.format(constants.getGraduationProgram(), programCode))
                        .headers(h -> h.setBearerAuth(accessToken))
                        .retrieve()
                        .bodyToMono(GradProgramImpl.class)
                        .block();
                if (result != null) {
                    result.setCode();
                }
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Unable to get graduation program by {0} code. Reason {1}", new String[]{programCode, e.getMessage()});
            }
        }
        return result;
    }

    String createReportTypeName(
            final String reportName,
            final Locale locale) {
        return reportName
                + " "
                + locale.getISO3Language();
    }

    byte[] getPdfReportAsBytes(GraduationReport graduationReport, String methodName, String reportFilePrefix) throws IOException {
        String timestamp = new SimpleDateFormat(DATE_ISO_8601_FULL).format(new Date());
        final ReportDocument rptDoc = reportService.export(graduationReport);

        StringBuilder sb = new StringBuilder(reportFilePrefix);
        sb.append(CANADA.toLanguageTag());
        sb.append("_");
        sb.append(timestamp);
        sb.append(".");
        sb.append(PDF.getFilenameExtension());

        byte[] inData = rptDoc.asBytes();
        inData = ArrayUtils.nullToEmpty(inData);
        if (ArrayUtils.isEmpty(inData)) {
            String msg = "The generated report output is empty.";
            DomainServiceException dse = new DomainServiceException(null,
                    msg);
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }
        return inData;
    }
}

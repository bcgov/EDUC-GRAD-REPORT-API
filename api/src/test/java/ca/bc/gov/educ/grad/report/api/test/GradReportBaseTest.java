package ca.bc.gov.educ.grad.report.api.test;


import ca.bc.gov.educ.grad.report.api.client.*;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.dao.*;
import ca.bc.gov.educ.grad.report.dto.impl.GradProgramImpl;
import ca.bc.gov.educ.grad.report.entity.ProgramCertificateTranscriptEntity;
import ca.bc.gov.educ.grad.report.entity.StudentTranscriptEntity;
import ca.bc.gov.educ.grad.report.model.district.District;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class GradReportBaseTest {

    @Autowired
    protected JsonTransformer jsonTransformer;
    @Autowired
    protected ReportApiConstants reportApiConstants;

    @MockBean
    protected TranscriptTypeCodeRepository transcriptTypeCodeRepository;
    @MockBean
    protected CertificateTypeCodeRepository certificateTypeCodeRepository;
    @MockBean
    protected StudentCertificateRepository studentCertificateRepository;
    @MockBean
    protected StudentTranscriptRepository studentTranscriptRepository;
    @MockBean
    protected ProgramCertificateTranscriptRepository programCertificateTranscriptRepository;
    @MockBean
    protected StudentReportRepository studentReportRepository;
    @MockBean
    protected SignatureBlockTypeRepository signatureBlockTypeRepository;
    @MockBean
    protected DocumentStatusCodeRepository documentStatusCodeRepository;
    @MockBean
    protected ReportTypeCodeRepository reportTypeCodeRepository;
    @MockBean
    protected SignatureImageRepository signatureImageRepository;

    @MockBean
    protected WebClient webClient;
    @Mock protected WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock protected WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock protected WebClient.RequestBodySpec requestBodyMock;
    @Mock protected WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock protected WebClient.ResponseSpec responseMock;

    @Autowired
    protected EducGradReportApiConstants constants;

    @BeforeClass
    public static void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void init() throws Exception {
    }

    protected School getReportDataSchool(ReportData reportData) {
        reportData.setAccessToken("accessToken");
        if(reportData.getSchool() == null || reportData.getSchool().getMincode() == null) {
            reportData.setSchool(new School());
        }
        return reportData.getSchool();
    }

    protected TraxSchool adaptTraxSchool(School school) {
        TraxSchool traxSchool = new TraxSchool();
        traxSchool.setMinCode(school.getMincode());
        traxSchool.setSchoolName(school.getName());
        if(school.getAddress() != null) {
            traxSchool.setAddress1(school.getAddress().getStreetLine1());
            traxSchool.setAddress2(school.getAddress().getStreetLine2());
            traxSchool.setCity(school.getAddress().getCity());
            traxSchool.setProvCode(school.getAddress().getRegion());
            traxSchool.setPostal(school.getAddress().getCode());
            traxSchool.setCountryCode(school.getAddress().getCountry());
        }
        return traxSchool;
    }

    protected void mockTraxSchool(TraxSchool traxSchool) {
        when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
        when(this.requestHeadersUriMock.uri(String.format(constants.getSchoolDetails(),traxSchool.getMinCode()))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
        when(this.responseMock.bodyToMono(TraxSchool.class)).thenReturn(Mono.just(traxSchool));
    }

    protected void mockTraxCountry(TraxCountry traxCountry) {
        when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
        when(this.requestHeadersUriMock.uri(String.format(constants.getCountryDetails(),traxCountry.getCountryCode()))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
        when(this.responseMock.bodyToMono(TraxCountry.class)).thenReturn(Mono.just(traxCountry));
    }

    protected void mockGradProgramEntity(String gradProgramCode, String transcriptTypeCode) {
        ProgramCertificateTranscriptEntity programCertificateTranscriptEntity = new ProgramCertificateTranscriptEntity();
        programCertificateTranscriptEntity.setGraduationProgramCode(gradProgramCode);
        programCertificateTranscriptEntity.setTranscriptTypeCode(transcriptTypeCode);
        List<ProgramCertificateTranscriptEntity> entities = List.of(programCertificateTranscriptEntity);
        when(this.programCertificateTranscriptRepository.findByTranscriptTypeCode(transcriptTypeCode)).thenReturn(entities);
    }

    protected void mockGradProgram(GradProgramImpl gradProgram) {
        when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
        when(this.requestHeadersUriMock.uri(String.format(constants.getGraduationProgram(),gradProgram.getCode().getCode()))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
        when(this.responseMock.bodyToMono(GradProgramImpl.class)).thenReturn(Mono.just(gradProgram));
    }

    protected void mockDistrictInfo(District district) {
        when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
        when(this.requestHeadersUriMock.uri(String.format(constants.getDistrictDetails(),district.getDistno()))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
        when(this.responseMock.bodyToMono(District.class)).thenReturn(Mono.just(district));
    }

    protected GraduationStudentRecord mockGraduationStudentRecord(String pen, String studentId) throws Exception {

        GraduationStudentRecord graduationStudentRecord = new GraduationStudentRecord();
        graduationStudentRecord.setPen(pen);
        graduationStudentRecord.setStudentID(UUID.fromString(studentId));
        graduationStudentRecord.setLastUpdateDate(new Date());

        String studentGradData = readFile("data/student_grad_data.json");
        assertNotNull(studentGradData);
        graduationStudentRecord.setStudentGradData(studentGradData);

        when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
        when(this.requestHeadersUriMock.uri(String.format(reportApiConstants.getReadGradStudentRecord(),graduationStudentRecord.getStudentID().toString()))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
        when(this.responseMock.bodyToMono(GraduationStudentRecord.class)).thenReturn(Mono.just(graduationStudentRecord));

        StudentTranscriptEntity studentTranscriptEntity = new StudentTranscriptEntity();
        studentTranscriptEntity.setId(UUID.randomUUID());
        studentTranscriptEntity.setGraduationStudentRecordId(graduationStudentRecord.getStudentID());
        studentTranscriptEntity.setUpdatedTimestamp(graduationStudentRecord.getLastUpdateDate());

        when(this.studentTranscriptRepository.findByGraduationStudentRecordId(graduationStudentRecord.getStudentID())).thenReturn(studentTranscriptEntity);
        when(this.studentCertificateRepository.getCertificateDistributionDate(graduationStudentRecord.getStudentID())).thenReturn(Optional.of(new Date()));
        when(this.studentTranscriptRepository.getTranscriptLastUpdateDate(graduationStudentRecord.getStudentID())).thenReturn(Optional.of(new Date()));
        when(this.studentReportRepository.getReportUpdatedTimestamp(graduationStudentRecord.getStudentID())).thenReturn(Optional.of(new Date()));

        return graduationStudentRecord;
    }

    protected GradSearchStudent mockGradSearchStudent(String pen, String entityId) {
        GradSearchStudent gradSearchStudent = new GradSearchStudent();
        gradSearchStudent.setPen(pen);
        if(StringUtils.isNotBlank(entityId)) {
            gradSearchStudent.setStudentID(entityId);
        } else {
            gradSearchStudent.setStudentID(UUID.randomUUID().toString());
        }

        final ParameterizedTypeReference<List<GradSearchStudent>> gradSearchStudentResponseType = new ParameterizedTypeReference<>() {
        };
        when(this.webClient.get()).thenReturn(this.requestHeadersUriMock);
        when(this.requestHeadersUriMock.uri(String.format(reportApiConstants.getPenStudentApiByPenUrl(),gradSearchStudent.getPen()))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.headers(any(Consumer.class))).thenReturn(this.requestHeadersMock);
        when(this.requestHeadersMock.retrieve()).thenReturn(this.responseMock);
        when(this.responseMock.bodyToMono(gradSearchStudentResponseType)).thenReturn(Mono.just(List.of(gradSearchStudent)));

        return gradSearchStudent;

    }

    protected byte[] loadTestImage(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        byte[] imageBytes = inputStream.readAllBytes();
        inputStream.close();
        return imageBytes;
    }

    protected ReportRequest createReportRequest(String jsonPath) throws Exception {
        return (ReportRequest)createReportRequest(jsonPath, ReportRequest.class);
    }

    protected XmlReportRequest createXmlReportRequest(String jsonPath) throws Exception {
        return (XmlReportRequest)createReportRequest(jsonPath, XmlReportRequest.class);
    }

    protected Object createReportRequest(String jsonPath, Class clazz) throws Exception {
        String transcriptJson = readFile(jsonPath);
        return jsonTransformer.unmarshall(transcriptJson, clazz);
    }

    protected String readFile(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        return readInputStream(inputStream);
    }

    private String readInputStream(InputStream is) throws Exception {
        StringBuffer sb = new StringBuffer();
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}

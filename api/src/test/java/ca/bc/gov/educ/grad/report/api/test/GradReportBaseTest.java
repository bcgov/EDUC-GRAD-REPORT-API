package ca.bc.gov.educ.grad.report.api.test;


import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.XmlReportRequest;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class GradReportBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GradReportBaseTest.class);
    public static final String REPORT_DATA_MISSING = "REPORT_DATA_MISSING";

    @Autowired
    JsonTransformer jsonTransformer;

    @BeforeClass
    public static void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void init() throws Exception {
    }

    @Test
    public void dummyTest() {}

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

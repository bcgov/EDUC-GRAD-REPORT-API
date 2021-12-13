package ca.bc.gov.educ.grad.report.api.test;


import ca.bc.gov.educ.grad.report.api.dto.GenerateReportData;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.dto.GenerateReportRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
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

    protected byte[] loadTestImage(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        byte[] imageBytes = inputStream.readAllBytes();
        inputStream.close();
        return imageBytes;
    }

    protected GenerateReportRequest createReportRequest(String jsonPath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(jsonPath);
        String transcriptJson = readInputStream(inputStream);
        return (GenerateReportRequest)jsonTransformer.unmarshall(transcriptJson, GenerateReportRequest.class);
    }

    protected GenerateReportData createReportRequestObj(String jsonPath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(jsonPath);
        String transcriptJson = readInputStream(inputStream);
        return (GenerateReportData)jsonTransformer.unmarshall(transcriptJson, GenerateReportData.class);
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

package ca.bc.gov.educ.grad.task;

import ca.bc.gov.educ.grad.dao.SignatureImageLightRepository;
import ca.bc.gov.educ.grad.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.entity.GragReportSignatureImageEntity;
import ca.bc.gov.educ.grad.entity.GragReportSignatureImageLightEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploadSignaturesTask {
    private static final Logger log = LoggerFactory.getLogger(UploadSignaturesTask.class);
    private static final String CLASS_NAME = UploadSignaturesTask.class.getSimpleName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final String signatureImageResourcePath = "reports/resources/images/signatures";

    @Autowired
    SignatureImageLightRepository signatureImageLightRepository;

    @Autowired
    SignatureImageRepository signatureImageRepository;

    //@Scheduled(initialDelay = 1000 * 30, fixedDelay=Long.MAX_VALUE)
    public void uploadSignatures() throws IOException, URISyntaxException {
        log.debug("<{}.uploadSignatures at {}", CLASS_NAME, dateFormat.format(new Date()));
        List<File> result = getAllFilesFromResource(signatureImageResourcePath);
        for (File file : result) {
            uploadSignatureImageFile(file);
        }
        log.debug(">uploadSignatures");
    }

    private void uploadSignatureImageFile(File file) throws IOException {
        log.debug("<{}.uploadSignatureImageFile at {} file name {}", CLASS_NAME, dateFormat.format(new Date()), file.getName());
        GragReportSignatureImageLightEntity signatureImageLightEntity = signatureImageLightRepository.findBySignatureCode(file.getName());
        if(signatureImageLightEntity == null) {
            GragReportSignatureImageEntity toBeSaved = new GragReportSignatureImageEntity();
            toBeSaved.setSignatureId(UUID.randomUUID());
            toBeSaved.setGradReportSignatureCode(file.getName());
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(signatureImageResourcePath + "/" + file.getName());
            byte[] imageContent = inputStream.readAllBytes();
            toBeSaved.setSignatureContent(imageContent);
            inputStream.close();

            signatureImageRepository.save(toBeSaved);
        }
        log.debug(">uploadSignatureImageFile");
    }

    private List<File> getAllFilesFromResource(String folder) throws URISyntaxException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(folder);

        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
                .collect(Collectors.toList());

        return collect;
    }
}

package ca.bc.gov.educ.grad.service;

import ca.bc.gov.educ.grad.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.dto.GragReportSignatureImage;
import ca.bc.gov.educ.grad.entity.GragReportSignatureImageEntity;
import ca.bc.gov.educ.grad.transformer.GradReportSignatureTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class GradReportSignatureService {

    private static final String CLASS_NAME = GradReportSignatureService.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    SignatureImageRepository signatureImageRepository;

    @Autowired
    GradReportSignatureTransformer gradReportSignatureTransformer;

    @Transactional
    public GragReportSignatureImage getSignatureImageBySignatureId(String id) {
        String _m = String.format("getSignatureImageBySignatureId(String %s)", id);
        log.debug("<{}.{}", _m, CLASS_NAME);
        Optional<GragReportSignatureImageEntity> entity = signatureImageRepository.findById(UUID.fromString(id));
        if(!entity.isPresent()) {
            try {
                entity = Optional.of(new GragReportSignatureImageEntity());
                byte[] imageBinary = loadBlankImage("reports/resources/images/signatures/BLANK.png");
                entity.get().setGradReportSignatureCode("BLANK.png");
                entity.get().setSignatureContent(imageBinary);
            } catch (Exception e) {
                log.error("Unable to load BLANK image from resources", e);
            }
        }
        GragReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(entity);
        log.debug(">{}.{}", _m, CLASS_NAME);
        return  signatureImage;
    }

    @Transactional
    public GragReportSignatureImage getSignatureImageByCode(String code) {
        String _m = String.format("getSignatureImageByCode(String %s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);
        GragReportSignatureImageEntity entity = signatureImageRepository.findBySignatureCode(code);
        if(entity ==  null) {
            try {
                entity = new GragReportSignatureImageEntity();
                byte[] imageBinary = loadBlankImage("reports/resources/images/signatures/BLANK.png");
                entity.setGradReportSignatureCode("BLANK.png");
                entity.setSignatureContent(imageBinary);
            } catch (Exception e) {
                log.error("Unable to load BLANK image from resources", e);
            }
        }
        GragReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(entity);
        log.debug(">{}.{}", _m, CLASS_NAME);
        return  signatureImage;
    }

    @Transactional
    public GragReportSignatureImage saveSignatureImage(GragReportSignatureImage signatureImage) {
        GragReportSignatureImageEntity toBeSaved = gradReportSignatureTransformer.transformToEntity(signatureImage);
        if(toBeSaved.getSignatureId() != null) {
            Optional<GragReportSignatureImageEntity> existingEnity = signatureImageRepository.findById(toBeSaved.getSignatureId());
            if(existingEnity.isPresent()) {
                GragReportSignatureImageEntity signEntity = existingEnity.get();
                signEntity.setSignatureContent(signatureImage.getSignatureContent());
                return gradReportSignatureTransformer.transformToDTO(signatureImageRepository.save(signEntity));
            }
        }
        return gradReportSignatureTransformer.transformToDTO(signatureImageRepository.save(toBeSaved));
    }

    private byte[] loadBlankImage(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        byte[] imageBytes = inputStream.readAllBytes();
        inputStream.close();
        return imageBytes;
    }
}

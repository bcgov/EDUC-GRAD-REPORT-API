package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.report.dto.District;
import ca.bc.gov.educ.grad.report.dto.GradReportSignatureImage;
import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.transformer.GradReportSignatureTransformer;
import ca.bc.gov.educ.grad.report.utils.EducGradSignatureImageApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@Service
public class GradReportSignatureService {

    private static final String CLASS_NAME = GradReportSignatureService.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    SignatureImageRepository signatureImageRepository;

    @Autowired
    GradReportSignatureTransformer gradReportSignatureTransformer;

    @Autowired WebClient webClient;
    @Autowired EducGradSignatureImageApiConstants constants;

    @Transactional
    public GradReportSignatureImage getSignatureImageBySignatureId(String id) {
        String methodName = String.format("getSignatureImageBySignatureId(String %s)", id);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        Optional<GradReportSignatureImageEntity> entity = signatureImageRepository.findById(UUID.fromString(id));
        if(!entity.isPresent()) {
            try {
                entity = Optional.of(new GradReportSignatureImageEntity());
                byte[] imageBinary = loadBlankImage("reports/resources/images/signatures/BLANK.png");
                entity.get().setGradReportSignatureCode("BLANK.png");
                entity.get().setSignatureContent(imageBinary);
            } catch (Exception e) {
                log.error("Unable to load BLANK image from resources", e);
            }
        }
        GradReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(entity);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return  signatureImage;
    }

    @Transactional
    public List<GradReportSignatureImage> getSignatureImages(String accessToken) {
        String methodName = "getSignatureImages()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        List<GradReportSignatureImageEntity> entities = signatureImageRepository.findAll();
        List<GradReportSignatureImage> result = new ArrayList();
        for(GradReportSignatureImageEntity entity: entities) {
            GradReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(entity);
            District dist = getDistrictInfo(entity.getGradReportSignatureCode(),accessToken);
            if(dist != null)
                signatureImage.setDistrictName(dist.getDistrictName());
            result.add(signatureImage);
        }
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return result;
    }

    @Transactional
    public GradReportSignatureImage getSignatureImageByCode(String code) {
        String methodName = String.format("getSignatureImageBinaryByCode(String %s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        GradReportSignatureImageEntity entity = signatureImageRepository.findBySignatureCode(code);
        if(entity ==  null) {
            try {
                entity = new GradReportSignatureImageEntity();
                byte[] imageBinary = loadBlankImage("reports/resources/images/signatures/BLANK.png");
                entity.setGradReportSignatureCode("BLANK.png");
                entity.setSignatureContent(imageBinary);
            } catch (Exception e) {
                log.error("Unable to load BLANK image from resources", e);
                throw new DomainServiceException("Unable to load default blank image");
            }
        }
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return gradReportSignatureTransformer.transformToDTO(entity);
    }

    @Transactional
    public GradReportSignatureImage getSignatureImageByCode(String code, String accessToken) {
        String methodName = String.format("getSignatureImageByCode(String %s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        GradReportSignatureImage signatureImage = getSignatureImageByCode(code);
        District dist = getDistrictInfo(signatureImage.getGradReportSignatureCode(),accessToken);
        if(dist != null)
            signatureImage.setDistrictName(dist.getDistrictName());
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return  signatureImage;
    }

    @Transactional
    public GradReportSignatureImage saveSignatureImage(GradReportSignatureImage signatureImage) {
        GradReportSignatureImageEntity toBeSaved = gradReportSignatureTransformer.transformToEntity(signatureImage);
        if(toBeSaved.getSignatureId() != null) {
            Optional<GradReportSignatureImageEntity> existingEnity = signatureImageRepository.findById(toBeSaved.getSignatureId());
            if(existingEnity.isPresent()) {
                GradReportSignatureImageEntity signEntity = existingEnity.get();
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

    public District getDistrictInfo(String districtCode,String accessToken) {
        return webClient.get()
                .uri(String.format(constants.getDistrictDetails(), districtCode))
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(District.class)
                .block();
    }
}

package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.report.dto.GradReportSignatureImage;
import ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl;
import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.transformer.GradReportSignatureTransformer;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@Slf4j
@Service
public class GradReportSignatureService {

    private static final String CLASS_NAME = GradReportSignatureService.class.getName();
    private static final int SIGNATURE_TEXT_COLUMN_MAX_BYTES = 64;
    private static final byte[] PNG_FILE_SIGNATURE = {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};
    SignatureImageRepository signatureImageRepository;
    GradReportSignatureTransformer gradReportSignatureTransformer;
    WebClient webClient;
    EducGradReportApiConstants constants;
    RESTService restService;

    @Autowired
    public GradReportSignatureService(SignatureImageRepository signatureImageRepository,
                                      GradReportSignatureTransformer gradReportSignatureTransformer,
                                      @Qualifier("reportApiClient") WebClient webClient,
                                      EducGradReportApiConstants constants,
                                      RESTService restService) {
        this.signatureImageRepository = signatureImageRepository;
        this.gradReportSignatureTransformer = gradReportSignatureTransformer;
        this.webClient = webClient;
        this.constants = constants;
        this.restService = restService;
    }

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
    public List<GradReportSignatureImage> getSignatureImages() {
        String methodName = "getSignatureImages()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        List<GradReportSignatureImageEntity> entities = signatureImageRepository.findAll();
        List<GradReportSignatureImage> result = new ArrayList();
        for(GradReportSignatureImageEntity entity: entities) {
            GradReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(entity);

            DistrictImpl dist = null;
            try {
                dist = getDistrictInfo(entity.getGradReportSignatureCode());
            } catch (Exception e) {
                log.error(String.format("Cannot retrieve District information for: %s", entity.getGradReportSignatureCode()));
            }
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
    public GradReportSignatureImage getSignatureImageWithDistInfoByCode(String code) {
        String methodName = String.format("getSignatureImageByCode(String %s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        GradReportSignatureImage signatureImage = getSignatureImageByCode(code);

        DistrictImpl dist = null;
        try {
            dist = getDistrictInfo(signatureImage.getGradReportSignatureCode());
        } catch (Exception e) {
            log.error(String.format("Cannot retrieve District information for: %s", signatureImage.getGradReportSignatureCode()));
        }
        if(dist != null)
            signatureImage.setDistrictName(dist.getDistrictName());
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);
        return  signatureImage;
    }

    @Transactional
    public GradReportSignatureImage saveSignatureImage(GradReportSignatureImage signatureImage) {
        validateSignatureImageForSave(signatureImage);
        GradReportSignatureImageEntity toBeSaved = gradReportSignatureTransformer.transformToEntity(signatureImage);
        if(toBeSaved.getSignatureId() != null) {
            Optional<GradReportSignatureImageEntity> existingEnity = signatureImageRepository.findById(toBeSaved.getSignatureId());
            if(existingEnity.isPresent()) {
                GradReportSignatureImageEntity signEntity = existingEnity.get();
                signEntity.setSignatureContent(signatureImage.getSignatureContent());
                signEntity.setGradReportSignatureName(signatureImage.getGradReportSignatureName());
                signEntity.setGradReportSignatureOrganizationName(signatureImage.getGradReportSignatureOrganizationName());
                // BaseEntity only fills updatedBy when blank, so set it here to record who made this change
                signEntity.setUpdatedBy(ReportRequestDataThreadLocal.getCurrentUser());
                return gradReportSignatureTransformer.transformToDTO(signatureImageRepository.save(signEntity));
            }
        }
        return gradReportSignatureTransformer.transformToDTO(signatureImageRepository.save(toBeSaved));
    }

    private void validateSignatureImageForSave(GradReportSignatureImage signatureImage) {
        if (!isDecodablePngImage(signatureImage.getSignatureContent())) {
            throw new IllegalArgumentException("Signature image must be a valid PNG file");
        }
        validateRequiredSignatureTextColumn(signatureImage.getGradReportSignatureName(), "Signature Name");
        validateRequiredSignatureTextColumn(signatureImage.getGradReportSignatureOrganizationName(), "Organization Name");
    }

    private static boolean isDecodablePngImage(byte[] imageContent) {
        if (imageContent == null
                || imageContent.length < PNG_FILE_SIGNATURE.length
                || !Arrays.equals(imageContent, 0, PNG_FILE_SIGNATURE.length, PNG_FILE_SIGNATURE, 0, PNG_FILE_SIGNATURE.length)) {
            return false;
        }
        // the header alone doesn't prove the file is intact; decode it so a corrupt upload can't break certificates
        try {
            return ImageIO.read(new ByteArrayInputStream(imageContent)) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private static void validateRequiredSignatureTextColumn(String value, String fieldName) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(String.format("%s is required", fieldName));
        }
        // Oracle columns are VARCHAR2(64 BYTE); accented and Indigenous-language characters take more than one byte
        int valueLengthInBytes = value.getBytes(StandardCharsets.UTF_8).length;
        if (valueLengthInBytes > SIGNATURE_TEXT_COLUMN_MAX_BYTES) {
            throw new IllegalArgumentException(String.format("%s is too long: %d bytes, maximum is %d (accented and special characters use more than one byte)",
                    fieldName, valueLengthInBytes, SIGNATURE_TEXT_COLUMN_MAX_BYTES));
        }
    }

    private byte[] loadBlankImage(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        byte[] imageBytes = inputStream.readAllBytes();
        inputStream.close();
        return imageBytes;
    }

    public DistrictImpl getDistrictInfo(String districtCode) {
        return restService.get(String.format(constants.getDistrictDetails(), districtCode), DistrictImpl.class, webClient);
    }
}

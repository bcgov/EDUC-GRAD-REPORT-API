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
        GragReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(signatureImageRepository.findById(UUID.fromString(id)));
        log.debug(">{}.{}", _m, CLASS_NAME);
        return  signatureImage;
    }

    @Transactional
    public GragReportSignatureImage getSignatureImageByCode(String code) {
        String _m = String.format("getSignatureImageByCode(String %s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);
        GragReportSignatureImage signatureImage = gradReportSignatureTransformer.transformToDTO(signatureImageRepository.findBySignatureCode(code));
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
}

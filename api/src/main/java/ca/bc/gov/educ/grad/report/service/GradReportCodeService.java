package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.dao.*;
import ca.bc.gov.educ.grad.report.dto.*;
import ca.bc.gov.educ.grad.report.entity.*;
import ca.bc.gov.educ.grad.report.exception.ReportApiServiceException;
import ca.bc.gov.educ.grad.report.transformer.*;
import ca.bc.gov.educ.grad.report.utils.SerializableMap;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@Service
public class GradReportCodeService {

    private static final String CLASS_NAME = GradReportCodeService.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    private static final String UNABLE_TO_RETRIEVE_RESOURCE = "Unable to retrieve %s";

    @Autowired
    CertificateTypeCodeRepository certificateTypeCodeRepository;
    @Autowired
    DocumentStatusCodeRepository documentStatusCodeRepository;
    @Autowired
    ReportTypeCodeRepository reportTypeCodeRepository;
    @Autowired
    SignatureBlockTypeRepository signatureBlockTypeRepository;
    @Autowired
    TranscriptTypeCodeRepository transcriptTypeCodeRepository;

    @Autowired
    GradReportCertificateTypeCodeTransformer gradReportCertificateTypeCodeTransformer;
    @Autowired
    GradReportSignatureBlockTypeCodeTransformer gradReportSignatureBlockTypeCodeTransformer;
    @Autowired
    GradReportDocumentStatusCodeTransformer gradReportDocumentStatusCodeTransformer;
    @Autowired
    GradReportTranscriptTypeCodeTransformer gradReportTranscriptTypeCodeTransformer;
    @Autowired
    GradReportReportTypeCodeTransformer gradReportReportTypeCodeTransformer;

    public List<CertificateTypeCode> getCertificateTypeCodes() {
        String methodName = "getCertificateTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<CertificateTypeCode> result;

        try {

            List<CertificateTypeCodeEntity> dtos = certificateTypeCodeRepository.findAll();
            result = gradReportCertificateTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "List<CertificateTypeCode>"), e);
        }

        return result;

    }

    public CertificateTypeCode getCertificateTypeCode(String code) {
        String methodName = String.format("getCertificateTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        CertificateTypeCode result;

        try {

            CertificateTypeCodeEntity dto = certificateTypeCodeRepository.findByCertificateCode(code);
            result = gradReportCertificateTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "CertificateTypeCode"), e);
        }

        return result;

    }

    public List<TranscriptTypeCode> getTranscriptTypeCodes() {
        String methodName = "getTranscriptTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<TranscriptTypeCode> result;

        try {

            List<TranscriptTypeCodeEntity> dtos = transcriptTypeCodeRepository.findAll();
            result = gradReportTranscriptTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "List<TranscriptTypeCode>"), e);
        }

        return result;

    }

    public TranscriptTypeCode getTranscriptTypeCode(String code) {
        String methodName = String.format("getTranscriptTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        TranscriptTypeCode result;

        try {

            TranscriptTypeCodeEntity dto = transcriptTypeCodeRepository.findByTranscriptCode(code);
            result = gradReportTranscriptTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "TranscriptTypeCode"), e);
        }

        return result;

    }

    public List<SignatureBlockTypeCode> getSignatureBlockTypeCodes() {
        String methodName = "getSignatureBlockTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<SignatureBlockTypeCode> result;

        try {

            List<SignatureBlockTypeCodeEntity> dtos = signatureBlockTypeRepository.findAll();
            result = gradReportSignatureBlockTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "List<SignatureBlockTypeCode>"), e);
        }

        return result;

    }

    public SerializableMap<String, SignatureBlockTypeCode> getSignatureBlockTypeCodesMap() {
        String methodName = "getSignatureBlockTypeCodesMap()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        SerializableMap<String, SignatureBlockTypeCode> result = new SerializableMap<>();

        try {

            List<SignatureBlockTypeCode> dtos = getSignatureBlockTypeCodes();
            for(SignatureBlockTypeCode code: dtos) {
                result.put(code.getSignatureBlockTypeCode(), code);
            }

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "Map<String, SignatureBlockTypeCode>"), e);
        }

        return result;

    }

    public SignatureBlockTypeCode getSignatureBlockTypeCode(String code) {
        String methodName = String.format("getSignatureBlockTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        SignatureBlockTypeCode result;

        try {

            SignatureBlockTypeCodeEntity dto = signatureBlockTypeRepository.findBySignatureBlockTypeCode(code);
            result = gradReportSignatureBlockTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "SignatureBlockTypeCode"), e);
        }

        return result;

    }

    @Transactional
    public SignatureBlockTypeCode saveSignatureBlockTypeCode(SignatureBlockTypeCode code) {
        SignatureBlockTypeCodeEntity toBeSaved = gradReportSignatureBlockTypeCodeTransformer.transformToEntity(code);
        if(toBeSaved.getSignatureBlockType() != null) {
            Optional<SignatureBlockTypeCodeEntity> existingEnity = signatureBlockTypeRepository.findById(toBeSaved.getSignatureBlockType());
            if(existingEnity.isPresent()) {
                SignatureBlockTypeCodeEntity signEntity = existingEnity.get();
                signEntity.setLabel(code.getLabel());
                signEntity.setDescription(code.getDescription());
                return gradReportSignatureBlockTypeCodeTransformer.transformToDTO(signatureBlockTypeRepository.save(signEntity));
            }
        }
        return gradReportSignatureBlockTypeCodeTransformer.transformToDTO(signatureBlockTypeRepository.save(toBeSaved));
    }

    public List<DocumentStatusCode> getDocumentStatusCodes() {
        String methodName = "getDocumentStatusCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<DocumentStatusCode> result;

        try {

            List<DocumentStatusCodeEntity> dtos = documentStatusCodeRepository.findAll();
            result = gradReportDocumentStatusCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "List<DocumentStatusCode>"), e);
        }

        return result;

    }

    public DocumentStatusCode getDocumentStatusCode(String code) {
        String methodName = String.format("getDocumentStatusCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        DocumentStatusCode result;

        try {

            DocumentStatusCodeEntity dto = documentStatusCodeRepository.findByDocumentStatusCode(code);
            result = gradReportDocumentStatusCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "DocumentStatusCode"), e);
        }

        return result;

    }

    public List<ReportTypeCode> getReportTypeCodes() {
        String methodName = "getReportTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<ReportTypeCode> result;

        try {

            List<ReportTypeCodeEntity> dtos = reportTypeCodeRepository.findAll();
            result = gradReportReportTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "List<ReportTypeCode>"), e);
        }

        return result;

    }

    public ReportTypeCode getReportTypeCode(String code) {
        String methodName = String.format("getReportTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        ReportTypeCode result;

        try {

            ReportTypeCodeEntity dto = reportTypeCodeRepository.findByReportTypeCode(code);
            result = gradReportReportTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ReportApiServiceException(String.format(UNABLE_TO_RETRIEVE_RESOURCE, "ReportTypeCode"), e);
        }

        return result;

    }
}

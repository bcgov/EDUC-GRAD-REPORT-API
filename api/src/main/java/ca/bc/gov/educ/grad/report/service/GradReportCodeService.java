package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.dao.*;
import ca.bc.gov.educ.grad.report.dto.*;
import ca.bc.gov.educ.grad.report.entity.*;
import ca.bc.gov.educ.grad.report.exception.ServiceException;
import ca.bc.gov.educ.grad.report.transformer.*;
import ca.bc.gov.educ.grad.report.utils.SerializableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DEBUG_LOG_PATTERN;

@Service
public class GradReportCodeService implements Serializable {

    private static final String CLASS_NAME = GradReportCodeService.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

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

        List<CertificateTypeCode> _result;

        try {

            List<CertificateTypeCodeEntity> dtos = certificateTypeCodeRepository.findAll();
            _result = gradReportCertificateTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "List<CertificateTypeCode>"));
        }

        return _result;

    }

    public CertificateTypeCode getCertificateTypeCode(String code) {
        String methodName = String.format("getCertificateTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        CertificateTypeCode _result;

        try {

            CertificateTypeCodeEntity dto = certificateTypeCodeRepository.findByCertificateCode(code);
            _result = gradReportCertificateTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "CertificateTypeCode"));
        }

        return _result;

    }

    public List<TranscriptTypeCode> getTranscriptTypeCodes() {
        String methodName = "getTranscriptTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<TranscriptTypeCode> _result;

        try {

            List<TranscriptTypeCodeEntity> dtos = transcriptTypeCodeRepository.findAll();
            _result = gradReportTranscriptTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "List<TranscriptTypeCode>"));
        }

        return _result;

    }

    public TranscriptTypeCode getTranscriptTypeCode(String code) {
        String methodName = String.format("getTranscriptTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        TranscriptTypeCode _result;

        try {

            TranscriptTypeCodeEntity dto = transcriptTypeCodeRepository.findByTranscriptCode(code);
            _result = gradReportTranscriptTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "TranscriptTypeCode"));
        }

        return _result;

    }

    public List<SignatureBlockTypeCode> getSignatureBlockTypeCodes() {
        String methodName = "getSignatureBlockTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<SignatureBlockTypeCode> _result;

        try {

            List<SignatureBlockTypeCodeEntity> dtos = signatureBlockTypeRepository.findAll();
            _result = gradReportSignatureBlockTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "List<SignatureBlockTypeCode>"));
        }

        return _result;

    }

    public SerializableMap<String, SignatureBlockTypeCode> getSignatureBlockTypeCodesMap() {
        String methodName = "getSignatureBlockTypeCodesMap()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        SerializableMap<String, SignatureBlockTypeCode> _result = new SerializableMap<>();

        try {

            List<SignatureBlockTypeCode> dtos = getSignatureBlockTypeCodes();
            for(SignatureBlockTypeCode code: dtos) {
                _result.put(code.getSignatureBlockTypeCode(), code);
            }

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "Map<String, SignatureBlockTypeCode>"));
        }

        return _result;

    }

    public SignatureBlockTypeCode getSignatureBlockTypeCode(String code) {
        String methodName = String.format("getSignatureBlockTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        SignatureBlockTypeCode _result;

        try {

            SignatureBlockTypeCodeEntity dto = signatureBlockTypeRepository.findBySignatureBlockTypeCode(code);
            _result = gradReportSignatureBlockTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "SignatureBlockTypeCode"));
        }

        return _result;

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

        List<DocumentStatusCode> _result;

        try {

            List<DocumentStatusCodeEntity> dtos = documentStatusCodeRepository.findAll();
            _result = gradReportDocumentStatusCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "List<DocumentStatusCode>"));
        }

        return _result;

    }

    public DocumentStatusCode getDocumentStatusCode(String code) {
        String methodName = String.format("getDocumentStatusCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        DocumentStatusCode _result;

        try {

            DocumentStatusCodeEntity dto = documentStatusCodeRepository.findByDocumentStatusCode(code);
            _result = gradReportDocumentStatusCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "DocumentStatusCode"));
        }

        return _result;

    }

    public List<ReportTypeCode> getReportTypeCodes() {
        String methodName = "getReportTypeCodes()";
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        List<ReportTypeCode> _result;

        try {

            List<ReportTypeCodeEntity> dtos = reportTypeCodeRepository.findAll();
            _result = gradReportReportTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "List<ReportTypeCode>"));
        }

        return _result;

    }

    public ReportTypeCode getReportTypeCode(String code) {
        String methodName = String.format("getReportTypeCode(%s)", code);
        log.debug(DEBUG_LOG_PATTERN, methodName, CLASS_NAME);

        ReportTypeCode _result;

        try {

            ReportTypeCodeEntity dto = reportTypeCodeRepository.findByReportTypeCode(code);
            _result = gradReportReportTypeCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "ReportTypeCode"));
        }

        return _result;

    }
}

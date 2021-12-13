package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.dao.*;
import ca.bc.gov.educ.grad.report.dto.*;
import ca.bc.gov.educ.grad.report.entity.*;
import ca.bc.gov.educ.grad.report.exception.ServiceException;
import ca.bc.gov.educ.grad.report.transformer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GradReportCodeService {

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
        String _m = String.format("getCertificateTypeCodes()");
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getCertificateTypeCode(%s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getTranscriptTypeCodes()");
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getTranscriptTypeCode(%s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getSignatureBlockTypeCodes()");
        log.debug("<{}.{}", _m, CLASS_NAME);

        List<SignatureBlockTypeCode> _result;

        try {

            List<SignatureBlockTypeCodeEntity> dtos = signatureBlockTypeRepository.findAll();
            _result = gradReportSignatureBlockTypeCodeTransformer.transformToDTO(dtos);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "List<SignatureBlockTypeCode>"));
        }

        return _result;

    }

    public Map<String, SignatureBlockTypeCode> getSignatureBlockTypeCodesMap() {
        String _m = String.format("getSignatureBlockTypeCodesMap()");
        log.debug("<{}.{}", _m, CLASS_NAME);

        Map<String, SignatureBlockTypeCode> _result = new HashMap<>();

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
        String _m = String.format("getSignatureBlockTypeCode(%s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getDocumentStatusCodes()");
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getDocumentStatusCode(%s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getReportTypeCodes()");
        log.debug("<{}.{}", _m, CLASS_NAME);

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
        String _m = String.format("getReportTypeCode(%s)", code);
        log.debug("<{}.{}", _m, CLASS_NAME);

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

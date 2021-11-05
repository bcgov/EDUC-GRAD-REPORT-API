package ca.bc.gov.educ.grad.service;

import ca.bc.gov.educ.exception.ServiceException;
import ca.bc.gov.educ.grad.dao.*;
import ca.bc.gov.educ.grad.dto.CertificateTypeCode;
import ca.bc.gov.educ.grad.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.entity.CertificateTypeCodeEntity;
import ca.bc.gov.educ.grad.entity.SignatureBlockTypeCodeEntity;
import ca.bc.gov.educ.grad.transformer.GradReportCodeTransformer;
import ca.bc.gov.educ.grad.transformer.GradReportSignatureBlockTypeCodeTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    GradReportCodeTransformer gradReportCodeTransformer;
    @Autowired
    GradReportSignatureBlockTypeCodeTransformer gradReportSignatureBlockTypeCodeTransformer;

    public List<CertificateTypeCode> getCertificateTypeCodes() {
        String _m = String.format("getCertificateTypeCodes()");
        log.debug("<{}.{}", _m, CLASS_NAME);

        List<CertificateTypeCode> _result;

        try {

            List<CertificateTypeCodeEntity> dtos = certificateTypeCodeRepository.findAll();
            _result = gradReportCodeTransformer.transformToDTO(dtos);

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
            _result = gradReportCodeTransformer.transformToDTO(dto);

        } catch (Exception e) {
            throw new ServiceException(String.format("Unable to retrieve %s", "CertificateTypeCode"));
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


}

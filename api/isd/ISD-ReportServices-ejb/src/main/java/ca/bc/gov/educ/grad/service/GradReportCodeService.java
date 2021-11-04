package ca.bc.gov.educ.grad.service;

import ca.bc.gov.educ.grad.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}

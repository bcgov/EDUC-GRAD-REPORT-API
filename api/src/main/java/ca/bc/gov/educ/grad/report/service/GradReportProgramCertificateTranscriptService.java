package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.dao.ProgramCertificateTranscriptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradReportProgramCertificateTranscriptService {

    private static final String CLASS_NAME = GradReportProgramCertificateTranscriptService.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    ProgramCertificateTranscriptRepository programCertificateTranscriptRepository;


}

package ca.bc.gov.educ.grad.report.service;

import ca.bc.gov.educ.grad.report.dao.ProgramCertificateTranscriptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GradReportProgramCertificateTranscriptService {

    @Autowired
    ProgramCertificateTranscriptRepository programCertificateTranscriptRepository;

}

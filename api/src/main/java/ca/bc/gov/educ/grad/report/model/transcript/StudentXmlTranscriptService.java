package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.common.BusinessService;

public interface StudentXmlTranscriptService extends BusinessService {

    StudentTranscriptReport buildXmlTranscriptReport();
}

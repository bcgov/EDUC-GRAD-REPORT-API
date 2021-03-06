package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.ProgramCertificateTranscriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProgramCertificateTranscriptRepository extends JpaRepository<ProgramCertificateTranscriptEntity, String>, JpaSpecificationExecutor<ProgramCertificateTranscriptEntity> {

}
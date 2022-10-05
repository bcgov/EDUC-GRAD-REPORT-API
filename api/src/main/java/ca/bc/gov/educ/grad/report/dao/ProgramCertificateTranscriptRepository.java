package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.ProgramCertificateTranscriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramCertificateTranscriptRepository extends JpaRepository<ProgramCertificateTranscriptEntity, String>, JpaSpecificationExecutor<ProgramCertificateTranscriptEntity> {

    @Query("select c from ProgramCertificateTranscriptEntity c where c.transcriptTypeCode=:transcriptTypeCode")
    List<ProgramCertificateTranscriptEntity> findBySchoolCategoryCodeAndTranscriptTypeCode(String transcriptTypeCode);
}
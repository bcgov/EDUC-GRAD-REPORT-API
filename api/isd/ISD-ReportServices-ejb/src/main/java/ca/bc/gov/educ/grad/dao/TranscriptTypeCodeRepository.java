package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.TranscriptTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TranscriptTypeCodeRepository extends JpaRepository<TranscriptTypeCodeEntity, String>, JpaSpecificationExecutor<TranscriptTypeCodeEntity> {

    @Query("select c from TranscriptTypeCodeEntity c where c.transcriptTypeCode=:code")
    TranscriptTypeCodeEntity findByTranscriptCode(String code);
}
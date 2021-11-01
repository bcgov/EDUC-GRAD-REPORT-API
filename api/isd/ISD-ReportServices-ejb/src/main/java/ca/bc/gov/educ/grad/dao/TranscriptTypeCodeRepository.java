package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.TranscriptTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TranscriptTypeCodeRepository extends JpaRepository<TranscriptTypeCodeEntity, String>, JpaSpecificationExecutor<TranscriptTypeCodeEntity> {

}
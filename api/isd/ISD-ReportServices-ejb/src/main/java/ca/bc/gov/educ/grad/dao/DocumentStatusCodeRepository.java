package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.DocumentStatusCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DocumentStatusCodeRepository extends JpaRepository<DocumentStatusCodeEntity, String>, JpaSpecificationExecutor<DocumentStatusCodeEntity> {

}
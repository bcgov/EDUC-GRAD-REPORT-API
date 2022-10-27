package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.DocumentStatusCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentStatusCodeRepository extends JpaRepository<DocumentStatusCodeEntity, String> {

    @Query("select c from DocumentStatusCodeEntity c where c.documentStatusCode=:code")
    DocumentStatusCodeEntity findByDocumentStatusCode(String code);
}
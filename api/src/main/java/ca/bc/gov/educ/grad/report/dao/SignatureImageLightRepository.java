package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageLightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SignatureImageLightRepository extends JpaRepository<GradReportSignatureImageLightEntity, UUID> {

    @Query("select c from GradReportSignatureImageLightEntity c where c.gradReportSignatureCode=:signatureCode")
    GradReportSignatureImageLightEntity findBySignatureCode(String signatureCode);
}

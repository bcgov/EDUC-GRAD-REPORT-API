package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.GragReportSignatureImageLightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SignatureImageLightRepository extends JpaRepository<GragReportSignatureImageLightEntity, UUID> {

    @Query("select c from GragReportSignatureImageLightEntity c where c.gradReportSignatureCode=:signatureCode")
    GragReportSignatureImageLightEntity findBySignatureCode(String signatureCode);
}

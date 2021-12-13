package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.GragReportSignatureImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SignatureImageRepository extends JpaRepository<GragReportSignatureImageEntity, UUID> {

    @Query("select c from GragReportSignatureImageEntity c where c.gradReportSignatureCode=:signatureCode")
    GragReportSignatureImageEntity findBySignatureCode(String signatureCode);

}

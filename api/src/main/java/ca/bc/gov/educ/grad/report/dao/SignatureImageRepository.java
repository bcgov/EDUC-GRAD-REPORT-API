package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SignatureImageRepository extends JpaRepository<GradReportSignatureImageEntity, UUID> {

    GradReportSignatureImageEntity findByGradReportSignatureCode(String signatureCode);

}

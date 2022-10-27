package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.CertificateTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificateTypeCodeRepository extends JpaRepository<CertificateTypeCodeEntity, String> {

    @Query("select c from CertificateTypeCodeEntity c where c.certificateTypeCode=:code")
    CertificateTypeCodeEntity findByCertificateCode(String code);
}
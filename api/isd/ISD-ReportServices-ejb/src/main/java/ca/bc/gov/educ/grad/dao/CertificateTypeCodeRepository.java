package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.CertificateTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CertificateTypeCodeRepository extends JpaRepository<CertificateTypeCodeEntity, String>, JpaSpecificationExecutor<CertificateTypeCodeEntity> {

    @Query("select c from CertificateTypeCodeEntity c where c.certificateTypeCode=:certificateCode")
    CertificateTypeCodeEntity findByCertificateCode(String certificateCode);
}
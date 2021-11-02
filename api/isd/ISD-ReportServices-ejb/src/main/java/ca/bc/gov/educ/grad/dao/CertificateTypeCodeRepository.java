package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.CertificateTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CertificateTypeCodeRepository extends JpaRepository<CertificateTypeCodeEntity, String>, JpaSpecificationExecutor<CertificateTypeCodeEntity> {

}
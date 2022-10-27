package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.SignatureBlockTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SignatureBlockTypeRepository extends JpaRepository<SignatureBlockTypeCodeEntity, String> {

    @Query("select c from SignatureBlockTypeCodeEntity c where c.signatureBlockType=:code")
    SignatureBlockTypeCodeEntity findBySignatureBlockTypeCode(String code);
}
package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.SignatureBlockTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SignatureBlockTypeRepository extends JpaRepository<SignatureBlockTypeCodeEntity, String>, JpaSpecificationExecutor<SignatureBlockTypeCodeEntity> {

    @Query("select c from SignatureBlockTypeCodeEntity c where c.signatureBlockType=:code")
    SignatureBlockTypeCodeEntity findBySignatureBlockTypeCode(String code);
}
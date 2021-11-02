package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.SignatureBlockTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SignatureBlockTypeRepository extends JpaRepository<SignatureBlockTypeEntity, String>, JpaSpecificationExecutor<SignatureBlockTypeEntity> {

}
package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.ReportTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReportTypeCodeRepository extends JpaRepository<ReportTypeCodeEntity, String>, JpaSpecificationExecutor<ReportTypeCodeEntity> {

}
package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.entity.ReportTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ReportTypeCodeRepository extends JpaRepository<ReportTypeCodeEntity, String>, JpaSpecificationExecutor<ReportTypeCodeEntity> {

    @Query("select c from ReportTypeCodeEntity c where c.reportTypeCode=:code")
    ReportTypeCodeEntity findByReportTypeCode(String code);
}
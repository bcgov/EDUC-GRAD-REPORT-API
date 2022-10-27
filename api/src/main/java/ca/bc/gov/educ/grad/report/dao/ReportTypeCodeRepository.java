package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.ReportTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportTypeCodeRepository extends JpaRepository<ReportTypeCodeEntity, String> {

    @Query("select c from ReportTypeCodeEntity c where c.reportTypeCode=:code")
    ReportTypeCodeEntity findByReportTypeCode(String code);
}
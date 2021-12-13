package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentReportRepository extends JpaRepository<StudentReportEntity, String>, JpaSpecificationExecutor<StudentReportEntity> {

}
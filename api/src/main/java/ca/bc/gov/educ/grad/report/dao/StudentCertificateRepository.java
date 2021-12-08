package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentCertificateRepository extends JpaRepository<StudentCertificateEntity, String>, JpaSpecificationExecutor<StudentCertificateEntity> {

}
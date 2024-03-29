package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface StudentCertificateRepository extends JpaRepository<StudentCertificateEntity, UUID> {

    @Query("select max(c.distributionDate) as distributionDate from StudentCertificateEntity c where c.graduationStudentRecordId=:graduationStudentRecordId")
    Optional<Date> getCertificateDistributionDate(UUID graduationStudentRecordId);

}
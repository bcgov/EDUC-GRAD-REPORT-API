package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentCertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentCertificateRepository extends JpaRepository<StudentCertificateEntity, UUID> {

    @Query("select max(c.distributionDate) as distributionDate from StudentCertificateEntity c where c.graduationStudentRecordId=:graduationStudentRecordId")
    Optional<Date> getCertificateDistributionDate(UUID graduationStudentRecordId);

    @Modifying
    @Query(value = "delete from STUDENT_CERTIFICATE s where s.GRADUATION_STUDENT_RECORD_ID = :graduationStudentRecordId", nativeQuery = true)
    void deleteByGraduationStudentRecordId(@Param(value = "graduationStudentRecordId") UUID graduationStudentRecordId);

    List<StudentCertificateEntity> findAllByGraduationStudentRecordId(UUID graduationStudentRecordId);
}
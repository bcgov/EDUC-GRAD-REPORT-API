package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface StudentReportRepository extends JpaRepository<StudentReportEntity, UUID> {

    @Query("select max(c.updatedTimestamp) as UpdatedTimestamp from StudentReportEntity c where c.graduationStudentRecordId=:graduationStudentRecordId")
    Optional<Date> getReportUpdatedTimestamp(UUID graduationStudentRecordId);

    StudentReportEntity findByGraduationStudentRecordId(UUID graduationStudentRecordId);
}
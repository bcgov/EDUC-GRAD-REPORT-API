package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentTranscriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StudentTranscriptRepository extends JpaRepository<StudentTranscriptEntity, UUID> {

    @Query("select c from StudentTranscriptEntity c where c.graduationStudentRecordId=:uuid")
    StudentTranscriptEntity findByGraduationStudentRecordId(UUID uuid);
}
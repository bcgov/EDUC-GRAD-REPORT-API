package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.StudentTranscriptEntity;
import ca.bc.gov.educ.grad.report.entity.TranscriptTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentTranscriptRepository extends JpaRepository<StudentTranscriptEntity, UUID> {

    @Query("select c from StudentTranscriptEntity c where c.graduationStudentRecordId=:graduationStudentRecordId")
    StudentTranscriptEntity findByGraduationStudentRecordId(UUID graduationStudentRecordId);

    @Query("select t from TranscriptTypeCodeEntity t join StudentTranscriptEntity c on t.transcriptTypeCode = c.transcriptTypeCode where c.documentStatusCode='COMPL' and c.graduationStudentRecordId=:graduationStudentRecordId")
    List<TranscriptTypeCodeEntity> getStudentTranscriptTypes(UUID graduationStudentRecordId);

    @Query("select max(c.updateDate) as updateDate from StudentTranscriptEntity c where c.graduationStudentRecordId=:graduationStudentRecordId")
    Optional<Date> getTranscriptLastUpdateDate(UUID graduationStudentRecordId);
}
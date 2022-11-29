package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.TranscriptTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TranscriptTypeCodeRepository extends JpaRepository<TranscriptTypeCodeEntity, String> {

    @Query("select c from TranscriptTypeCodeEntity c where c.transcriptTypeCode=:code")
    TranscriptTypeCodeEntity findByTranscriptCode(String code);

    @Query("select t from TranscriptTypeCodeEntity t join StudentTranscriptEntity c on t.transcriptTypeCode = c.transcriptTypeCode where c.documentStatusCode='COMPL' and c.graduationStudentRecordId=:graduationStudentRecordId")
    List<TranscriptTypeCodeEntity> getStudentTranscriptTypes(UUID graduationStudentRecordId);
}
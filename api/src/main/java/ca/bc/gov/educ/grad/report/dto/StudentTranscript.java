package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.entity.BaseEntity;
import ca.bc.gov.educ.grad.report.entity.TranscriptTypeCodeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class StudentTranscript extends BaseEntity {

    private UUID id;
    private TranscriptTypeCodeEntity transcriptTypeCode;
    private UUID graduationStudentRecordId;
    private String documentStatusCode;
    private Date distributionDate;
    private Date transcriptUpdateDate;

}
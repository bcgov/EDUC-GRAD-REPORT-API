package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "STUDENT_TRANSCRIPT")
public class StudentTranscriptEntity extends BaseEntity {

    @Id
    @Column(name = "STUDENT_TRANSCRIPT_ID", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRANSCRIPT_TYPE_CODE", nullable = false)
    private TranscriptTypeCodeEntity transcriptTypeCode;

    @Column(name = "GRADUATION_STUDENT_RECORD_ID", nullable = false, columnDefinition = "uuid")
    private UUID graduationStudentRecordId;

    @Column(name = "DOCUMENT_STATUS_CODE", length = 5)
    private String documentStatusCode;

    @Column(name = "DISTRIBUTION_DATE")
    private Date distributionDate;

    @Column(name = "TRANSCRIPT_UPDATE_DATE")
    private Date transcriptUpdateDate;



}
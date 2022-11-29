package ca.bc.gov.educ.grad.report.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "STUDENT_TRANSCRIPT")
public class StudentTranscriptEntity {

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

    @Column(name = "CREATE_USER", nullable = false, length = 30)
    private String createUser;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "UPDATE_USER", nullable = false, length = 30)
    private String updateUser;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Date updateDate;

}
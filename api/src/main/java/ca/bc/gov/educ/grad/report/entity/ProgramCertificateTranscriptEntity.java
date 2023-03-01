package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PROGRAM_CERTIFICATE_TRANSCRIPT")
public class ProgramCertificateTranscriptEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PROGRAM_CERT_TRANS_ID", nullable = false)
    private String programCertTransId;

    @Column(name = "GRADUATION_PROGRAM_CODE", nullable = false)
    private String graduationProgramCode;

    @Column(name = "SCHOOL_CATEGORY_CODE")
    private String schoolCategoryCode;

    @Column(name = "CERTIFICATE_TYPE_CODE")
    private String certificateTypeCode;

    @Column(name = "TRANSCRIPT_TYPE_CODE")
    private String transcriptTypeCode;

}

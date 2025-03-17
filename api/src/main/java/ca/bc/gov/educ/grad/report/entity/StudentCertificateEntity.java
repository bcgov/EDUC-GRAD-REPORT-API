package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "STUDENT_CERTIFICATE")
public class StudentCertificateEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STUDENT_CERTIFICATE_ID", nullable = false, columnDefinition = "uuid")
    private UUID studentCertificateId;

    @Column(name = "CERTIFICATE")
    private String certificate;

    @Column(name = "CERTIFICATE_TYPE_CODE", nullable = false)
    private String certificateTypeCode;

    @Column(name = "GRADUATION_STUDENT_RECORD_ID", nullable = false, columnDefinition = "uuid")
    private UUID graduationStudentRecordId;

    @Column(name = "DOCUMENT_STATUS_CODE")
    private String documentStatusCode;

    @Column(name = "DISTRIBUTION_DATE")
    private Date distributionDate;

}

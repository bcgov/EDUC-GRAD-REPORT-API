package ca.bc.gov.educ.grad.report.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "STUDENT_CERTIFICATE")
public class StudentCertificateEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STUDENT_CERTIFICATE_ID", nullable = false)
    private UUID studentCertificateId;

    @Column(name = "CERTIFICATE")
    private String CERTIFICATE;

    @Column(name = "CERTIFICATE_TYPE_CODE", nullable = false)
    private String certificateTypeCode;

    @Column(name = "GRADUATION_STUDENT_RECORD_ID", nullable = false)
    private UUID graduationStudentRecordId;

    @Column(name = "DOCUMENT_STATUS_CODE")
    private String documentStatusCode;

    @Column(name = "DISTRIBUTION_DATE")
    private Date distributionDate;

}

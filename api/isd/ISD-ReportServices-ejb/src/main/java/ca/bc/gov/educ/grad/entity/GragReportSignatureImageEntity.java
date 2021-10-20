package ca.bc.gov.educ.grad.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Immutable
@Entity
@Table(name = "DIGIT_SIGNATURE")
public class GragReportSignatureImageEntity extends BaseEntity {

    @Id
    @Column(name = "DIGIT_SIGNATURE_ID", nullable = false)
    private UUID signatureId;

    @Lob
    @Column(name = "DIGIT_SIGNATURE_BLOB", columnDefinition="BLOB", nullable = false)
    private byte[] signatureContent;

    @Column(name = "DIGIT_SIGNATURE_CODE", nullable = false)
    private String gradReportSignatureCode;

    @Column(name = "DIGIT_SIGNATURE_TITLE", nullable = false)
    private String gradReportSignatureTitle;

    @Column(name = "DIGIT_SIGNATURE_NAME", nullable = false)
    private String gradReportSignatureName;

    @Column(name = "EFFECTIVE_DATE", nullable = true)
    private Date effectiveTimestamp;

    @Column(name = "EXPIRY_DATE", nullable = true)
    private Date expiryTimestamp;

}

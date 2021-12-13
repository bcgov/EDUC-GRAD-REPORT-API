package ca.bc.gov.educ.grad.report.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Data
@Immutable
@Entity
@Table(name = "DIGITAL_SIGNATURE")
public class GragReportSignatureImageEntity extends BaseEntity {

    @Id
    @Column(name = "DIGITAL_SIGNATURE_KEY", nullable = false)
    private UUID signatureId;

    @Lob
    @Column(name = "DIGITAL_SIGNATURE_BLOB", columnDefinition="BLOB", nullable = false)
    private byte[] signatureContent;

    @Column(name = "DIGITAL_SIGNATURE_CODE", nullable = false)
    private String gradReportSignatureCode;

    @Column(name = "DIGITAL_SIGNATURE_NAME")
    private String gradReportSignatureName;

}

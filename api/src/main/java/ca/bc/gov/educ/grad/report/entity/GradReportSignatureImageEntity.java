package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "DIGITAL_SIGNATURE")
public class GradReportSignatureImageEntity extends BaseEntity {

    @Id
    @Column(name = "DIGITAL_SIGNATURE_KEY", nullable = false)
    private UUID signatureId;

    @Lob
    @Column(name = "DIGITAL_SIGNATURE_BLOB", columnDefinition="BLOB", nullable = false)
    private byte[] signatureContent;

    @Column(name = "DIGITAL_SIGNATURE_CODE", nullable = false)
    private String gradReportSignatureCode;

    @Column(name = "DIGITAL_SIGNATURE_ORGANIZATION_NAME")
    private String gradReportSignatureOrganizationName;

    @Column(name = "DIGITAL_SIGNATURE_NAME")
    private String gradReportSignatureName;

}

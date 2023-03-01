package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Data
@Immutable
@Entity
@Table(name = "DIGITAL_SIGNATURE")
public class GradReportSignatureImageLightEntity extends BaseEntity {

    @Id
    @Column(name = "DIGITAL_SIGNATURE_KEY", nullable = false)
    private UUID signatureId;

    @Column(name = "DIGITAL_SIGNATURE_CODE", nullable = false)
    private String gradReportSignatureCode;

    @Column(name = "DIGITAL_SIGNATURE_NAME")
    private String gradReportSignatureName;

}

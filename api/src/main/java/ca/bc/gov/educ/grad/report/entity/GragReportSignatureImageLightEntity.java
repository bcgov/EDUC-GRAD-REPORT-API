package ca.bc.gov.educ.grad.report.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Immutable
@Entity
@Table(name = "DIGITAL_SIGNATURE")
public class GragReportSignatureImageLightEntity extends BaseEntity {

    @Id
    @Column(name = "DIGITAL_SIGNATURE_KEY", nullable = false)
    private UUID signatureId;

    @Column(name = "DIGITAL_SIGNATURE_CODE", nullable = false)
    private String gradReportSignatureCode;

    @Column(name = "DIGITAL_SIGNATURE_NAME")
    private String gradReportSignatureName;

}

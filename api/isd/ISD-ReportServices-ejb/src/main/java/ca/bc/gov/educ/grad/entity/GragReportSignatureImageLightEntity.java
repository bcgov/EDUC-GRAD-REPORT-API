package ca.bc.gov.educ.grad.entity;

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
@Table(name = "GRAD_REPORT_SIGNATURE")
public class GragReportSignatureImageLightEntity extends BaseEntity {

    @Id
    @Column(name = "GRAD_REPORT_SIGNATURE_ID", nullable = false)
    private UUID signatureId;

    @Column(name = "GRAD_REPORT_SIGNATURE_CODE", nullable = false)
    private String gradReportSignatureCode;

}

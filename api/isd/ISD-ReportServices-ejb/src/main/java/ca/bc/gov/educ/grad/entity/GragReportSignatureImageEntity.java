package ca.bc.gov.educ.grad.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Data
@Immutable
@Entity
@Table(name = "GRAD_REPORT_SIGNATURE")
public class GragReportSignatureImageEntity extends BaseEntity {

    @Id
    @Column(name = "GRAD_REPORT_SIGNATURE_ID", nullable = false)
    private UUID signatureId;

    @Lob
    @Column(name = "GRAD_REPORT_SIGNATURE", columnDefinition="LOB")
    private byte[] signatureContent;

    @Column(name = "GRAD_REPORT_SIGNATURE_CODE", nullable = true)
    private String gradReportSignatureCode;

}

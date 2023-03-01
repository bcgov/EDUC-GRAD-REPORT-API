package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CERTIFICATE_TYPE_CODE")
@EqualsAndHashCode(callSuper=false)
public class CertificateTypeCodeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CERTIFICATE_TYPE_CODE", nullable = false)
    private String certificateTypeCode;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DISPLAY_ORDER", nullable = false)
    private String displayOrder;

    @Column(name = "EFFECTIVE_DATE", nullable = false)
    private Date effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @Column(name = "PAPER_TYPE", nullable = true)
    private String paperType;

    @Column(name = "LANGUAGE", nullable = true)
    private String language;

}

package ca.bc.gov.educ.grad.report.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "CERTIFICATE_TYPE_CODE")
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

}

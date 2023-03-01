package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "DOCUMENT_STATUS_CODE")
public class DocumentStatusCodeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DOCUMENT_STATUS_CODE", nullable = false)
    private String documentStatusCode;

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

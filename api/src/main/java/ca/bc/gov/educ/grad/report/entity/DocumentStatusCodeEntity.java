package ca.bc.gov.educ.grad.report.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String LABEL;

    @Column(name = "DESCRIPTION", nullable = false)
    private String DESCRIPTION;

    @Column(name = "DISPLAY_ORDER", nullable = false)
    private String displayOrder;

    @Column(name = "EFFECTIVE_DATE", nullable = false)
    private Date effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

}

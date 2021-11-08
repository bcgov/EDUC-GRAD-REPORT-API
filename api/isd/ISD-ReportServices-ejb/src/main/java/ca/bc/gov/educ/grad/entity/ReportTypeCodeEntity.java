package ca.bc.gov.educ.grad.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "REPORT_TYPE_CODE")
public class ReportTypeCodeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "REPORT_TYPE_CODE", nullable = false)
    private String reportTypeCode;

    @Column(name = "LABEL")
    private String LABEL;

    @Column(name = "DESCRIPTION")
    private String DESCRIPTION;

    @Column(name = "DISPLAY_ORDER")
    private String displayOrder;

    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

}

package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
    private String label;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DISPLAY_ORDER")
    private String displayOrder;

    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

}

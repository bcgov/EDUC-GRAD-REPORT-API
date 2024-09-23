package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "SCHOOL_REPORT")
public class SchoolReportEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SCHOOL_REPORT_ID", nullable = false)
    private UUID schoolReportId;

    @Column(name = "REPORT")
    private String REPORT;

    @Column(name = "REPORT_TYPE_CODE", nullable = false)
    private String reportTypeCode;

    @Column(name = "SCHOOL_OF_RECORD")
    private String schoolOfRecord;

    @Column(name = "SCHOOL_OF_RECORD_ID", columnDefinition = "uuid")
    private UUID schoolOfRecordId;

}

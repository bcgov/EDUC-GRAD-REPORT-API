package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "STUDENT_REPORT")
public class StudentReportEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STUDENT_REPORT_ID", nullable = false)
    private UUID studentReportId;

    @Column(name = "REPORT")
    private String REPORT;

    @Column(name = "REPORT_TYPE_CODE", nullable = false)
    private String reportTypeCode;

    @Column(name = "GRADUATION_STUDENT_RECORD_ID", nullable = false, columnDefinition = "uuid")
    private UUID graduationStudentRecordId;

    @Column(name = "DOCUMENT_STATUS_CODE")
    private String documentStatusCode;

    @Column(name = "DISTRIBUTION_DATE")
    private Date distributionDate;

}

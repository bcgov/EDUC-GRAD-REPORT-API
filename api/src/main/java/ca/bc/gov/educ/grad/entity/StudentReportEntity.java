package ca.bc.gov.educ.grad.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "STUDENT_REPORT")
public class StudentReportEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "STUDENT_REPORT_ID", nullable = false)
    private String studentReportId;

    @Column(name = "REPORT")
    private String REPORT;

    @Column(name = "REPORT_TYPE_CODE", nullable = false)
    private String reportTypeCode;

    @Column(name = "GRADUTION_STUDENT_RECORD_ID", nullable = false)
    private String gradutionStudentRecordId;

    @Column(name = "DOCUMENT_STATUS_CODE")
    private String documentStatusCode;

    @Column(name = "DISTRIBUTION_DATE")
    private Date distributionDate;

}

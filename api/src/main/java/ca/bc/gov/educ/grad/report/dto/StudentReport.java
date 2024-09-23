package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class StudentReport extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String studentReportId;
    private String report;
    private String reportTypeCode;
    private String gradutionStudentRecordId;
    private String documentStatusCode;
    private Date distributionDate;
    private Date reportUpdateDate;

}

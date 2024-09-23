package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class SchoolReport extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private UUID schoolReportId;
    private String report;
    private String reportTypeCode;
    private String schoolOfRecord;
    private UUID schoolOfRecordId;

}

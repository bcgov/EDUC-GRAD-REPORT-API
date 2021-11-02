package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.grad.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class TranscriptTypeCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String transcriptTypeCode;
    private String label;
    private String description;
    private String displayOrder;
    private Date effectiveDate;
    private Date expiryDate;

}

package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.grad.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class ProgramCertificateTranscript extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String programCertTransId;
    private String graduationProgramCode;
    private String schoolCategoryCode;
    private String certificateTypeCode;
    private String transcriptTypeCode;

}

package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.grad.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class SignatureBlockTypeCode extends BaseEntity implements ca.bc.gov.educ.isd.codes.SignatureBlockType {

    private static final long serialVersionUID = 1L;

    private String signatureBlockTypeCode;
    private String label;
    private String description;


    @Override
    public String getCode() {
        return signatureBlockTypeCode;
    }

    @Override
    public String toString() {
        return label;
    }
}

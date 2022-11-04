package ca.bc.gov.educ.grad.report.model.common;

import java.io.Serializable;

public interface SignatureBlockType extends Serializable {

    String getCode();

    String getLabel();

    String getDescription();

    void setLabel(String label);

    void setDescription(String description);
}

package ca.bc.gov.educ.grad.report.model.common;

public interface SignatureBlockType {

    String getCode();

    String getLabel();

    String getDescription();

    void setLabel(String label);

    void setDescription(String description);
}

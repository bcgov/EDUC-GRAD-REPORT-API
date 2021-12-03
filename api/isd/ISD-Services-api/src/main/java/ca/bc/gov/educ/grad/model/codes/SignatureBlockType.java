package ca.bc.gov.educ.grad.model.codes;

public interface SignatureBlockType {

    String getCode();

    String getLabel();

    String getDescription();

    void setLabel(String label);

    void setDescription(String description);
}

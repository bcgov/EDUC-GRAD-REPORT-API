package ca.bc.gov.educ.grad.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SIGNATURE_BLOCK_TYPE")
public class SignatureBlockTypeCodeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SIGNATURE_BLOCK_TYPE", nullable = false)
    private String signatureBlockType;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

}

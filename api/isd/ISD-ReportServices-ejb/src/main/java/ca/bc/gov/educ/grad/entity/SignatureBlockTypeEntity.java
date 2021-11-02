package ca.bc.gov.educ.grad.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SIGNATURE_BLOCK_TYPE")
public class SignatureBlockTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SIGNATURE_BLOCK_TYPE", nullable = false)
    private String signatureBlockType;

    @Column(name = "LABEL", nullable = false)
    private String LABEL;

    @Column(name = "DESCRIPTION", nullable = false)
    private String DESCRIPTION;

}

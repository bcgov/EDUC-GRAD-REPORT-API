package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class StudentCertificate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String studentCertificateId;
    private String certificate;
    private String certificateTypeCode;
    private String gradutionStudentRecordId;
    private String documentStatusCode;
    private Date distributionDate;

}

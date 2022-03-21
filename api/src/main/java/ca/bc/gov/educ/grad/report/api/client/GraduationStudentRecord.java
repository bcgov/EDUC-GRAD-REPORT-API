package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class GraduationStudentRecord {

    private String studentGradData;
    private String pen;
    private UUID studentID;

}

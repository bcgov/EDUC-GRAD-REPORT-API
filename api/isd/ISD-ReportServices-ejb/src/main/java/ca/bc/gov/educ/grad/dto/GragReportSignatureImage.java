package ca.bc.gov.educ.grad.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class GragReportSignatureImage extends BaseModel {

    private UUID signatureId;
    private byte[] signatureContent;
    private String gradReportSignatureCode;

    @Override
    public String toString() {
        return "GragReportSignatureImage[" +
                "signatureId=" + signatureId +
                ", gradReportSignatureCode='" + gradReportSignatureCode + '\'' +
                ']';
    }
}

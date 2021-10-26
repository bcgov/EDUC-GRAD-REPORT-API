package ca.bc.gov.educ.grad.dto;

import ca.bc.gov.educ.grad.utils.Base64Deserializer;
import ca.bc.gov.educ.grad.utils.Base64Serializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class GragReportSignatureImage extends BaseModel {

    private UUID signatureId;
    @JsonSerialize(using = Base64Serializer.class)
    @JsonDeserialize(using = Base64Deserializer.class)
    private byte[] signatureContent;
    private String gradReportSignatureCode;
    private String gradReportSignatureTitle;
    private String gradReportSignatureName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date effectiveDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiryDate;

    @Override
    public String toString() {
        return "GragReportSignatureImage[" +
                "signatureId=" + signatureId +
                ", gradReportSignatureCode='" + gradReportSignatureCode + '\'' +
                ']';
    }
}

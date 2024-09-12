package ca.bc.gov.educ.grad.report.dto;

import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.utils.Base64Deserializer;
import ca.bc.gov.educ.grad.report.utils.Base64Serializer;
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
public class GradReportSignatureImage extends BaseModel {

    private UUID signatureId;
    @JsonSerialize(using = Base64Serializer.class)
    @JsonDeserialize(using = Base64Deserializer.class)
    private byte[] signatureContent;
    private String gradReportSignatureCode;
    private String gradReportSignatureName;
    private String gradReportSignatureOrganizationName;
    private String districtName;
    @JsonFormat(pattern= ReportApiConstants.DEFAULT_DATE_FORMAT)
    private Date effectiveDate;
    @JsonFormat(pattern= ReportApiConstants.DEFAULT_DATE_FORMAT)
    private Date expiryDate;

    @Override
    public String toString() {
        return "GragReportSignatureImage[" +
                "signatureId=" + signatureId +
                ", gradReportSignatureCode='" + gradReportSignatureCode + '\'' +
                ']';
    }
}

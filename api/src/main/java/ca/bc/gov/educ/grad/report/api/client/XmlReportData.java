package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter

public class XmlReportData implements Serializable {

    private String requestTrackId;
    private Pen pen;
    private String authorizeDate;
    private String psis;
    private String organizationName;
    private String addressLine1, addressLine2, addressLine3;
    private String city;
    private String stateProvince;
    private String postalCode;

    @JsonIgnore
    private String accessToken;

}

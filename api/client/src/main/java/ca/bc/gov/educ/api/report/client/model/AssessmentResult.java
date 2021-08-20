package ca.bc.gov.educ.api.report.client.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class AssessmentResult {

    @JsonProperty("name")
    private String assessmentName;
    @JsonProperty("code")
    private String assessmentCode;
    @JsonProperty("proficiencyScore")
    private int assessmentProficiencyScore;
    @JsonProperty("sessionDate")
    private String assessmentSession;
    private String requirementMet;
    private String specialCase;
    private String exceededWrites;
}

package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssessmentResult {

    private String assessmentName;
    private String assessmentCode;
    private double assessmentProficiencyScore;
    private String assessmentSession;
    private String requirementMet;
    private String specialCase;
    private String exceededWrites;

    @JsonProperty("assessmentName")
    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    @JsonProperty("assessmentCode")
    public String getAssessmentCode() {
        return assessmentCode;
    }

    public void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = assessmentCode;
    }

    @JsonProperty("proficiencyScore")
    public double getAssessmentProficiencyScore() {
        return assessmentProficiencyScore;
    }

    public void setAssessmentProficiencyScore(double assessmentProficiencyScore) {
        this.assessmentProficiencyScore = assessmentProficiencyScore;
    }

    @JsonProperty("sessionDate")
    public String getAssessmentSession() {
        return assessmentSession;
    }

    public void setAssessmentSession(String assessmentSession) {
        this.assessmentSession = assessmentSession;
    }

    @JsonProperty("gradReqMet")
    public String getRequirementMet() {
        return requirementMet;
    }

    public void setRequirementMet(String requirementMet) {
        this.requirementMet = requirementMet;
    }

    @JsonProperty("specialCase")
    public String getSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(String specialCase) {
        this.specialCase = specialCase;
    }

    @JsonProperty("exceededWriteFlag")
    public String getExceededWrites() {
        return exceededWrites;
    }

    public void setExceededWrites(String exceededWrites) {
        this.exceededWrites = exceededWrites;
    }
}

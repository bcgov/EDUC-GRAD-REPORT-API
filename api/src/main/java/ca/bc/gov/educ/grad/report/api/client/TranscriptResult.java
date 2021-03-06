package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;


public class TranscriptResult implements Serializable {

    private TranscriptCourse course = new TranscriptCourse();
    private Mark mark = new Mark();
    private String requirement = "";
    private String requirementName = "";
    private String equivalency = "";
    private String usedForGrad = "";

    @JsonDeserialize(as = TranscriptCourse.class)
    public TranscriptCourse getCourse() {
        return course;
    }

    public void setCourse(TranscriptCourse value) {
        this.course = value;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark value) {
        this.mark = value;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String value) {
        this.requirement = value;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String value) {
        this.requirementName = value;
    }

    public String getEquivalency() {
        return equivalency;
    }

    public void setEquivalency(String value) {
        this.equivalency = value;
    }

    public String getUsedForGrad() {
        return usedForGrad;
    }

    public void setUsedForGrad(String value) {
        this.usedForGrad = value;
    }
}

package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class GraduationData {
    private String graduationDate;
    private boolean honorsFlag;
    private boolean dogwoodFlag;
    private List<String> programCodes;
    private List<String> programNames;
    private String totalCreditsUsedForGrad;

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String value) {
        this.graduationDate = value;
    }

    public boolean getHonorsFlag() {
        return honorsFlag;
    }

    public void setHonorsFlag(boolean value) {
        this.honorsFlag = value;
    }

    public boolean getDogwoodFlag() {
        return dogwoodFlag;
    }

    public void setDogwoodFlag(boolean value) {
        this.dogwoodFlag = value;
    }

    public List<String> getProgramCodes() {
        return programCodes;
    }

    public void setProgramCodes(List<String> value) {
        this.programCodes = value;
    }

    public List<String> getProgramNames() {
        return programNames;
    }

    public void setProgramNames(List<String> value) {
        this.programNames = value;
    }

    public String getTotalCreditsUsedForGrad() {
        return totalCreditsUsedForGrad;
    }

    public void setTotalCreditsUsedForGrad(String value) {
        this.totalCreditsUsedForGrad = value;
    }
}

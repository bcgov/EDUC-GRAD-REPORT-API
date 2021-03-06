package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GraduationData implements Serializable {
    private Date graduationDate;
    private boolean honorsFlag;
    private boolean dogwoodFlag;
    private List<String> programCodes = new ArrayList<>();
    private List<String> programNames = new ArrayList<>();
    private String totalCreditsUsedForGrad = "";

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date value) {
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

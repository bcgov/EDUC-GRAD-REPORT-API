package ca.bc.gov.educ.grad.report.api.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Assessment {

    private Date issueDate;
    private List<AssessmentResult> results;
    private String interim;

    public String getInterim() {
        return interim;
    }

    public void setInterim(String value) {
        this.interim = value;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date value) {
        this.issueDate = value;
    }

    @JsonProperty("result")
    public List<AssessmentResult> getResults() {
        return results;
    }

    public void setResults(List<AssessmentResult> value) {
        this.results = value;
    }
}

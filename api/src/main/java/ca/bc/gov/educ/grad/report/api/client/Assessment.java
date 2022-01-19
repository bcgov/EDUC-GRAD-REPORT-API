package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.api.client.utils.AssessmentResultListDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

public class Assessment {

    private Date issueDate;
    private List<AssessmentResult> results;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date value) {
        this.issueDate = value;
    }

    @JsonProperty("results")
    @JsonDeserialize(using = AssessmentResultListDeserializer.class)
    public List<AssessmentResult> getResults() {
        return results;
    }

    public void setResults(List<AssessmentResult> value) {
        this.results = value;
    }
}

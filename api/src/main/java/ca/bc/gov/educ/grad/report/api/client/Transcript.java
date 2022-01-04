package ca.bc.gov.educ.grad.report.api.client;

import ca.bc.gov.educ.grad.report.api.client.utils.TranscriptResultListDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

public class Transcript {

    private String interim;
    private Date issueDate;
    private List<TranscriptResult> results;

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

    @JsonProperty("results")
    @JsonDeserialize(using = TranscriptResultListDeserializer.class)
    public List<TranscriptResult> getResults() {
        return results;
    }

    public void setResults(List<TranscriptResult> value) {
        this.results = value;
    }
}

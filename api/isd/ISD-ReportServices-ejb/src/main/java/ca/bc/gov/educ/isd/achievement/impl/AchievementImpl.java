package ca.bc.gov.educ.isd.achievement.impl;

import ca.bc.gov.educ.grad.utils.TranscriptResultListDeserializer;
import ca.bc.gov.educ.isd.achievement.Achievement;
import ca.bc.gov.educ.isd.achievement.AchievementResult;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.grad.GraduationProgramCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A container for a list of transcript courses associated with a student.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class AchievementImpl extends AbstractDomainEntity
        implements Achievement, Serializable  {

    private static final long serialVersionUID = 3L;

    private List<AchievementResult> results = new ArrayList<>();
    private Date issueDate;
    private boolean interim;

    @Override
    @JsonDeserialize(using = TranscriptResultListDeserializer.class)
    public List<AchievementResult> getResults() {
        return this.results;
    }

    /**
     * Returns the results, unsorted.
     *
     * @param code
     * @return
     * @deprecated Add sorting to ReportServices and remove the sorting from
     * TranscriptServices.
     */
    @Override
    @JsonIgnore
    public List<AchievementResult> getResults(final GraduationProgramCode code) {
        return getResults();
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getIssueDate() {
        return this.issueDate;
    }

    @Override
    public boolean isEmpty() {
        return getResults().isEmpty();
    }

    /**
     *
     * @param issueDate
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Set an entire collection of transcript results.
     *
     * @param results
     */
    public void setResults(List<AchievementResult> results) {
        // Prevents resetting the list to empty.
        if (results != null && !results.isEmpty()) {
            this.results = results;
        }
    }

    /**
     * Add an individual course to the transcript results list.
     *
     * @param result A transcript result to add to the list.
     */
    public void addResult(AchievementResult result) {
        if (result != null) {
            getResults().add(result);
        }
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setInterim(boolean interim) {
        this.interim = interim;
    }

    public boolean getInterim() {
        return this.interim;
    }
}

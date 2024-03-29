/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.report.model.transcript.Transcript;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptResult;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptTypeCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A container for a list of transcript courses associated with a student.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class TranscriptImpl extends AbstractDomainEntity
        implements Transcript, Serializable {

    private static final long serialVersionUID = 3L;

    private boolean interim;
    private boolean blank;
    private Date issueDate;
    private TranscriptTypeCode transcriptTypeCode;
    private List<TranscriptResult> results = new ArrayList<>();

    @Override
    public List<TranscriptResult> getResults() {
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
    public List<TranscriptResult> getResults(final GraduationProgramCode code) {
        return getResults();
    }

    @Override
    @JsonFormat(pattern= ReportApiConstants.DEFAULT_DATE_FORMAT)
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
    public void setResults(List<TranscriptResult> results) {
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
    public void addResult(TranscriptResult result) {
        if (result != null) {
            getResults().add(result);
        }
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setInterim(boolean interim) {
        this.interim = interim;
    }

    @Override
    public boolean getInterim() {
        return this.interim;
    }

    @Override
    public boolean getBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }

    @Override
    public TranscriptTypeCode getTranscriptTypeCode() {
        return transcriptTypeCode;
    }

    public void setTranscriptTypeCode(TranscriptTypeCode transcriptTypeCode) {
        this.transcriptTypeCode = transcriptTypeCode;
    }
}

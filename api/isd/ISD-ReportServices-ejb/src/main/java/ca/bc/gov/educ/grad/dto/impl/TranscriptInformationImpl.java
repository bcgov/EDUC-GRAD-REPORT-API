/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
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
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.model.transcript.Transcript;
import ca.bc.gov.educ.grad.model.transcript.TranscriptResult;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Basic Transcript information without any courses - use isEmpty to determine
 * if actual Transcript would contain courses.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptInformationImpl extends AbstractDomainEntity
        implements Transcript, Serializable {

    private static final long serialVersionUID = 3L;

    private Date issueDate;
    private boolean isEmpty;

    public TranscriptInformationImpl() {
    }

    public TranscriptInformationImpl(final Date issueDate, final boolean isEmpty) {
        this.issueDate = issueDate;
        this.isEmpty = isEmpty;
    }

    /**
     * Throws an exception when called.
     *
     * @return Nothing.
     */
    @Override
    public List<TranscriptResult> getResults() {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Throws an exception when called.
     *
     * @param code Not used.
     * @return Nothing.
     */
    @Override
    public List<TranscriptResult> getResults(
            final GraduationProgramCode code) {
        return getResults();
    }

    @Override
    public Date getIssueDate() {
        return this.issueDate;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

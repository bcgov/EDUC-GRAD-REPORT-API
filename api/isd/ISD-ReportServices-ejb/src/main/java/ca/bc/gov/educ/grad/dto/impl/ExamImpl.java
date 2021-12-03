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
 *  File:                ExamImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.exam.Exam;
import ca.bc.gov.educ.grad.model.exam.ExamResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * A container class for the exam examResults of a specific student.
 * <p>
 * @author CGI Information Management Consultants Inc.
 */
public class ExamImpl extends AbstractDomainEntity implements Exam {

    private static final long serialVersionUID = 2L;

    private final List<ExamResult> examResults = new ArrayList<>();
    private Date issueDate = new Date();

    @Override
    public List<ExamResult> getResults() {
        return this.examResults;
    }

    @Override
    public boolean isEmpty() {
        return getResults().isEmpty();
    }

    /**
     * set an entire collection of exam examResults to this student exam.
     * <p>
     * @param results
     */
    public void setResults(Collection<? extends ExamResult> results) {
        this.examResults.addAll(results);
    }

    /**
     * add an individual exam result to the exiting list of exam examResults.
     * <p>
     * @param result
     */
    public void addResult(ExamResult result) {
        this.examResults.add(result);
    }

    @Override
    public Date getIssueDate() {
        return this.issueDate;
    }

    /**
     * set the date in which the exam was last updated or run.
     * <p>
     * @param date
     */
    public void setIssueDate(Date date) {
        this.issueDate = date;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

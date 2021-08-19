/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.exam.impl;

import ca.bc.gov.educ.grad.utils.AssessmentResultListDeserializer;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.exam.Assessment;
import ca.bc.gov.educ.isd.exam.AssessmentResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AssessmentImpl extends AbstractDomainEntity implements Assessment, Serializable {

    private static final long serialVersionUID = 2L;

    private Date issueDate;
    private List<AssessmentResult> assessmentResults;

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @JsonProperty("results")
    @JsonDeserialize(using = AssessmentResultListDeserializer.class)
    public List<AssessmentResult> getResults() {
        return this.assessmentResults;
    }

    public void setResults(final List<AssessmentResult> assessmentResults) {
        this.assessmentResults = assessmentResults;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

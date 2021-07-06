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

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.exam.Assessment;
import ca.bc.gov.educ.isd.exam.AssessmentResult;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AssessmentImpl extends AbstractDomainEntity implements Assessment, Serializable {

    private static final long serialVersionUID = 2L;

    private List<AssessmentResult> assessmentResults;

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AssessmentResult> getResults() {
        return this.assessmentResults;
    }

    public void setResults(final List<AssessmentResult> assessmentResults) {
        this.assessmentResults = assessmentResults;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

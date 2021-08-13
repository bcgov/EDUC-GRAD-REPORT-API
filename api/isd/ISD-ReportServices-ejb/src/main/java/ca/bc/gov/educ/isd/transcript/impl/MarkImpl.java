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
 *  File:                MarkImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.transcript.impl;

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.transcript.Mark;

import java.io.Serializable;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 * Represents the marks associated with a course.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class MarkImpl extends AbstractDomainEntity
        implements Mark, Serializable {

    private static final long serialVersionUID = 2L;

    private String schoolPercent = "";
    private String examPercent = "";
    private String finalPercent = "";
    private String finalLetterGrade = "";
    private String interimPercent = "";
    private String interimLetterGrade = "";

    public MarkImpl() {
    }
    
    public MarkImpl(
            final String schoolPercent,
            final String examPercent,
            final String finalPercent,
            final String finalLetterGrade) {
        this.schoolPercent = nullSafe(schoolPercent);
        this.examPercent = nullSafe(examPercent);
        this.finalPercent = nullSafe(finalPercent);
        this.finalLetterGrade = nullSafe(finalLetterGrade);
    }

    public MarkImpl(
            final String schoolPercent,
            final String examPercent,
            final String finalPercent,
            final String finalLetterGrade,
            final String interimPercent,
            final String interimLetterGrade) {
        this(schoolPercent, examPercent, finalPercent, finalLetterGrade);

        this.interimPercent = nullSafe(interimPercent);
        this.interimLetterGrade = nullSafe(interimLetterGrade);
    }
    
    @Override
    public String getSchoolPercent() {
        return this.schoolPercent;
    }

    @Override
    public String getExamPercent() {
        return this.examPercent;
    }

    @Override
    public String getFinalPercent() {
        return this.finalPercent;
    }

    @Override
    public String getFinalLetterGrade() {
        return this.finalLetterGrade;
    }

    @Override
    public String getInterimPercent() {
        return this.interimPercent;
    }

    @Override
    public String getInterimLetterGrade() {
        return this.interimLetterGrade;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSchoolPercent(final String schoolPercent) {
        this.schoolPercent = schoolPercent;
    }

    public void setExamPercent(final String examPercent) {
        this.examPercent = examPercent;
    }

    public void setFinalPercent(final String finalPercent) {
        this.finalPercent = finalPercent;
    }

    public void setFinalLetterGrade(final String finalLetterGrade) {
        this.finalLetterGrade = finalLetterGrade;
    }

    public void setInterimPercent(final String interimPercent) {
        this.interimPercent = interimPercent;
    }

    public void setInterimLetterGrade(final String interimLetterGrade) {
        this.interimLetterGrade = interimLetterGrade;
    }
    
    
}

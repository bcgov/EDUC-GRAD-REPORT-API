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
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.transcript.Mark;

import java.io.Serializable;

import static ca.bc.gov.educ.grad.model.common.support.VerifyUtils.nullSafe;

/**
 * Represents the marks associated with a course.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class MarkImpl extends AbstractDomainEntity
        implements Mark, Serializable {

    private static final long serialVersionUID = 2L;

    private String schoolPercent = "";
    private String bestSchoolPercent = "";
    private String examPercent = "";
    private String bestExamPercent = "";
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
    public String getBestSchoolPercent() {
        return this.bestSchoolPercent;
    }

    @Override
    public String getExamPercent() {
        return this.examPercent;
    }

    @Override
    public String getBestExamPercent() {
        return this.bestExamPercent;
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

    public void setBestSchoolPercent(String bestSchoolPercent) {
        this.bestSchoolPercent = bestSchoolPercent;
    }

    public void setExamPercent(String examPercent) {
        this.examPercent = examPercent;
    }

    public void setBestExamPercent(String bestExamPercent) {
        this.bestExamPercent = bestExamPercent;
    }

    public void setFinalPercent(String finalPercent) {
        this.finalPercent = finalPercent;
    }

    public void setFinalLetterGrade(String finalLetterGrade) {
        this.finalLetterGrade = finalLetterGrade;
    }

    public void setInterimPercent(final String interimPercent) {
        this.interimPercent = interimPercent;
    }

    public void setInterimLetterGrade(final String interimLetterGrade) {
        this.interimLetterGrade = interimLetterGrade;
    }
    
    
}

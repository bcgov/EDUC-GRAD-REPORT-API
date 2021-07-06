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
package ca.bc.gov.educ.isd.exam.impl;

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.exam.Mark;

import java.io.Serializable;

/**
 * Represents the examination marks for a course.
 * <p>
 * @author CGI Information Management Consultants Inc.
 */
public class MarkImpl extends AbstractDomainEntity implements Mark, Serializable {

    private static final long serialVersionUID = 1L;

    private String schoolPercent = "";
    private String bestSchoolPercent = "";
    private String examPercent = "";
    private String bestExamPercent = "";
    private String finalPercent = "";
    private String finalLetterGrade = "";

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

    public void setSchoolPercent(String schoolPercent) {
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

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

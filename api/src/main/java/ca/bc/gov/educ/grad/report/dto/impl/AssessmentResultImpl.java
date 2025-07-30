/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education and Child Care, BC.
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

import ca.bc.gov.educ.grad.report.model.assessment.AssessmentResult;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
public class AssessmentResultImpl extends AbstractDomainEntity
        implements AssessmentResult, Serializable {

    private static final long serialVersionUID = 3L;

    private String studentNumber;
    private String assessmentName;
    private String assessmentCode;
    private String proficiencyScore;
    private String sessionDate;
    private String gradReqMet;
    private String specialCase;
    private String exceededWriteFlag;
    private Boolean projected;

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String getAssessmentCode() {
        return assessmentCode;
    }

    @Override
    public String getSessionDate() {
        return sessionDate;
    }

    @Override
    public String getProficiencyScore() {
        return proficiencyScore;
    }

    @Override
    public String getAssessmentName() {
        return assessmentName;
    }

    public Boolean getProjected() {
        return projected;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getGradReqMet() {
        return gradReqMet;
    }

    public void setGradReqMet(String gradReqMet) {
        this.gradReqMet = gradReqMet;
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(String specialCase) {
        this.specialCase = specialCase;
    }

    public String getExceededWriteFlag() {
        return exceededWriteFlag;
    }

    public void setExceededWriteFlag(String exceededWriteFlag) {
        this.exceededWriteFlag = exceededWriteFlag;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = nullSafe(assessmentCode);
    }

    public void setProficiencyScore(String proficiencyScore) {
        this.proficiencyScore = proficiencyScore;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = nullSafe(sessionDate);
    }

    public void setProjected(Boolean projected) {
        this.projected = projected;
    }
}

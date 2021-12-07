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
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.assessment.AssessmentResult;
import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;

import java.io.Serializable;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.model.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AssessmentResultImpl extends AbstractDomainEntity
        implements AssessmentResult, Serializable {

    private static final long serialVersionUID = 3L;

    private static final String CLASSNAME = AssessmentResultImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    final private static Integer DEFAULT_SCORE = -1;
    final private static String DEFAULT_NAME = "";

    private String studentNumber;
    private String assessmentName;
    private String assessmentCode;
    private int assessmentProficiencyScore;
    private String assessmentSession;
    private String requirementMet;
    private String specialCase;
    private String exceededWrites;

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
    public String getAssessmentSession() {
        return assessmentSession;
    }

    @Override
    public int getAssessmentProficiencyScore() {
        return assessmentProficiencyScore;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getRequirementMet() {
        return requirementMet;
    }

    public void setRequirementMet(String requirementMet) {
        this.requirementMet = requirementMet;
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(String specialCase) {
        this.specialCase = specialCase;
    }

    public String getExceededWrites() {
        return exceededWrites;
    }

    @Override
    public String getAssessementSession() {
        return this.assessmentSession;
    }

    public void setExceededWrites(String exceededWrites) {
        this.exceededWrites = exceededWrites;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = nullSafe(assessmentCode);
    }

    public void setAssessmentProficiencyScore(int assessmentProficiencyScore) {
        this.assessmentProficiencyScore = assessmentProficiencyScore;
    }

    public void setAssessmentSession(String assessmentSession) {
        this.assessmentSession = nullSafe(assessmentSession);
    }

}

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

import ca.bc.gov.educ.grad.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.model.exam.AssessmentResult;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

    private int assessmentTotalSelectedResponse;
    private int studentAssessmentTotalSelectedResponse;
    private int assessmentTotalExtendedResponse;
    private int studentAssessmentTotalExtendedResponse;

    private List<String> assessmentSelectedResponseNames;
    private List<Integer> assessmentSelectedResponseValues;
    private List<String> assessmentExtendedResponseNames;
    private List<Integer> assessmentExtendedResponseValues;

    private Date updateDate;

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

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public int getAssessmentTotalSelectedResponse() {
        return assessmentTotalSelectedResponse;
    }

    @Override
    public int getStudentAssessmentTotalSelectedResponse() {
        return studentAssessmentTotalSelectedResponse;
    }

    @Override
    public int getAssessmentTotalExtendedResponse() {
        return assessmentTotalExtendedResponse;
    }

    @Override
    public int getStudentAssessmentTotalExtendedResponse() {
        return studentAssessmentTotalExtendedResponse;
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

    public void setExceededWrites(String exceededWrites) {
        this.exceededWrites = exceededWrites;
    }

    /**
     *
     * @param index zero based index
     * @return
     */
    @Override
    public String getAssessmentSelectedResponseName(int index) {
        String retVal = (String) getObjectAtIndex(this.assessmentSelectedResponseNames, index, DEFAULT_NAME);
        return retVal;
    }

    /**
     *
     * @param index zero based index
     * @return
     */
    @Override
    public int getAssessmentSelectedResponseValue(int index) {
        int retVal;

        Integer score = (Integer) getObjectAtIndex(this.assessmentSelectedResponseValues, index, DEFAULT_SCORE);
        retVal = score;

        return retVal;
    }

    @Override
    public Iterator<String> getAssessmentSelectedResponseNamesIterator() {
        Iterator retVal = this.assessmentSelectedResponseNames.iterator();
        return retVal;
    }

    @Override
    public Iterator<Integer> getAssessmentSelectedResponseValuesIterator() {
        Iterator retVal = this.assessmentSelectedResponseValues.iterator();
        return retVal;
    }

    /**
     *
     * @param index zero based index
     * @return
     */
    @Override
    public String getAssessmentExtendedResponseName(int index) {
        String retVal = (String) getObjectAtIndex(this.assessmentExtendedResponseNames, index, DEFAULT_NAME);
        return retVal;
    }

    /**
     *
     * @param index zero based index
     * @return
     */
    @Override
    public int getAssessmentExtendedResponseValue(int index) {
        int retVal;

        Integer score = (Integer) getObjectAtIndex(this.assessmentExtendedResponseValues, index, DEFAULT_SCORE);
        retVal = score;

        return retVal;
    }

    @Override
    public Iterator<String> getAssessmentExtendedResponseNamesIterator() {

        Iterator retVal = this.assessmentExtendedResponseNames.iterator();
        return retVal;
    }

    @Override
    public Iterator<Integer> getAssessmentExtendedResponseValuesIterator() {

        Iterator retVal = this.assessmentExtendedResponseValues.iterator();
        return retVal;
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

    public void setAssessmentTotalSelectedResponse(int assessmentTotalSelectedResponse) {
        this.assessmentTotalSelectedResponse = assessmentTotalSelectedResponse;
    }

    public void setStudentAssessmentTotalSelectedResponse(int studentAssessmentTotalSelectedResponse) {
        this.studentAssessmentTotalSelectedResponse = studentAssessmentTotalSelectedResponse;
    }

    public void setAssessmentTotalExtendedResponse(int assessmentTotalExtendedResponse) {
        this.assessmentTotalExtendedResponse = assessmentTotalExtendedResponse;
    }

    public void setStudentAssessmentTotalExtendedResponse(int studentAssessmentTotalExtendedResponse) {
        this.studentAssessmentTotalExtendedResponse = studentAssessmentTotalExtendedResponse;
    }

    public void setAssessmentSelectedResponseNames(List<String> list) {
        this.assessmentSelectedResponseNames = list;
    }

    public void setAssessmentSelectedResponseValues(List<Integer> list) {
        this.assessmentSelectedResponseValues = list;
    }

    public void setAssessmentExtendedResponseNames(List<String> list) {
        this.assessmentExtendedResponseNames = list;
    }

    public void setAssessmentExtendedResponseValues(List<Integer> list) {
        this.assessmentExtendedResponseValues = list;
    }

    public void setUpdateDate(Date date) {
        this.updateDate = date;
    }

    public void setAssessmentSession(String assessmentSession) {
        this.assessmentSession = nullSafe(assessmentSession);
    }

    private Object getObjectAtIndex(List<?> list, int index, Object defaultValue) {
        final String methodName = "getObjectAtIndex(List, int, Object)";
        LOG.entering(CLASSNAME, methodName);

        Object retVal = defaultValue;
        try {
            retVal = list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            // No need to do anything;
        }
        LOG.exiting(CLASSNAME, methodName);
        return retVal;
    }

}

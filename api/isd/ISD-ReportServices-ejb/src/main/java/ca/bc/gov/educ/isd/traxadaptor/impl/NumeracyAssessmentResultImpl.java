/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.traxadaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.NumAssessmentResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is for an assessment course the student has taken.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class NumeracyAssessmentResultImpl implements NumAssessmentResult {

    private static final long serialVersionUID = 4L;

    private static final String CLASSNAME = NumeracyAssessmentResultImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String studentNumber;
    private String assessmentCode;
    private int assessmentProficiencyScore;
    private String assessmentName;
    private String assessementSession;
    private String requirementMet;
    private String specialCase;
    private String exceededWrites;

    private int assessmentTotalSelectedResponse;
    private int studentAssessmentTotalSelectedResponse;
    private int assessmentTotalExtendedResponse;
    private int studentAssessmentTotalExtendedResponse;

    private final List<String> assessmentSelectedResponseNames = new ArrayList<>();
    private final List<Integer> assessmentSelectedResponseValues = new ArrayList<>();
    private final List<Integer> assessmentSelectedResponseTotals = new ArrayList<>();
    private final List<String> assessmentExtendedResponseNames = new ArrayList<>();
    private final List<Integer> assessmentExtendedResponseValues = new ArrayList<>();
    private final List<Integer> assessmentExtendedResponseTotals = new ArrayList<>();

    public NumeracyAssessmentResultImpl(
            final String studentNumber,
            final String assessmentName,
            final String assessmentCode,
            final String assessmentSession,
            final String requirementMet,
            final String specialCase,
            final String exceededWrites,
            final BigDecimal assessmentProficiencyScore) {
        setAssessmentName(assessmentName);
        setStudentNumber(studentNumber);
        setAssessmentCode(assessmentCode);
        setAssessmentSession(assessmentSession);
        setRequirementMet(requirementMet);
        setSpecialCase(specialCase);
        setExceededWrites(exceededWrites);
        setAssessmentProficiencyScore(assessmentProficiencyScore);
    }

    public NumeracyAssessmentResultImpl(
            final String studentNumber,
            final String assessmentCode,
            final String assessmentSession,
            final BigDecimal assessmentProficiencyScore,
            final BigDecimal assessmentTotalSelectedResponse,
            final BigDecimal studentAssessmentTotalSeletedResponse,
            final BigDecimal assessmentTotalExtendedResponse,
            final BigDecimal studentAssessmentTotalExtendedResponse,
            final String assessmentSelectedResponseName1,
            final BigDecimal assessmentSelectedResponseValue1,
            final BigDecimal assessmentSelectedResponseTotal1,
            final String assessmentSelectedResponseName2,
            final BigDecimal assessmentSelectedResponseValue2,
            final BigDecimal assessmentSelectedResponseTotal2,
            final String assessmentSelectedResponseName3,
            final BigDecimal assessmentSelectedResponseValue3,
            final BigDecimal assessmentSelectedResponseTotal3,
            final String assessmentSelectedResponseName4,
            final BigDecimal assessmentSelectedResponseValue4,
            final BigDecimal assessmentSelectedResponseTotal4,
            final String assessmentExtendedResponseName1,
            final BigDecimal assessmentExtendedResponseValue1,
            final BigDecimal assessmentExtendedResponseTotal1,
            final String assessmentExtendedResponseName2,
            final BigDecimal assessmentExtendedResponseValue2,
            final BigDecimal assessmentExtendedResponseTotal2) {

        setStudentNumber(studentNumber);
        setAssessmentCode(assessmentCode);
        setAssessmentSession(assessmentSession);
        setAssessmentProficiencyScore(assessmentProficiencyScore);

        setAssessmentTotalSeletedResponse(assessmentTotalSelectedResponse);
        setStudentAssessmentTotalSeletedResponse(studentAssessmentTotalSeletedResponse);
        setAssessmentTotalExtendedResponse(assessmentTotalExtendedResponse);
        setStudentAssessmentTotalExtendedResponse(studentAssessmentTotalExtendedResponse);

        addAssessmentSelectedResponseName(assessmentSelectedResponseName1);
        addAssessmentSelectedResponseName(assessmentSelectedResponseName2);
        addAssessmentSelectedResponseName(assessmentSelectedResponseName3);
        addAssessmentSelectedResponseName(assessmentSelectedResponseName4);

        addAssessmentSelectedResponseValue(assessmentSelectedResponseValue1);
        addAssessmentSelectedResponseValue(assessmentSelectedResponseValue2);
        addAssessmentSelectedResponseValue(assessmentSelectedResponseValue3);
        addAssessmentSelectedResponseValue(assessmentSelectedResponseValue4);

        addAssessmentSelectedResponseTotal(assessmentSelectedResponseTotal1);
        addAssessmentSelectedResponseTotal(assessmentSelectedResponseTotal2);
        addAssessmentSelectedResponseTotal(assessmentSelectedResponseTotal3);
        addAssessmentSelectedResponseTotal(assessmentSelectedResponseTotal4);

        addAssessmentExtendedResponseName(assessmentExtendedResponseName1);
        addAssessmentExtendedResponseName(assessmentExtendedResponseName2);

        addAssessmentExtendedResponseValue(assessmentExtendedResponseValue1);
        addAssessmentExtendedResponseValue(assessmentExtendedResponseValue2);

        addAssessmentExtendedResponseTotal(assessmentExtendedResponseTotal1);
        addAssessmentExtendedResponseTotal(assessmentExtendedResponseTotal2);
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
    public int getAssessmentProficiencyScore() {
        return assessmentProficiencyScore;
    }

    @Override
    public String getAssessmentSession() {
        return this.assessementSession;
    }

    @Override
    public String getAssessmentName() {
        return assessmentName;
    }

    @Override
    public String getRequirementMet() {
        return requirementMet;
    }

    @Override
    public String getSpecialCase() {
        return specialCase;
    }

    @Override
    public String getExceededWrites() {
        return exceededWrites;
    }

    @Override
    public String getAssessementSession() {
        return assessementSession;
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

    @Override
    public List<String> getAssessmentSelectedResponseNames() {
        return this.assessmentSelectedResponseNames;
    }

    @Override
    public List<Integer> getAssessmentSelectedResponseValues() {
        return this.assessmentSelectedResponseValues;
    }

    @Override
    public List<Integer> getAssessmentSelectedResponseTotals() {
        return this.assessmentSelectedResponseTotals;
    }

    @Override
    public List<String> getAssessmentExtendedResponseNames() {
        return this.assessmentExtendedResponseNames;
    }

    @Override
    public List<Integer> getAssessmentExtendedResponseValues() {
        return this.assessmentExtendedResponseValues;
    }

    @Override
    public List<Integer> getAssessmentExtendedResponseTotals() {
        return this.assessmentExtendedResponseTotals;
    }

    private void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    private void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = nullSafe(assessmentCode);
    }

    private void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    private void setRequirementMet(String requirementMet) {
        this.requirementMet = requirementMet;
    }

    private void setSpecialCase(String specialCase) {
        this.specialCase = specialCase;
    }

    private void setExceededWrites(String exceededWrites) {
        this.exceededWrites = exceededWrites;
    }

    private void setAssessmentProficiencyScore(BigDecimal assessmentProficiencyScore) {
        this.assessmentProficiencyScore = nullSafe(assessmentProficiencyScore).intValue();
    }

    private void setAssessmentTotalSeletedResponse(BigDecimal assessmentTotalSelectedResponse) {
        this.assessmentTotalSelectedResponse = nullSafe(assessmentTotalSelectedResponse).intValue();
    }

    private void setStudentAssessmentTotalSeletedResponse(BigDecimal studentAssessmentTotalSeletedResponse) {
        this.studentAssessmentTotalSelectedResponse = nullSafe(studentAssessmentTotalSeletedResponse).intValue();
    }

    private void setAssessmentTotalExtendedResponse(BigDecimal assessmentTotalExtendedResponse) {
        this.assessmentTotalExtendedResponse = nullSafe(assessmentTotalExtendedResponse).intValue();
    }

    private void setStudentAssessmentTotalExtendedResponse(BigDecimal studentAssessmentTotalExtendedResponse) {
        this.studentAssessmentTotalExtendedResponse = nullSafe(studentAssessmentTotalExtendedResponse).intValue();
    }

    private void addAssessmentSelectedResponseName(String assessmentSelectedResponseName) {
        this.assessmentSelectedResponseNames.add(nullSafe(assessmentSelectedResponseName));
    }

    private void addAssessmentSelectedResponseValue(BigDecimal assessmentSelectedResponseValue) {
        this.assessmentSelectedResponseValues.add(nullSafe(assessmentSelectedResponseValue).intValue());
    }

    private void addAssessmentSelectedResponseTotal(BigDecimal assessmentSelectedResponseTotal) {
        this.assessmentSelectedResponseTotals.add(nullSafe(assessmentSelectedResponseTotal).intValue());
    }

    private void addAssessmentExtendedResponseName(String assessmentExtendedResponseName) {
        this.assessmentExtendedResponseNames.add(nullSafe(assessmentExtendedResponseName));
    }

    private void addAssessmentExtendedResponseValue(BigDecimal assessmentExtendedResponseValue) {
        this.assessmentExtendedResponseValues.add(nullSafe(assessmentExtendedResponseValue).intValue());
    }

    private void addAssessmentExtendedResponseTotal(BigDecimal assessmentExtendedResponseTotal) {
        this.assessmentExtendedResponseTotals.add(nullSafe(assessmentExtendedResponseTotal).intValue());
    }

    private void setAssessmentSession(String assessmentSession) {
        this.assessementSession = assessmentSession;
    }

}

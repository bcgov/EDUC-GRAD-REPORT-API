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
package ca.bc.gov.educ.isd.adaptor.impl;

import ca.bc.gov.educ.isd.eis.trax.db.LitAssessmentResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;
import static ca.bc.gov.educ.isd.eis.assessment.AssessmentCode.*;

/**
 * A TRAX data object containing data from the TRAX database for the given
 * student PEN. The data is for an assessment course the student has taken.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class LiteracyAssessmentResultImpl implements LitAssessmentResult {

    private static final long serialVersionUID = 4L;

    private static final String CLASSNAME = LiteracyAssessmentResultImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String studentNumber;
    private String assessmentCode;
    private int assessmentProficiencyScore;
    private String assessmentName;
    private String assessementSession;
    private String requirementMet;
    private String specialCase;
    private String exceededWrites;
    
    private final List<String> assessmentByTaskNames = new ArrayList<>();
    private final List<Integer> assessmentByTaskValues = new ArrayList<>();
    private final List<Integer> assessmentByTaskTotals = new ArrayList<>();
    
    private final List<String> assessmentByPartANames = new ArrayList<>();
    private final List<Integer> assessmentByPartAValues = new ArrayList<>();
    private final List<Integer> assessmentByPartATotals = new ArrayList<>();
    
    private final List<String> assessmentByPartBNames = new ArrayList<>();
    private final List<Integer> assessmentByPartBValues = new ArrayList<>();
    private final List<Integer> assessmentByPartBTotals = new ArrayList<>();
    
    private final List<String> assessmentByPartConceptsNames = new ArrayList<>();
    private final List<Integer> assessmentByPartConceptsValues = new ArrayList<>();
    private final List<Integer> assessmentByPartConceptsTotals = new ArrayList<>();

    private String assessmentSession;
    private String choicePathID;

    public LiteracyAssessmentResultImpl(
            final String studentNumber,
            final String assessmentCode,
            final String assessmentSession,
            final BigDecimal assessmentProficiencyScore,
            
            final BigDecimal assessmentTotalClaimC,
            final BigDecimal studentAssessmentTotalClaimC,
            final String assessmentNameClaimC,
            
            final BigDecimal assessmentTotalClaimW,
            final BigDecimal studentAssessmentTotalClaimW,
            final String assessmentNameClaimW,
            
            final String assessmentSelectedResponseNameTaskA,
            final BigDecimal assessmentSelectedResponseValueTaskA,
            final BigDecimal assessmentSelectedResponseTotalTaskA,            
            
            final String assessmentSelectedResponseNameTaskEI,
            final BigDecimal assessmentSelectedResponseValueTaskEI,
            final BigDecimal assessmentSelectedResponseTotalTaskEI,   
            
            final String assessmentExtendedResponseNameTaskEI,
            final BigDecimal assessmentExtendedResponseValueTaskEI,
            final BigDecimal assessmentExtendedResponseTotalTaskEI,    
            
            final String assessmentExtendedResponseNameConceptsGO,            
            final BigDecimal assessmentExtendedResponseValueConceptsGO,
            final BigDecimal assessmentExtendedResponseTotalConceptsGO,
            
            final String assessmentExtendedResponseNameConceptsWRA,
            final BigDecimal assessmentExtendedResponseValueConceptsWRA,
            final BigDecimal assessmentExtendedResponseTotalConceptsWRA,
            
            final String choicePathID
            ) {

        setStudentNumber(studentNumber);
        setAssessmentCode(assessmentCode);
        setAssessmentSession(assessmentSession);
        setAssessmentProficiencyScore(assessmentProficiencyScore);

        addAssessmentByTaskName(COMPREHEND.getCode());
        addAssessmentByTaskName(COMMUNICATE.getCode());
        addAssessmentByTaskValue(studentAssessmentTotalClaimC);
        addAssessmentByTaskValue(studentAssessmentTotalClaimW);
        addAssessmentByTaskTotal(assessmentTotalClaimC);
        addAssessmentByTaskTotal(assessmentTotalClaimW);
        
        addSubCategoryA(PART_A.getCode());
        
        addAssessmentByPartAName(SELECTED_RESPONSE_A.getCode());
        addAssessmentByPartAName(GRAPHIC_ORGANIZER.getCode());
        addAssessmentByPartAName(WRITTEN_RESPONSE_A.getCode());
        addAssessmentByPartAValue(assessmentSelectedResponseValueTaskA);
        addAssessmentByPartAValue(assessmentExtendedResponseValueConceptsGO);
        addAssessmentByPartAValue(assessmentExtendedResponseValueConceptsWRA);
        addAssessmentByPartATotal(assessmentSelectedResponseTotalTaskA);
        addAssessmentByPartATotal(assessmentExtendedResponseTotalConceptsGO);
        addAssessmentByPartATotal(assessmentExtendedResponseTotalConceptsWRA);
        
        addSubCategoryB(choicePathID);
        
        addAssessmentByPartBName(SELECTED_RESPONSE_B.getCode());
        addAssessmentByPartBName(WRITTEN_RESPONSE_B.getCode());
        addAssessmentByPartBValue(assessmentSelectedResponseValueTaskEI);
        addAssessmentByPartBValue(assessmentExtendedResponseValueTaskEI);
        addAssessmentByPartBTotal(assessmentSelectedResponseTotalTaskEI);
        addAssessmentByPartBTotal(assessmentExtendedResponseTotalTaskEI);
        
        setChoicePathID(choicePathID);
    }
    
    public LiteracyAssessmentResultImpl(
            final String studentNumber,
            final String assessmentCode,
            final String assessmentSession,
            final BigDecimal assessmentProficiencyScore,
            
            final BigDecimal assessmentTotalClaimC,
            final BigDecimal studentAssessmentTotalClaimC,
            final String assessmentNameClaimC,
            
            final BigDecimal assessmentTotalClaimW,
            final BigDecimal studentAssessmentTotalClaimW,
            final String assessmentNameClaimW,
            
            final BigDecimal assessmentTotalClaimO,
            final BigDecimal studentAssessmentTotalClaimO,
            final String assessmentNameClaimO,
            
            final String assessmentSelectedResponseNameTaskA,
            final BigDecimal assessmentSelectedResponseValueTaskA,
            final BigDecimal assessmentSelectedResponseTotalTaskA,            
            
            final String assessmentSelectedResponseNameTaskEI,
            final BigDecimal assessmentSelectedResponseValueTaskEI,
            final BigDecimal assessmentSelectedResponseTotalTaskEI,   
            
            final String assessmentExtendedResponseNameTaskEI,
            final BigDecimal assessmentExtendedResponseValueTaskEI,
            final BigDecimal assessmentExtendedResponseTotalTaskEI,    
            
            final String assessmentExtendedResponseNameConceptsGO,            
            final BigDecimal assessmentExtendedResponseValueConceptsGO,
            final BigDecimal assessmentExtendedResponseTotalConceptsGO,
            
            final String assessmentExtendedResponseNameConceptsWRA,
            final BigDecimal assessmentExtendedResponseValueConceptsWRA,
            final BigDecimal assessmentExtendedResponseTotalConceptsWRA,
            
            final String assessmentExtendedResponseNameConceptsWRS,
            final BigDecimal assessmentExtendedResponseValueConceptsWRS,
            final BigDecimal assessmentExtendedResponseTotalConceptsWRS,
            
            final String assessmentNameConcepts1,
            final BigDecimal assessmentValueConcepts1,
            final BigDecimal assessmentTotalConcepts1,
            
            final String assessmentNameConcepts2,
            final BigDecimal assessmentValueConcepts2,
            final BigDecimal assessmentTotalConcepts2,
            
            final String assessmentNameConcepts3,
            final BigDecimal assessmentValueConcepts3,
            final BigDecimal assessmentTotalConcepts3,
            
            final String choicePathID
            ) {

        setStudentNumber(studentNumber);
        setAssessmentCode(assessmentCode);
        setAssessmentSession(assessmentSession);
        setAssessmentProficiencyScore(assessmentProficiencyScore);

        addAssessmentByTaskName(C_FR.getCode());
        addAssessmentByTaskName(W_FR.getCode());
        addAssessmentByTaskName(O_FR.getCode());
        addAssessmentByTaskValue(studentAssessmentTotalClaimC);
        addAssessmentByTaskValue(studentAssessmentTotalClaimW);
        addAssessmentByTaskValue(studentAssessmentTotalClaimO);
        addAssessmentByTaskTotal(assessmentTotalClaimC);
        addAssessmentByTaskTotal(assessmentTotalClaimW);
        addAssessmentByTaskTotal(assessmentTotalClaimO);
        
        addSubCategoryA(PART_A_FR.getCode());
        
        addAssessmentByPartAName(SRA_FR.getCode());
        addAssessmentByPartAName(WRS_FR.getCode());
        addAssessmentByPartAName(GRO_FR.getCode());
        addAssessmentByPartAName(WRA_FR.getCode());
        addAssessmentByPartAValue(assessmentSelectedResponseValueTaskA);
        addAssessmentByPartAValue(assessmentExtendedResponseValueConceptsWRS);
        addAssessmentByPartAValue(assessmentExtendedResponseValueConceptsGO);
        addAssessmentByPartAValue(assessmentExtendedResponseValueConceptsWRA);
        addAssessmentByPartATotal(assessmentSelectedResponseTotalTaskA);
        addAssessmentByPartATotal(assessmentExtendedResponseTotalConceptsWRS);
        addAssessmentByPartATotal(assessmentExtendedResponseTotalConceptsGO);
        addAssessmentByPartATotal(assessmentExtendedResponseTotalConceptsWRA);
        
        addSubCategoryB(choicePathID+"_FR");
        
        addAssessmentByPartBName(SRB_FR.getCode());
        addAssessmentByPartBName(WRB_FR.getCode());
        addAssessmentByPartBValue(assessmentSelectedResponseValueTaskEI);
        addAssessmentByPartBValue(assessmentExtendedResponseValueTaskEI);
        addAssessmentByPartBTotal(assessmentSelectedResponseTotalTaskEI);
        addAssessmentByPartBTotal(assessmentExtendedResponseTotalTaskEI);
        
        addSubCategoryC(PART_C_FR.getCode());
        
        addAssessmentByPartConceptsName(CO_1.getCode());
        addAssessmentByPartConceptsName(CO_2.getCode());
        addAssessmentByPartConceptsName(CO_3.getCode());
        addAssessmentByPartConceptsValue(assessmentValueConcepts1);
        addAssessmentByPartConceptsValue(assessmentValueConcepts2);
        addAssessmentByPartConceptsValue(assessmentValueConcepts3);
        addAssessmentByPartConceptsTotal(assessmentTotalConcepts1);
        addAssessmentByPartConceptsTotal(assessmentTotalConcepts2);
        addAssessmentByPartConceptsTotal(assessmentTotalConcepts3);
        
        setChoicePathID(choicePathID);
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
        return this.assessmentSession;
    }

    @Override
    public String getAssessmentName() {
        return assessmentName;
    }

    @Override
    public String getAssessementSession() {
        return assessementSession;
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
    public List<String> getAssessmentByTaskNames() {
        return assessmentByTaskNames;
    }

    @Override
    public List<Integer> getAssessmentByTaskValues() {
        return assessmentByTaskValues;
    }

    @Override
    public List<Integer> getAssessmentByTaskTotals() {
        return assessmentByTaskTotals;
    }

    @Override
    public List<String> getAssessmentByPartANames() {
        return assessmentByPartANames;
    }

    @Override
    public List<Integer> getAssessmentByPartAValues() {
        return assessmentByPartAValues;
    }

    @Override
    public List<Integer> getAssessmentByPartATotals() {
        return assessmentByPartATotals;
    }

    @Override
    public List<String> getAssessmentByPartBNames() {
        return assessmentByPartBNames;
    }

    @Override
    public List<Integer> getAssessmentByPartBValues() {
        return assessmentByPartBValues;
    }

    @Override
    public List<Integer> getAssessmentByPartBTotals() {
        return assessmentByPartBTotals;
    }
    
    @Override
    public List<String> getAssessmentByPartConceptsNames() {
        return assessmentByPartConceptsNames;
    }
    
    @Override
    public List<Integer> getAssessmentByPartConceptsValues() {
        return assessmentByPartConceptsValues;
    }
    
    @Override
    public List<Integer> getAssessmentByPartConceptsTotals() {
        return assessmentByPartConceptsTotals;
    }
    
    @Override
    public String getChoicePathID() {
        return choicePathID;
    }

    private void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    private void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = nullSafe(assessmentCode);
    }

    private void setAssessmentProficiencyScore(BigDecimal assessmentProficiencyScore) {
        this.assessmentProficiencyScore = nullSafe(assessmentProficiencyScore).intValue();
    }

    private void addAssessmentByTaskName(String assessmentByTaskName) {
        this.assessmentByTaskNames.add(nullSafe(assessmentByTaskName));
    }

    private void addAssessmentByTaskValue(BigDecimal assessmentByTaskValue) {
        this.assessmentByTaskValues.add(nullSafe(assessmentByTaskValue).intValue());
    }

    private void addAssessmentByTaskTotal(BigDecimal assessmentByTaskTotal) {
        this.assessmentByTaskTotals.add(nullSafe(assessmentByTaskTotal).intValue());
    }

    private void addAssessmentByPartAName(String assessmentByPartAName) {
        this.assessmentByPartANames.add(nullSafe(assessmentByPartAName));
    }

    private void addAssessmentByPartAValue(BigDecimal assessmentByPartAValue) {
        this.assessmentByPartAValues.add(nullSafe(assessmentByPartAValue).intValue());
    }

    private void addAssessmentByPartATotal(BigDecimal assessmentByPartATotal) {
        this.assessmentByPartATotals.add(nullSafe(assessmentByPartATotal).intValue());
    }
    
    private void addAssessmentByPartBName(String assessmentByPartBName) {
        this.assessmentByPartBNames.add(nullSafe(assessmentByPartBName));
    }

    private void addAssessmentByPartBValue(BigDecimal assessmentByPartBValue) {
        this.assessmentByPartBValues.add(nullSafe(assessmentByPartBValue).intValue());
    }

    private void addAssessmentByPartBTotal(BigDecimal assessmentByPartBTotal) {
        this.assessmentByPartBTotals.add(nullSafe(assessmentByPartBTotal).intValue());
    }
    
    private void addAssessmentByPartConceptsName(String assessmentByPartConceptsName) {
        this.assessmentByPartConceptsNames.add(assessmentByPartConceptsName);
    }
    
    private void addAssessmentByPartConceptsValue(BigDecimal assessmentByPartConceptsValue) {
        this.assessmentByPartConceptsValues.add(nullSafe(assessmentByPartConceptsValue).intValue());
    }
    
    private void addAssessmentByPartConceptsTotal(BigDecimal assessmentByPartConceptsTotal) {
        this.assessmentByPartConceptsTotals.add(nullSafe(assessmentByPartConceptsTotal).intValue());
    }

    private void setAssessmentSession(String assessmentSession) {
        this.assessmentSession = assessmentSession;
    }

    private void setChoicePathID(String choicePathID) {
        this.choicePathID = choicePathID;
    }
    
    private void addSubCategoryA(String code) {
        addAssessmentByPartAName(code);
        this.assessmentByPartATotals.add(null);
        this.assessmentByPartAValues.add(null);
    }
    
    private void addSubCategoryB(String code) {
        addAssessmentByPartBName(code);
        this.assessmentByPartBTotals.add(null);
        this.assessmentByPartBValues.add(null);
    }
    
    private void addSubCategoryC(String code) {
        addAssessmentByPartConceptsName(code);
        this.assessmentByPartConceptsTotals.add(null);
        this.assessmentByPartConceptsValues.add(null);
    }

}

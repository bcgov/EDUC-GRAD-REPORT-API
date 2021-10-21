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
package ca.bc.gov.educ.isd.adaptor.dao.tsw.impl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TswMailerLiteracyAssessmentFrenchEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    //STUD_NO
    private String studentNumber;

    private String assessmentCode;

    private String assessmentSession;

    private String formName;

    private BigDecimal assessmentProficiencyScore;

    private BigDecimal assessmentTotalClaimC;

    private BigDecimal studentAssessmentTotalClaimC;

    private String assessmentNameClaimC;

    private BigDecimal assessmentTotalClaimW;

    private BigDecimal studentAssessmentTotalClaimW;

    private String assessmentNameClaimW;

    private BigDecimal assessmentTotalClaimO;

    private BigDecimal studentAssessmentTotalClaimO;

    private String assessmentNameClaimO;

    private BigDecimal assessmentSelectedResponseTotalTaskA;

    private BigDecimal assessmentSelectedResponseValueTaskA;

    private String assessmentSelectedResponseNameTaskA;

    private BigDecimal assessmentSelectedResponseTotalTaskEI;

    private BigDecimal assessmentSelectedResponseValueTaskEI;

    private String assessmentSelectedResponseNameTaskEI;

    private BigDecimal assessmentExtendedResponseTotalTaskEI;

    private BigDecimal assessmentExtendedResponseValueTaskEI;

    private String assessmentExtendedResponseNameTaskEI;

    private BigDecimal assessmentExtendedResponseTotalConceptsGO;

    private BigDecimal assessmentExtendedResponseValueConceptsGO;

    private String assessmentExtendedResponseNameConceptsGO;

    private BigDecimal assessmentExtendedResponseTotalConceptsWRA;

    private BigDecimal assessmentExtendedResponseValueConceptsWRA;

    private String assessmentExtendedResponseNameConceptsWRA;

    private BigDecimal assessmentExtendedResponseTotalConceptsWRS;

    private BigDecimal assessmentExtendedResponseValueConceptsWRS;

    private String assessmentExtendedResponseNameConceptsWRS;

    private BigDecimal assessmentTotalConcepts1;

    private BigDecimal assessmentValueConcepts1;

    private String assessmentNameConcepts1;

    private BigDecimal assessmentTotalConcepts2;

    private BigDecimal assessmentValueConcepts2;

    private String assessmentNameConcepts2;

    private BigDecimal assessmentTotalConcepts3;

    private BigDecimal assessmentValueConcepts3;

    private String assessmentNameConcepts3;

    private BigDecimal updateDate;

    private String choicePathID;

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getAssessmentCode() {
        return assessmentCode;
    }

    public String getAssessmentSession() {
        return assessmentSession;
    }

    public String getFormName() {
        return formName;
    }

    public BigDecimal getAssessmentProficiencyScore() {
        return assessmentProficiencyScore;

    }

    public BigDecimal getAssessmentTotalClaimC() {
        return assessmentTotalClaimC;
    }

    public BigDecimal getStudentAssessmentTotalClaimC() {
        return studentAssessmentTotalClaimC;
    }

    public String getAssessmentNameClaimC() {
        return assessmentNameClaimC;
    }
    
    public BigDecimal getAssessmentTotalClaimW() {
        return assessmentTotalClaimW;
    }

    public BigDecimal getStudentAssessmentTotalClaimW() {
        return studentAssessmentTotalClaimW;
    }

    public String getAssessmentNameClaimW() {
        return assessmentNameClaimW;
    }
    
    public BigDecimal getAssessmentTotalClaimO() {
        return assessmentTotalClaimO;
    }

    public BigDecimal getStudentAssessmentTotalClaimO() {
        return studentAssessmentTotalClaimO;
    }

    public String getAssessmentNameClaimO() {
        return assessmentNameClaimO;
    }
    
    public BigDecimal getAssessmentSelectedResponseTotalTaskA() {
        return assessmentSelectedResponseTotalTaskA;
    }

    public BigDecimal getAssessmentSelectedResponseValueTaskA() {
        return assessmentSelectedResponseValueTaskA;
    }

    public String getAssessmentSelectedResponseNameTaskA() {
        return assessmentSelectedResponseNameTaskA;
    }
    
    public BigDecimal getAssessmentSelectedResponseTotalTaskEI() {
        return assessmentSelectedResponseTotalTaskEI;
    }

    public BigDecimal getAssessmentSelectedResponseValueTaskEI() {
        return assessmentSelectedResponseValueTaskEI;
    }

    public String getAssessmentSelectedResponseNameTaskEI() {
        return assessmentSelectedResponseNameTaskEI;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalTaskEI() {
        return assessmentExtendedResponseTotalTaskEI;
    }

    public BigDecimal getAssessmentExtendedResponseValueTaskEI() {
        return assessmentExtendedResponseValueTaskEI;
    }

    public String getAssessmentExtendedResponseNameTaskEI() {
        return assessmentExtendedResponseNameTaskEI;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalConceptsGO() {
        return assessmentExtendedResponseTotalConceptsGO;
    }

    public BigDecimal getAssessmentExtendedResponseValueConceptsGO() {
        return assessmentExtendedResponseValueConceptsGO;
    }

    public String getAssessmentExtendedResponseNameConceptsGO() {
        return assessmentExtendedResponseNameConceptsGO;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalConceptsWRA() {
        return assessmentExtendedResponseTotalConceptsWRA;
    }

    public BigDecimal getAssessmentExtendedResponseValueConceptsWRA() {
        return assessmentExtendedResponseValueConceptsWRA;
    }

    public String getAssessmentExtendedResponseNameConceptsWRA() {
        return assessmentExtendedResponseNameConceptsWRA;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalConceptsWRS() {
        return assessmentExtendedResponseTotalConceptsWRS;
    }

    public BigDecimal getAssessmentExtendedResponseValueConceptsWRS() {
        return assessmentExtendedResponseValueConceptsWRS;
    }

    public String getAssessmentExtendedResponseNameConceptsWRS() {
        return assessmentExtendedResponseNameConceptsWRS;
    }
    
    public BigDecimal getAssessmentTotalConcepts1() {
        return assessmentTotalConcepts1;
    }
    
    public BigDecimal getAssessmentValueConcepts1() {
        return assessmentValueConcepts1;
    }
    
    public String getAssessmentNameConcepts1() {
        return assessmentNameConcepts1;
    }
    
    public BigDecimal getAssessmentTotalConcepts2() {
        return assessmentTotalConcepts2;
    }
    
    public BigDecimal getAssessmentValueConcepts2() {
        return assessmentValueConcepts2;
    }
    
    public String getAssessmentNameConcepts2() {
        return assessmentNameConcepts2;
    }
    
    public BigDecimal getAssessmentTotalConcepts3() {
        return assessmentTotalConcepts3;
    }
    
    public BigDecimal getAssessmentValueConcepts3() {
        return assessmentValueConcepts3;
    }
    
    public String getAssessmentNameConcepts3() {
        return assessmentNameConcepts3;
    }
    
    public BigDecimal getUpdateDate() {
        return updateDate;
    }
    
    public String getChoicePathID() {
        return choicePathID;
    }
}

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
public class TswMailerLiteracyAssessmentEntity implements Serializable {

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
    
    public BigDecimal getUpdateDate() {
        return updateDate;
    }
    
    public String getChoicePathID() {
        return choicePathID;
    }
}

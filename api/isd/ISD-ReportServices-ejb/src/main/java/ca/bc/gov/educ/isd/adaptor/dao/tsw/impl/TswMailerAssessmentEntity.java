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
public class TswMailerAssessmentEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    //STUD_NO
    private String studentNumber;

    private String assessmentCode;

    private String assessmentSession;

    private String formName;

    private BigDecimal assessmentProficiencyScore;

    private BigDecimal assessmentTotalSelectedResponse;

    private BigDecimal studentAssessmentTotalSeletedResponse;

    private BigDecimal assessmentTotalExtendedResponse;

    private BigDecimal studentAssessmentTotalExtendedResponse;

    // TODO: move to separate class with @Embeddable/@Embedded
    private String assessmentSelectedResponseName1;

    private String assessmentSelectedResponseName2;

    private String assessmentSelectedResponseName3;

    private String assessmentSelectedResponseName4;

    private BigDecimal assessmentSelectedResponseValue1;

    private BigDecimal assessmentSelectedResponseValue2;

    private BigDecimal assessmentSelectedResponseValue3;

    private BigDecimal assessmentSelectedResponseValue4;

    private BigDecimal assessmentSelectedResponseTotal1;

    private BigDecimal assessmentSelectedResponseTotal2;

    private BigDecimal assessmentSelectedResponseTotal3;

    private BigDecimal assessmentSelectedResponseTotal4;

    private String assessmentExtendedResponseName1;

    private String assessmentExtendedResponseName2;

    private BigDecimal assessmentExtendedResponseValue1;

    private BigDecimal assessmentExtendedResponseValue2;

    private BigDecimal assessmentExtendedResponseTotal1;

    private BigDecimal assessmentExtendedResponseTotal2;

    private BigDecimal updateDate;

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

    public BigDecimal getAssessmentTotalSelectedResponse() {
        return assessmentTotalSelectedResponse;
    }

    public BigDecimal getStudentAssessmentTotalSeletedResponse() {
        return studentAssessmentTotalSeletedResponse;
    }

    public BigDecimal getAssessmentTotalExtendedResponse() {
        return assessmentTotalExtendedResponse;
    }

    public BigDecimal getStudentAssessmentTotalExtendedResponse() {
        return studentAssessmentTotalExtendedResponse;
    }

    public BigDecimal getUpdateDate() {
        return updateDate;
    }

    public BigDecimal getAssessmentSelectedResponseValue1() {
        return assessmentSelectedResponseValue1;
    }

    public BigDecimal getAssessmentSelectedResponseValue2() {
        return assessmentSelectedResponseValue2;
    }

    public BigDecimal getAssessmentSelectedResponseValue3() {
        return assessmentSelectedResponseValue3;
    }

    public BigDecimal getAssessmentSelectedResponseValue4() {
        return assessmentSelectedResponseValue4;
    }

    public BigDecimal getAssessmentSelectedResponseTotal1() {
        return assessmentSelectedResponseTotal1;
    }

    public BigDecimal getAssessmentSelectedResponseTotal2() {
        return assessmentSelectedResponseTotal2;
    }

    public BigDecimal getAssessmentSelectedResponseTotal3() {
        return assessmentSelectedResponseTotal3;
    }

    public BigDecimal getAssessmentSelectedResponseTotal4() {
        return assessmentSelectedResponseTotal4;
    }

    public String getAssessmentSelectedResponseName1() {
        return assessmentSelectedResponseName1;
    }

    public String getAssessmentSelectedResponseName2() {
        return assessmentSelectedResponseName2;
    }

    public String getAssessmentSelectedResponseName3() {
        return assessmentSelectedResponseName3;
    }

    public String getAssessmentSelectedResponseName4() {
        return assessmentSelectedResponseName4;
    }

    public String getAssessmentExtendedResponseName1() {
        return assessmentExtendedResponseName1;
    }

    public String getAssessmentExtendedResponseName2() {
        return assessmentExtendedResponseName2;
    }

    public BigDecimal getAssessmentExtendedResponseValue1() {
        return assessmentExtendedResponseValue1;
    }

    public BigDecimal getAssessmentExtendedResponseValue2() {
        return assessmentExtendedResponseValue2;
    }

    public BigDecimal getAssessmentExtendedResponseTotal1() {
        return assessmentExtendedResponseTotal1;
    }

    public BigDecimal getAssessmentExtendedResponseTotal2() {
        return assessmentExtendedResponseTotal2;
    }
}

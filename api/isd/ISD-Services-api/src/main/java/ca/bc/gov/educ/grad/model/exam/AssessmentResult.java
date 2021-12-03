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
package ca.bc.gov.educ.grad.model.exam;

import ca.bc.gov.educ.grad.model.common.DomainEntity;

import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AssessmentResult extends DomainEntity {

    public String getStudentNumber(); 

    public String getAssessmentCode();

    public int getAssessmentProficiencyScore();

    public String getAssessmentSession();

    public String getAssessmentName();

    public void setAssessmentName(String assessmentName);

    public String getRequirementMet();

    public void setRequirementMet(String requirementMet);

    public String getSpecialCase();

    public void setSpecialCase(String specialCase);

    public String getExceededWrites();

    public void setExceededWrites(String exceededWrites);
    
    public Date getUpdateDate();

    public int getAssessmentTotalSelectedResponse();

    public int getStudentAssessmentTotalSelectedResponse();

    public int getAssessmentTotalExtendedResponse();

    public int getStudentAssessmentTotalExtendedResponse();

    public String getAssessmentSelectedResponseName(int index);

    public int getAssessmentSelectedResponseValue(int index);

    public Iterator<String> getAssessmentSelectedResponseNamesIterator();

    public Iterator<Integer> getAssessmentSelectedResponseValuesIterator();

    public String getAssessmentExtendedResponseName(int index);

    public int getAssessmentExtendedResponseValue(int index);

    public Iterator<String> getAssessmentExtendedResponseNamesIterator();

    public Iterator<Integer> getAssessmentExtendedResponseValuesIterator();
}

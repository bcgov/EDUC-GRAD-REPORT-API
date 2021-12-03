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
package ca.bc.gov.educ.grad.model.assessment;

import java.util.List;

/**
 * Container class for information pertaining to a student's assessment for a
 * particular session.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NumAssessmentResult extends AssessmentResult {

    public int getAssessmentTotalSelectedResponse();

    public int getStudentAssessmentTotalSelectedResponse();

    public int getAssessmentTotalExtendedResponse();

    public int getStudentAssessmentTotalExtendedResponse();

    public List<String> getAssessmentSelectedResponseNames();

    public List<Integer> getAssessmentSelectedResponseValues();

    public List<Integer> getAssessmentSelectedResponseTotals();

    public List<String> getAssessmentExtendedResponseNames();

    public List<Integer> getAssessmentExtendedResponseValues();

    public List<Integer> getAssessmentExtendedResponseTotals();
}

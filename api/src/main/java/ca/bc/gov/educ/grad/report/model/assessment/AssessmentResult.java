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
package ca.bc.gov.educ.grad.report.model.assessment;

/**
 * Container class for information pertaining to a student's assessment for a
 * particular session.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AssessmentResult {

    public String getStudentNumber();

    public String getAssessmentCode();

    public String getProficiencyScore();

    public String getSessionDate();

    public String getAssessmentName();

    public String getGradReqMet();

    public String getSpecialCase();

    public String getExceededWriteFlag();

    public Boolean getProjected();

}

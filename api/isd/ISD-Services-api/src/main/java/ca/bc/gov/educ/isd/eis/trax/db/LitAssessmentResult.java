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
package ca.bc.gov.educ.isd.eis.trax.db;

import java.util.List;

/**
 * Container class for information pertaining to a student's assessment for a
 * particular session.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface LitAssessmentResult extends AssessmentResult {

    public List<String> getAssessmentByTaskNames();
    
    public List<Integer> getAssessmentByTaskValues();
    
    public List<Integer> getAssessmentByTaskTotals();

    public List<String> getAssessmentByPartANames();
    
    public List<Integer> getAssessmentByPartAValues();
    
    public List<Integer> getAssessmentByPartATotals();

    public List<String> getAssessmentByPartBNames();
    
    public List<Integer> getAssessmentByPartBValues();
    
    public List<Integer> getAssessmentByPartBTotals();
    
    public List<String> getAssessmentByPartConceptsNames();
    
    public List<Integer> getAssessmentByPartConceptsValues();
    
    public List<Integer> getAssessmentByPartConceptsTotals();
    
    public String getChoicePathID();
}

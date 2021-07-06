/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: ProvincialExaminationReport.java 4514 2016-10-2#$
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016)  $
 *  Revision Number:     $Rev:: 4514                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.exam.Exam;
 
/**
 * Represents information required to generate a student scholarship report.
 * 
 * @author CGI Information Management Consultants Inc.
 */
public interface ProvincialExaminationReport extends StudentReport {

    /**
     * Sets the student's examination results based on the given container for
     * examination results..
     *
     * @param examination The examination object that contains a list of
     * examinations.
     */
    void setExamination(Exam examination);
}

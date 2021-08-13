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
 *  File:                $Id:: StudentUpdateProcess.java 7180 2017-05-18 23:48#$
 *  Date of Last Commit: $Date:: 2017-05-18 16:48:08 -0700 (Thu, 18 May 2017)  $
 *  Revision Number:     $Rev:: 7180                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.process.admin;

import ca.bc.gov.educ.isd.common.BusinessProcess;
import java.util.Map;

/**
 * Business Processes for updating a students.
 *
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:17 PM
 */
public interface StudentUpdateProcess extends BusinessProcess {

    /**
     * Update student demographic information.
     *
     * @param demoMap place holder parameter / TBD
     */
    void postStudentDemo(Map<String, String> demoMap);

    /**
     * Post a new or updated course mark for a student.
     *
     * @param pen The student's PEN.
     * @param cin The course instance number
     * @param mark The students score in the course
     */
    void postCourseMark(String pen, String cin, Double mark);

    /**
     * Post an exam mark.
     *
     * @param pen The Student's PEN
     * @param ein The exam instance number
     * @param mark The student's score on the exam
     */
    void postExamMark(String pen, String ein, Double mark);
}

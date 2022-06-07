/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: TranscriptReport.java 6148 2017-02-10 22:38:41Z#$
 *  Date of Last Commit: $Date:: 2017-02-10 14:38:41 -0800 (Fri, 10 Feb 2017)  $
 *  Revision Number:     $Rev:: 6148                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.reports;

import ca.bc.gov.educ.grad.report.model.student.Student;

import java.util.List;

/**
 * Represents information required to generate a student transcript report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DistributionReport extends SchoolReport {

    /**
     * Sets the student to use for populating the reports.
     *
     * @param students The list of student instance with data used for filling reports.
     */
    void setStudents(List<Student> students);
}

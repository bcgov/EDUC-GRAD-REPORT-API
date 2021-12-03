/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: StudentReport.java 10969 2018-07-25 16:13:39Z D#$
 *  Date of Last Commit: $Date:: 2018-07-25 09:13:39 -0700 (Wed, 25 Jul 2018)  $
 *  Revision Number:     $Rev:: 10969                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports;

import ca.bc.gov.educ.grad.model.school.School;
import ca.bc.gov.educ.grad.model.student.Student;

import java.util.Date;

/**
 * Superclass for various reports (transcript, provincial examination,
 * scholarships, certificates, etc.) that involve student information.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentReport extends Report {

    /**
     * Sets the student to use for populating the reports.
     *
     * @param student The student instance with data used for filling reports.
     */
    void setStudent(Student student);

    /**
     * Sets the school to use for populating the reports. The default logo code
     * will be BC.
     *
     * @param school The school instance with data used for filling reports.
     */
    void setSchool(School school);

    /**
     * Sets the school to use for populating the reports.
     *
     * @param school The school instance with data used for filling reports.
     * @param logoCode The logo code to use for the district organization
     * responsible for the school's operations.
     */
    void setSchool(School school, String logoCode);

    /**
     * Sets the last updated or issue date for reports. If null, or not set, the
     * date and corresponding label will not be shown on the report.
     *
     * @param date The date the information on the report was last updated, or
     * the date the report was issued.
     */
    void setReportDate(Date date);
}

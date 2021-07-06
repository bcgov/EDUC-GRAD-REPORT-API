/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: StudentExamService.java 12336 2019-12-13 19:30:#$
 *  Date of Last Commit: $Date:: 2019-12-13 11:30:20 -0800 (Fri, 13 Dec 2019)  $
 *  Revision Number:     $Rev:: 12336                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.exam;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.reports.ReportFormat;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentExamService extends BusinessService {

    /**
     * Generate the student exam results report. Reports can be generated for
     * different media types such as HTML or PDF. If the current user does not
     * have an entry in the Student XRef or if the current user is not a student
     * (no PEN) then a DomainServiceExcepton is thrown. If the current user is a
     * student but has no exam results in TRAX then a DomainServiceException is
     * thrown. If there is an error generating the report then a
     * DomainServiceException is thrown.
     *
     * @param format
     *
     * @return Report data for consumption by the GUI.
     * @throws DomainServiceException
     */
    StudentExamResultsReport buildReport(ReportFormat format)
            throws DomainServiceException;

    /**
     * Retrieves the exam for the currently logged in student.
     *
     * @return An exam with information about the student's examination results.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Exam getExam() throws DomainServiceException;

    /**
     * Retrieves the exam for the student with the passed PEN.
     *
     * @param pen Student identifier.
     * @return An exam with information about the student's examination results.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Exam getExam(String pen) throws DomainServiceException;

    /**
     * Check to see if a Student has a numeracy assessments.
     *
     * @return true if a student has any assessments
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Boolean hasNumeracyAssessments() throws DomainServiceException;
    
    /**
     * Check to see if a Student has a literacy assessments.
     *
     * @return true if a student has any assessments
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Boolean hasLiteracyAssessments() throws DomainServiceException;

}

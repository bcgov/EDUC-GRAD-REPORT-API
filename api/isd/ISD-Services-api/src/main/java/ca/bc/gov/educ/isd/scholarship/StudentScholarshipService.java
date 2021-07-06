/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: StudentScholarshipService.java 5681 2016-12-16 #$
 *  Date of Last Commit: $Date:: 2016-12-16 12:02:43 -0800 (Fri, 16 Dec 2016)  $
 *  Revision Number:     $Rev:: 5681                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.scholarship;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.reports.ReportFormat;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentScholarshipService extends BusinessService {

    /**
     * Generate the student scholarships report. Reports can be generated for
     * different MIME types such as HTML or PDF. If the current user does not
     * have an entry in the Student XRef or if the current user is not a student
     * (no PEN) then a DomainServiceExcepton is thrown. If the current user is a
     * student but has no scholarship TRAX then a DomainServiceException is
     * thrown. If there is an error generating the report then a
     * DomainServiceException is thrown.
     *
     * @param format
     *
     * @return Report data for consumption by the GUI.
     *
     * @throws DomainServiceException
     */
    StudentScholarshipReport buildReport(ReportFormat format) throws DomainServiceException;

    /**
     * Retrieves a list of all scholarships associated with the currently logged
     * in student.
     *
     * @return A list of scholarship instances or an empty list if none found.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    List<Scholarship> getScholarships() throws DomainServiceException;

    /**
     * Retrieves a list of all scholarships associated with the passed PEN.
     *
     * @param pen The student PEN for finding a student's scholarships.
     * @return A list of scholarship instances or an empty list if none found.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    List<Scholarship> getScholarships(String pen) throws DomainServiceException;
}

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
 *  File:                $Id:: StudentAdminService.java 8289 2017-09-26 23:04:#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * Used to find student demographic information based on information provided by
 * a StudentAdmin interface. This is primarily used by the unit tests to find
 * students with attributes matching particular criteria without having to rely
 * on PEN values.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentAdminService extends CommonEntityService<StudentAdmin, SearchResult> {

    /**
     * Finds a student that matches the information provided by the admin
     * interface. This will return the first student record that matches.
     *
     * @param student Partial student
     * @return As student record with all demographic data filled in, never
     * null.
     *
     * @throws DataException If the database cannot be accessed or a student
     * matching the information provided by the given parameter cannot be found.
     */
    public StudentDetails find(StudentAdmin student)
            throws DomainServiceException, DataException;

}

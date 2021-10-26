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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.service;

import ca.bc.gov.educ.isd.eis.common.DomainServiceException;
import ca.bc.gov.educ.isd.eis.trax.db.StudentDemographic;
import ca.bc.gov.educ.isd.eis.trax.db.StudentProfileMasterLite;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;

import java.util.List;

/**
 * An interface to the student data access object providing create functionality
 * for <code>StudentDemographic</code>. The data is source is TRAX, it is
 * searched using the provided PEN.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentData extends TRAXData {

    /**
     * Create a <code>StudentDemographic</code> object containing student
     * information for the given PEN. Create a list of objects containing the
     * student information.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul>
     * <li><code>transcriptEligible</code></li>
     * <li><code>studentType</code></li>
     * <li><code>schoolCategroy</code></li>
     * </ul>
     *
     * @param PEN
     * @return List of 1 StudentDemographic object, or NULL if student data was
     * not found.
     */
    List<? extends StudentDemographic> findDemogByPEN(String PEN);

    /**
     * Search for students by their partial PEN and last name, only return the
     * students meet all given criteria.
     *
     * "Partial" means partial match. Ex: a PEN input of "357" can match both
     * "13579" and "3570"
     *
     * Search a token against PEN if it matches "^\\d+$". Search a token against
     * Last name if it matches "^\\w+$".
     *
     * @param tokens List of pen or last name.
     * @return list of <code>StudentProfileMasterLite</code> who match all given
     * PENs and last names.
     * @throws ca.bc.gov.educ.isd.eis.common.DomainServiceException if a given
     * token doesn't match "^\\d+$" or "^\\w+$"
     */
    List<StudentProfileMasterLite> searchStudentPartialMatchAll(List<String> tokens)
            throws DomainServiceException;

}

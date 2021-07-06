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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.student.StudentDetails;
import ca.bc.gov.educ.isd.student.StudentXRef;

/**
 * Helper services to load student details info from the entity.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentXRefEntityHelper {

    StudentXRef read() throws DomainServiceException;

    StudentDetails searchDetailedInfoForExisting(String pen)
            throws DomainServiceException, DataException;
}

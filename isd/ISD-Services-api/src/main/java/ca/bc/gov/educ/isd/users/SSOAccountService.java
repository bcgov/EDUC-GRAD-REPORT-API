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
package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.GuidUserType;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * Definition of a service that facilitates management of roles.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SSOAccountService extends CommonEntityService<SSOAccount, SearchResult> {

    UserProfile exists(GuidUserType guidUserType) throws DataException;

    SSOAccount find(GuidUserType guidUserType) throws DataException;
}

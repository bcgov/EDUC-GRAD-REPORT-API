/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CodeAdminEntityService.java 10209 2018-05-18 16#$
 *  Date of Last Commit: $Date:: 2018-05-18 09:53:27 -0700 (Fri, 18 May 2018)  $
 *  Revision Number:     $Rev:: 10209                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.codes;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;

/**
 * Provides CRUD operations for code admin entities.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CodeAdminEntityService extends CommonEntityService<CodeAdmin, CodeSearchResult> {

    /**
     * Creates a new code based on a code set and code entity.
     *
     * @param codeSet Typically the system settings code set.
     * @param codeEntity The code entity attributes conferred upon the code
     * returned.
     * @return A new Code instance, never null, populated with fields from the
     * code entity enum.
     * @throws DomainServiceException Could not create the Code.
     */
    CodeAdmin create(CodeSet codeSet, CodeEntityEnum codeEntity) throws DomainServiceException;

}

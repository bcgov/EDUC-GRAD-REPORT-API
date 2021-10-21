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
 *  File:                $Id:: UserProfileAdminService.java 9480 2018-02-19 23#$
 *  Date of Last Commit: $Date:: 2018-02-19 15:50:27 -0800 (Mon, 19 Feb 2018)  $
 *  Revision Number:     $Rev:: 9480                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.party.Identifier;

import java.util.List;
import java.util.Map;

/**
 * <h1>Implementation Notes</h1>
 * Implementors of this service are should follow these guidelines in order to
 * ensure all of the systems requirements and constraints are fulfilled.
 *
 * <h1>Security</h1>
 * The User Profile Administration Service <b>must</b> only be called by users
 * who are allowed to change and alter the profiles of other users. It is
 * important that user's that can modify their own profile be restricted to the
 * basic User Profile Service.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface UserProfileAdminService extends CommonEntityService<UserProfile, UserProfileSearchResult> {

    Identifier createIdentifier() throws DataException;

    SSOAccount createSSOAccount() throws DataException;

    /**
     * Paged search for an entity based on a set of specific search criteria.
     *
     * This version of the search function is specifically tied to business
     * requirements and is different from the search function defined in
     * CommonEntityService. This version supports wildcard searches by using the
     * LIKE mechanism. It also supports searching by PEN and "Former Name",
     * which is not part of the User Profile proper.
     *
     * The paging is accomplished through a page number and page size parameters
     * as follows
     *
     * @see #search(java.util.Map)
     * @param searchCriteria Bean consisting of search criteria fields
     * @param pageSize The number of records on each page.
     * @param pageNum The page number to retrieve (determined by the page size)
     * @return
     * @throws DataException
     */
    List<UserProfileSearchResult> searchUsingCriteriaBean(
            UserProfileSearchCriteria searchCriteria,
            Integer pageSize,
            Integer pageNum)
            throws DataException;

    /**
     * Count of entities based on a UserProfileSearchCriteria object.
     *
     * @see #searchCriteria(java.util.Map)
     * @param searchCriteria Bean consisting of search criteria fields
     * @return
     * @throws DataException
     */
    Long countUsingCriteriaBean(UserProfileSearchCriteria searchCriteria)
            throws DataException;

    /**
     * Called by the admin application when a student's record in TRAX is
     * merged. The profile service is called which grabs the demographic data
     * from StudentDemographicService and updates the profile with the active
     * TRAX record.
     *
     * @param studentData
     * @return true if the update was successful.
     */
    Boolean updateMergedStudentRecord(Map<String, Object> studentData);

    /**
     * Retrieves a list of all user profiles in the system that have
     * administrative rights.
     *
     * @return A non-null, but possibly empty, list.
     * @throws DomainServiceException Service or database unavailable.
     */
    List<UserProfile> getAdminProfiles() throws DomainServiceException;
}

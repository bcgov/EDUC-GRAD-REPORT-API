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
package ca.bc.gov.educ.isd.process.admin;

import ca.bc.gov.educ.isd.common.DomainServiceException;

/**
 * Registration of User profiles and access to services (roles).
 *
 * User registration services provide the forms and work flows needed to
 * register various classes of users in order to create their profile and
 * activate services.
 *
 * <h3>Notes:</h3>
 * Registration forms for user services can be dynamically rendered by the user
 * interface. But because the skill to create dynamically rendered forms are
 * currently uncommon some convenience methods have been added.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AdminTaskProcess {

    /**
     * This method is used by the STS Admin application to handle the merging of
     * TRAX records. It is called from the Manual Intervention Queue on tasks
     * that are created when a user logs in an is flagged for the appropriate
     * status change (M in TRAX).
     *
     * @param pen the Personal Education Number of the merged record.
     * @return true if the merge was successful.
     * @throws DomainServiceException
     */
    Boolean updateMergedStudentRecord(String pen) throws DomainServiceException;

    /**
     * This method is used by the STS Admin application to handle incomplete
     * registration attempts by students. It is called from the Manual
     * Intervention Queue on tasks that are created when a user fails their
     * registration.
     *
     * @param profileEid the profile entityId of the entity to be removed.
     * @return true if the removal was successful.
     * @throws DomainServiceException
     */
    Boolean clearRegistrationAttempt(String profileEid) throws DomainServiceException;
}

/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: UserProfileService.java 11200 2018-09-28 18:26:#$
 *  Date of Last Commit: $Date:: 2018-09-28 11:26:10 -0700 (Fri, 28 Sep 2018)  $
 *  Revision Number:     $Rev:: 11200                                          $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.GuidUserType;
import ca.bc.gov.educ.isd.reports.admin.data.StudentProfile;
import java.util.Date;
import java.util.List;

/**
 * User profile and account self-service.
 *
 * Provides self service capabilities for users to manage their own profiles and
 * on-line service registrations.
 *
 * <h3>Implementation Notes</h3>
 * <p>
 * Implementors of this service are should follow these guidelines in order to
 * ensure all of the systems requirements and constraints are fulfilled.
 * </p>
 * <h4>Security</h4>
 * <p>
 * The User Profile Service <b>MUST</b> only be used to modify a user's own
 * profile. The methods of this service inherit from the Common Entity Service
 * they must throw a Security Exception if they are used for any User Profile
 * other than the currently authenticated in user.</p>
 * <p>
 * The details of the currently authenticated user's identity must be taken from
 * the Security Principal provided by the EJB Context</p>
 * <p>
 * If a user has the privileges to modify the profile of another user, they must
 * used the User Profile Administration Service.</p>
 *
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:18 PM
 */
public interface UserProfileService extends CommonEntityService<UserProfile, UserProfileSearchResult> {

    //TODO - refactor / pull-up the exists method to common entity services and add id parameter
    /**
     * Check if a user account exists.
     *
     * This method performs a search for the currently authenticated user based
     * the principal object retrieved from the Session Context. This query will
     * search match properties of the custom principal object to the SSO account
     * records in the underlying data store.
     *
     * @return TRUE if the user profile exists.
     * @throws DataException If there is an error accessing the data store.
     */
    UserProfile exists() throws DomainServiceException;

    /**
     * Create an initial user account for the currently authenticated user.
     *
     * Use this method for the self-service initialization of a users profile /
     * account.
     *
     * For an authenticated user which does not have an existing user profile:
     * <ol>
     * <li>The user profile is populated with values from the user principal
     * object (EJB Session Context).
     *
     * <li>The new user profile is granted the "un-registered user" role group.
     * This role group allows them to access only the features needed for the
     * Online Service Registration.
     *
     * <li>The new user profile and role group grant is saved to the underlying
     * data store.
     * </ol>
     *
     * If the currently authenticated user has an existing user profile, and
     * exception is thrown.
     *
     * @return
     * @throws DomainServiceException If the user profile already exists, or
     * there is any error communicating with the underlying data store.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    UserProfile initialize() throws DomainServiceException;

    /**
     * Reads the profile of the currently authenticated user.
     *
     * This uses caching if enabled.
     *
     * If the user is a new user, the account is not yet in existence, an
     * exception will be thrown. This is because a user may be authenticated in
     * the SSO federation, but not yet be known to the application.
     *
     * @return The user account for the currently authenticated user.
     * @throws DomainServiceException If the user account cannot be found, there
     * is an error accessing the data store, or the session has expired.
     */
    UserProfile read() throws DomainServiceException;

    /**
     * Reads the profile of the currently authenticated user, skipping cache,
     * even if enabled.
     *
     * If the user is a new user, the account is not yet in existence, an
     * exception will be thrown. This is because a user may be authenticated in
     * the SSO federation, but not yet be known to the application.
     *
     * @return The user account for the currently authenticated user.
     * @throws DomainServiceException If the user account cannot be found, there
     * is an error accessing the data store, or the session has expired.
     */
    UserProfile readNoCache() throws DomainServiceException;

    /**
     * Writes the UserProfile, explicitly including the GuidUserType for
     * caching.
     *
     * @param guidUserType
     * @param entity
     * @return
     * @throws DomainServiceException
     */
    public UserProfile write(final GuidUserType guidUserType, final UserProfile entity) throws DomainServiceException;

    //TODO - go over the alternate flows with the BA
    /**
     * Register for an on-line service.
     *
     * <p>
     * Adds an on-line service to the users profile. An activation code is
     * attached service record and returned. This supports the work flow in
     * sending a verification email to the user.
     *
     * <p>
     * Activation Codes are unique to the registration instance.</p>
     *
     * <p>
     * If the user is already registered for the type of service and they
     * <b>have not yet activated it</b>, the previous registration form version
     * will not be used. The newly submitted registration form will use a new
     * activation code.</p>
     *
     * <p>
     * If the user is already registered for the type of service and they
     * <b>have activated it</b>, the previous service is disabled, the new
     * version of the registration form will be used. A new activation code will
     * be returned.
     * </p>
     *
     * @param servReg A service registration
     * @param userProfile
     * @param isRebind If the registration is a rebind the profile data will not
     * be overwritten.
     * @param activationCode The code used to complete registration.
     * @return if the registration was successful.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    Boolean registerService(OnlineServiceReg servReg, UserProfile userProfile, String activationCode) throws DomainServiceException;

    /**
     * Activate a service by providing the registration code.
     *
     * Enables access to an online service for the currently authenticated user.
     * As part of a self-service registration process.
     *
     * The user will be granted access to the role group associated with the
     * on-line service, if the activation code matches the service registration
     * and has not expired, then the service is activated. The user is added to
     * the RoleGroup associated with this service type.
     *
     *
     * @param fetchedReg
     * @param userProfile
     * @return TRUE if this code activated a service.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    Boolean activateService(UserProfile userProfile) throws DomainServiceException;

    /**
     * Find OnlineServiceReg linked with provided activation code.
     *
     * @param profile
     * @param activationCode
     * @return
     * @throws DataException
     */
    OnlineServiceReg getOnlineServiceReg(UserProfile profile, String activationCode) throws DomainServiceException;

    /**
     * Activate a Email update by providing the registration code.
     *
     * @param contactReg
     * @return TRUE if this code activated a service.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    Boolean activateEmailUpdate(UpdateContactReg contactReg) throws DomainServiceException;

    /**
     * Creates a new registration form for a student to use in the self
     * registration process.
     *
     * @return an empty registration form.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    OnlineServiceReg createSTSRegistration() throws DomainServiceException;

    /**
     * Creates a new update contact form for a user to confirm a new email.
     *
     * @return an empty registration form.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    OnlineServiceReg createUpdateContactRegistration() throws DomainServiceException;

    /**
     * Method to lockout a user profile after registration conditions are
     * violated.
     *
     * @throws DataException
     * @throws DomainServiceException
     */
    void lockoutUserProfile() throws DomainServiceException;

    /**
     * Used to get the date of the sent email notification for the current
     * OnlineServiceReg to display.
     *
     * @param profile
     * @return The date that the notification was sent.
     */
    OnlineServiceReg getCurrentRegistration(UserProfile profile);

    /**
     * Used to set a new email which requires activation prior to being applied
     * to the user profile.
     *
     * @param emailRegistration to set to the user profile.
     * @return activationCode to be emailed to user.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    String registerEmail(OnlineServiceReg emailRegistration) throws DomainServiceException;

    /**
     * Searches for a user profile given its user entity ID.
     *
     * @param userEntityId The unique identifier for the profile.
     * @return A UserProfile that singularly matches the given ID.
     * @throws DomainServiceException Service unavailable, multiple matches, or
     * zero matches found.
     */
    UserProfile search(String userEntityId) throws DomainServiceException;

    /**
     * Provides a list of results for the Student Profile report. This will
     * return all non-IDIR accounts, ordered by registration date.
     *
     * @param dateBegan Start date to query student profiles.
     * @param dateEnded End date to query student profiles.
     * @return A list of objects suitable for display on the student profile
     * report.
     * @throws DomainServiceException Could not obtain the results.
     */
    List<StudentProfile> search(Date dateBegan, Date dateEnded)
            throws DomainServiceException;

    /**
     * Method which records the last login field and saves the entity for an
     * authenticated user with an existing user profile. The updated user
     * profile is saved to the underlying data store.
     *
     * @param lastLoginDate Date last login date to set to the user profile.
     * @throws DataException If the user account cannot be found or there is an
     * error accessing the data store.
     */
    void recordLoginEvent(Date lastLoginDate) throws DomainServiceException;
}

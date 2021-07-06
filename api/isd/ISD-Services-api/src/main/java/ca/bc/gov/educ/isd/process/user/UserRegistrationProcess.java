/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: UserRegistrationProcess.java 11200 2018-09-28 1#$
 *  Date of Last Commit: $Date:: 2018-09-28 11:26:10 -0700 (Fri, 28 Sep 2018)  $
 *  Revision Number:     $Rev:: 11200                                          $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.process.user;

import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SelfServeProcess;
import ca.bc.gov.educ.isd.task.TaskType;
import ca.bc.gov.educ.isd.users.STSProfileReg;
import ca.bc.gov.educ.isd.users.UpdateContactReg;
import ca.bc.gov.educ.isd.users.UserProfile;
import ca.bc.gov.educ.isd.users.UserProfileService;
import java.util.List;
import java.util.Map;

/**
 * Registration of User profiles and access to services (roles).
 *
 * <p>
 * User registration services provide the forms and work flows needed to
 * register various classes of users in order to create their profile and
 * activate services.
 *
 * <p>
 * <b>Notes:</b></p>
 * <p>
 * Registration forms for user services can be dynamically rendered by the user
 * interface. But because the skill to create dynamically rendered forms are
 * currently uncommon some convenience methods have been added.
 * </p>*
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface UserRegistrationProcess extends SelfServeProcess {

    /**
     * Initialize a user self-service registration process.
     *
     * If this is a registration process for a new, never before encountered
     * user, then they will not have a user profile. In this case the initial
     * user profile is created.
     * <p>
     * If the user has submitted a Registration form for an on-line service, and
     * it is pending activation, then the current status of the registration
     * process is loaded and made ready for the next step.
     *
     * @see UserProfileService#initialize()
     */
    @Override
    void initialize() throws DomainServiceException;

    /**
     *
     * @return @throws DomainServiceException
     */
    UserProfile initializeProfile() throws DomainServiceException;

    /**
     * Gets a list of keys for user services that can be registered for.
     *
     * <p>
     * Currently this only returns a STS service and does not need to be
     * used.</p>
     *
     * @return List of user Service symbolic names as Strings.
     */
    List<String> getUserServices();

    /**
     * Create a new blank registration form for the student transcript service.
     *
     * @throws DomainServiceException
     * @deprecated Temporary convenience method, should use factory and dynamic
     * forms.
     * @return An instance of a user Profile Registration Form for a Student
     */
    STSProfileReg createSTSRegistration() throws DomainServiceException;

    /**
     * Register a STS Profile Registration Form for the student transcript
     * service.
     *
     * @param reg A registration form for user services.
     * @param userProfile
     * @param isRebind flag for the user rebind credential
     * @return Unique ID for the users profile.
     * @throws DomainServiceException If the registration data is invalid or the
     * user profile cannot be created.
     */
    String registerSTS(STSProfileReg reg, UserProfile userProfile, Boolean isRebind) throws DomainServiceException;

    /**
     * Check the status of a registration form to determine eligibility based on
     * the defined business rules.
     *
     * @param reg A registration form for user services.
     * @return A boolean if the registration requirements are met.
     * @throws DomainServiceException If the registration data is invalid or the
     * user profile cannot be created.
     */
    Boolean checkSTSRegistration(STSProfileReg reg) throws DomainServiceException;

    /**
     * Activate a User Profile with an activation code which a user returns with
     * after receiving a notification.
     *
     * @param regForm
     * @param userProfile
     * @return Boolean if the activation was successful.
     * @throws DomainServiceException
     */
    Boolean activateUserProfile(STSProfileReg regForm, UserProfile userProfile) throws DomainServiceException;

    /**
     * Activate a User Profile email update for a registered user.
     *
     * @param contactReg
     * @return Boolean if the activation was successful.
     * @throws DomainServiceException
     */
    Boolean activateEmailUpdate(UpdateContactReg contactReg) throws DomainServiceException;

    /**
     * Used to create tasks based on failed registrations. The map will populate
     * the task attributes to be displayed on the manual intervention queue.
     *
     * @param inMap map of registration and an empty secondary school data
     * @param issueType
     * @throws DomainServiceException
     */
    void createRegistrationTaskAndNotification(
            Map<String, String> inMap,
            TaskType issueType) throws DomainServiceException;

    /**
     * Used to update tasks based on failed registrations. The map will populate
     * the task attributes to be displayed on the manual intervention queue.
     *
     * @param inMap map of registration and secondary school data
     * @param issueType
     * @throws DomainServiceException
     */
    void updateRegistrationTaskAndNotification(
            Map<String, String> inMap,
            TaskType issueType) throws DomainServiceException;

    /**
     * Used when a user wants to rebind their profile to a new SSOGuid. This
     * will delete the temporary profile and associate the xref and profile to
     * the new guid.
     *
     * @param regForm
     * @param userProfile
     * @return the old email from the prior registration.
     * @throws DomainServiceException
     */
    String rebindCredentials(STSProfileReg regForm, UserProfile userProfile) throws DomainServiceException;

    /**
     * Used when a user requires an activation link to be resent.
     *
     * @throws DomainServiceException
     */
    void resendActivation() throws DomainServiceException;

    /**
     * Used when a Admin requires an activation link to be resent to a user.
     *
     * @param profileEid The profile entityId of the student
     * @throws DomainServiceException
     */
    void resendActivation(String profileEid) throws DomainServiceException;

    /**
     * Used when a user requires an email update activation link to be resent.
     *
     * @throws DomainServiceException
     */
    void resendEmailUpdateActivation() throws DomainServiceException;

    /**
     * Used to remove the current user's profile and StudentXRef prior to
     * retrying registration.
     *
     * @throws DomainServiceException
     */
    void restartRegistration() throws DomainServiceException;

    /**
     *
     * Used to register a new email for a current user. An email is sent with an
     * activation code that is required for activation before the changes are
     * applied to the UserProfile.
     *
     * @param email the new email.
     * @throws DomainServiceException
     */
    void registerNewEmail(final String email) throws DomainServiceException;

    /**
     * Used to validate if email address already registered by another STs
     * profile
     *
     * @param emailAddress
     * @return true if email address found in STs profile
     * @throws DomainServiceException
     */
    Boolean checkSubmittedEmail(String emailAddress) throws DomainServiceException;

    /**
     * Create a new registration from for changing email addresses.
     *
     * @return An instance of a user Profile Registration email form.
     * @throws DomainServiceException
     */
    UpdateContactReg createNewEmailRegistration() throws DomainServiceException;

    /**
     *
     * Used to notify user account when the credentials have been rebound.
     *
     * @param oldEmail email of the previous account to be notified.
     * @param profileEid
     * @throws DomainServiceException
     */
    void userRegistrationRebindNotification(String oldEmail, String profileEid) throws DomainServiceException;
}

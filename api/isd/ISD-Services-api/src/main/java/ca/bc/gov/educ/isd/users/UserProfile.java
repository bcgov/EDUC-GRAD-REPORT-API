/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: UserProfile.java 10324 2018-05-25 19:08:12Z DAJ#$
 *  Date of Last Commit: $Date:: 2018-05-25 12:08:12 -0700 (Fri, 25 May 2018)  $
 *  Revision Number:     $Rev:: 10324                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.party.Identifier;
import ca.bc.gov.educ.isd.common.party.Person;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * A record of a user of the system including identifying details and
 * authorization information.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface UserProfile extends Person {

    /**
     * Indicates if the profile is in an active state.
     *
     * If a profile is active then the user can use business services associated
     * with their account.
     *
     * @return the active status of the profile.
     */
    Boolean getActive();

    /**
     * Getter for the role groups that this profile belongs to.
     *
     * @return a set of role groups.
     */
    Set<RoleGroup> getRoleGroups();

    /**
     * Setter for if a user profile is active.
     *
     * @param active if the profile is active.
     */
    void setActive(Boolean active);

    /**
     * Convenience method for adding an identifier to a user profile.
     *
     * @param identifier the identifier to add.
     */
    void addIdentifier(Identifier identifier);

    /**
     * Convenience method for adding an SSO account to a user profile.
     *
     * @param ssoAccount the SSO account to add.
     */
    void addSSOAccount(SSOAccount ssoAccount);

    /**
     * Convenience method for removing an identifier.
     *
     * @param identifier the identifier to remove.
     */
    void removeIdentifier(Identifier identifier);

    /**
     * Convenience method for removing an SSO Account.
     *
     * @param ssoAccount
     */
    void removeSSOAccount(SSOAccount ssoAccount);

    @Override
    List<Identifier> getIdentifiers();

    /**
     * Getter for the SSO accounts linked to a user profile.
     *
     * @return a list of SSO accounts.
     */
    List<SSOAccount> getSSOAccounts();

    /**
     * Returns the profile display name.
     *
     * @return the display name of the user.
     */
    String getDisplayName();

    void setDisplayName(String displayName);

    /**
     * Getter for the date of birth in the profile.
     *
     * @return the date of birth of the user.
     */
    Date getDateOfBirth();

    /**
     * Setter for the date of birth in the profile.
     *
     * @param dateOfBirth to be set.
     */
    void setDateOfBirth(Date dateOfBirth);

    /**
     * Getter for the email address in the profile.
     *
     * @return the email address in the profile.
     */
    String getEmailAddress();

    /**
     * Setter for the users email address.
     *
     * @param emailAddress the email address to set.
     */
    void setEmailAddress(String emailAddress);

    /**
     * Getter for the phone number in the profile.
     *
     * @return the phone number in the profile.
     */
    String getPhoneNumber();

    /**
     * Setter for the users phone number.
     *
     * @param phoneNumber the phone number to set.
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Get the registration forms associated with this profile.
     *
     * @return a list of registration forms.
     */
    List<OnlineServiceReg> getOnlineServiceRegs();

    /**
     * Set the registration forms for this profile.
     *
     * @param onlineServiceRegs the list of registration forms to set.
     */
    void setOnlineServiceRegs(List<OnlineServiceReg> onlineServiceRegs);

    /**
     * Getter for the locked status of a profile.
     *
     * @return the locked status of a profile.
     */
    Boolean getLocked();

    /**
     * Setter for the locked status of a profile.
     *
     * @param locked the locked status of a profile.
     */
    void setLocked(Boolean locked);

    /**
     * Gets the current registration form for the user. Null will be returned if
     * one has not been created yet.
     *
     * @return The current registration form.
     */
    OnlineServiceReg getCurrentRegForm();

    /**
     * Getter for the admin notes made on the user profile.
     *
     * @return the admin notes written
     */
    String getAdminNotes();

    /**
     * Setter for the admin notes on a user profile.
     *
     * @param adminNotes the admin note to set.
     */
    void setAdminNotes(String adminNotes);

    /**
     * Getter for the last login date for the user.
     *
     * @return Date - the last login date for the user.
     */
    Date getLastLoginDt();

    /**
     * Answers whether this SSO identifier is an administrator account, as
     * denoted by a TYPECODE value of 'IDIR'.
     *
     * @return true The SSO ID belongs to an administrator.
     */
    Boolean isAdmin();
}

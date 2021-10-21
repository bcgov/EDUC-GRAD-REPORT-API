/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        OnlineServiceReg.java
 *  Date of Last Commit: $Date:: 2018-01-17 13:55:29 -0800 (Wed, 17 Jan 2018)  $
 *  Revision Number:     $Rev:: 9127                                           $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.DomainEntity;

import java.util.Collection;
import java.util.Date;

//TODO may need to add a service instance and service type/configuration concept.
/**
 * A User's registration for an on-line self-service offering.
 *
 * The system may offer multiple on-line services to users. The online service
 * registration connects the user account to the granting of access and
 * agreement to the terms of service for an on-line service.
 *
 * <p>
 * For example, a user that is a student who registers for the Student
 * Transcript Service. Or a user representing a post-secondary institution
 * accessing transcript downloads.
 *
 * <p>
 * Each on-line service has an associated Role Group. Users who are registered
 * and permitted to use this service should be granted this role group.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface OnlineServiceReg extends DomainEntity {

    /**
     * Retrieve the coded value for the description of this service.
     *
     * The User friendly display name and description of this on-line service
     * can be retrieved from the code set.
     *
     * @return Coded value for this service name and description.
     */
    String getServiceCode();

    /**
     * A user profile will be associated with a GUID or UUID, this can be used
     * retrieve their profile.
     *
     * @return Unique Identifier for the user's profile.
     */
    String getUserGuid();

    /**
     * Retrieve the Terms of Service agreements that are associated with the
     * current form.
     *
     * @return the Terms of Service.
     */
    Collection<OnlineAgreement> getTermsOfService();

    /**
     * Attach a terms and usage agreements to this on-line service.
     *
     * @param tosAgree
     */
    void setTermsOfService(Collection<OnlineAgreement> tosAgree);

    /**
     * Retrieve the role group which users of the service are granted.
     *
     * Once a registration for this type of service is successful the user's
     * profile is linked to this role group.
     *
     * @return The Role Group used by this service.
     */
    String getRoleGroup();

    /**
     * Indicates if the service has been activated.
     *
     * @return
     */
    Boolean isActive();

    /**
     * Retrieve the activation code used to unlock the User Profile after
     * registration.
     *
     * @return
     */
    String getActivationCode();

    /**
     * Retrieve the date that the user has until to activate their registration.
     *
     * @return the date calculated from the start of their application.
     */
    Date getActivationDeadline();

    /**
     * Return if the activation deadline is after the current Date.
     *
     * @return true if the registration has expired.
     */
    Boolean isExpired();

}

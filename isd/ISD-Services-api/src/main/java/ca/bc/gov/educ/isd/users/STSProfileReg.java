/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        STSProfileReg.java
 *  Date of Last Commit: $Date:: 2017-06-28 14:55:47 -0700 (Wed, 28 Jun 2017)  $
 *  Revision Number:     $Rev:: 7596                                           $
 *  Last Commit by:      $Author:: dstepano                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.users;

import java.util.Date;

/**
 * Interface for a student registration for used for self registration of a new
 * STS user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface STSProfileReg extends OnlineServiceReg {

    /**
     * Returns if the user's is a current secondary student.
     *
     * @return the status if a student is a current secondary student.
     */
    Boolean isCurrentSecondaryStudent();

    /**
     * Setter for the users secondary school status.
     *
     * @param current boolean if the student is a secondary school student.
     */
    void setCurrentSecondaryStudent(Boolean current);

    /**
     * Getter for date of birth.
     *
     * @return the date of birth.
     */
    Date getDateOfBirth();

    /**
     * Setter for date of birth.
     *
     * @param dob the day of birth to be set.
     */
    void setDateOfBirth(Date dob);

    /**
     * Setter for the service code.
     *
     * @param serviceCode the service code to be set.
     */
    void setServiceCode(String serviceCode);

    /**
     * Setter for the user guid.
     *
     * @param userGuid the user guid to be set.
     */
    void setUserGuid(String userGuid);

    /**
     * Setter for the role group.
     *
     * @param roleGroup the role group to be set.
     */
    void setRoleGroup(String roleGroup);

    /**
     * Setter for the form active status.
     *
     * @param active the status to be set.
     */
    void setActive(Boolean active);

    /**
     * Getter for the PEN.
     *
     * @return the PEN for the form.
     */
    String getPen();

    /**
     * Setter for the PEN.
     *
     * @param pen the PEN to be set.
     */
    void setPen(String pen);

    /**
     * Getter for the last name.
     *
     * @return the last name.
     */
    String getLastName();

    /**
     * Getter for the first name.
     *
     * @return the first name.
     */
    String getFirstName();

    /**
     * Getter for the middle name.
     *
     * @return the middle name.
     */
    String getMiddleName();

    /**
     * Setter for the last name.
     *
     * @param lastName the last name to be set.
     */
    void setLastName(String lastName);

    /**
     * Setter for the first name.
     *
     * @param firstName the first name to be set.
     */
    void setFirstName(String firstName);

    /**
     * Setter for the middle name.
     *
     * @param middleName the middle name to be set.
     */
    void setMiddleName(String middleName);

    /**
     * Getter for the email.
     *
     * @return the email.
     */
    String getEmail();

    /**
     * Setter for the email.
     *
     * @param email the email to be set.
     */
    void setEmail(String email);

    /**
     * Getter for the phone number.
     *
     * @return the phone number.
     */
    String getPhoneNumber();

    /**
     * Setter for the last name.
     *
     * @param phoneNumber the phone number to set.
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Returns the user's rebind credential flag.
     *
     * @return the flag for the rebind user credential.
     */
    Boolean isRebindUserCredential();

    /**
     * Setter for the rebindUserCredential.
     *
     * @param rebindUserCredential the rebind user credential to be set.
     */
    void setRebindUserCredential(Boolean rebindUserCredential);
}

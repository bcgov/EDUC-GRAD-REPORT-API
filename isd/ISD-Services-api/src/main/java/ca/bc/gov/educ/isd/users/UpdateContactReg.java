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
 *  File:                UpdateContactReg.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.users;

/**
 * Interface for a user contact info update that requires confirmation.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface UpdateContactReg extends OnlineServiceReg {

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

}

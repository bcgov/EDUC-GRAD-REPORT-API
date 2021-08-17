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
package ca.bc.gov.educ.isd.process.user;

/**
 * FIXME: This looks like it should be an enumerated type.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class RegistrationStatusError {

    public static final String RETRY = "retry";
    public static final String LOCKED = "locked";
    public static final String LOCKING = "Locking profile, failed attempts";
    public static final String M_D_STATUS = "M or D Status";
    public static final String PROMPT_CONTINUE = "prompt_continue"; //Rebind to new profile
    public static final String RETRY_MISMATCH = "Retry attempt, mismatch record";
    public static final String RETRY_EMAIL = "Retry attempt, email in use";
    public static final String LOCKED_STUD_DATA = "Locked student data";
    public static final String RETRY_PEN_IN_USE = "Pen already registered";
    public static final String EXIST_PROFILE = "Profile already exists";
}

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

package ca.bc.gov.educ.isd.student;

/**
 * Represents combined data from student profile and student master. Correlated by the PEN.
 * 
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentProfileMasterInfo {

    String getPen();

    String getProfileId();

    String getLastLoginDate();
    
    String getBirthdate();
    
    String getFirstName();
    
    String getMiddleName();
    
    String getLastName();

    String getFullName();
}

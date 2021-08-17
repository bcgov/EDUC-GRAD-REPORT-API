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
package ca.bc.gov.educ.isd.eis.trax.db;


public interface StudentProfileMasterLite {

    public String getPen();
    
    public String getProfileId();
    
    public String getProfileEntityId();
    
    public String getLastLoginDate();
    
    public String getBirthdate();
    
    public String getFirstName();
    
    public String getMiddleName();
    
    public String getLastName();

}

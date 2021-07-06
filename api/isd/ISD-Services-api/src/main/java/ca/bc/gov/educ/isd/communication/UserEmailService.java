/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        UserEmailService.java 
 *  Date of Last Commit: $Date:: 2016-04-06 13:01:46 -0700 (Wed, 06 Apr 2016)  $  
 *  Revision Number:     $Rev:: 1115                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.communication;

import ca.bc.gov.educ.isd.users.UserProfile;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface UserEmailService {

    /**
     *
     * @return
     */
    Email create();

    /**
     *
     * @param profile
     * @param message
     */
    void emailUser(UserProfile profile, Email message);
}

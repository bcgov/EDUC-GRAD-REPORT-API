/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        AssistedServiceProcess.java 
 *  Date of Last Commit: $Date:: 2016-04-06 13:01:46 -0700 (Wed, 06 Apr 2016)  $  
 *  Revision Number:     $Rev:: 1115                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.common;

/**
 * Workflow performed on behalf of another user.  The performer of the workflow,
 * is determined by the currently logged in user, is acting on behalf of another
 * user.
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:14 PM
 */
public interface AssistedServiceProcess extends BusinessProcess {

    /** Initialize the  business process to act on behalf of another user.
     * 
     * @param userEid   Entity Identifier of the subject user.
     */
    void initialize(String userEid);
}

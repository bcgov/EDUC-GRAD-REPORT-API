/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        GlobalNotificationAdminService.java 
 *  Date of Last Commit: $Date:: 2016-09-15 13:33:24 -0700 (Thu, 15 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3593                                           $  
 *  Last Commit by:      $Author:: matalbot                                    $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.communication;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GlobalNotificationAdminService extends CommonEntityService<GlobalNotification, SearchResult> {
    
    
    /** Expires a notification global immediately.
     * 
     * @param note Notification to expire
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void expireNotification(GlobalNotification note) throws DomainServiceException;
    
}

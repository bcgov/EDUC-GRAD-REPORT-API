/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        GlobalNotification.java 
 *  Date of Last Commit: $Date:: 2016-09-20 08:47:57 -0700 (Tue, 20 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3675                                           $  
 *  Last Commit by:      $Author:: matalbot                                    $ 
 *  
 * ********************************************************************** */
package ca.bc.gov.educ.isd.communication;

import java.util.Date;

/**
 * A global notification message which should be applied to all users of the
 * system.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GlobalNotification extends Notification {

    /**
     * The date, after which, the notification should not be displayed to the
     * user.
     *
     * @return Expiry Date.
     */
    Date getExpiryDate();
    void setExpiryDate(Date expiryDate);
    Date getStartDate();
    void setStartDate(Date startDate);
    
    void setDetail(String detail);
    void setSummary(String summary);
    
}

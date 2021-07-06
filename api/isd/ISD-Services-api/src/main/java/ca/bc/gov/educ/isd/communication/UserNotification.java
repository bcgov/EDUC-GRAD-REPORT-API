/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        UserNotification.java 
 *  Date of Last Commit: $Date:: 2016-10-20 13:09:52 -0700 (Thu, 20 Oct 2016)  $  
 *  Revision Number:     $Rev:: 4518                                           $  
 *  Last Commit by:      $Author:: matalbot                                    $ 
 *  
 * ********************************************************************** */
package ca.bc.gov.educ.isd.communication;

import java.util.Map;

/**
 * A notification message addressed to a single user.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface UserNotification extends Notification {

    /**
     * Reference to the user, who is the recipient of this notification.
     *
     * @return Unique identifier for the user account.
     */
    String getUserEntityId();

    /**
     * Setter for the reference to the user whom the notification relates to.
     *
     * @param userEntitiyId Unique identifier for the user account.
     */
    void setUserEntityId(String userEntitiyId);

    /**
     * Indicates this notification been read (or displayed) to the user.
     *
     * For some notifications, once they have been displayed to the user, they
     * do not need to be displayed again.
     * </p>
     * <p>
     * A notification is declared to have been read, if a user interface has
     * retrieved it for display to the user. This is a separate concern from the
     * idea of a email or SMS message having been read for notifications which
     * are transmitted to the user.
     * </p>
     * <p>
     * This feature is available only to user level notifications. Global
     * notifications must rely on the expiry date.
     *
     *
     * @return TRUE, if this notification has been displayed to the current
     * user.
     */
    Boolean isRead();

    /**
     * Getter for the name of the template to use in rendering.
     *
     * @return the template name to be used.
     */
    String getTemplateName();

    /**
     * Get the variables required for the user notification. These include template
     * details, recipient information and substitution variables.
     * 
     * @return Variables used in assembling and delivering an user notification. 
     */
    Map getVariables();
    
    void setDetail(String detail);

}

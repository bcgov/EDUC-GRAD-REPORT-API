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
 *  File:                $Id:: Email.java 8419 2017-10-13 16:03:09Z DAJARVIS   $
 *  Date of Last Commit: $Date:: 2017-10-13 09:03:09 -0700 (Fri, 13 Oct 2017)  $
 *  Revision Number:     $Rev:: 8419                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.communication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * An email message to a user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Email extends Notification {

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
     * Get the variables required for the email notification. These include
     * template details, recipient information and substitution variables.
     *
     * @return Variables used in assembling and delivering an email.
     */
    HashMap<String, String> getVariables();

    /**
     * Getter for the name of the template to use in rendering.
     *
     * @return the template name to be used.
     */
    String getTemplateName();

    /**
     * Set the variable map used to render and send the email.
     *
     * @param variables Map of email details used in composition.
     */
    void setVariables(Map<String, String> variables);

    /**
     * The date, after which, the notification should not be displayed to the
     * user.
     *
     * @return Expiry Date.
     */
    Date getExpiry();

    void setDetail(String detail);

    void setSummary(String summary);

    String getOverrideEmailAddress();

    void setOverrideEmailAddress(String overrideEmailAddress);

    Date getSentOn();
}

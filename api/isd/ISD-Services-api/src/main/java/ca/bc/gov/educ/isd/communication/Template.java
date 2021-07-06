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
 *  File:                Template.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.communication;

import ca.bc.gov.educ.isd.common.DomainEntity;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Template extends DomainEntity {

    /**
     * Return the name assigned to this template. These names may be referenced
     * as a Constant.
     * <p>
     * @return
     */
    String getName();

    /**
     * Return the template body. This is the notification content stored in a
     * format that reflects the template type.
     * <p>
     * @return
     */
    String getTemplate();

    /**
     * Return the media type of this notification. A media type is composed of a
     * type, a subtype and optional parameters. common type/subtype examples
     * are:
     * <ul><li>text/html<li>text/plain</ul>
     * a parameter can be "; charset=UTF-8".<br>
     * A complete media type would look like: "text/html; charset=UTF-8"
     * <p>
     * @return
     */
    String getMediaType();

    /**
     * Return the template type which indicates what mechanism should be used to
     * perform any interpretation on the template. For example if the template
     * is stored in a specific format interpreted by the Velocity engine then
     * this value is "Velocity". If the intent is a simple MessageFormatter then
     * this value is "String".
     * <p>
     * @return
     */
    String getTemplateType();

    /**
     * Retrieve the displayable (rendered) summary of this notification.
     * <p>
     * <p>
     * This value is analogous to the summary field of a faces message, or the
     * subject line of an email message.
     * <p>
     * @return Displayable summary line of the notification.
     */
    String getSummary();
    
    /**
     * Retrieve what method should be used to deliver the message once
     * it has been applied to the template.
     * 
     * @return The method of delivery.
     */
    String getNotificationMethod();
    
    void setSenderEmail(String senderEmail);
    String getSenderEmail();
    
    void setSummary(String summary);
    void setTemplate(String template);

}

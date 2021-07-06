/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Notification.java 8419 2017-10-13 16:03:09Z DAJ#$
 *  Date of Last Commit: $Date:: 2017-10-13 09:03:09 -0700 (Fri, 13 Oct 2017)  $
 *  Revision Number:     $Rev:: 8419                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.communication;

import ca.bc.gov.educ.isd.common.DomainEntity;

/**
 * Notifications represent messages communicated to the user.
 *
 * This notifications are longer lived than user interface notifications.
 * Depending on the user profile settings, they may be send to the user by email
 * (or other messaging system such as SMS), or displayed on a user interface.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Notification extends DomainEntity {

    /**
     * The coded value for the Severity of the notification message.
     *
     * This is analogous Severity of a FacesMessage in JSF.
     *
     * @return String Coded value for the severity of the notification message.
     */
    String getSeverityCode();

    /**
     * Retrieve the displayable (rendered) summary of this notification.
     *
     * <p>
     * This value is analogous to the summary field of a faces message, or the
     * subject line of an email message.
     *
     * @return Displayable summary line of the notification.
     */
    String getSummary();

    /**
     * Retrieve the displayable (rendered) detail of this notification.
     *
     * <p>
     * This value is analogous to the detail field of a faces message, or the
     * body of an email message.
     *
     * @return Displayable body / detail of the notification.
     */
    String getDetail();

}

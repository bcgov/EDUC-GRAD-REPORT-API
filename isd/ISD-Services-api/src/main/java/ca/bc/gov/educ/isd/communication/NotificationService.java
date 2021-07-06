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
 *  File:                $Id:: NotificationService.java 10182 2018-05-14 22:34#$
 *  Date of Last Commit: $Date:: 2018-05-14 15:34:13 -0700 (Mon, 14 May 2018)  $
 *  Revision Number:     $Rev:: 10182                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.communication;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import java.util.List;

/**
 * Access to notifications for the currently logged-in user.
 *
 * Notification service provides lists of messages that should be displayed to
 * the user during a user interface session.
 *
 *
 * <p>
 * This notification service has the ability to create notifications which are
 * addressed to the currently logged-in user. This feature is used when takes an
 * action which required an email to be sent to them. i.e. placing and online
 * order. This feature is also available in the administrative version of this
 * service, but two service end-points are provided in support of least common
 * mechanisms.</p>
 *
 * <h5>Implementation Notes</h5>
 * <p>
 * At first glance the notification service is just another entity service and
 * should inherit from the CommonEntityService Hierarchy. However, in the entity
 * service hierarchy each method in directly associated with a typical CRUD
 * function. With the Notification Service each CRUD-like method has other
 * implications and and consequences, such as marking notifications as having
 * been displayed, or ending email messages.
 * </p>
 * <p>
 * Internally the service implementation <b>must</b> use the Common Entity
 * Service hierarchy. This ensures consistent behavior with the enforcement of
 * business rules. However it is not exposed to the service consumer directly.
 * </p>
 * <h6>Assumptions</h6>
 * <p>
 * It is assumed that the total number of current notifications to be displayed
 * to the user will be relatively small. Hence, it would be suitable to load the
 * entire list.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NotificationService extends BusinessService {

    /**
     * Create a new empty User Notification object.
     *
     * The notification object will be rendered with the default template.
     *
     * @return New, empty, user notification object.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    UserNotification create() throws DomainServiceException;

    /**
     * Create a user notification based on a defined rendering template.
     *
     * @param templateName Name of the rendering template to use.
     * @return New, empty, user notification object.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    Notification create(String templateName) throws DomainServiceException;

    /**
     * Create a user notification based on a defined rendering template. To be
     * used outside the siteminder environment.
     *
     * @param templateName Name of the rendering template to use.
     * @param profileId the profile entityId of the student.
     * @return New, empty, user notification object.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    Notification create(String templateName, String profileId) throws DomainServiceException;

    /**
     * A user notification, sent to the currently logged-in user.
     * <p>
     *
     * @param note A user notification to the currently authenticated user.
     * @return TRUE, if the message was posted successfully.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    Boolean notify(UserNotification note) throws DomainServiceException, DataException;

    /**
     * A email notification, sent to the currently logged-in user.
     * <p>
     *
     * @param note A email notification to the currently authenticated user.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    void notify(Email note) throws DomainServiceException, DataException;

    /**
     * List all, non-expired, user notifications for the current user.
     *
     * This returns a list of all current, unread, user notifications of all
     * severities.
     * <p>
     * <b>Note:</b> All of the notifications in this list will be marked as read
     * once they are returned by this method. If it is necessary to retrieve
     * them once again use the 'includeUnRead' parameter.
     * </p>
     *
     * @throws ca.bc.gov.educ.isd.common.DataException
     * @see NotificationService#listUser(java.lang.Boolean)
     *
     * @return List of notifications to be displayed.
     */
    List<UserNotification> listUser() throws DomainServiceException;

    /**
     * List all, non-expired, user notifications for the current user,
     * optionally including previously read messages.
     * <p>
     * This method behaves exactly as the 'listUser()' method, but add the
     * option to return previously read notifications.
     *
     * @throws ca.bc.gov.educ.isd.common.DataException
     * @see NotificationService#listUser()
     *
     * @param includeRead TRUE, if read notifications should be included in the
     * list.
     * @return List of notifications to be displayed.
     */
    List<UserNotification> listUser(Boolean includeRead) throws DomainServiceException;

    /**
     * List all user notifications, including expired and read.
     * <p>
     * This list includes the expired user notifications.
     *
     * @return List of all user notification associated with this user.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    List<UserNotification> listUserAll() throws DomainServiceException;

    /**
     * List the current global notifications which should be displayed to the
     * user.
     *
     * @return List of notifications to be displayed.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    List<GlobalNotification> listGlobal() throws DataException;

    /**
     * Retrieve a list of the named templates which can be used in rendering a
     * notification.
     *
     * Template are used for specially formatted messages such as user account
     * activation email. Templates have a pre-defined summary and detail.
     *
     * @return
     * @throws DomainServiceException
     * @throws DataException
     */
    List<String> listTemplates() throws DomainServiceException, DataException;

    /**
     * Retrieves email notifications that were never sent.
     *
     * @return
     */
    List<Email> fetchUnSentEmails();
}

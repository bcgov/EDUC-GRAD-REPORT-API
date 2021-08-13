/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        EventBroadcastSupport.java
 *  Date of Last Commit: $Date:: 2017-06-13 13:21:37 -0700 (Tue, 13 Jun 2017)  $
 *  Revision Number:     $Rev:: 7498                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common;

import java.util.Date;

/**
 * The event broadcaster provides access to various event notification topics.
 *
 * <p>
 * Events such as data changes in the Operational Data Store are broadcast to
 * interested parties through this set of convenience methods.
 *
 * <h3>Implementation Notes</h3>
 * <p>
 * The intent is that the implementation of this interface will be backed by an
 * EJB which places the change message on JMS topics. Support for additional
 * message channels can be added by extending (sub-classing) this interface.</p>
 * <p>
 * Support for broadcasting the complete ODS record may be added in order to
 * support integration with the Enterprise Data Warehouse.</p>
 * <h2>Notification Consumer Implementations</h2>
 * <p>
 * Event Notification consumers, which of course must be message driven beans,
 * should take care to provide a mechanism to support the administrative message
 * types.
 *
 * <h3>When to publish a data change event</h3>
 * <p>
 * Data change events should only be published once as the last step in a
 * service transaction. The publication service <b>MUST</b> participate in the
 * transaction, so that if the change to the domain entity fails, the
 * notification message is rolled back.</p>
 *
 * <h3>Data Change Event Message</h3>
 * <p>
 * The data change events are published on a durable JMS Topic
 * "ISD_Entity_Change_Events" as follows:</p>
 *
 * <h4>JMS Headers</h4>
 * <p>
 * Each message contains the JMS headers:</p>
 * <ol><li>"<b>entity_type</b>"</li>
 * <li>and "<b>component_name</b>"</li>
 * </ol>
 * <p>
 * The <b>entity_type</b> is the fully qualified class name of the entity's
 * interface in the ISD-Services API. This value can be used by listening
 * components to determine if the event is for an entity type they are
 * interested in.</p>
 *
 * <p>
 * The <b>component_name</b> is the standard ISD name for the component /
 * functional domain. This value can be further used by the JMS selector to
 * filter messages of interest. Additionally if a component changes an entity
 * that it listens to it must publish the data change event, however it may not
 * want to respond to events that it triggers.</p>
 *
 * <h4>The message content</h4>
 * <p>
 * The message is a JMS map message. The map contains at least the following
 * properties from the entity:</p>
 * <dl>
 * <dt>"entityId"</dt>
 * <dd>The unique business identifier of the entity.</dd>
 * <dt>"createdOn"</dt>
 * <dd>A java.util.Date containing the value of the "createdOn" audit
 * property.</dd>
 * <dt>"createdBy"</dt>
 * <dd>A java.lang.String containing the value of the "createdBy" audit
 * property.</dd>
 * <dt>"updatedOn"</dt>
 * <dd>A java.util.Date containing the value of the "updatedOn" audit
 * property.</dd>
 * <dt>"lastUpdatedBy"</dt>
 * <dd>A java.lang.String containing the value of the "lastUpdatedBy" audit
 * property.</dd>
 * </dl>
 *
 * <p>
 * The map may contain other properties of the entity if needed.</p>
 *
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @since 1.0
 */
public interface EventBroadcastSupport extends BusinessService {

    /**
     * Publishes the domain object to the data change notification topic.
     *
     * The domain object, or its representation, is broadcast to data change
     * notification subscribers. The fully qualified name of the domain entity
     * interface class is used as the channel selector.
     *
     * @param componentName String identifier of the component changing the
     * entity (as found in
     * ISD-Services-api.ca.bc.gov.educ.isd.common.Constants).
     * @param interfaceClass The fully qualified name of the domain entities
     * interface class (as returned by class.getName() ).
     * @param entityId The id of the new domain entity.
     * @param lastUpdatedBy The principal user who created the entity.
     * @param lastUpdatedOn The Date the entity was modified.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void publishDataChange(String componentName, String interfaceClass, String entityId, String lastUpdatedBy, Date lastUpdatedOn) throws DomainServiceException;

    /**
     * Publishes the domain object to the data change notification topic.
     *
     * The domain object, or its representation, is broadcast to data change
     * notification subscribers. The fully qualified name of the domain entity
     * interface class is used as the channel selector.
     *
     * @param componentName String identifier of the component changing the
     * entity.
     * @param interfaceClass The fully qualified name of the domain entities
     * interface class.
     * @param status The status of the domain entity.
     * @param entityId The id of the new domain entity.
     * @param lastUpdatedBy The principal user who created the entity.
     * @param lastUpdatedOn The Date the entity was modified.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void publishDataChange(String componentName, String interfaceClass, String status, String entityId, String lastUpdatedBy, Date lastUpdatedOn) throws DomainServiceException;

    /**
     * Publishes the domain object to the data change notification topic.
     *
     * The domain object, or its representation, is broadcast to data change
     * notification subscribers. The fully qualified name of the domain entity
     * interface class is used as the channel selector.
     *
     * @param componentName String identifier of the component changing the
     * entity.
     * @param interfaceClass The fully qualified name of the domain entities
     * interface class.
     * @param entityName
     * @param status The status of the domain entity.
     * @param operation the CRUD operation causing the data change. either WRITE
     * or REMOVE
     * @param entityId The id of the new domain entity.
     * @param lastUpdatedBy The principal user who created the entity.
     * @param lastUpdatedOn The Date the entity was modified.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void publishDataChange(String componentName, String interfaceClass, String entityName, String status, String operation, String entityId, String lastUpdatedBy, Date lastUpdatedOn) throws DomainServiceException;

    static final String OP_WRITE = "WRITE";

    static final String OP_REMOVE = "REMOVE";

}

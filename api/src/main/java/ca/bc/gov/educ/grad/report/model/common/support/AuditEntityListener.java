/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information
 *  File:                NewClass.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.support;

import java.util.Date;
import java.util.logging.Logger;

/**
 * This class fulfills the need for a mechanism to fill in audit fields when the
 * entity class is being managed by the parent's service class instead of its
 * own. Since CDI injection is not possible in an EntityListener class, we need
 * to retrieve the current user from the parent class audit fields.
 *
 * This listener can be wired into an entity by using the class annotation:
 * <code>@EntityListeners(AuditEntityListener.class)</code>
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AuditEntityListener {

    private static final String CLASSNAME = AuditEntityListener.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Populates the audit fields <code>createdBy</code> and
     * <code>lastUpdatedBy</code> based on the values from the parent. Populates
     * the audit fields <code>createdOn</code> and <code>lastUpdatedOn</code>
     * based on the current time.
     *
     * Note that <code>createdBy</code> uses the value of
     * <code>lastUpdatedBy</code> from the parent object to handle the case
     * where the parent was previously created and the detail is new.
     *
     * @param entity The entity whose audit fields need populating.
     */
    public void populateAuditFieldsCreate(Object entity) {
        LOG.entering(CLASSNAME, "populateAuditFieldsCreate");

        if (entity instanceof AbstractDomainEntity && entity instanceof AuditableDetail) {
            AbstractDomainEntity domainEntity = (AbstractDomainEntity) entity;

            final String updatedByUser = ((AuditableDetail) entity).getParent().getLastUpdatedBy();

            {
                String currentCreatedBy = domainEntity.getCreatedBy();
                if (currentCreatedBy != null && !currentCreatedBy.isEmpty()) {
                    if (!currentCreatedBy.equals(updatedByUser)) {
                        throw new IllegalStateException("CreatedBy has already been set.");
                    }
                }
                LOG.finer("Validated that createdBy is not being overwritten.");
            }

            final Date date = new Date();
            domainEntity.setCreatedOn(date);
            domainEntity.setCreatedBy(updatedByUser);
            domainEntity.setLastUpdatedOn(date);
            domainEntity.setLastUpdatedBy(updatedByUser);

            validateAuditFields(domainEntity);
        }
        LOG.exiting(CLASSNAME, "populateAuditFieldsCreate");
    }

    /**
     * Populates the audit fields <code>createdBy</code> and
     * <code>lastUpdatedBy</code> based on the values from the parent. Populates
     * the audit fields <code>createdOn</code> and <code>lastUpdatedOn</code>
     * based on the current time.
     *
     * Note that <code>createdBy</code> is not set as this is an update
     * operation.
     *
     * @param entity The entity whose audit fields need populating.
     */
    public void populateAuditFieldsUpdate(Object entity) {
        LOG.entering(CLASSNAME, "populateAuditFieldsUpdate");

        if (entity instanceof AbstractDomainEntity) {
            AbstractDomainEntity domainEntity = (AbstractDomainEntity) entity;
            final Date currentUpdatedOn = new Date();

            {
                final Date previousLastUpdatedOn = domainEntity.getLastUpdatedOn();
                if (previousLastUpdatedOn != null && currentUpdatedOn.before(previousLastUpdatedOn)) {
                    throw new IllegalStateException("LastUpdatedOn should NOT be earlier than getLastUpdatedOn.");
                }
                LOG.finer("Validated that createdBy is not being overwritten.");
            }

            final String updatedByUser = ((AuditableDetail) entity).getParent().getLastUpdatedBy();

            domainEntity.setLastUpdatedOn(currentUpdatedOn);
            domainEntity.setLastUpdatedBy(updatedByUser);

            if (domainEntity.getCreatedBy() == null) {
                domainEntity.setCreatedBy(updatedByUser);
                domainEntity.setCreatedOn(currentUpdatedOn);
            }

            validateAuditFields(domainEntity);
        }

        LOG.exiting(CLASSNAME, "populateAuditFieldsUpdate");
    }

    private void validateAuditFields(AbstractDomainEntity domainEntity) {
        LOG.entering(CLASSNAME, "validateAuditFields");

        String createdBy = domainEntity.getCreatedBy();
        String lastUpdatedBy = domainEntity.getLastUpdatedBy();
        Date createdOn = domainEntity.getCreatedOn();
        Date lastUpdatedOn = domainEntity.getLastUpdatedOn();

        if (createdBy == null) {
            throw new NullPointerException("CreatedBy should NOT be null.");
        }
        if (createdBy.trim().isEmpty()) {
            throw new IllegalArgumentException("CreatedBy should NOT be empty.");
        }

        if (lastUpdatedBy == null) {
            throw new NullPointerException("LastUpdatedBy should NOT be null.");
        }
        if (lastUpdatedBy.trim().isEmpty()) {
            throw new IllegalArgumentException("LastUpdatedBy should NOT be empty.");
        }

        if (createdOn == null) {
            throw new NullPointerException("createdOn should not be null");
        }

        if (lastUpdatedOn == null) {
            throw new NullPointerException("lastUpdatedOn should NOT be null.");
        }

        if (lastUpdatedOn.before(createdOn)) {
            throw new IllegalStateException("LastUpdatedOn should NOT be earlier than createdOn.");
        }

        LOG.exiting(CLASSNAME, "validateAuditFields");
    }

}

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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.model.common.support;

import ca.bc.gov.educ.grad.model.common.DomainEntity;

import java.util.Date;
import java.util.Objects;

/**
 * Common super class for all DomainEntity.
 *
 * <p>
 * <b>All domain entity implementations must inherit from this class.</b>
 * </p>
 *
 * <p>
 * This class provides an common, reuse implementation of the Domain Entity
 * interface. Field in this class can be applied as entity fields in its
 * sub-classes by using the Attribute Override features, for example:
 * </p>
 * <p>
 * <code>
 * @AttributeOverrides({
 * @AttributeOverride(name = "createdBy", column =
 * @Column(length = 30, nullable = false, name = "CREATE_USER")),
 * @AttributeOverride(name = "entityId", column =
 * @Column(length = 30, nullable = false, name = "ENTITY_ID", unique = true)),
 * @AttributeOverride(name = "createdOn", column =
 * @Column(name = "CREATE_DTTM", insertable = true, updatable = false)),
 * @AttributeOverride(name = "lastUpdatedBy", column =
 * @Column(length = 30, nullable = false, name = "UPDATE_USER")),
 * @AttributeOverride(name = "lastUpdatedOn", column =
 * @Column(name = "UPDATE_DTTM", insertable = true, updatable = true)),
 * @AttributeOverride(name = "xactVersionId", column =
 * @Column(nullable = false, name = "XACT_ID")) })
 * </code>
 * </p>
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class AbstractDomainEntity implements DomainEntity {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = AbstractDomainEntity.class.getName();

    /**
     * ID of user that created entity.
     */
    private String createdBy;

    /**
     * Timestamp of when object was first persisted.
     */
    private Date createdOn = new Date(0L);

    /**
     * Timestamp of when this object was last changed and written.
     */
    private Date lastUpdatedOn = new Date(0L);

    /**
     * ID of user that updated entity.
     */
    private String lastUpdatedBy;

    /**
     * Stateless Transaction ID, used to deal with record concurrent
     * modifications.
     */
    private int xactId = 0;

    private String entityId;

    public AbstractDomainEntity() {
    }

    public AbstractDomainEntity(final String entityId) {
        this.entityId = entityId;
    }

    /**
     * Constructor to aid in the clonable support.
     *
     * Subclasses can use this constructor to simplify their clone methods. The
     * sub-class should provide a protected delegate for this constructor and
     * then use it when creating a new instance with clone override.
     *
     * @param template
     */
    protected AbstractDomainEntity(final AbstractDomainEntity template) {
        this.createdBy = template.createdBy;
        this.createdOn = template.createdOn == null ? null : (Date) template.createdOn.clone();
        this.entityId = template.entityId;
        this.lastUpdatedBy = template.lastUpdatedBy;
        this.lastUpdatedOn = template.lastUpdatedOn == null ? null : (Date) template.lastUpdatedOn.clone();
        this.xactId = template.xactId;
    }

    @Override
    public String getEntityId() {
        return this.entityId;
    }

    @Override
    public Date getCreatedOn() {
        if (createdOn == null) {
            createdOn = new Date();
        }

        return (Date) createdOn.clone();
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public Date getLastUpdatedOn() {
        if (lastUpdatedOn == null) {
            lastUpdatedOn = new Date();
        }

        return (Date) lastUpdatedOn.clone();
    }

    @Override
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(Date createdOn) {
        if (createdOn == null) {
            createdOn = new Date();
        }

        this.createdOn = (Date) createdOn.clone();
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        if (lastUpdatedOn == null) {
            lastUpdatedOn = new Date();
        }

        this.lastUpdatedOn = (Date) lastUpdatedOn.clone();
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setXactId(final Integer xactId) {
        int exact = 0;
        if (xactId != null) {
            exact = xactId;
        }
        this.xactId = exact;
    }

    public void setEntityId(final String entityId) {
        if (this.entityId != null && !this.entityId.isEmpty() && !this.entityId.equals(entityId)) {
            throw new IllegalStateException("EntityId has already been set - illegal to reset");
        }
        this.entityId = entityId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.createdBy);
        hash = 29 * hash + Objects.hashCode(this.createdOn);
        hash = 29 * hash + Objects.hashCode(this.lastUpdatedOn);
        hash = 29 * hash + Objects.hashCode(this.lastUpdatedBy);
        hash = 29 * hash + Objects.hashCode(this.entityId);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDomainEntity other = (AbstractDomainEntity) obj;
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.createdOn)) {
            return false;
        }
        if (!Objects.equals(this.lastUpdatedOn, other.lastUpdatedOn)) {
            return false;
        }
        if (!Objects.equals(this.lastUpdatedBy, other.lastUpdatedBy)) {
            return false;
        }
        return Objects.equals(this.entityId, other.entityId);
    }

    @Override
    public String toString() {
        return CLASSNAME + "{createdBy=" + createdBy + ", createdOn=" + createdOn + ", lastUpdatedOn=" + lastUpdatedOn + ", lastUpdatedBy=" + lastUpdatedBy + ", xactId=" + xactId + ", entityId=" + entityId + '}';
    }
}

/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: DomainEntity.java 8289 2017-09-26 23:04:07Z CGO#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * A common interface of all entities in the domain.
 *
 * <p>
 * Notice there is no setEntityId. Domain entities can only be created and
 * populated by the Entity's data service. The underlying implementation may
 * have a setter, but that is transparent to the service consumer.
 * </p>
 * <p>
 * Each Entity ID is intended to represent exactly one real world instance of
 * the entity. This is helpful when the data is owned/controlled by external
 * systems or the data could span multiple databases. (similar to the OID
 * pattern)
 * </p>
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DomainEntity extends Serializable {

    /**
     * Retrieve the globally unique identity of the entity.
     *
     * @return A globally unique identifier for the entity.
     */
    String getEntityId();

    /**
     * Retrieve the timestamp on which this entity was first created.
     *
     * @return Timestamp of when the entity was created.
     */
    Date getCreatedOn();

    /**
     * Retrieve name of the user or system that created this entity.
     *
     * The value of the created by is the value of the User Principal object at
     * the time of creation.
     *
     * @return Name of the user which created this entity.
     */
    String getCreatedBy();

    /**
     * Retrieve the timestamp on which this entity was last updated.
     *
     * @return Timestamp of when this entity was last updated.
     */
    Date getLastUpdatedOn();

    /**
     * Retrieve name of the user or system that last updated this entity.
     *
     * The value of the created by is the value of the User Principal object at
     * the time of last update.
     *
     * @return User who last updated this entity.
     */
    String getLastUpdatedBy();

    /**
     * Retrieve the primary key id for persistence.
     *
     * @return id
     */
    Long getId();
}

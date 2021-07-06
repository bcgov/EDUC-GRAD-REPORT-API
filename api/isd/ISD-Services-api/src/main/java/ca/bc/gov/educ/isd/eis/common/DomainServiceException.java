/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: DomainServiceException.java 7094 2017-05-11 21:#$
 *  Date of Last Commit: $Date:: 2017-05-11 14:17:35 -0700 (Thu, 11 May 2017)  $
 *  Revision Number:     $Rev:: 7094                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.common;

/**
 * A application exception wrapper for and exception with regard to business
 * logic.
 *
 * <b>Note:</b> In the configuration for Java&trade; EE applications this must
 * be declared as an application exception in the deployment descriptors.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class DomainServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private DomainEntity entity;

    /**
     * A business service exception that doesn't have a domain entity.
     *
     * @param message Descriptive message explaining the problem.
     */
    public DomainServiceException(final String message) {
        this(null, message);
    }

    /**
     * A business service exception that doesn't have a domain entity.
     *
     * @param message Descriptive message explaining the problem.
     * @param cause The underlying exception, if any.
     */
    public DomainServiceException(final String message, final Throwable cause) {
        this(null, message, cause);
    }

    /**
     * Create a new exception due to some service anomaly.
     *
     * @param entity
     */
    public DomainServiceException(final DomainEntity entity) {
        this.entity = entity;
    }

    /**
     * Create a new exception due to some service anomaly.
     *
     * @param entity The entity instance involved in the failed transaction.
     * @param message Descriptive message explaining the problem.
     */
    public DomainServiceException(final DomainEntity entity, final String message) {
        super(message);
        this.entity = entity;
    }

    /**
     * Create a new exception due to some service anomaly.
     *
     * @param entity The entity instance involved in the failed transaction.
     * @param msg Descriptive message explaining the problem.
     * @param cause The underlying exception, if any.
     */
    public DomainServiceException(final DomainEntity entity, final String msg, final Throwable cause) {
        super(msg, cause);
        this.entity = entity;
    }

    /**
     * Create a new exception due to some service anomaly.
     *
     * @param entity The entity instance involved in the failed transaction.
     * @param cause The underlying exception, if any.
     */
    public DomainServiceException(final DomainEntity entity, final Throwable cause) {
        super(cause);
        this.entity = entity;
    }

    /**
     * Create a new exception due to some service anomaly.
     *
     * @param entity The entity instance involved in the failed transaction.
     * @param msg Descriptive message explaining the problem.
     * @param cause The underlying exception, if any.
     * @param enableSuppression
     * @param writableStackTrace
     */
    @SuppressWarnings("LongVariable")  // Long Variable name is inherited from external api.
    public DomainServiceException(final DomainEntity entity, final String msg, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
        this.entity = entity;
    }

    /**
     * Get the entity object instance involved in this exception.
     *
     * @return The entity instance involved in the exception.
     */
    public DomainEntity getEntity() {
        return this.entity;
    }

    /**
     * Set the entity object instance involved in this exception.
     *
     * @param entity
     */
    void setEntity(final DomainEntity entity) {
        this.entity = entity;
    }
}

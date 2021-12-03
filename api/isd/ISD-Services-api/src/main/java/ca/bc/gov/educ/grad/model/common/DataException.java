/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        DataException.java
 *  Date of Last Commit: $Date:: 2018-05-14 15:34:13 -0700 (Mon, 14 May 2018)  $
 *  Revision Number:     $Rev:: 10182                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.model.common;

/**
 * A application exception wrapper for any exception regarding access to data.
 *
 * <b>Note: </b> In the configuration for Java EE applications this must be
 * declared as an application exception in the deployment descriptors.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class DataException extends DomainServiceException {

    private static final long serialVersionUID = 1817217871285315446L;

    private String eid;

    /**
     * Creates a new exception due to a data access layer anomaly.
     *
     * @param eID The globally unique entity ID string.
     * @param entity The entity instance involved in the failed transaction.
     */
    public DataException(final String eID, final DomainEntity entity) {
        super(entity);
        this.eid = eID;
    }

    /**
     * Creates a new exception due to a data access layer anomaly.
     *
     * @param eID The globally unique entity ID string.
     * @param entity The entity instance involved in the failed transaction.
     * @param message Descriptive message explaining the problem.
     */
    public DataException(final String eID, final DomainEntity entity, final String message) {
        super(entity, message);
        this.eid = eID;
    }

    /**
     * Creates a new exception due to a data access layer anomaly.
     *
     * @param eID The globally unique entity ID string.
     * @param entity The entity instance involved in the failed transaction.
     * @param message Descriptive message explaining the problem.
     * @param cause The underlying exception, if any.
     */
    public DataException(final String eID, final DomainEntity entity, final String message, final Throwable cause) {
        super(entity, message, cause);
        this.eid = eID;
    }

    /**
     * Creates a new exception due to a data access layer anomaly.
     *
     * @param eID The globally unique entity ID string.
     * @param entity The entity object involved in the failed transaction.
     * @param cause The underlying exception, if any.
     */
    public DataException(final String eID, final DomainEntity entity, final Throwable cause) {
        super(entity, cause);
        this.eid = eID;
    }

    /**
     * Creates a new exception due to a data access layer anomaly. Long Variable
     * name is inherited from external API.
     *
     * @param eID The globally unique entity ID string.
     * @param entity The entity object involved in the failed transaction.
     * @param message Descriptive message explaining the problem.
     * @param cause The underlying exception, if any.
     * @param enableSuppression
     * @param writableStackTrace
     */
    @SuppressWarnings("LongVariable")
    public DataException(
            final String eID,
            final DomainEntity entity,
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(entity, message, cause, enableSuppression, writableStackTrace);
        this.eid = eID;
    }

    /**
     *
     * @return The globally unique entity ID string.
     */
    String getEid() {
        return this.eid;
    }

    /**
     *
     * @param eid The globally unique entity ID string.
     */
    void setEid(final String eid) {
        this.eid = eid;
    }

}

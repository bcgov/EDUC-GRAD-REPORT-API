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
 *  File:                $Id:: RegistrationIssueImpl.java 7208 2017-05-23 23:#$
 *  Date of Last Commit: $Date:: 2017-05-23 16:25:16 -0700 (Tue, 23 May 2017) $
 *  Revision Number:     $Rev:: 7208                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.data.admin.impl;

import ca.bc.gov.educ.isd.reports.data.BusinessEntity;

import java.util.Date;

/**
 * Represents information placed on a report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class RegistrationIssueImpl extends BusinessEntity {

    private static final long serialVersionUID = 10L;

    private Date registered;
    private String reference;
    private String status;
    private String queued;

    /**
     * Default (empty) constructor.
     */
    public RegistrationIssueImpl() {
    }

    /**
     * Changes the value for registered.
     *
     * @param registered Profile activation date.
     */
    public void setRegistered(final Date registered) {
        assert registered != null;
        this.registered = new Date(registered.getTime());
    }

    /**
     * Returns Profile activation date.
     *
     * @return A non-null value.
     */
    public Date getRegistered() {
        return nullSafe(this.registered);
    }

    /**
     * Changes the value for reference.
     *
     * @param reference STs-assigned unique number.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Returns STs-assigned unique number.
     *
     * @return A non-null value.
     */
    public String getReference() {
        return nullSafe(this.reference);
    }

    /**
     * Changes the value for status.
     *
     * @param status Intervention status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns Intervention status.
     *
     * @return A non-null value.
     */
    public String getStatus() {
        return nullSafe(this.status);
    }

    /**
     * Changes the value for queued.
     *
     * @param queued Preformatted time (dd:hh:mm) that the orders have been in
     * queue.
     */
    public void setQueued(String queued) {
        this.queued = queued;
    }

    /**
     * Returns Preformatted time (dd:hh:mm) that the orders have been in queue.
     *
     * @return A non-null value.
     */
    public String getQueued() {
        return nullSafe(this.queued);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<RegistrationIssueImpl, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New RegistrationIssueImpl instance.
         */
        @Override
        protected RegistrationIssueImpl createObject() {
            return new RegistrationIssueImpl();
        }

        /**
         * Profile activation date.
         *
         * @param registered Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withRegistered(final Date registered) {
            getObject().setRegistered(registered);
            return thisBuilder();
        }

        /**
         * STs-assigned unique number.
         *
         * @param reference Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withReference(final String reference) {
            getObject().setReference(reference);
            return thisBuilder();
        }

        /**
         * Intervention status.
         *
         * @param status Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withStatus(final String status) {
            getObject().setStatus(status);
            return thisBuilder();
        }

        /**
         * Preformatted time (dd:hh:mm) that the orders have been in queue.
         *
         * @param queued Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withQueued(final String queued) {
            getObject().setQueued(queued);
            return thisBuilder();
        }
    }
}

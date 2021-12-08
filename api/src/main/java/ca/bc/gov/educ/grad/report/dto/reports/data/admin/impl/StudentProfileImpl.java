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
 *  File:                $Id:: StudentProfileImpl.java 10182 2018-05-14 22:34#$
 *  Date of Last Commit: $Date:: 2018-05-14 15:34:13 -0700 (Mon, 14 May 2018) $
 *  Revision Number:     $Rev:: 10182                                         $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.admin.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import java.util.Date;

/**
 * Represents information placed on a report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentProfileImpl extends BusinessEntity {

    private static final long serialVersionUID = 10L;

    private Date registered;
    private Integer bceId;
    private Integer bcServices;

    /**
     * Default (empty) constructor.
     */
    public StudentProfileImpl() {
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
     * Changes the value for bceId.
     *
     * @param bceId Has associated BCeID credentials.
     */
    public void setBceId(final Integer bceId) {
        this.bceId = bceId;
    }

    /**
     * Returns Has associated BCeID credentials.
     *
     * @return A non-null value.
     */
    public Integer getBceId() {
        return nullSafe(this.bceId);
    }

    /**
     * Changes the value for bcServices.
     *
     * @param bcServices Has associated BC Services credentials.
     */
    public void setBcServices(final Integer bcServices) {
        this.bcServices = bcServices;
    }

    /**
     * Returns Has associated BC Services credentials.
     *
     * @return A non-null value.
     */
    public Integer getBcServices() {
        return nullSafe(this.bcServices);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<StudentProfileImpl, Builder> {

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
         * @return New StudentProfileImpl instance.
         */
        @Override
        protected StudentProfileImpl createObject() {
            return new StudentProfileImpl();
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
         * Has associated BCeID credentials.
         *
         * @param bceId Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withBceId(final Integer bceId) {
            getObject().setBceId(bceId);
            return thisBuilder();
        }

        /**
         * Has associated BC Services credentials.
         *
         * @param bcServices Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withBcServices(final Integer bcServices) {
            getObject().setBcServices(bcServices);
            return thisBuilder();
        }

    }
}

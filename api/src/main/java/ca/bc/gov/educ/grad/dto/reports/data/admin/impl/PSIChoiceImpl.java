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
 *  File:                $Id:: PSIChoiceImpl.java 6188 2017-02-16 17:00:12Z D#$
 *  Date of Last Commit: $Date:: 2017-02-16 09:00:12 -0800 (Thu, 16 Feb 2017) $
 *  Revision Number:     $Rev:: 6188                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports.data.admin.impl;

import ca.bc.gov.educ.grad.dto.reports.data.BusinessEntity;

import java.util.Date;

/**
 * Represents information placed on a report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PSIChoiceImpl extends BusinessEntity {

    private static final long serialVersionUID = 10L;

    private Date transmitted;
    private String name;
    private String code;
    private String transmission;
    private Integer tally;

    /**
     * Default (empty) constructor.
     */
    public PSIChoiceImpl() {
    }

    /**
     * Changes the value for transmitted.
     *
     * @param transmitted Used to group the PSI Choices by month.
     */
    public void setTransmitted(final Date transmitted) {
        assert transmitted != null;
        this.transmitted = new Date(transmitted.getTime());
    }

    /**
     * Returns Used to group the PSI Choices by month.
     *
     * @return A non-null value.
     */
    public Date getTransmitted() {
        return nullSafe(this.transmitted);
    }

    /**
     * Changes the value for name.
     *
     * @param name Name of the Post-Secondary Institution (PSI).
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns Name of the Post-Secondary Institution (PSI).
     *
     * @return A non-null value.
     */
    public String getName() {
        return nullSafe(this.name);
    }

    /**
     * Changes the value for code.
     *
     * @param code PSI code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns PSI code.
     *
     * @return A non-null value.
     */
    public String getCode() {
        return nullSafe(this.code);
    }

    /**
     * Changes the value for transmission.
     *
     * @param transmission How the transcripts were transmitted.
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Returns How the transcripts were transmitted.
     *
     * @return A non-null value.
     */
    public String getTransmission() {
        return nullSafe(this.transmission);
    }

    /**
     * Changes the value for tally.
     *
     * @param tally Total number of transcripts sent to the PSI.
     */
    public void setTally(Integer tally) {
        this.tally = tally;
    }

    /**
     * Returns Total number of transcripts sent to the PSI.
     *
     * @return A non-null value.
     */
    public Integer getTally() {
        return nullSafe(this.tally);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<PSIChoiceImpl, Builder> {

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
         * @return New PSIChoiceImpl instance.
         */
        @Override
        protected PSIChoiceImpl createObject() {
            return new PSIChoiceImpl();
        }

        /**
         * Used to group the PSI Choices by month.
         *
         * @param transmitted Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withTransmitted(final Date transmitted) {
            getObject().setTransmitted(transmitted);
            return thisBuilder();
        }

        /**
         * Name of the Post-Secondary Institution (PSI).
         *
         * @param name Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withName(final String name) {
            getObject().setName(name);
            return thisBuilder();
        }

        /**
         * PSI code.
         *
         * @param code Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withCode(final String code) {
            getObject().setCode(code);
            return thisBuilder();
        }

        /**
         * How the transcripts were transmitted.
         *
         * @param transmission Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withTransmission(final String transmission) {
            getObject().setTransmission(transmission);
            return thisBuilder();
        }

        /**
         * Total number of transcripts sent to the PSI.
         *
         * @param tally Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withTally(final Integer tally) {
            getObject().setTally(tally);
            return thisBuilder();
        }
    }
}

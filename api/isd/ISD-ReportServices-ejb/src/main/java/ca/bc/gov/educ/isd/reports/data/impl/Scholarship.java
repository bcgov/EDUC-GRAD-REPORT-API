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
package ca.bc.gov.educ.isd.reports.data.impl;

import ca.bc.gov.educ.isd.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

/**
 * Represents information about a single scholarship or award.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Scholarship extends BusinessEntity {

    private static final long serialVersionUID = 3387782870869480429L;

    /**
     * Award or scholarship name.
     */
    private String name;

    /**
     * Monetary value of the award or scholarship.
     */
    private Integer amount;

    /**
     * Date when the award or scholarship is set to expire.
     */
    private Date expiry;

    /**
     * Indicates whether the Student has cashed the award or scholarship.
     */
    private String redeemed;

    /**
     * Scholarship type code.
     */
    private String code;

    /**
     * Year the scholarship was awarded.
     */
    private Date yearAwarded;

    /**
     * Default (empty) constructor.
     */
    public Scholarship() {
    }

    /**
     * Returns the award or scholarship name. The calling client must format
     * this as required. This should return the name in Title Case, but that is
     * dependent upon the system requirements.
     *
     * @return The award or scholarship name, or an empty string, never null.
     */
    public String getName() {
        return this.name == null ? "" : this.name;
    }

    /**
     * Returns the amount of the award rounded to the nearest dollar.
     *
     * @return A dollar amount or 0, never null.
     */
    public Integer getAmount() {
        Integer result = this.amount;

        if (result == null) {
            result = 0;
        }

        return result;
    }

    /**
     * Returns the scholarship's or award's expiry date.
     *
     * @return The expiry date, or Jan 1, 1900, never null.
     */
    public Date getExpiry() {
        return this.expiry == null ? new Date() : new Date(this.expiry.getTime());
    }

    /**
     * Answers whether the scholarship or award has been redeemed. The calling
     * client must format this value as required.
     *
     * @return Boolean.FALSE by default.
     */
    public String getRedeemed() {
        return nullSafe(this.redeemed);
    }

    /**
     * Returns the scholarship type code.
     *
     * @return "A", "D", "S", or "T".
     */
    public String getCode() {
        return nullSafe(this.code);
    }

    /**
     * Returns the date when the scholarship was awarded.
     *
     * @return format YYYY
     */
    public Date getYearAwarded() {
        return nullSafe(this.yearAwarded);
    }

    /**
     * Changes the name of the award or scholarship. Null safe.
     *
     * @param name The new award or scholarship name.
     */
    protected void setName(final String name) {
        if (name != null) {
            this.name = name;
        }
    }

    /**
     * Changes the dollar amount offered by this award or scholarship.
     *
     * @param amount The monetary value associated with the award or
     * scholarship.
     */
    protected void setAmount(final Integer amount) {
        if (amount != null) {
            this.amount = amount;
        }
    }

    /**
     * Changes the ending date for when the award or scholarship must be claimed
     * by.
     *
     * @param expiry Last day that the award or scholarship can be claimed.
     */
    protected void setExpiry(final Date expiry) {
        if (expiry != null) {
            this.expiry = expiry;
        }
    }

    /**
     * Answers whether the award or scholarship has been claimed by the student.
     *
     * @param redeemed "Y", "N", or "N/A".
     */
    protected void setRedeemed(final String redeemed) {
        if (redeemed != null) {
            this.redeemed = redeemed;
        }
    }

    /**
     * Answers whether the award or scholarship has been claimed by the student.
     * If redeemed is null, this will set the status to "N".
     *
     * @param redeemed true - The moneys have been acquired.
     */
    protected void setRedeemed(Boolean redeemed) {
        if (redeemed == null) {
            redeemed = Boolean.FALSE;
        }

        setRedeemed(redeemed ? "Y" : "N");
    }

    /**
     * Sets the scholarship type code.
     *
     * @param code "A", "D", "S", or "T".
     */
    protected void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the award year.
     *
     * @param yearAwarded Year the scholarship was given to the student.
     */
    protected void setYearAwarded(Date yearAwarded) {
        this.yearAwarded = yearAwarded;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<Scholarship, Builder> {

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
         * @return New Scholarship instance.
         */
        @Override
        protected Scholarship createObject() {
            return new Scholarship();
        }

        /**
         * Sets the name of the award or scholarship.
         *
         * @param name Award or scholarship name.
         * @return thisBuilder
         */
        public Builder withName(final String name) {
            getObject().setName(name);
            return thisBuilder();
        }

        /**
         * Sets the monetary amount of the award or scholarship.
         *
         * @param amount Monetary amount of the award or scholarship name.
         * @return thisBuilder
         */
        public Builder withAmount(final Integer amount) {
            getObject().setAmount(amount);
            return thisBuilder();
        }

        /**
         * Sets when the award or scholarship is due to expire.
         *
         * @param expiry Last day the award or scholarship can be claimed.
         * @return thisBuilder
         */
        public Builder withExpiry(final Date expiry) {
            getObject().setExpiry(expiry);
            return thisBuilder();
        }

        /**
         * Convenience method to indicate whether the scholarship or award has
         * been cashed.
         *
         * @param redeemed true - Use "Y" on the report; otherwise "N".
         * @return thisBuilder
         */
        public Builder withRedeemed(final Boolean redeemed) {
            getObject().setRedeemed(redeemed);
            return thisBuilder();
        }

        /**
         * Convenience method to indicate whether the scholarship or award has
         * been cashed.
         *
         * @param redeemed "Y", "N", or "N/A".
         * @return thisBuilder
         */
        public Builder withRedeemed(final String redeemed) {
            getObject().setRedeemed(redeemed);
            return thisBuilder();
        }

        /**
         * Sets the scholarship type code.
         *
         * @param code "A", "D", "S", or "T".
         * @return thisBuilder
         */
        public Builder withCode(final String code) {
            getObject().setCode(code);
            return thisBuilder();
        }

        /**
         * Sets the scholarship type code.
         *
         * @param yearAwarded Year the award was given to the student.
         * @return thisBuilder
         */
        public Builder withYearAwarded(final Date yearAwarded) {
            getObject().setYearAwarded(yearAwarded);
            return thisBuilder();
        }
    }
}

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
 *  File:                $Id:: PackingSlipDetails.java 6093 2017-02-08 20:41:5#$
 *  Date of Last Commit: $Date:: 2017-02-08 12:41:58 -0800 (Wed, 08 Feb 2017)  $
 *  Revision Number:     $Rev:: 6093                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;
import ca.bc.gov.educ.grad.report.model.reports.DestinationType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Represents information required to produce a packing slip. This will product
 * a packing slip for transcripts by default. Use setOrderType to change the
 * packing slip for certificates.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class PackingSlipDetails extends BusinessEntity {

    private static final long serialVersionUID = 3L;

    public static final String TRANSCRIPTS = "Transcripts";
    public static final String CERTIFICATES = "Certificates";

    private final static String DEFAULT_ORDER_TYPE = TRANSCRIPTS;

    /**
     * The document number, one per order. For the first release of STs this
     * value should always be "000001".
     */
    private final static String SEQUENCE_NUMBER = "000001";

    /**
     * e.g., "Bartholomew Featherstonehaugh"
     */
    private String recipient;

    /**
     * e.g., "ADMINISTRATION"
     */
    private String attentionTo = "";

    /**
     * Postal mail delivery address.
     */
    private PostalAddress address;

    /**
     * Student's TRAX name.
     *
     * e.g., "Constantine Featherstonehaugh"
     */
    private String orderedByName;

    /**
     * Number of certificates or transcript sent.
     */
    private Integer documentsShipped;

    /**
     * Number of slips sent.
     */
    private Integer currentSlip;

    /**
     * Number of certificates or transcript sent.
     */
    private Integer totalSlips;

    /**
     * The STs order number.
     */
    private String orderNumber;

    /**
     * Date the order was placed.
     */
    private Date orderDate;

    /**
     * The Paper Type Code.
     */
    private String paperTypeCode;

    /**
     * Transcript or Certificates have minor variations on packing slip.
     * Transcript by default (not null to ensure valid data is passed into the
     * report).
     */
    private String orderTypeName = DEFAULT_ORDER_TYPE;

    /**
     * Packing slips that are destined for post-secondary institutions do not
     * show ordered by or order number values. Setting this value to "PSI" will
     * prevent those fields from being displayed on the packing slip.
     */
    private DestinationType destinationType;

    /**
     * Used by the builder to create an instance of this class without first
     * assigning a postal address.
     */
    protected PackingSlipDetails() {
    }

    /**
     * All packing slips must be directed somewhere.
     *
     * @param address The destination for the package labeled by this packing
     * slip.
     */
    public PackingSlipDetails(PostalAddress address) {
        setAddress(address);
    }

    /**
     * Returns the name of the student who has transcript and certificate
     * records that comprise the package to which this packing slip applies.
     *
     * @return A non-null String, possibly empty.
     */
    public String getRecipient() {
        return nullSafe(this.recipient);
    }

    /**
     * Returns attention to
     *
     * @return A non-null String, possibly empty.
     */
    public String getAttentionTo() {
        return attentionTo;
    }

    public void setAttentionTo(String attentionTo) {
        this.attentionTo = attentionTo;
    }

    /**
     * Returns paper type code
     *
     * @return A non-null String, possibly empty.
     */
    public String getPaperTypeCode() {
        return paperTypeCode;
    }

    public void setPaperTypeCode(String paperTypeCode) {
        this.paperTypeCode = paperTypeCode;
    }

    /**
     * Returns the delivery address for mailing the transcripts and
     * certificates.
     *
     * @return A mailing address instance suitable for directing postal mail,
     * never null.
     */
    public PostalAddress getAddress() {
        return this.address;
    }

    public String getCompositeOrderNumber() {
        return "PS_ID " + StringUtils.substringBefore(nullSafe(this.recipient), " ") + " " + getOrderNumber() + "\n" + nullSafe(this.recipient);
    }

    /**
     * Returns the name of the person who ordered the transcripts and
     * certificates on behalf of the student whose records are sought.
     *
     * @return A non-null String, possibly empty.
     */
    public String getOrderedByName() {
        return nullSafe(this.orderedByName);
    }

    /**
     * Returns the number of documents included in the package.
     *
     * @return A whole number, never null.
     */
    public Integer getDocumentsShipped() {
        return nullSafe(this.documentsShipped);
    }

    /**
     * Returns the STs order number.
     *
     * @return A non-null String, possibly empty.
     */
    public String getOrderNumber() {
        return nullSafe(this.orderNumber);
    }

    /**
     * Indicates whether this packing slip is for a certificate or transcript.
     *
     * @return "Certificates" or "Transcripts", never null, never empty.
     */
    public String getOrderTypeName() {
        final String s = nullSafe(this.orderTypeName);
        return s.isEmpty() ? DEFAULT_ORDER_TYPE : s;
    }

    /**
     * Indicates whether this packing slip is destined for a post-secondary
     * institution. This may return null, but should return an empty string if
     * not set.
     *
     * @return "PSI" or empty.
     */
    public DestinationType getDestinationType() {
        return this.destinationType;
    }

    /**
     * Returns name of the destination type
     *
     * @return "PSI" or empty.
     */
    public String getDestinationTypeName() {
        return this.destinationType == null ? "" : nullSafe(this.destinationType.name());
    }

    /**
     * Returns the STs sequence number.
     *
     * @return PackingSlipDetails.SEQUENCE_NUMBER
     */
    public String getSequenceNumber() {
        return SEQUENCE_NUMBER;
    }

    /**
     * Gets current slip.
     *
     * @return the current slip
     */
    public Integer getCurrentSlip() {
        return currentSlip;
    }

    /**
     * Sets current slip.
     *
     * @param currentSlip the current slip
     */
    public void setCurrentSlip(Integer currentSlip) {
        this.currentSlip = currentSlip;
    }

    /**
     * Sets whether this packing slip is for a certificate or transcript. If
     * orderType is null, this will default to TRANSCRIPT.
     *
     * @param orderTypeName "Certificates" or "Transcripts".
     */
    public void setOrderTypeName(String orderTypeName) {
        if (orderTypeName == null || orderTypeName.isEmpty()) {
            orderTypeName = TRANSCRIPTS;
        }

        this.orderTypeName = orderTypeName;
    }

    /**
     * Sets whether this packing slip is destined for a post-secondary
     * institution.
     *
     * @param destinationType "PSI", empty, or null.
     */
    public void setDestinationType(final DestinationType destinationType) {
        this.destinationType = destinationType;
    }

    /**
     * Returns the date the packing slip was transmitted to the postal mail
     * handler. The transmission date should not be null when constructing an
     * instance of this class.
     *
     * @return The transmission date or the current date in the event that the
     * transmission date is null. This will never return null.
     */
    public Date getOrderDate() {
        return nullSafe(this.orderDate);
    }

    /**
     * Changes the student records name for the packing slip.
     *
     * @param recipient Populated by the builder.
     */
    private void setRecipient(final String recipient) {
        this.recipient = recipient;
    }

    /**
     * Changes the mailing address for the packing slip.
     *
     * @param address Populated by the builder or corresponding constructor.
     */
    private void setAddress(final PostalAddress address) {
        this.address = address;
    }

    /**
     * Changes the ordered by name for the packing slip.
     *
     * @param orderedByName Populated by the builder.
     */
    private void setOrderedByName(final String orderedByName) {
        this.orderedByName = orderedByName;
    }

    /**
     * Sets the number of documents included in the package.
     *
     * @param documentsShipped Populated by the builder.
     */
    private void setDocumentsShipped(final Integer documentsShipped) {
        this.documentsShipped = documentsShipped;
    }

    /**
     * Sets the date the print materials were ordered.
     *
     * @param orderDate Populated by the builder.
     */
    private void setOrderDate(final Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Sets the Student Transcripts services (STs) order number.
     *
     * @param orderNumber The STs order number.
     */
    private void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getTotalSlips() {
        return totalSlips;
    }

    public void setTotalSlips(Integer totalSlips) {
        this.totalSlips = totalSlips;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<PackingSlipDetails, Builder> {

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
         * @return New PackingSlipDetails instance.
         */
        @Override
        protected PackingSlipDetails createObject() {
            return new PackingSlipDetails();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param recipient Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withRecipient(final String recipient) {
            getObject().setRecipient(recipient);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param attentionTo Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withAttentionTo(final String attentionTo) {
            getObject().setAttentionTo(attentionTo);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param paperTypeCode Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withPaperTypeCode(final String paperTypeCode) {
            getObject().setPaperTypeCode(paperTypeCode);
            return thisBuilder();
        }


        /**
         * Delegates to the outer instance.
         *
         * @param address Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withAddress(final PostalAddress address) {
            getObject().setAddress(address);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param orderedByName Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withOrderedByName(final String orderedByName) {
            getObject().setOrderedByName(orderedByName);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param documentsShipped Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withDocumentsShipped(final Integer documentsShipped) {
            getObject().setDocumentsShipped(documentsShipped);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param currentSlip Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withCurrentSlip(final Integer currentSlip) {
            getObject().setCurrentSlip(currentSlip);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param totalSlips Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withTotalSlips(final Integer totalSlips) {
            getObject().setTotalSlips(totalSlips);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param orderDate Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withOrderDate(final Date orderDate) {
            getObject().setOrderDate(orderDate);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param orderNumber Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withOrderNumber(final String orderNumber) {
            getObject().setOrderNumber(orderNumber);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param orderTypeName Passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withOrderTypeName(final String orderTypeName) {
            getObject().setOrderTypeName(orderTypeName);
            return thisBuilder();
        }

        /**
         * Delegates to the outer instance.
         *
         * @param dt String value is passed to the outer instance.
         * @return thisBuilder
         */
        public Builder withDestinationType(final DestinationType dt) {
            if (dt != null) {
                getObject().setDestinationType(dt);
            }

            return thisBuilder();
        }
    }
}

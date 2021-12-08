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
 *  File:                $Id:: SelfServeOrderImpl.java 7157 2017-05-17 20:04:#$
 *  Date of Last Commit: $Date:: 2017-05-17 13:04:54 -0700 (Wed, 17 May 2017) $
 *  Revision Number:     $Rev:: 7157                                          $
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
public class SelfServeOrderImpl extends BusinessEntity {

    private static final long serialVersionUID = 10L;

    private Date ordered;
    private Long order;
    private String status;
    private Boolean transcript;
    private Boolean certificate;
    private String delivery;
    private String payment;
    private Double cost;

    /**
     * Default (empty) constructor.
     */
    public SelfServeOrderImpl() {
    }

    /**
     * Changes the value for ordered.
     *
     * @param ordered Date and time the order was placed.
     */
    public void setOrdered(final Date ordered) {
        assert ordered != null;
        this.ordered = new Date(ordered.getTime());
    }

    /**
     * Returns Date and time the order was placed.
     *
     * @return A non-null value.
     */
    public Date getOrdered() {
        return nullSafe(this.ordered);
    }

    /**
     * Changes the value for order.
     *
     * @param order Order number.
     */
    public void setOrder(final Long order) {
        this.order = order;
    }

    /**
     * Returns Order number.
     *
     * @return A non-null value.
     */
    public Long getOrder() {
        return nullSafe(this.order);
    }

    /**
     * Changes the value for status.
     *
     * @param status Order status when queued.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Returns Order status when queued.
     *
     * @return A non-null value.
     */
    public String getStatus() {
        return nullSafe(this.status);
    }

    /**
     * Changes the value for transcript.
     *
     * @param transcript True if a transcript was ordered.
     */
    public void setTranscript(final Boolean transcript) {
        this.transcript = transcript;
    }

    /**
     * Returns True if a transcript was ordered.
     *
     * @return A non-null value.
     */
    public Boolean getTranscript() {
        return nullSafe(this.transcript);
    }

    /**
     * Changes the value for certificate.
     *
     * @param certificate True if a certificate was ordered.
     */
    public void setCertificate(final Boolean certificate) {
        this.certificate = certificate;
    }

    /**
     * Returns True if a certificate was ordered.
     *
     * @return A non-null value.
     */
    public Boolean getCertificate() {
        return nullSafe(this.certificate);
    }

    /**
     * Changes the value for delivery.
     *
     * @param delivery Mechanism used to send the report.
     */
    public void setDelivery(final String delivery) {
        this.delivery = delivery;
    }

    /**
     * Returns Mechanism used to send the report.
     *
     * @return A non-null value.
     */
    public String getDelivery() {
        return nullSafe(this.delivery);
    }

    /**
     * Changes the value for payment.
     *
     * @param payment Payment type.
     */
    public void setPayment(final String payment) {
        this.payment = payment;
    }

    /**
     * Returns Payment type.
     *
     * @return A non-null value.
     */
    public String getPayment() {
        return nullSafe(this.payment);
    }

    /**
     * Changes the value for cost.
     *
     * @param cost Total cost for the order.
     */
    public void setCost(final Double cost) {
        this.cost = cost;
    }

    /**
     * Returns Total cost for the order.
     *
     * @return A non-null value.
     */
    public Double getCost() {
        return nullSafe(this.cost);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<SelfServeOrderImpl, Builder> {

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
         * @return New SelfServeOrderImpl instance.
         */
        @Override
        protected SelfServeOrderImpl createObject() {
            return new SelfServeOrderImpl();
        }

        /**
         * Date and time the order was placed.
         *
         * @param ordered Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withOrdered(final Date ordered) {
            getObject().setOrdered(ordered);
            return thisBuilder();
        }

        /**
         * Order number.
         *
         * @param order Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withOrder(final Long order) {
            getObject().setOrder(order);
            return thisBuilder();
        }

        /**
         * Order status when queued.
         *
         * @param status Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withStatus(final String status) {
            getObject().setStatus(status);
            return thisBuilder();
        }

        /**
         * True if a transcript was ordered.
         *
         * @param transcript Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withTranscript(final Boolean transcript) {
            getObject().setTranscript(transcript);
            return thisBuilder();
        }

        /**
         * True if a certificate was ordered.
         *
         * @param certificate Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withCertificate(final Boolean certificate) {
            getObject().setCertificate(certificate);
            return thisBuilder();
        }

        /**
         * Mechanism used to send the report.
         *
         * @param delivery Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withDelivery(final String delivery) {
            getObject().setDelivery(delivery);
            return thisBuilder();
        }

        /**
         * Payment type.
         *
         * @param payment Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withPayment(final String payment) {
            getObject().setPayment(payment);
            return thisBuilder();
        }

        /**
         * Total cost for the order.
         *
         * @param cost Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withCost(final Double cost) {
            getObject().setCost(cost);
            return thisBuilder();
        }

    }
}

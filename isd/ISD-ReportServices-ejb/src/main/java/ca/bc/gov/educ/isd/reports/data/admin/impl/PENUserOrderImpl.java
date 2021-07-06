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
 *  File:                $Id:: PENUserOrderImpl.java 6887 2017-04-19 22:28:51#$
 *  Date of Last Commit: $Date:: 2017-04-19 15:28:51 -0700 (Wed, 19 Apr 2017) $
 *  Revision Number:     $Rev:: 6887                                          $
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
public class PENUserOrderImpl extends BusinessEntity {

    private static final long serialVersionUID = 10L;

    private Date ordered;
    private String delivery;
    private Integer orders;
    private Double cost;

    /**
     * Default (empty) constructor.
     */
    public PENUserOrderImpl() {
    }

    /**
     * Changes the value for ordered.
     *
     * @param ordered Used to group the orders by month.
     */
    public void setOrdered(final Date ordered) {
        assert ordered != null;
        this.ordered = new Date(ordered.getTime());
    }

    /**
     * Returns grouping for orders by month.
     *
     * @return A non-null value.
     */
    public Date getOrdered() {
        return nullSafe(this.ordered);
    }

    /**
     * Changes the value for delivery.
     *
     * @param delivery Delivery mechanism used to send the report.
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * Returns delivery mechanism used to send the report.
     *
     * @return A non-null value.
     */
    public String getDelivery() {
        return nullSafe(this.delivery);
    }

    /**
     * Changes the value for orders.
     *
     * @param orders Total number of orders using the delivery mechanism.
     */
    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    /**
     * Returns total number of orders using the delivery mechanism.
     *
     * @return A non-null value.
     */
    public Integer getOrders() {
        return nullSafe(this.orders);
    }

    /**
     * Changes the value for cost.
     *
     * @param cost Total costs for the total orders placed.
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * Returns total costs for the total orders placed.
     *
     * @return A non-null value.
     */
    public Double getCost() {
        return nullSafe(this.cost);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<PENUserOrderImpl, Builder> {

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
         * @return New PENUserOrderImpl instance.
         */
        @Override
        protected PENUserOrderImpl createObject() {
            return new PENUserOrderImpl();
        }

        /**
         * Used to group the orders by month.
         *
         * @param ordered Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withOrdered(final Date ordered) {
            getObject().setOrdered(ordered);
            return thisBuilder();
        }

        /**
         * Delivery mechanism used to send the report.
         *
         * @param delivery Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withDelivery(final String delivery) {
            getObject().setDelivery(delivery);
            return thisBuilder();
        }

        /**
         * Total number of orders using the delivery mechanism.
         *
         * @param orders Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public Builder withOrders(final Integer orders) {
            getObject().setOrders(orders);
            return thisBuilder();
        }

        /**
         * Total costs for the total orders placed.
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

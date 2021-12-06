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
 *  File:                $Id:: NonPENUserOrderImpl.java 5676 2016-12-15 21:25#$
 *  Date of Last Commit: $Date:: 2016-12-15 13:25:30 -0800 (Thu, 15 Dec 2016) $
 *  Revision Number:     $Rev:: 5676                                          $
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
public class NonPENUserOrderImpl extends BusinessEntity {

    private static final long serialVersionUID = 10L;

    private Date ordered;
    private String delivery;
    private Integer orders;
    private Double cost;

    /**
     * Default (empty) constructor.
     */
    public NonPENUserOrderImpl() {
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
     * Returns Used to group the orders by month.
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
     * Returns Delivery mechanism used to send the report.
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
     * Returns Total number of orders using the delivery mechanism.
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
     * Returns Total costs for the total orders placed.
     *
     * @return A non-null value.
     */
    public Double getCost() {
        return nullSafe(this.cost);
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<NonPENUserOrderImpl, Builder> {

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
         * @return New NonPENUserOrderImpl instance.
         */
        @Override
        protected NonPENUserOrderImpl createObject() {
            return new NonPENUserOrderImpl();
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

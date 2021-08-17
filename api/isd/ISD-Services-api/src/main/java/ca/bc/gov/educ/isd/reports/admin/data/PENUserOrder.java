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
 *  File:                $Id:: PENUserOrder.java 7192 2017-05-23 16:06:12Z DA#$
 *  Date of Last Commit: $Date:: 2017-05-23 09:06:12 -0700 (Tue, 23 May 2017) $
 *  Revision Number:     $Rev:: 7192                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.admin.data;

import java.util.Date;

/**
 * Defines the accessors for admin report data.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PENUserOrder extends AdminReportDAO {

    /**
     * Returns the value for ordered.
     *
     * @return Used to group the orders by month.
     */
    public Date getOrdered();

    /**
     * Sets the value for ordered.
     *
     * @param ordered Used to group the orders by month.
     */
    void setOrdered(Date ordered);

    /**
     * Returns the value for delivery.
     *
     * @return Delivery mechanism used to send the report.
     */
    String getDelivery();

    /**
     * Sets the value for delivery.
     *
     * @param delivery Delivery mechanism used to send the report.
     */
    void setDelivery(String delivery);

    /**
     * Returns the value for orders.
     *
     * @return Total number of orders using the delivery mechanism.
     */
    public Integer getOrders();

    /**
     * Sets the value for orders.
     *
     * @param orders Total number of orders using the delivery mechanism.
     */
    void setOrders(Integer orders);

    /**
     * Returns the value for cost.
     *
     * FIXME: Use BigDecimal.
     *
     * @return Total costs for the total orders placed.
     */
    public Double getCost();

    /**
     * Sets the value for cost.
     *
     * FIXME: Use BigDecimal.
     *
     * @param cost Total costs for the total orders placed.
     */
    void setCost(Double cost);
}

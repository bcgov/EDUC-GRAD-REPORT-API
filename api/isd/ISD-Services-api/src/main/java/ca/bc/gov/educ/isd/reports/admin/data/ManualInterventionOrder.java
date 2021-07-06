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
 *  File:                $Id:: ManualInterventionOrder.java 7208 2017-05-23 2#$
 *  Date of Last Commit: $Date:: 2017-05-23 16:25:16 -0700 (Tue, 23 May 2017) $
 *  Revision Number:     $Rev:: 7208                                          $
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
public interface ManualInterventionOrder extends AdminReportDAO, Comparable {

    /**
     * Returns the value for ordered.
     *
     * @return Date and time the order was placed.
     */
    public Date getOrdered();

    /**
     * Sets the value for ordered.
     *
     * @param ordered Date and time the order was placed.
     */
    void setOrdered(Date ordered);

    /**
     * Returns the value for order.
     *
     * @return Order number.
     */
    public Long getOrder();

    /**
     * Sets the value for order.
     *
     * @param order Order number.
     */
    public void setOrder(Long order);

    /**
     * Returns the value for status.
     *
     * @return Order status when queued.
     */
    String getStatus();

    /**
     * Sets the value for status.
     *
     * @param status Order status when queued.
     */
    void setStatus(String status);

    /**
     * Returns the value for transcript.
     *
     * @return True if a transcript was ordered.
     */
    public Boolean getTranscript();

    /**
     * Sets the value for transcript.
     *
     * @param transcript True if a transcript was ordered.
     */
    void setTranscript(Boolean transcript);

    /**
     * Returns the value for certificate.
     *
     * @return True if a certificate was ordered.
     */
    public Boolean getCertificate();

    /**
     * Sets the value for certificate.
     *
     * @param certificate True if a certificate was ordered.
     */
    void setCertificate(Boolean certificate);

    /**
     * Returns the value for delivery.
     *
     * @return Mechanism used to send the report.
     */
    String getDelivery();

    /**
     * Sets the value for delivery.
     *
     * @param delivery Mechanism used to send the report.
     */
    void setDelivery(String delivery);

    /**
     * Returns the value for payment.
     *
     * @return Payment type.
     */
    String getPayment();

    /**
     * Sets the value for payment.
     *
     * @param payment Payment type.
     */
    void setPayment(String payment);

    /**
     * Returns the value for cost.
     *
     * FIXME: Use BigDecimal.
     *
     * @return Total cost for the order.
     */
    public Double getCost();

    /**
     * Sets the value for cost.
     *
     * FIXME: Use BigDecimal.
     *
     * @param cost Total cost for the order.
     */
    void setCost(Double cost);

    /**
     * Returns the value for queued.
     *
     * @return Preformatted time (dd:hh:mm) that the order has spent in the
     * processing queue.
     */
    String getQueued();

    /**
     * Sets the value for queued.
     *
     * @param queued Preformatted time (dd:hh:mm) that the order has spent in
     * the processing queue.
     */
    void setQueued(String queued);
}

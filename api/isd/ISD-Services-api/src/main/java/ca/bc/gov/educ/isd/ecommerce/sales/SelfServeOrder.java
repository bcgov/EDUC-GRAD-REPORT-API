/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * FIXME: Merge this and SelfServeOrder in the reports.admin.data package.
 *
 * Representation of a self serve order item to be added to a self serve order
 * report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SelfServeOrder extends DomainEntity {

    public Long getSalesOrderId();

    /**
     * Provide the overall state of the order.
     *
     * @return
     */
    public String getOrderStatus();

    /**
     * Returns the payment sequence number passed along to the payment merchant
     * so that the transaction status for a given order can be retrieved.
     *
     * @return The payment sequence number.
     */
    public Long getOrderNumber();

    /**
     * Date that the order was place by the client.
     *
     * @return
     */
    public Date getOrderDate();

    /**
     *
     * @return true if the order has at least one transcript.
     */
    public Boolean hasTranscripts();

    /**
     *
     * @return true if the order has at least one certificate.
     */
    public Boolean hasCertificates();

    /**
     * Provides a CSV String of delivery methods.
     *
     * @return A comma separated value String where each value represents the
     * type of delivery method with the total of documents delivered with that
     * specific delivery method.
     *
     * Ex: DOWNLOAD(1),POSTAL_MAIL(1),PSI_PREF(1)
     */
    public String getDeliveryMethods();

    /**
     * Type of card use for settling the transaction.
     *
     * @return
     */
    public String getCardType();

    /**
     * Total cost of the order.
     *
     * @return
     */
    public BigDecimal getTotal();

}

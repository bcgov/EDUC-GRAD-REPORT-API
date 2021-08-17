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
 *  File:                $Id:: SalesOrder.java 11976 2019-06-17 16:47:00Z cfun#$
 *  Date of Last Commit: $Date:: 2019-06-17 09:47:00 -0700 (Mon, 17 Jun 2019)  $
 *  Revision Number:     $Rev:: 11976                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents an item purchased by a user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SalesOrder extends DomainEntity {

    /**
     * Returns the payment sequence number passed along to the payment merchant
     * so that the transaction status for a given order can be retrieved.
     *
     * @return The payment sequence number.
     */
    Long getOrderNumber();

    List<SalesOrderItem> getSalesOrderItems();

    /**
     * Total amount owing for the order.
     *
     * @return
     */
    BigDecimal getTotalAmount();

    Date getPiConsent();

    Date getOrderDate();

    SalesOrderStatusEnum getStatus();

    String getUserEntityId();

    PaymentMethodEnum getPaymentMethod();

    /**
     * Amount paid for the order.
     *
     * @return
     */
    BigDecimal getPaymentAmount();

    List<PaymentStatus> getPaymentStatus();

    /**
     * Get a list of the recipients in the order. Note that this is a transient
     * list that is generated from the SalesOrderItem s, so adding or removing
     * from the list will not have any effect on the order.
     *
     * @return
     */
    List<SaleOrderRecipient> getRecipients();
    
    String getHttpSessionId();
    
    void setHttpSessionId(String httpSessionId);
}

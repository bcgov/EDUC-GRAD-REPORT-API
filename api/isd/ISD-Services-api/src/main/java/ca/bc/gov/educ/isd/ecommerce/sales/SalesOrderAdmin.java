
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                     $
 *
 *
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatus;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface SalesOrderAdmin extends SalesOrder {

    void setSalesOrderItems(List<SalesOrderItem> items);

    void setOrderNumber(Long orderNumber);

    void setTotalAmount(BigDecimal totalAmount);

    void setPiConsent(Date piConsent);

    void setUserEntityId(String userEntityId);

    void setStatus(SalesOrderStatusEnum status);

    void setOrderDate(Date orderDate);

    void setPaymentStatus(List<PaymentStatus> statusList);

    void setPaymentMethod(PaymentMethodEnum paymentMethod);

    /**
     * Delegates to setPaymentAmount after converting from a String to a
     * BigDecimal.
     *
     * @param paymentAmount The amount of a purchase from the merchant.
     */
    void setPaymentAmount(String paymentAmount);

    void setPaymentAmount(BigDecimal paymentAmount);
}

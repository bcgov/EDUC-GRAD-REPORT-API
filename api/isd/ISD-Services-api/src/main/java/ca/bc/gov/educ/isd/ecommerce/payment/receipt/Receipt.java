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
package ca.bc.gov.educ.isd.ecommerce.payment.receipt;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.List;

/**
 * Represents a single receipt associated with a sales order. This includes the
 * payment information and indirectly the line items (via the sales order).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Receipt extends DomainEntity {

    /**
     * Transaction number, amount, purchase date, payment method, payment
     * status, etc.
     *
     * @return Details about the payment that are added to the receipt.
     */
    Payment getPayment();

    /**
     * Returns a list of payment line items that directly correspond to the list
     * of sales order items for a particular sales order.
     *
     * @return A lightweight sales order item.
     */
    List<PaymentLineItem> getLineItems();
}

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
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Payment;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentAdmin;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItem;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItemAdmin;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentMethod;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentStatus;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Receipt;
import java.util.List;

/**
 * Allows the creation of instances that can be filled using sales order data.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ReportAdminService extends BusinessService {

    /**
     * Returns a single receipt object for a payment and list of line items.
     *
     * @param payment The payment, adapted from a sales order.
     * @param lineItems The line items, adapted from a list of sales order items
     * associated with the sales order.
     * @return A non-null instance.
     */
    Receipt createReceipt(
            final Payment payment,
            final List<PaymentLineItem> lineItems);

    /**
     * Creates a new payment instance that represents information about when a
     * payment was made regarding a particular sales order. The caller must
     * populate the instance returned from this method with data.
     *
     * @return A non-null instance, not populated with data.
     */
    PaymentAdmin createPayment();

    /**
     * Creates a new payment line item instance that represents information for
     * a particular sales order item. The caller must populate the instance
     * returned from this method with data.
     *
     * @return A non-null instance, not populated with data.
     */
    PaymentLineItemAdmin createPaymentLineItemAdmin();

    /**
     * Creates a new payment method that represents how a sales order was paid.
     * The caller must populate the instance returned from this method with
     * data.
     *
     * @param code The payment method unique code.
     * @param description The human-readable text for the given code.
     * @return A non-null instance, not populated with data.
     */
    PaymentMethod createPaymentMethod(String code, String description);

    /**
     * Creates a new payment method that represents whether the transaction for
     * an associated payment method was successful. The caller must populate the
     * instance returned from this method with data.
     *
     * @param code The payment status unique code.
     * @param description The human-readable text for the given code.
     * @return A non-null instance, not populated with data.
     */
    PaymentStatus createPaymentStatus(String code, String description);
}

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
package ca.bc.gov.educ.isd.reports.data.receipt.impl;

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Payment;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItem;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Receipt;
import ca.bc.gov.educ.isd.reports.impl.AbstractReportDomainEntity;

import java.util.List;

/**
 * Represents a single receipt associated with a sales order. This includes the
 * payment information and indirectly the line items (via the sales order).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ReceiptImpl extends AbstractReportDomainEntity implements Receipt {

    private Payment payment;
    private List<PaymentLineItem> lineItems;

    /**
     * Creates a new payment receipt based on the sales order data.
     *
     * @param lineItems Information about the sales order items.
     * @param payment Information about the payment (method, status, date,
     * etc.).
     */
    public ReceiptImpl(
            final Payment payment,
            final List<PaymentLineItem> lineItems) {
        setPayment(payment);
        setLineItems(lineItems);
    }

    /**
     * Transaction number, amount, purchase date, payment method, payment
     * status, etc.
     *
     * @return Details about the payment that are added to the receipt.
     */
    @Override
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Changes the payment information for this receipt.
     *
     * @param payment The payment information "header".
     */
    private void setPayment(final Payment payment) {
        this.payment = payment;
    }

    /**
     * Returns the list of lightweight sales order items (without a purchased
     * document).
     *
     * @return A set of sales order items without purchased document.
     */
    @Override
    public List<PaymentLineItem> getLineItems() {
        return this.lineItems;
    }

    /**
     * Changes the line items associated with the receipt.
     *
     * @param lineItems The payment information "body".
     */
    private void setLineItems(final List<PaymentLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}

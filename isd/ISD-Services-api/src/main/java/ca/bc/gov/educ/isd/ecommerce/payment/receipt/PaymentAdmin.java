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

import java.math.BigDecimal;
import java.util.Date;

/**
 * Provides the ability to populate a payment instance from an external data
 * source, commonly a sales order.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentAdmin extends Payment {

    /**
     * Sets the order number from the sales order
     * associated with this payment instance.
     *
     * @param orderNumber The sales order number identifier.
     */
    void setOrderNumber(Long orderNumber);

    /**
     * Sets the transaction number for a payment. This could be a credit card
     * processing validation number, a cheque number, or any other information
     * that identifies the payment. This number is not guaranteed to be unique
     * across different payments.
     *
     * @param referenceNumber A non-null, possibly empty, string to help
     * identify the payment transaction.
     */
    void setReferenceNumber(String referenceNumber);

    /**
     * Sets the date that the payment was made.
     *
     * @param date A non-null instance.
     */
    void setPurchased(Date date);

    /**
     * Sets the amount paid for this particular payment.
     *
     * @param amount A positive number, possibly zero, never null.
     */
    void setAmount(BigDecimal amount);

    /**
     * Sets what payment type was used for the transaction (credit card, cheque,
     * etc.).
     *
     * @param paymentMethod A non-null payment method code and description.
     */
    void setPaymentMethod(PaymentMethod paymentMethod);

    /**
     * Sets whether the payment cleared.
     *
     * @param paymentStatus A status to indicate approved, declined, NSF, etc.
     */
    void setPaymentStatus(PaymentStatus paymentStatus);
}

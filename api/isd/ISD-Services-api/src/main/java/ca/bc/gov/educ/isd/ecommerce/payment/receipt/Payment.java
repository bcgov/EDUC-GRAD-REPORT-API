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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents information about a payment transaction, including: when the
 * payment happened (purchase date), what method was used to make the payment
 * (credit card, debit card, cheque, wire, etc.), and the status of the payment
 * (approved, declined, insufficient funds [NSF], etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Payment extends DomainEntity {

    /**
     * Returns the order number from the sales order associated with this payment instance.
     *
     * @return The sales order number identifier.
     */
    Long getOrderNumber();

    /**
     * Returns the transaction number for a payment. This could be a credit card
     * processing validation number, a cheque number, or any other information
     * that identifies the payment. This number is not guaranteed to be unique
     * across different payments.
     *
     * @return A non-null, possibly empty, string to help identify the payment
     * transaction.
     */
    String getReferenceNumber();

    /**
     * Returns the date that the payment was made.
     *
     * @return A non-null instance.
     */
    Date getPurchased();

    /**
     * Returns the amount paid for this particular payment.
     *
     * @return A positive number, possibly zero, never null.
     */
    BigDecimal getAmount();

    /**
     * Returns what payment type was used for the transaction (credit card,
     * cheque, etc.).
     *
     * @return A non-null instance.
     */
    PaymentMethod getPaymentMethod();

    /**
     * Returns whether the payment cleared.
     *
     * @return A status to indicate approved, declined, NSF, etc.
     */
    PaymentStatus getPaymentStatus();

    /**
     * Answers whether this payment has valid transactional information,
     * suitable for displaying in a report.
     *
     * @return false Suppress transaction data from output.
     */
    Boolean hasTransaction();
}

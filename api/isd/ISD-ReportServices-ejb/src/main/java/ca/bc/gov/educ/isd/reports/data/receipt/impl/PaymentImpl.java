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
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentMethod;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentStatus;
import ca.bc.gov.educ.isd.reports.impl.AbstractReportDomainEntity;

import java.math.BigDecimal;
import java.util.Date;

import static java.math.BigDecimal.ZERO;

/**
 * Represents information about a payment transaction, including: when the
 * payment happened (purchase date), what method was used to make the payment
 * (credit card, debit card, cheque, wire, etc.), and the status of the payment
 * (approved, declined, insufficient funds [NSF], etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PaymentImpl extends AbstractReportDomainEntity implements Payment {

    private static final long serialVersionUID = 1L;

    private Long orderNumber;

    private String referenceNumber = "";
    private Date purchased = new Date();
    private BigDecimal amount = ZERO;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    /**
     * Use the builder to make a payment instance.
     */
    public PaymentImpl() {
    }

    /**
     * Returns true if a monetary transaction took place and the status of said
     * transaction is available.
     *
     * @return true A valid payment method was used for the transaction.
     */
    @Override
    public Boolean hasTransaction() {
        final PaymentStatus status = getPaymentStatus();
        final PaymentMethod method = getPaymentMethod();
        return status != null && method != null && method.isValid();
    }

    /**
     * Returns the order number for the sales order used to create this
     * instance.
     *
     * @return The sales order identifier.
     */
    @Override
    public Long getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Sets the order number for the sales order used to create this instance.
     *
     * @param orderNumber The sales order identifier.
     */
    public void setOrderNumber(final Long orderNumber) {
        this.orderNumber = nullSafe(orderNumber);
    }

    /**
     * Returns the reference number for a payment. This could be a credit card
     * processing validation number, a cheque number, or any other information
     * that identifies the payment. This number is not guaranteed to be unique
     * across different payments.
     *
     * @return A non-null, possibly empty, string to help identify the payment
     * transaction.
     */
    @Override
    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    /**
     * Sets the reference number for a payment. This could be a credit card
     * processing validation number, a cheque number, or any other information
     * that identifies the payment. This number is not guaranteed to be unique
     * across different payments.
     *
     * @param referenceNumber A string to help identify the payment transaction,
     * can be null or empty.
     */
    public void setReferenceNumber(final String referenceNumber) {
        this.referenceNumber = trimSafe(referenceNumber);
    }

    /**
     * Returns the date that the payment was made.
     *
     * @return A non-null instance.
     */
    @Override
    public Date getPurchased() {
        return this.purchased;
    }

    /**
     * Sets the date that the payment was made.
     *
     * @param purchased The sales order purchased date, can be null.
     */
    public void setPurchased(final Date purchased) {
        this.purchased = nullSafe(purchased);
    }

    /**
     * Returns the amount paid for this particular payment.
     *
     * @return A positive number, possibly zero, never null.
     */
    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount paid for this particular payment.
     *
     * @param amount A positive number, can be null, should not be negative.
     */
    public void setAmount(final BigDecimal amount) {
        this.amount = nullSafe(amount);
    }

    /**
     * Returns what payment type was used for the transaction (credit card,
     * cheque, etc.). This returns null if no payment method has been set.
     *
     * @return The type of payment method used for the transaction, or null if
     * not set.
     */
    @Override
    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Sets what payment type was used for the transaction (credit card, cheque,
     * etc.). This returns null if no payment method has been set.
     *
     * @param paymentMethod The type of payment method used for the transaction,
     * or null if not set.
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Returns whether the payment cleared. This returns null if no payment
     * status has been set.
     *
     * @return A status to indicate approved, declined, NSF, or null.
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    /**
     * Sets whether the payment cleared. This returns null if no payment status
     * has been set.
     *
     * @param paymentStatus Indicates approved, declined, NSF, or null.
     */
    public void setPaymentStatus(final PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Unsupported.
     *
     * @return Nothing; throws instead.
     */
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

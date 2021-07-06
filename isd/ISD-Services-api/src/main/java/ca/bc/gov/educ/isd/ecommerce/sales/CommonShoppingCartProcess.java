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

import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SelfServeProcess;
import ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatus;
import java.net.URL;

/**
 * Shopping cart process steps that are common between PEN, Non-PEN and GED
 * order processes.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CommonShoppingCartProcess extends SelfServeProcess {

    /**
     * Records that the currently authenticated user has consented to the
     * collection and use of their personal information.
     *
     * When placing an order the (Student) User must consent to the collection
     * and use of personal information prior to adding order items to the
     * shopping cart. This is typically accomplished in a user interface by
     * displaying the legal agreement or terms of service and asking the user to
     * click a check box.
     *
     * The user interface can call this method to indicated that the user has
     * consented. The timestamp of the consent will be recorded, and then items
     * can be added to the shopping cart.
     *
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    void consentToPersonalInfo() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Has the user consented to the collection and use of their personal
     * information for the current sales order?
     *
     * @return TRUE if there is the user consented
     * @throws DomainServiceException
     */
    Boolean hasConsented() throws DomainServiceException;

    /**
     * Check that the current sales order is ready for payment.
     *
     * If a sales order has all information needed for the calculation of the
     * final total and payment information then it is a valid order.
     *
     * This method is used to check any final business rules and determine if
     * the order as a whole can be checked out.
     *
     * <b>Note:</b> If the order is already checked out this method will still
     * check the order it valid rather than throw an exception, because it does
     * not modify any order contents.
     *
     * @return ValidateResults.isValid = TRUE if the order is valid for
     * purchasing, FALSE if information is missing.
     * @throws SalesOrderLifeCycleException if order validation cannot be
     * processed.
     */
    ValidateResults validate() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Retrieve the order items in the shopping cart for the currently
     * authenticated user.
     *
     * @param <T> The SalesOrder subclass to return.
     * @return Order Items in the shopping cart.
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    public abstract <T extends SalesOrder> T viewCart() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Once the order is checked out, its contents are fixed, so that a payment
     * total can be finalized. Checkout in this context can be thought of
     * locking the order items so they cannot be changed, while the payment is
     * made.
     *
     * After this method is called any changes to the order other than adding a
     * payment receipt and status will throw an exception.
     *
     * @return The sales order that was checked out.
     * @throws SalesOrderLifeCycleException
     */
    SalesOrder checkout() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Reverts from checkout state. Allows changes to be made to the order
     * contents once more. If payment information is present then this method
     * throws and exception.
     *
     * @throws SalesOrderLifeCycleException if payment has been made on this
     * order, the checked out status cannot be reverted.
     */
    void unCheckout() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Is the current sales order in a checked out state?
     *
     * @return TRUE if the current sales order is checked out.
     */
    Boolean isCheckedOut();

    /**
     * Marks the order for off-line payment (cheque or money order). When paying
     * offline the order is marked as placed (cannot be unChecked-out) and a
     * task is created for an payment administrator to receive the payment.
     *
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     */
    void payOffline() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Mark the order as paid on-line. The order is placed and marked as being
     * paid on-line. The caller is then provided with a URL for the payment
     * page. (Beanstream payment page)
     *
     * The connection information for the payment provider is taken from the
     * JNDI environment.
     *
     * @return A URL for the on-line payment provider, which can be used to pay
     * for the order.
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    URL payOnline() throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Provide Proof of payment when paying on-line.
     *
     * After paying online, the caller must provide the payment transaction
     * number. This value will be used to lookup and verify the payment and
     * payment amount.
     *
     * If transaction id is for an approved transaction and the amount of the
     * transaction matches the amount of the order, then this service returns
     * TRUE, if the payment is declined FALSE. In all other cases such as a
     * mismatch in the amount of the order, an exception is thrown.
     *
     * @param transactionId The payment transaction number
     * @return True, if the Sales Order has been moved to "PAID" status.
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     * @deprecated Functionality provided by TransactionProcessor
     */
    Boolean presentReciept(String transactionId) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Sets the status of the sales order in this shopping cart to PAID.
     *
     * @param payStatus The merchant information provided about a payment.
     * @throws DomainServiceException Could not update the sales order.
     */
    void processPaymentTransaction(PaymentStatus payStatus) throws DomainServiceException;
    
    void setHttpSessionId(String httpSessionId) throws SalesOrderLifeCycleException, DomainServiceException;
}

/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: ShoppingCartProcess.java 12289 2019-12-05 23:52#$
 *  Date of Last Commit: $Date:: 2019-12-05 15:52:40 -0800 (Thu, 05 Dec 2019)  $
 *  Revision Number:     $Rev:: 12289                                          $
 *  Last Commit by:      $Author:: arybakov                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.ecommerce.catalogue.CatalogueItem;
import ca.bc.gov.educ.isd.ecommerce.delivery.DeliveryInfo;
import ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatus;
import ca.bc.gov.educ.isd.ecommerce.sales.nonpen.NonPENOrderPerson;

/**
 * Encapsulates the actions of completing a sales order and executing the
 * purchase.
 *
 * <h5>Implementation Notes</h5>
 *
 * <h6>A user can only have one order at a time</h6>
 *
 * Several methods only work with the most current order available in the
 * underlying database. This creation of multiple orders in the event of
 * multiple sessions for the same user. For access to history of orders and past
 * orders use the Sales Order Entity Service.
 *
 * <h6>The Current Shopping Cart</h6>
 *
 * The current shopping cart is the most recent Sales order record in the
 * system.
 *
 * <ol>
 * <li>If the most recent shopping cart is in the process of being checked out.
 * i.e. entering payment information, then its order items cannot be changed.
 * <li>If the most recent shopping cart / order has been paid for, then a new
 * order can be started.</li>
 * </ol>
 *
 * <h6>Anonymous Orders for GED and non-PEN student.</h6>
 *
 * If no user is currently authenticated then in each session the shopping cart
 * is always newly created and no user entity id
 *
 * <h6>Initialization</h6>
 * Setups up the initial state of the ordering process.
 *
 * After initializing the process a sales order entity is ready for the addition
 * of sales order items and all handles to any supporting business services are
 * acquired and ready.
 *
 * If the user has an existing new or checked out order, it is loaded. Otherwise
 * a new order is created and the blank order form is saved.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ShoppingCartProcess extends CommonShoppingCartProcess {

    /**
     * Setups up the initial state of the ordering process.
     *
     * After initializing the process a sales order entity is ready for the
     * addition of sales order items and all handles to any supporting business
     * services are acquired and ready.
     *
     * If the user has an existing new or checked out order, it is loaded.
     * Otherwise a new order is created and the blank order form is saved.
     *
     * @param student
     * @throws DomainServiceException If the Sales Order Item could not be
     * created or the supporting business services could not be connected.
     *
     * @deprecated Use initialize(); this should expose a common (homogenous)
     * interface.
     */
    void initializeNonPEN(NonPENOrderPerson student) throws DomainServiceException;

    /**
     * Adds an item to the shopping cart.
     *
     * Delivery information must be provided which is compatible with the
     * delivery methods for the catalogue item
     *
     * @param psiCode
     *
     * @return True if the item was successfully added to the cart.
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     */
    SalesOrder addTranscriptRecipientToCart(String psiCode) throws DomainServiceException, SalesOrderLifeCycleException;

    Boolean selectSendOption(String psiCode, Boolean immediate, String applicantId) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Adds a report item to the shopping cart.
     *
     * <p>
     * Add an instance of a specific student report to the order item. This add
     * a "ReportOrderItem" to the order.
     * <p>
     * Use this method when a specific <b>reviewed and approved</b> instance of
     * a report needs to be part of the order item.
     * <p>
     * Delivery information must be provided which is compatible with the
     * delivery methods for the catalogue item.
     * <p>
     * The order item quantity is set to 1.
     *
     * @param item
     * @param delivery
     * @param report
     * @return True if the item was successfully added to the cart.
     * @throws SalesOrderLifeCycleException if the process is not
     */
    Boolean addToCart(CatalogueItem item, DeliveryInfo delivery, BusinessReport report) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Adds a report item to the shopping cart. Add an instance of a specific
     * student report to the order item. This add a "ReportOrderItem" to the
     * order.
     * <p>
     * Use this method when a specific <b>reviewed and approved</b> instance of
     * a report needs to be part of the order item.
     * <p>
     * Delivery information must be provided which is compatible with the
     * delivery methods for the catalogue item.
     * <p>
     * Quantity must be bigger than 0.
     *
     * @param item
     * @param delivery
     * @param report
     * @param quantity
     * @deprecated
     * @return True if the item was successfully added to the cart.
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     */
    Boolean addToCart(CatalogueItem item, DeliveryInfo delivery, BusinessReport report, Integer quantity) throws DomainServiceException, SalesOrderLifeCycleException;

    SalesOrder updateDeliveryInfo(SalesOrderItem item, DeliveryInfo info) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Remove the given order item from the shopping cart.
     *
     * @param item Order Item to remove.
     * @return True if the item was successfully removed.
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     */
    Boolean removeTranscriptRecipient(String item) throws DomainServiceException, SalesOrderLifeCycleException;

    SalesOrder removeItem(SalesOrderItem item) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Check if a specific sales order item is valid.
     *
     * Determines if an individual order item can proceed to payment.
     *
     * A sales order item is valid if all of its needed information is present.
     * Such as, the correct and complete delivery information.
     *
     * <b>Note:</b> If the order is already checked out this method will still
     * check the order it valid rather than throw an exception, because it does
     * not modify any order contents.
     *
     * @param item A specific order item.
     * @return ValidateResults.isValid = True if all information is present that
     * allows the item to be purchased.
     * @throws SalesOrderLifeCycleException
     */
    ValidateResults validate(SalesOrderItem item) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Called to transition the sales order into a PAID state given a valid
     * transaction response.
     *
     * @param payStatus Status of the payment transaction.
     * @param salesOrder Sales order to process.
     * @throws DomainServiceException
     */
    void processPaymentTransaction(final PaymentStatus payStatus, final SalesOrder salesOrder) throws DomainServiceException;
    
    /**
     * Changes the quantity of the selected sale order item.
     *
     * @param order The order that contains the item to be adjusted.
     * @param orderItemId The id of the item to be adjusted.
     * @param quantity The quantity to set the item to.
     * @throws DomainServiceException
     */
    void setItemQuantity(SalesOrder order, Long orderItemId, Integer quantity) throws DomainServiceException;
    
    /**
     * Sets the current number of items in the shopping cart that can use an available entitlement.
     *
     * @param entsInCart the number of entitlements in the shopping cart.
     */
    void setEntitlementsInCart(Integer entsInCart);
    
    /**
     * Get the number of entitlements that have been used by the current user for transcripts
     * being sent to PSIs.
     *
     * @return the number of PSI entitlements already used.
     */
    Integer getEntUsedPSI();
    
    /**
     * Get the number of entitlements that have been used by the current user for postal mail 
     * order transcripts.
     *
     * @return the number of mail transcript entitlements already used.
     */
    Integer getEntUsedTranscript();
    
    /**
     * Call EJB container to invalidate @Stateful session bean
     */
    void invalidateSessionBean();
}

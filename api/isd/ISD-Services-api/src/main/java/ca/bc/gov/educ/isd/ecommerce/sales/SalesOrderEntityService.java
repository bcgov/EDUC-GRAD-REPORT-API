/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *  Revision Number:     $Rev:: 36                                             $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatus;
import java.util.Date;
import java.util.List;

/**
 * Entity services for Sales Order, filtered for the current user.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface SalesOrderEntityService extends CommonEntityService<SalesOrder, SearchResult> {

    /**
     * Add a saved Sales Order Item to the Sales Order.
     *
     * Detail records cannot be simply added to a master record, due to the
     * auditing constraints. A cascade persist will not correctly set the
     * created By / On and Last Updated By / On fields. First each object must
     * be created, then the relationship added.
     *
     * @param order An sales order which has been previously persisted.
     * @param item A sales order item which has been previously persisted.
     * @return The refreshed Sales Order Entity.
     * @throws DomainServiceException Could not obtain service instance.
     */
    SalesOrder addSalesOrderItem(SalesOrder order, SalesOrderItem item) throws DomainServiceException;

    /**
     * Add a saved Payment status to the Sales Order.
     *
     * Detail records cannot be simply added to a master record, due to the
     * auditing constraints. A cascade persist will not correctly set the
     * created By / On and Last Updated By / On fields. First each object must
     * be created, then the relationship added.
     *
     * @param order An sales order which has been previously persisted.
     * @param status A payment status which has been previously persisted.
     * @return The refreshed Sales Order Entity.
     * @throws DomainServiceException Could not obtain service instance.
     */
    SalesOrder addPaymentStatus(SalesOrder order, PaymentStatus status) throws DomainServiceException;

    /**
     * The PEN Users Orders Totals report requires the ability to find users
     * with PENs (i.e., students with STs profiles). Each array returned is
     * populated as follows:
     *
     * <pre>
     * [0] - Year and month the order was created (format: <code>YYYY-MM</code>)
     * [1] - Delivery method (DeliveryMethodEnum)
     * [2] - Total amount for the orders using this delivery method (BigDecimal)
     * [3] - Total number of orders for this delivery method (Integer)
     * </pre>
     *
     * @param dateBegan Date of the oldest order to retrieve.
     * @param dateEnded Date of the most recent order to retrieve.
     * @return A list of order totals grouped by year-month and delivery method,
     * sorted by year-month and delivery method.
     * @throws DomainServiceException Could not obtain service instance.
     */
    List<Object[]> search(Date dateBegan, Date dateEnded) throws DomainServiceException;

    /**
     * Finds a sales order using the given sales order number and ensures that
     * the current user is associated with the sales order.
     *
     * @param orderNumber The payment sequence number associated with the sales
     * order.
     * @return The sales order for the current user.
     * @throws DomainServiceException The sales order could not be found, or is
     * not associated with the current user.
     */
    SalesOrder findPrincipalSalesOrder(Long orderNumber) throws DomainServiceException;

    /**
     * TODO: Temporary until a unique session is created for anonymous users and
     * associated with a sales order.
     *
     * @param orderNumber
     * @return
     * @throws DomainServiceException
     */
    SalesOrder getSalesOrder(Long orderNumber) throws DomainServiceException;

    /**
     *
     * @param dateEnded
     * @return
     */
    List<String> findPaidOrders(final Date dateEnded);
}

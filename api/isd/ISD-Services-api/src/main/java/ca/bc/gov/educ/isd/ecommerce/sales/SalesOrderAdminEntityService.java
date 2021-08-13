
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

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatusAdmin;

/**
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface SalesOrderAdminEntityService extends CommonEntityService<SalesOrderAdmin, SearchResult> {

    SalesOrderAdmin addSalesOrderItem(SalesOrderAdmin order, SalesOrderItemAdmin item) throws DomainServiceException;

    /**
     * Add a saved Payment status to the Sales Order.
     *
     * Detail records cannot be simply added to a master record, due to the
     * auditing constraints. A cascade persist will not correctly set the
     * created By / On and Last Updated By / On fields. First each object must
     * be created, then the relationship added.
     *
     * @param order A sales order which has been previously persisted.
     * @param status A payment status which has been previously persisted.
     * @return The refreshed Sales Order Entity.
     * @throws DomainServiceException Could not obtain service instance.
     */
    SalesOrderAdmin addPaymentStatus(SalesOrderAdmin order, PaymentStatusAdmin status) throws DomainServiceException;

    SalesOrderAdmin removeSalesOrderItem(SalesOrderAdmin order, SalesOrderItemAdmin item) throws DomainServiceException;

    /**
     * Change the status for orders that are paid off-line. The order has to be
     * in status "PLACED" in order to be moved to "PAID" status.
     *
     * @param orderNumber
     * @return True, if the sales order has been moved to "PAID" status.
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    Boolean changeStatusOfflineOrder(final Long orderNumber) throws DomainServiceException;

    /**
     * Changes sales order items to Fulfilled. Also changes the status of the
     * sales order to FULFILLED if its previous status was PLACED.
     *
     * @param saleOrderNumber
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void changeSOIStatusToFulfilled(final Long saleOrderNumber) throws DomainServiceException;

    /**
     * Changes the sales order status to CANCELLED.
     *
     * @param saleOrderNumber Sale order number to cancel.
     * @throws DomainServiceException
     */
    void changeSOStatusToCancelled(final Long saleOrderNumber) throws DomainServiceException;
}

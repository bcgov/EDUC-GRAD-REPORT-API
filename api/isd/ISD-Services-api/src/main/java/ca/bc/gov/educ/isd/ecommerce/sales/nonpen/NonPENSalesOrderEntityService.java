 /* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        SalesOrder.java
 *  Date of Last Commit: $Date:: 2016-10-07 15:37:55 -0700 (Fri, 07 Oct 2016)  $
 *  Revision Number:     $Rev:: 4113                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.sales.nonpen;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import ca.bc.gov.educ.isd.ecommerce.sales.SaleOrderRecipient;
import java.util.Date;
import java.util.List;

/**
 * Entity services for GED Sales Order, filtered for the current user.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface NonPENSalesOrderEntityService extends CommonEntityService<NonPENSalesOrder, SearchResult> {

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
     *
     * @return the ca.bc.gov.educ.isd.ecommerce.sales.SaleOrderRecipient
     * @throws DomainServiceException
     */
    SaleOrderRecipient createRecipient() throws DomainServiceException;

    /**
     * Change the status for orders that are paid off-line.
     *
     * <p>
     * The order has to be in status "PLACED" in order to be moved to "PAID"
     * status.
     *
     * @param orderNumber
     * @return True, if the sales order has been moved to "PAID" status.
     * @throws DomainServiceException
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
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

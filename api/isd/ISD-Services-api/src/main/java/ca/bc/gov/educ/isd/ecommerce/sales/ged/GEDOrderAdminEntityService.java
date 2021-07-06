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
 *  File:                $Id:: GEDOrderAdminEntityService.java 7651 2017-07-05#$
 *  Date of Last Commit: $Date:: 2017-07-05 14:44:54 -0700 (Wed, 05 Jul 2017)  $
 *  Revision Number:     $Rev:: 7651                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales.ged;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * @author CGI Information Management Consultants Inc.
 */
public interface GEDOrderAdminEntityService extends CommonEntityService<GEDOrderAdmin, SearchResult> {

    /**
     * Change the status for orders that are paid off-line.
     *
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

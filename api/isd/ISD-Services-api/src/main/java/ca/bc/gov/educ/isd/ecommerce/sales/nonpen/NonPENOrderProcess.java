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
 *  File:                $Id:: NonPENOrderProcess.java 11977 2019-06-17 20:33:#$
 *  Date of Last Commit: $Date:: 2019-06-17 13:33:00 -0700 (Mon, 17 Jun 2019)  $
 *  Revision Number:     $Rev:: 11977                                          $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales.nonpen;

import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.ecommerce.sales.SaleOrderRecipient;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrder;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException;
import ca.bc.gov.educ.isd.ecommerce.sales.ShoppingCartProcess;
import java.util.List;

/**
 * The non-PEN order service provides a convenience service which wraps the
 * non-PEN ordering process around the Shopping Cart service.
 *
 * <p>
 * The Shopping cart service is designed for a multi-step ordering process used
 * by PEN based students as they order transcripts and certificates sent to
 * multiple destinations. The GED and non-PEN users however have a simpler
 * ordering process in which orders items are few and can be sent to multiple
 * addresses.</p>
 *
 * <p>
 * Although this service appears similar to an entity service it is not directly
 * linked to a single persistent domain entity.
 *
 * <p>
 * Because non-PEN transcripts and certificates may be ordered by
 * unauthenticated users. The process uses Run-As capability to access other
 * underlying services.
 *
 * @deprecated There should be no difference between the interfaces for non-PEN
 * order processes and other order processes. Homogenize the API to reduce the
 * amount of duplicate code.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NonPENOrderProcess extends ShoppingCartProcess {

    /**
     * Creates a form which may be populated by a user interface such as a web
     * page.
     *
     * @return An instance of a non-PEN Order Form.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    NonPENSalesOrder create() throws DomainServiceException;

    /**
     *
     * @return the ca.bc.gov.educ.isd.ecommerce.sales.SaleOrderRecipient
     * @throws DomainServiceException
     */
    SaleOrderRecipient createRecipient() throws DomainServiceException;

    NonPENSalesOrder addRecipient(NonPENSalesOrder order, SaleOrderRecipient recipAddr, String newOrderType) throws DomainServiceException;

    NonPENSalesOrder addRecipients(NonPENSalesOrder order, List<SaleOrderRecipient> recipList) throws DomainServiceException;

    /**
     * Remove the items being sent to the given recipient from the order.
     *
     * @param recipient
     * @return
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    SalesOrder removeRecipient(SaleOrderRecipient recipient) throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Remove a given SalesOrderItem from the sales order.
     *
     * @param id the id of the SalesOrderItem
     * @return
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    SalesOrder removeSalesOrderItem(final Long id) throws DomainServiceException, SalesOrderLifeCycleException;
}

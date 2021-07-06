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
 *  File:                $Id:: PaymentReceiptService.java 11149 2018-08-21 23:#$
 *  Date of Last Commit: $Date:: 2018-08-21 16:04:25 -0700 (Tue, 21 Aug 2018)  $
 *  Revision Number:     $Rev:: 11149                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.payment.receipt;

import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrder;
import java.io.Serializable;
import java.util.List;
import javax.naming.NamingException;

/**
 * Administrative service for manipulating payment receipt data.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentReceiptService extends Serializable {

    /**
     * Creates and populates multiple receipts for the receipt report. The
     * method populates those instances with information from the sales order.
     * This mechanism avoids sending the data for a purchased document when
     * generating a receipt.
     *
     * This will include payments that were declined, NSF, and so forth.
     *
     * @param salesOrder The sales order containing data to put on the receipt.
     * @return A list of receipts for a sales order.
     * @throws DomainServiceException Could not create the instances needed to
     * populate with sales order data.
     * @throws NamingException Could not look up the report service.
     */
    public List<Receipt> createReceipts(SalesOrder salesOrder)
            throws DomainServiceException, NamingException;

    /**
     * Creates and populates a single receipt for the receipt report. This will
     * only include the last transaction for the sales order.
     *
     * @param salesOrder The sales order containing data to put on the receipt.
     * @return A list of receipts for a sales order.
     * @throws DomainServiceException Could not create the instances needed to
     * populate with sales order data.
     * @throws NamingException Could not look up the report service.
     */
    public List<Receipt> createReceipt(SalesOrder salesOrder)
            throws DomainServiceException, NamingException;
}

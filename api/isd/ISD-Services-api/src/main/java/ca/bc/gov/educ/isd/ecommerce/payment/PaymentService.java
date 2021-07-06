/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PaymentService.java 7364 2017-06-02 20:48:32Z D#$
 *  Date of Last Commit: $Date:: 2017-06-02 13:48:32 -0700 (Fri, 02 Jun 2017)  $
 *  Revision Number:     $Rev:: 7364                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.payment;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;

/**
 * Payments and access to third party payment providers. Payment services may be
 * accessed through multiple methods depending on the service provider
 * implementation. This service provides common methods and support for each of
 * the popular integration types. The implementation class should guarantee
 * thread safety.
 *
 * <h2>Supported Scenarios</h2>
 * <ol>
 * <li>Browser Redirection</li>
 * <li>Integrated Gateway</li>
 * </ol>
 *
 * @author CGI Information Management Consultants Inc.
 * @see <a href="#">EDUC_CCGI16_003_ISD_ICD-PaymentsWithBeanStream.docx</a>
 */
public interface PaymentService extends BusinessService {

    /**
     * Receives payment from the external (third party) payment provider.
     *
     * Verifies the receipt of payment for the currently in-progress order. The
     * receipt of payment is verified, by this method by calling the payment
     * providers transaction status service.
     * <p>
     * If verification is successful, the customers current order is updated
     * with a payment receipt and a transaction status.
     * <p>
     * In the event that the transaction is completely valid, but for some
     * reason the payment processing is not complete, the payment receipt and
     * transaction status is still created but the order is not cleared for
     * fulfillment.
     * <p>
     * When customer completes payment at a payment provider they are
     * re-directed back to the originating (user interface) web site's "success"
     * page with a transaction reference number. The user interface should
     * provide the transaction reference number to this method.
     * <p>
     * If this payment has already been received for the current or any order of
     * the customer (currently authenticated user) then this method has no
     * effect.
     * <p>
     * If this payment has already been received for a different customer, then
     * an exception is thrown.
     *
     * @param trnID Transaction reference number provided by the 3rd Party
     * payment provider.
     * @return True if the receipt of payment is confirmed.
     * @throws DomainServiceException If the payment is invalid.
     * @throws DataException if the order could not be read or modified.
     */
    Boolean recieveOnlinePayment(String trnID) throws DomainServiceException,
            DataException;

    /**
     * Pay of an order via off-line means.
     *
     * An order may be paid for via cheque or money order provided directly to
     * the Ministry. (Typically by postal mail.)
     *
     * This method marks a checked out order as awaiting the receipt of off-line
     * payment.
     *
     * @return TRUE, if the order status has successfully been updated.
     * @throws DomainServiceException
     * @throws DataException
     */
    Boolean recieveOfflinePayment() throws DomainServiceException, DataException;
}

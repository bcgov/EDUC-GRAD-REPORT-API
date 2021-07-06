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
package ca.bc.gov.educ.isd.eis.beanstream;

import ca.bc.gov.educ.isd.eis.EISException;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Isolates how merchant-specific transaction status information is retrieved.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentAdaptor {

    /**
     * Retrieve a redirection URL for a payment service provider.
     * <p>
     * The resulting String contains a URL which must be used verbatim as the
     * redirection target for the users web browser. This support payment
     * service providers in which the client simply redirects to the payment
     * provider with URL encoded purchase information.
     *
     * <p>
     * This service always scales the payment amount to have 2 decimal places
     * using the {@code BigDecimal.HALF_EVEN} (a.k.a. Banker's rounding).
     *
     * @param orderNo The Transcript / Certificate Order for which the payment
     * is to be made.
     * @param paymentAmount The amount of the payment.
     * @return URL which can be used by the {@code HTTPServletResponse} to
     * redirect the user-agent.
     * @throws ca.bc.gov.educ.isd.eis.EISException If the Adaptor configuration
     * cannot be loaded, or the parameter values cannot be verified.
     */
    URL buildExternalPaymentURL(String orderNo, BigDecimal paymentAmount) throws EISException;

    /**
     * Retrieve a redirection URL for a payment service provider.
     * <p>
     * The resulting String contains a URL which must be used verbatim as the
     * redirection target for the users web browser. This support payment
     * service providers in which the client simply redirects to the payment
     * provider with URL encoded purchase information.
     *
     * <p>
     * This service always scales the payment amount to have 2 decimal places
     * using the {@code BigDecimal.HALF_EVEN} (a.k.a. Banker's rounding).
     *
     * @param orderNo The Transcript / Certificate Order for which the payment
     * is to be made.
     * @param paymentAmount The amount of the payment.
     * @param protectedReturnPage if true (default value), the ref1 return URL
     * should be a logon protected page
     * @return URL which can be used by the {@code HTTPServletResponse} to
     * redirect the user-agent.
     * @throws ca.bc.gov.educ.isd.eis.EISException If the Adaptor configuration
     * cannot be loaded, or the parameter values cannot be verified.
     */
    URL buildExternalPaymentURL(String orderNo, BigDecimal paymentAmount, boolean protectedReturnPage) throws EISException;

    /**
     * Query the payment services provider to get the transaction status.
     *
     * @param transactionId The transaction number returned from the merchant's
     * payment form.
     * @param salesOrderNumber
     * @return The payment result for the transaction either success or fail.
     * @throws EISException If anything wrong during the query of status from a
     * payment service.
     */
    TransactionStatusResponse queryStatus(String transactionId, String salesOrderNumber) throws EISException;

    /**
     * Returns a new instance of TransactionStatusResponse with its approved
     * status to true.
     *
     * @return A non-null instance.
     */
    TransactionStatusResponse createApprovedTransactionResponse();
}


/*  *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 * 
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 * 
 *   Revision Control Information
 *   File:                $Id::                                                $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015) $
 *   Revision Number:     $Rev:: 36                                            $
 *   Last Commit by:      $Author:: bbates                                     $
 * 
 *  
  **********************************************************************  */

/**
 * The Beanstream Adaptor provides a connection to Beanstream's payment gateway.
 * 
 * This adaptor supports a single merchant account using Beanstream's payment 
 * form.  The adaptor provides support for constructing a HTTP URL 
 * to enable a user interface to redirect a user agent to Beanstream as 
 * well as a query service to verify the transaction.  
 *
 * <h5>Example Use</h5>
 * 
 * <p>...</p>
 * 
 * The account parameters are specified as in JNDI environment strings.
 *
 * <h5>JNDI Configuration</h5>
 * <p>The adaptor is configured through the EJB environment (JNDI strings).  
 * For Example:</p>
 * 
 * <pre>
 * {@code
 *    <subsystem xmlns="urn:jboss:domain:naming:1.4">
 *        <bindings>
 *          ...
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/HASHKEY" value="f8e85c19919B4467972Ad8d064BdC3D8"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/merchant_id" value="318900000"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/URL_payment" value="https://www.beanstream.com/scripts/Payment/Payment.asp?"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/URL_queryStatus" value="https://www.beanstream.com/scripts/process_transaction.asp?"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/trnType_payment" value="P"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/ref1" value="https://dev.bced.gov.bc.ca/eos/taco/paymentReceipt.php"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/errorPage" value="/samples/order_form.asp"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/hashMethod" value="md5"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/hashExpiryDurationInMinutes" value="30"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/hashExpiryDateFormat" value="yyyyMMddHHmm"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/transactionDateFormat" value="MM/dd/yyyy HH:mm:ss a"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/trnType_query" value="Q"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/requestType" value="BACKEND"/>
 *          <simple name="java:global/ISD-PaymentServices-ejb/merchant/readTimeout" value="30000"/>
 *          <simple name="java:/isd/communication/email/baseURL" value="http://www2.gov.bc.ca/gov/content/education-training/k-12/support/studenttranscripts/registration/"/>
 *          <simple name="java:/isd/communication/email/activationURL" value="http://www2.gov.bc.ca/gov/content/education-training/k-12/support/studenttranscripts/registration/"/>
 *       </bindings>
 *       ...
 * }
 * </pre>
 */
package ca.bc.gov.educ.isd.eis.beanstream;

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

import java.util.Map;

/**
 * The response data structure form a Beanstream payment status query.
 *
 * When a payment status query is performed the response includes a variety of
 * values, messages, and codes. Those values are parsed and store in the
 * Transaction Status Response.
 *
 * FIXME: Eliminate this interface. Re-use or adapt an existing interface, such
 * as PaymentStatus.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TransactionStatusResponse extends Map<String, String> {

    public static final String TRANSACTION_STATUS_APPROVED = "1";
    public static final String TRANSACTION_STATUS_CANCELED = "0";

    public static final String TRANSACTION_APPROVED = "trnApproved";
    public static final String TRANSACTION_ID = "trnId";
    public static final String TRANSACTION_ORDER_NUMBER = "trnOrderNumber";
    public static final String TRANSACTION_TYPE = "trnType";
    public static final String TRANSACTION_AMOUNT = "trnAmount";
    public static final String TRANSACTION_DATE = "trnDate";

    public static final String MESSAGE_ID = "messageId";
    public static final String MESSAGE_TEXT = "messageText";

    public static final String AUTH_CODE = "authCode";
    public static final String PAYMENT_METHOD = "paymentMethod";
    public static final String RESPONSE_TYPE = "responseType";
    public static final String CARD_TYPE = "cardType";
    public static final String CVD_ID = "cvdId";

    public static final String ERROR_TYPE = "errorType";
    public static final String ERROR_FIELDS = "errorFields";

    public static final String AVS_PROCESSED = "avsProcessed";
    public static final String AVS_ID = "avsId";
    public static final String AVS_RESULT = "avsResult";
    public static final String AVS_ADDR_MATCH = "avsAddrMatch";
    public static final String AVS_POSTAL_MATCH = "avsPostalMatch";
    public static final String AVS_MESSAGE = "avsMessage";

    public static final String REF1 = "ref1";
    public static final String REF2 = "ref2";
    public static final String REF3 = "ref3";
    public static final String REF4 = "ref4";
    public static final String REF5 = "ref5";

    String getAuthCode();

    String getAvsAddrMatch();

    String getAvsId();

    String getAvsMessage();

    String getAvsPostalMatch();

    String getAvsProcessed();

    String getAvsResult();

    String getCardType();

    String getCvdId();

    String getErrorFields();

    String getErrorType();

    String getMessageId();

    String getMessageText();

    String getPaymentMethod();

    String getRef1();

    String getRef2();

    String getRef3();

    String getRef4();

    String getRef5();

    String getResponseType();

    String getTrnApproved();

    String getTrnAmount();

    String getTrnDate();

    /**
     * TODO: What does this return? How does it differ from TrnOrderNumber?
     *
     * @return
     */
    String getTrnId();

    /**
     * TODO: What does this return?
     *
     * @return
     */
    String getTrnOrderNumber();

    String getTrnType();

    /**
     * Answers whether the transaction was approved by the merchant.
     *
     * @return TRUE iff the transaction was approved.
     */
    Boolean isApproved();

    /**
     * Answers whether the transaction was canceled (no transaction found).
     *
     * @return TRUE - the transaction was canceled.
     */
    Boolean isCanceled();

    /**
     * Answers whether the transaction status response code refers to a missing
     * transaction.
     *
     * @return TRUE - The transaction identifier was not found.
     */
    Boolean isMissing();

    /**
     * Answers whether the transaction was declined by the merchant.
     *
     * @return FALSE if the transaction was not declined.
     */
    Boolean isDeclined();
}

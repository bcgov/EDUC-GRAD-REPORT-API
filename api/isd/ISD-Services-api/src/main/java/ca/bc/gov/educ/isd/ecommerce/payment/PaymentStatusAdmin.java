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
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.payment;

/**
 * An administrative form for payment transaction status records.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface PaymentStatusAdmin extends PaymentStatus {

    /**
     * Sets the reference number for the order's transaction.
     *
     * @param value A unique identifier for the transaction.
     */
    void setTransactionId(String value);

    /**
     * Message ID from merchant regarding the transaction.
     *
     * @param value The message ID from the merchant.
     */
    void setMsgId(String value);

    /**
     * Message text from merchant regarding the transaction.
     *
     * @param value The human-readable text for the corresponding message ID.
     */
    void setMsg(String value);

    /**
     * Sets the authorization code.
     *
     * @param value
     */
    void setAuthCode(String value);

    /**
     * Sets the payment method (VI, MC, AE, etc.).
     *
     * @param value Code to indicate VISA, MasterCard, etc.
     */
    void setCardType(String value);

    /**
     * Sets the payment status code.
     *
     * @param value Boolean.TRUE indicates the transaction completed
     * successfully.
     */
    void setApproved(Boolean value);

    /**
     * Sets the transaction date.
     *
     * @param date The date the transaction for an order transpired.
     */
    void setDate(String date);

    /**
     * This amount comes directly from the merchant, as such it is stored as a
     * string.
     *
     * @param amount The amount for the transaction regarding a sales order
     * purchase.
     */
    void setAmount(final String amount);

}

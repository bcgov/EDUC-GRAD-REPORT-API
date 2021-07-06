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
package ca.bc.gov.educ.isd.ecommerce.sales;

/**
 * The method of payment used by a sales order.
 *
 * @author Ministry of Education, BC
 */
public enum PaymentMethodEnum {

    ON_LINE("ON_LINE", "ONLINE PAYMENT", "Pay on-line."),
    OFF_LINE("OFF_LINE", "OFFLINE_PAYMENT", "Pay off-line (Cheque or Money Order).");

    private final String code;
    private final String description;

    /**
     * Payment type is an attribute sent to the Message Queue. The payment type
     * has a different value when it is sent to the queue.
     */
    private final String messageQueuePaymentType;

    private PaymentMethodEnum(String code, String messageQueuePaymentType, String description) {
        this.code = code;
        this.description = description;
        this.messageQueuePaymentType = messageQueuePaymentType;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMessageQueuePaymentType() {
        return this.messageQueuePaymentType;
    }
}

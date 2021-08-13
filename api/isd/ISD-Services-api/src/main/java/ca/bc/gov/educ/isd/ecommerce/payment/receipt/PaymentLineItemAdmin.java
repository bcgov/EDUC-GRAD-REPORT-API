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
package ca.bc.gov.educ.isd.ecommerce.payment.receipt;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a lightweight line item for the receipt report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentLineItemAdmin extends PaymentLineItem {

    /**
     * Sets the number of items ordered.
     *
     * @param quantity The tally of an individual line item ordered for a
     * particular sales order payment.
     */
    void setQuantity(Integer quantity);

    /**
     * The extended price is the total price for a line item. Usually, the price
     * is defined by:
     *
     * <pre>
     * ((total quantity - minus free items) * unit price + taxes)
     * </pre>
     *
     * However, for our purposes, there are neither taxes nor shipping fees.
     *
     * @param extPrice The line item price.
     */
    void setExtPrice(BigDecimal extPrice);

    /**
     * Sets the name of the party to receive the purchased items.
     *
     * @param name A non-null String, shouldn't be empty.
     */
    void setRecipientName(String name);

    /**
     * Sets human-readable text for the line item.
     *
     * @param description Text to distinguish one line item from the others.
     */
    void setDescription(String description);

    /**
     * Sets the date that the purchase was attempted.
     *
     * @param date A non-null Date.
     */
    void setOrderDate(Date date);
}

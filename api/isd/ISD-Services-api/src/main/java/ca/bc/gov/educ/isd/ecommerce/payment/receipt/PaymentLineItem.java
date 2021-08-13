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

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a lightweight line item for the receipt report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentLineItem extends DomainEntity {

    /**
     * Number of items ordered.
     *
     * @return The tally of an individual line item ordered for a particular
     * sales order payment.
     */
    Integer getQuantity();

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
     * @return The line item price.
     */
    BigDecimal getExtPrice();

    /**
     * Returns the name of the party to receive the purchased items.
     *
     * @return A non-null String, shouldn't be empty.
     */
    String getRecipientName();

    /**
     * Returns a human-readable string that identifies the type of item ordered.
     * This could be Transcript, English Certificate, French Certificate, etc.
     *
     * @return A non-null String.
     */
    String getDescription();

    /**
     * Returns the date that the purchase was attempted.
     *
     * @return A non-null Date.
     */
    Date getOrderDate();
}

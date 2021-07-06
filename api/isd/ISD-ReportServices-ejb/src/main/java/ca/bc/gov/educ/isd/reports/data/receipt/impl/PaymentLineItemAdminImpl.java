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
package ca.bc.gov.educ.isd.reports.data.receipt.impl;

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItemAdmin;

import java.math.BigDecimal;

/**
 * Allows for populating the fields using data from an external source, such as
 * a sales order.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PaymentLineItemAdminImpl
        extends PaymentLineItemImpl implements PaymentLineItemAdmin {

    private static final long serialVersionUID = 1L;

    public PaymentLineItemAdminImpl() {
    }

    /**
     * Changes the line item's tally.
     *
     * @param quantity The number ordered.
     */
    @Override
    public void setQuantity(final Integer quantity) {
        super.setQuantity(quantity);
    }

    /**
     * Changes the line item's extension price.
     *
     * @param extPrice The new price.
     */
    @Override
    public void setExtPrice(final BigDecimal extPrice) {
        super.setExtPrice(extPrice);
    }

    /**
     * Changes the line item's recipient.
     *
     * @param recipientName The new recipient name.
     */
    @Override
    public void setRecipientName(final String recipientName) {
        super.setRecipientName(recipientName);
    }

    /**
     * Changes the human-readable line item description.
     *
     * @param description Text that distinguishes the line item from other line
     * items.
     */
    @Override
    public void setDescription(final String description) {
        super.setDescription(description);
    }
}

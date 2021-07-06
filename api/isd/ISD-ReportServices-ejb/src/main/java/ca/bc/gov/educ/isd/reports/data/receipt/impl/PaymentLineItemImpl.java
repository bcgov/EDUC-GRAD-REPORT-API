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

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItem;
import ca.bc.gov.educ.isd.reports.impl.AbstractReportDomainEntity;

import java.math.BigDecimal;
import java.util.Date;

import static java.math.BigDecimal.ZERO;

/**
 * Provides a lightweight equivalent for sales order item instances.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PaymentLineItemImpl extends AbstractReportDomainEntity
        implements PaymentLineItem {

    private static final long serialVersionUID = 1L;

    private Integer quantity = 0;
    private BigDecimal extPrice = ZERO;
    private String recipientName = "";
    private String description = "";
    private Date orderDate = new Date();

    /**
     * Default (empty) constructor.
     */
    public PaymentLineItemImpl() {
    }

    /**
     * Returns the tally of this particular line item.
     *
     * @return A positive number, or zero, never null.
     */
    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the purchase price of this particular line item.
     *
     * @return A positive number, or zero, never null.
     */
    @Override
    public BigDecimal getExtPrice() {
        return this.extPrice;
    }

    /**
     * Returns the recipient for this line item.
     *
     * @return A non-null String, possibly empty (if not set).
     */
    @Override
    public String getRecipientName() {
        return this.recipientName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Date getOrderDate() {
        return this.orderDate;
    }

    /**
     * Changes the line item's tally.
     *
     * @param quantity The number ordered.
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = nullSafe(quantity);
    }

    /**
     * Changes the line item's extension price.
     *
     * @param extPrice The new price.
     */
    public void setExtPrice(final BigDecimal extPrice) {
        this.extPrice = nullSafe(extPrice);
    }

    /**
     * Changes the line item's recipient.
     *
     * @param recipientName The new recipient name.
     */
    public void setRecipientName(final String recipientName) {
        this.recipientName = trimSafe(recipientName);
    }

    /**
     * Changes the line item's order item name.
     *
     * @param description The new order item name.
     */
    public void setDescription(final String description) {
        this.description = trimSafe(description);
    }

    /**
     * Changes the date the order was made.
     *
     * @param date The order item's purchase date.
     */
    public void setOrderDate(final Date date) {
        this.orderDate = date;
    }
}

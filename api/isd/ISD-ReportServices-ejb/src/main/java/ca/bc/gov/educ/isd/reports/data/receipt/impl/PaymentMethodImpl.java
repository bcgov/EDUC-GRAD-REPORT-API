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

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentMethod;

/**
 * Represents a payment method (VISA, MasterCard, cheque, etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PaymentMethodImpl extends AbstractCode implements PaymentMethod {

    private static final long serialVersionUID = 1L;

    /**
     * Passes the name to the superclass.
     *
     * @param name Code name for the status.
     */
    public PaymentMethodImpl(final String name) {
        super(name);
    }

    /**
     * Passes the name and description to the superclass.
     *
     * @param name Code name for the status.
     * @param description Human-readable status text.
     */
    public PaymentMethodImpl(final String name, final String description) {
        super(name, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Boolean isValid() {
        final String name = nullSafe(getName());
        final String description = nullSafe(getDescription());
        return !name.isEmpty() && !description.isEmpty();
    }
}

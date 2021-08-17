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

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentStatus;

import static ca.bc.gov.educ.isd.common.Constants.PAYMENT_APPROVED_STATUS;
import static ca.bc.gov.educ.isd.common.Constants.PAYMENT_DECLINED_STATUS;

/**
 * Represents the state for a particular payment, which is related to its
 * payment method. A payment by cheque, for example, could have a payment status
 * of "insufficient funds" for a code of "NSF".
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PaymentStatusImpl extends AbstractCode implements PaymentStatus {

    private static final long serialVersionUID = 1L;

    /**
     * TODO: Remove this constructor once payment methods become enumerated
     * types or code sets.
     *
     * @param approved Indicates whether the payment was tendered successfully,
     * must not be null.
     */
    public PaymentStatusImpl(final Boolean approved) {
        // These are used as both the code and the description because the
        // description returns the code if no description is set. Calling
        // this( String ) only sets the code.
        this(approved ? PAYMENT_APPROVED_STATUS : PAYMENT_DECLINED_STATUS);
    }

    /**
     * Passes the name to the superclass.
     *
     * @param name Code name for the status.
     */
    public PaymentStatusImpl(final String name) {
        super(name);
    }

    /**
     * Passes the name and description to the superclass.
     *
     * @param name Code name for the status.
     * @param description Human-readable status text.
     */
    public PaymentStatusImpl(final String name, final String description) {
        super(name, description);
    }

}

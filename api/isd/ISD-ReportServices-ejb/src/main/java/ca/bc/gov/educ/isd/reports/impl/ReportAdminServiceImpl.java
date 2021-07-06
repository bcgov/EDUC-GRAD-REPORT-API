/* *********************************************************************
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
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.*;
import ca.bc.gov.educ.isd.reports.ReportAdminService;
import ca.bc.gov.educ.isd.reports.data.receipt.impl.*;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * Provides a mechanism to create reports to fill out and produce a specific
 * format.
 *
 * @author CGI Information Management Consultants Inc.
 */
@PermitAll
public class ReportAdminServiceImpl implements ReportAdminService {

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentAdmin createPayment() {
        return new PaymentAdminImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentLineItemAdmin createPaymentLineItemAdmin() {
        return new PaymentLineItemAdminImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentMethod createPaymentMethod(final String code, final String description) {
        return new PaymentMethodImpl(code, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentStatus createPaymentStatus(final String code, final String description) {
        return new PaymentStatusImpl(code, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public Receipt createReceipt(
            final Payment payment,
            final List<PaymentLineItem> lineItems) {
        return new ReceiptImpl(payment, lineItems);
    }
}

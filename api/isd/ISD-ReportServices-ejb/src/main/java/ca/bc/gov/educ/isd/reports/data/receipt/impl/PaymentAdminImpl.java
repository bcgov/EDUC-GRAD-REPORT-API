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

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentAdmin;

/**
 * Allows for populating the fields using data from an external source, such as
 * a sales order.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PaymentAdminImpl extends PaymentImpl implements PaymentAdmin {

    public PaymentAdminImpl() {
    }
}

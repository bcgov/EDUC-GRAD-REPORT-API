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

import ca.bc.gov.educ.isd.codes.Code;

/**
 * Represents a payment method (VISA, MasterCard, cheque, etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentMethod extends Code {

    /**
     * Returns true if this is a valid form of payment. That is, the code and the
     * description are set to a known payment method (e.g., "VI" for "VISA").
     *
     * @return TRUE the payment method is acceptable.
     */
    public Boolean isValid();
}

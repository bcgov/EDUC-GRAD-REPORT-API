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
 * Represents the state for a particular payment, which is related to its
 * payment method. A payment by cheque, for example, could have a payment status
 * of "insufficient funds" for a code of "NSF".
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentStatus extends Code {

}

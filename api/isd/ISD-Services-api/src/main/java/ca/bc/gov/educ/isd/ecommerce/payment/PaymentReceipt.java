/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PaymentReceipt.java 6471 2017-03-08 22:38:11Z D#$
 *  Date of Last Commit: $Date:: 2017-03-08 14:38:11 -0800 (Wed, 08 Mar 2017)  $
 *  Revision Number:     $Rev:: 6471                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.payment;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentReceipt extends DomainEntity {

    String getSerialNo();

    Date getTransactionDate();

    Double getAmount();

    List<PaymentStatus> getPaymentStatus();
}

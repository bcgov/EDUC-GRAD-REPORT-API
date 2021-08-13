/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PaymentStatus.java 6471 2017-03-08 22:38:11Z DA#$
 *  Date of Last Commit: $Date:: 2017-03-08 14:38:11 -0800 (Wed, 08 Mar 2017)  $
 *  Revision Number:     $Rev:: 6471                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.payment;

import ca.bc.gov.educ.isd.common.DomainEntity;

/**
 * A payment status is used to record verification of an online payment.
 *
 * When an order is paid online this entity records the transaction results.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentStatus extends DomainEntity {

    String getTransactionId();

    String getMsgId();

    String getMsg();

    String getAuthCode();

    String getCardType();

    Boolean getApproved();

    String getAmount();

    String getDate();
}

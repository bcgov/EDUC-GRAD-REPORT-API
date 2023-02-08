/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateOrderType.java 6786 2017-04-06 18:23#$
 *  Date of Last Commit: $Date:: 2017-04-06 11:23:40 -0700 (Thu, 06 Apr 2017)  $
 *  Revision Number:     $Rev:: 6786                                           $
 *  Last Commit by:      $Author:: tsalomon                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.school;

import ca.bc.gov.educ.grad.report.model.order.OrderType;

/**
 * Represents a certificate order type, to differentiate transcript and
 * certificate orders, as well as encapsulate paper type selection behaviour.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SchoolOrderType extends OrderType {

}

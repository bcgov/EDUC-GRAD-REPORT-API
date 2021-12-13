/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: TranscriptOrderType.java 4160 2016-10-11 00:23:#$
 *  Date of Last Commit: $Date:: 2016-10-10 17:23:03 -0700 (Mon, 10 Oct 2016)  $
 *  Revision Number:     $Rev:: 4160                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.order.OrderType;

/**
 * Represents a transcript order type, to differentiate transcript and
 * certificate orders, as well as encapsulate paper type selection behaviour.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TranscriptOrderType extends OrderType {

}

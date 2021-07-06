/*
 * *************************************************************************
 *   Copyright (c) 2017, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id:: PaymentReceiptEntityService.java 6430 2017-03-#$
 *   Date of Last Commit: $Date:: 2017-03-02 15:13:00 -0800 (Thu, 02 Mar 2017) $
 *   Revision Number:     $Rev:: 6430                                          $
 *   Last Commit by:      $Author:: DAJARVIS                                   $
 *
 * **************************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.payment;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * Non-Administrative / readonly service for manipulating payment receipt data.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentReceiptEntityService extends CommonEntityService<PaymentReceipt, SearchResult> {

}

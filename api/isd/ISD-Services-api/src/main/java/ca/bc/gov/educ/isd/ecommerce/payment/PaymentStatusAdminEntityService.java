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
package ca.bc.gov.educ.isd.ecommerce.payment;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * Since payment status are directly linked to the payment receipts they use the
 * same security roles. Also responsible for creating payment status instances
 * as well as receipt instances for reporting purposes.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface PaymentStatusAdminEntityService extends CommonEntityService<PaymentStatusAdmin, SearchResult> {
}

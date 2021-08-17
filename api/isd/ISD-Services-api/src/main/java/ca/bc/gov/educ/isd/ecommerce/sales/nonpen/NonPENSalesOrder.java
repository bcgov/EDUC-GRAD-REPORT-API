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
package ca.bc.gov.educ.isd.ecommerce.sales.nonpen;

import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrder;

/**
 * @deprecated Non-PEN sales order and GED sales order should have a common
 * super interface that has exposes the same user data.
 */
public interface NonPENSalesOrder extends SalesOrder {

    NonPENOrderPerson getStudent();

    void setStudent(NonPENOrderPerson student);

}

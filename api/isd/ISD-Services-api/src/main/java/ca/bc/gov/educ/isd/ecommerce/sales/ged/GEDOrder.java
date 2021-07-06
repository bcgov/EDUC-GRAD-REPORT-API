/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        GEDOrder.java
 *  Date of Last Commit: $Date:: 2017-08-03 11:27:08 -0700 (Thu, 03 Aug 2017)  $
 *  Revision Number:     $Rev:: 7832                                           $
 *  Last Commit by:      $Author:: EPOIRIER                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.sales.ged;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrder;

/**
 * A complete GED order form.
 *
 * The Address fields provided contain all the fields for all of the address
 * types. It is only necessary to populated the fields for the selected type.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GEDOrder extends SalesOrder, Cloneable, DomainEntity {

    OrderPerson getStudentInfo();

    void setStudentInfo(OrderPerson personInfo);

    String getSinLast3();

    void setSinLast3(String sinLast3);

    String getGedSessionYear();

    void setGedSessionYear(String gedSessionYear);

    String getGedTestCenterName();

    void setGedTestCenterName(String gedTestCenterName);

}

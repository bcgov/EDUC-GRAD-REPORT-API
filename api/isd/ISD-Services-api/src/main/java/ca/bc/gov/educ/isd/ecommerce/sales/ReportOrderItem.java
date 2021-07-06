/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        ReportOrderItem.java 
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $  
 *  Revision Number:     $Rev:: 3100                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.BusinessReport;

/**
 * An order item for a specific instance of a business report.
 *
 * <p>
 * Users can order copies of reports, such as a Transcript as it appears at a
 * exact point in time. This order item has an instance of the Business Report
 * attached to it.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ReportOrderItem extends SalesOrderItem {

    //TODO cp - might be better if this is held in the service component and we reference by EID?
    BusinessReport getBusinessReport();

}

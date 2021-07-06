/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 * 
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 * 
 *   Revision Control Information
 *   File:                $Id::                                                $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015) $
 *   Revision Number:     $Rev:: 36                                            $
 *   Last Commit by:      $Author:: rlo                                        $
 * 
 *  
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * Entity services for Sales Order, filtered for the current user.
 * 
 * @author CGI Information Management Consultants, Inc.
 */
public interface SalesOrderNumberEntityService extends CommonEntityService<SalesOrderNumber, SearchResult> {
    /**
     * returns a unique sequential number for SalesOrders
     * @return
     * @throws DomainServiceException
     * @throws DataException 
     */
    long generateSalesOrderNumber() throws DomainServiceException, DataException;
}

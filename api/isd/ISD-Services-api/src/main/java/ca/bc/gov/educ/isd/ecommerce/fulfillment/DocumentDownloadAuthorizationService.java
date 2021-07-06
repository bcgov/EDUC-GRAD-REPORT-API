
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 * 
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 * 
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                     $
 * 
 *  
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.fulfillment;

import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderItem;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException;

/**
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface DocumentDownloadAuthorizationService {
    
    
    /** Authorize the document download for the sales order item.
     * 
     * The authorize, provides a identifier which is can be used to retrieve
     * the document later.  this identifier would typically be added to the 
     * download link.
     * <p>
     * Use this service when constructing a download link.
     * 
     * @param orderItem The sales order item which contains the document to be downloaded.
     * @return  The id to be used in retrieving the document.
     * @throws DataException
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException 
     */
    String authorize(SalesOrderItem orderItem) throws DataException, DomainServiceException, SalesOrderLifeCycleException;
    
}


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
package ca.bc.gov.educ.isd.ecommerce.sales.ged;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import ca.bc.gov.educ.isd.ecommerce.sales.SaleOrderRecipient;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GEDOrderEntityService extends CommonEntityService<GEDOrder, SearchResult> {

    SaleOrderRecipient createRecipient() throws DomainServiceException;

}

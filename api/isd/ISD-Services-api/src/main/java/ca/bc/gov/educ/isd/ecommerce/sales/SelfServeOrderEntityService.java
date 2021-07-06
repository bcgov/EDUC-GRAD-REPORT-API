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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.Date;
import java.util.List;

/**
 * Entity services for querying SelfServeOrders.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface SelfServeOrderEntityService extends CommonEntityService<SelfServeOrder, SearchResult> {


    /**
     * Provides a query for the Self Serve Order report.
     *
     * @param dateBegan Start date to tally self serve order selections.
     * @param dateEnded End date to tally self serve order selections.
     * @return A list of objects suitable for display on the self serve order report.
     * @throws DomainServiceException Could not obtain the results.
     */
    List<Object[]> search(final Date dateBegan, final Date dateEnded) throws DomainServiceException;
    
}

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
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface SalesOrderItemAdminEntityService
        extends CommonEntityService<SalesOrderItemAdmin, SearchResult> {

    /**
     * Provides a query for the PSI Choice report.
     *
     * @param dateBegan Start date to tally PSI Choice selections.
     * @param dateEnded End date to tally PSI Choice selections.
     * @return A list of objects suitable for display on the PSI Choice report.
     * @throws DomainServiceException Could not obtain the results.
     */
    public List<Object[]> search(Date dateBegan, Date dateEnded)
            throws DomainServiceException;

    public List<SalesOrderItemAdmin> searchSlsOrdItemsToFulfill(
            Date dateBegan, Date dateEnded)
            throws DomainServiceException;

    public List<SalesOrderItemAdmin> searchSOIWithFailedDocuments()
            throws DomainServiceException;

    public List<SalesOrderItemAdmin> searchMailDeliveryItems()
            throws DomainServiceException;

}

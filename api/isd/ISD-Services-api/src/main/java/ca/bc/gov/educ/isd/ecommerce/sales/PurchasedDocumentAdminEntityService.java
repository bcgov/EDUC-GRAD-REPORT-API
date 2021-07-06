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

import ca.bc.gov.educ.isd.common.BusinessReport;
import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.Date;

/**
 * Entity services for Sales Order, filtered for the current user.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface PurchasedDocumentAdminEntityService extends CommonEntityService<PurchasedDocumentAdmin, SearchResult> {

    PurchasedDocumentAdmin create(BusinessReport report) throws DomainServiceException, DataException;

    public void updateReportFilenames(String id, String filename) throws DomainServiceException, DataException;

    public void updateReportSentDT(final String id) throws DomainServiceException, DataException;

    public void updatePurchasedDocuments(final String id, String filename, Date dt) throws DomainServiceException, DataException;
}

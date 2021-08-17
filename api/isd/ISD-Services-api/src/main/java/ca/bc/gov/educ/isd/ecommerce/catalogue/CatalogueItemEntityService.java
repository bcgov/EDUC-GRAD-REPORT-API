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
 *  File:                $Id:: CatalogueItemEntityService.java 6655 2017-03-27#$
 *  Date of Last Commit: $Date:: 2017-03-27 16:12:43 -0700 (Mon, 27 Mar 2017)  $
 *  Revision Number:     $Rev:: 6655                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * <h5>Implementation Notes</h5>
 *
 * This service is a read-only access to the Catalog Items. The administration
 * service is needed to make any alterations to a Catalogue Item.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CatalogueItemEntityService extends CommonEntityService<CatalogueItem, SearchResult> {

    /**
     * Finds the catalogue item that exactly matches the given name.
     *
     * @param name The catalogue item name to match.
     * @return A non-null instance.
     * @throws DomainServiceException Could not find the catalogue item by the
     * given name.
     */
    CatalogueItem findByName(String name) throws DomainServiceException;

    /**
     * Finds and returns the GED Transcript catalogue item.
     *
     * @return
     * @throws DomainServiceException
     */
    CatalogueItem findGEDXCRPT() throws DomainServiceException;

    CatalogueItem findGEDCert() throws DomainServiceException;

    /**
     * Looks up the catalogue item for a transcript that can be issued to a
     * student who does not have a PEN.
     *
     * @return A transcript catalogue item.
     * @throws DomainServiceException
     */
    CatalogueItem findNonPENTranscript() throws DomainServiceException;

    /**
     * Looks up the catalogue item for a certificate that can be issued to a
     * student who does not have a PEN.
     *
     * @return A certificate catalogue item.
     * @throws DomainServiceException
     */
    CatalogueItem findNonPENCert() throws DomainServiceException;

}

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
 *  File:                $Id:: CatalogueCategoryAdminEntityService.java 6471 2#$
 *  Date of Last Commit: $Date:: 2017-03-08 14:38:11 -0800 (Wed, 08 Mar 2017)  $
 *  Revision Number:     $Rev:: 6471                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CatalogueCategoryAdminEntityService extends CommonEntityService<CatalogueCategoryAdmin, SearchResult> {

    /**
     * Since the Catalogue Item may only have one category, there is no remove
     * method. Once a Catalogue has a category the category cannot be nulled.
     *
     * @param categoryAdmin
     * @param item
     * @return
     * @throws DomainServiceException
     */
    CatalogueCategoryAdmin addItem(CatalogueCategoryAdmin categoryAdmin, CatalogueItem item) throws DomainServiceException;

}

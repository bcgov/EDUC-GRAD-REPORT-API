/**
 * ***********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 *
 * Revision Control Information File: CatalogueCategoryAdminService.java Date of
 * Last Commit: $Date:: 2017-01-11 13:02:22 -0800 (Wed, 11 Jan 2017) $ Revision
 * Number: $Rev:: 57#$ Last Commit by: $Author:: bbates  $
 * 
**************************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface EntitlementCategoryAdminEntityService extends CommonEntityService<EntitlementCategoryAdmin, SearchResult> {

    /**
     *
     * Since the item may only have one category, there is no remove method.
     * Once a Catalogue has an item the category cannot be nulled.
     *
     * @param categoryAdmin
     * @param item
     * @return
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    EntitlementCategoryAdmin addItem(EntitlementCategoryAdmin categoryAdmin, EntitlementItem item) throws DomainServiceException;

}

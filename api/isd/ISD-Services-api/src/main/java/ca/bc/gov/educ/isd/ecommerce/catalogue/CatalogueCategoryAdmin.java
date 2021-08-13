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
 *  File:                $Id:: CatalogueCategoryAdmin.java 6471 2017-03-08 22:#$
 *  Date of Last Commit: $Date:: 2017-03-08 14:38:11 -0800 (Wed, 08 Mar 2017)  $
 *  Revision Number:     $Rev:: 6471                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

import java.util.List;

/**
 * An administration interface to a Catalogue Category Entity.
 *
 * The administration interface provides mutator methods which cannot normally
 * be used by normal user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CatalogueCategoryAdmin extends CatalogueCategory {

    void setName(String name);

    void setDescription(String desc);

    void setItems(List<CatalogueItem> categories);

}

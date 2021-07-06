/**
 * ***********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 *
 * Revision Control Information File: CatalogueCategoryAdmin.java Date of Last
 * Commit: $Date:: 2017-01-11 13:02:22 -0800 (Wed, 11 Jan 2017) $ Revision
 * Number: $Rev:: 57#$ Last Commit by: $Author:: bbates $
 * 
**************************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

import java.util.List;

/**
 * An administration interface to a Entitlement Category Entity.
 *
 * The administration interface provides mutator methods which cannot normally
 * be used by normal user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface EntitlementCategoryAdmin extends EntitlementCategory {

    void setName(String name);

    void setDescription(String desc);

    void setItems(List<EntitlementItem> items);

}

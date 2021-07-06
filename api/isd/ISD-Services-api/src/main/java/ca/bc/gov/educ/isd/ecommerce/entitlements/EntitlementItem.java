/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        EntitlementItem.java
 *  Date of Last Commit: $Date:: 2017-01-11 13:02:22 -0800 (Wed, 11 Jan 2017)  $
 *  Revision Number:     $Rev:: 5786                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

import ca.bc.gov.educ.isd.common.DescribedEntity;

/**
 * An item which may provide a discount on items purchased.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface EntitlementItem extends DescribedEntity {

    /**
     * Retrieves the count of items that can be obtained for free.
     *
     * @return The Canadian Dollar unit price of the item.
     */
    Integer getCountAllowed();

    /**
     * Retrieves the item's code.
     *
     * @return
     */
    EntitlementItemCode getCode();
}

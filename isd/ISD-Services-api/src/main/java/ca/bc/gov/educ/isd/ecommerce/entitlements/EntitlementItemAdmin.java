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
 *  Date of Last Commit: $Date:: 2016-09-27 18:10:22 -0700 (Tue, 27 Sep 2016)  $
 *  Revision Number:     $Rev:: 3863                                           $
 *  Last Commit by:      $Author:: rlo                                         $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

/**
 * An item which may provide a discount on purchased items.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface EntitlementItemAdmin extends EntitlementItem {

    /**
     * Sets the number of discounted items allowed.
     *
     * @param count
     */
    void setCountAllowed(Integer count);

    /**
     * Sets the type of entitlement item.
     *
     * @param code The code used to distinguish this item from other types.
     */
    void setCode(EntitlementItemCode code);

    void setName(String name);

    void setDescription(String description);
}

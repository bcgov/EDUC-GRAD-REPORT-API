/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        CatalogueItem.java
 *  Date of Last Commit: $Date:: 2016-09-27 18:10:22 -0700 (Tue, 27 Sep 2016)  $
 *  Revision Number:     $Rev:: 3863                                           $
 *  Last Commit by:      $Author:: rlo                                         $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

import ca.bc.gov.educ.isd.ecommerce.delivery.DeliveryMethodEnum;
import java.math.BigDecimal;

/**
 * An item which may be purchased.
 *
 * The Catalogue Item provides all the information about a single item such as a
 * Transcript or Graduation Certificate which can be ordered. The Catalogue Item
 * holds the price, as well as any special options needed for fulfillment such
 * as the code for the BC Mail Plus paper stock used when ordering printed
 * Transcripts.
 *
 * <p>
 *
 * <h5>Implementation Notes</h5>
 *
 * <p>
 * Catalogue items may be sub-classed to provide specific types of items,
 * however all of the item attributes must be available via the Attributes map.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CatalogueItemAdmin extends CatalogueItem {

    /**
     * sets the unit price of the item.
     *
     * @param unitPrice
     */
    void setUnitPrice(BigDecimal unitPrice);

    /**
     * sets the delivery methods which can be used with this catalogue item.
     *
     * @param deliveryMethod
     */
    void setDeliveryMethod(DeliveryMethodEnum deliveryMethod);

    /**
     * sets the catalogue category for the category item.
     *
     * @param catalogueCategory
     */
    public void setCatalogueCategory(CatalogueCategory catalogueCategory);

    /**
     * Sets the type of catalogue item.
     *
     * @param code The code used to distinguish this item from other types.
     */
    void setCode(CatalogueItemCode code);

    /**
     * Sets the name of catalogue item.
     *
     * @param name
     */
    void setName(String name);

    /**
     * Sets the description of catalogue item.
     *
     * @param description
     */
    void setDescription(String description);
}

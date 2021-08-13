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
 *  Date of Last Commit: $Date:: 2017-01-18 12:13:27 -0800 (Wed, 18 Jan 2017)  $
 *  Revision Number:     $Rev:: 5857                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

import ca.bc.gov.educ.isd.common.DescribedEntity;
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
public interface CatalogueItem extends DescribedEntity {

    /**
     * Retrieves the catalogue category of the item.
     *
     * @return
     */
    public CatalogueCategory getCatalogueCategory();

    /**
     * Retrieves the unit price of the item.
     *
     * @return The Canadian Dollar unit price of the item.
     */
    BigDecimal getUnitPrice();

    /**
     * The list of delivery methods which can be used with this catalogue item.
     *
     * @return
     */
    DeliveryMethodEnum getDeliveryMethod();

    /**
     * Retrieves the item's catalogue code.
     *
     * @return
     */
    CatalogueItemCode getCode();
}

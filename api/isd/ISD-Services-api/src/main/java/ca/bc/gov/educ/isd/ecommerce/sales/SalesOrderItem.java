/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        SalesOrderItem.java
 *  Date of Last Commit: $Date:: 2017-06-22 14:18:14 -0700 (Thu, 22 Jun 2017)  $
 *  Revision Number:     $Rev:: 7558                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.ecommerce.catalogue.CatalogueItem;
import ca.bc.gov.educ.isd.ecommerce.delivery.DeliveryInfo;
import java.math.BigDecimal;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SalesOrderItem extends DomainEntity {

    CatalogueItem getCatalogueItem();

    /**
     * Number of items ordered.
     *
     * @return The tally of an individual line item ordered for a particular
     * sales order.
     */
    Integer getQuantity();

    /**
     * Number of entitlement used for the order.
     *
     * @return
     */
    Integer getEntitlementQuantity();

    /**
     * The extended price is the total price for a line item. ((total quantity -
     * minus free items) * unit price + taxes) In our situation, there is no tax
     * or shipping fees, which means that the order total price is the sum of
     * the extended prices.
     *
     * @return line item price.
     */
    BigDecimal getExtPrice();

    DeliveryInfo getDeliveryInfo();

    PurchasedDocument getDocument();

    SalesOrderItemStatusEnum getStatus();

    SalesOrder getSalesOrder();

}

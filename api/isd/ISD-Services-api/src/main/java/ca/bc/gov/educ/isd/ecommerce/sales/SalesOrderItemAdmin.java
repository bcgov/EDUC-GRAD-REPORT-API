
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                     $
 *
 *
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.ecommerce.catalogue.CatalogueItem;
import ca.bc.gov.educ.isd.ecommerce.delivery.DeliveryInfo;
import java.math.BigDecimal;

/**
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface SalesOrderItemAdmin extends SalesOrderItem {

    void setCatalogueItem(CatalogueItem catalogueItem);

    void setQuantity(Integer quantity);

    void setEntitlementQuantity(Integer entitlementQuantity);

    void setExtPrice(BigDecimal extPrice);

    void setDeliveryInfo(DeliveryInfo deliveryInfo);

    void setDocument(PurchasedDocument document);

    void setSalesOrder(SalesOrder salesOrder);

    void setStatus(SalesOrderItemStatusEnum status);

}

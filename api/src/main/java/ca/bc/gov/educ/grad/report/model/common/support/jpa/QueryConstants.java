/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: QueryConstants.java 11960 2019-06-06 00:09:31Z #$
 *  Date of Last Commit: $Date:: 2019-06-05 17:09:31 -0700 (Wed, 05 Jun 2019)  $
 *  Revision Number:     $Rev:: 11960                                          $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.support.jpa;

/**
 * Provides common constants used for JPA queries used by various projects.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class QueryConstants {

    private QueryConstants() {
    }

    public final static String PARAM_DATE_BEGAN = "dateBegan";
    public final static String PARAM_DATE_ENDED = "dateEnded";
    public final static String PARAM_IGNORE_ORGANIZATIONS = "ignoreOrganizations";

    public final static String QUERY_PEN_USERS_ORDER_TOTALS
            = "SalesOrderEntity.findOrderTotals";
    public final static String QUERY_PEN_USERS_PAID_ORDERS
            = "SalesOrderEntity.findPaidOrders";
    public final static String QUERY_NONPEN_USERS_ORDER_TOTALS
            = "NonPENSalesOrderEntity.findOrderTotals";
    public final static String QUERY_PSI_CHOICE_TALLIES
            = "SalesOrderItemEntity.searchPSIChoices";
    public final static String QUERY_PSI_CHOICE
            = "SalesOrderItemEntity.fulfillPSIChoices";
    public final static String QUERY_FAILED_DOCUMENTS
            = "SalesOrderItemEntity.fulfillFailedDocuments";
    public final static String QUERY_USER_PROFILES
            = "UserProfileImpl.searchUserProfiles";
    public final static String QUERY_SELF_SERVE_ORDER_TALLIES
            = "SelfServeOrderEntity.searchSelfServeOrder";
    public final static String QUERY_PROCESSING_ITEMS
            = "SalesOrderItemEntity.searchProcessingItems";

    /**
     *
     */
    public final static String QUERY_PEN_USERS_PAID_ORDERS_QUERY
            = "SELECT soe.entityId FROM SalesOrderEntity soe WHERE soe.status = "
            + "ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderStatusEnum.PAID "
            + "and soe.createdOn < :" + PARAM_DATE_ENDED;

    /**
     * Sort by orderDate instead of createdOn.
     */
    public final static String QUERY_SELF_SERVE_ORDER_TALLIES_QUERY
            = "SELECT\n"
            + "  ssoe.orderStatus,\n"
            + "  ssoe.orderNumber,\n"
            + "  ssoe.orderDate,\n"
            + "  ssoe.hasTranscripts,\n"
            + "  ssoe.hasCertificates,\n"
            + "  ssoe.deliveryMethods,\n"
            + "  ssoe.cardType,\n"
            + "  ssoe.total\n"
            + "FROM\n"
            + "  SelfServeOrderEntity ssoe\n"
            + "WHERE\n"
            + "  ssoe.orderDate BETWEEN :" + PARAM_DATE_BEGAN + " AND :" + PARAM_DATE_ENDED;

    public final static String QUERY_PEN_USERS_ORDER_TOTALS_QUERY
            = "SELECT\n"
            + "  YEAR(soe.createdOn) || '-' || MONTH(soe.createdOn) AS CreatedYearMonth,\n"
            + "  cie.deliveryMethod AS DeliveryMethod,\n"
            + "  SUM(soie.extPrice) AS TotalAmount,\n"
            + "  SUM(soie.quantity) AS TotalOrders\n"
            + "FROM\n"
            + "  SalesOrderEntity soe,\n"
            + "  SalesOrderItemEntity soie,\n"
            + "  CatalogueItemEntity cie\n"
            + "WHERE\n"
            + "  soie.salesOrder.id = soe.id AND\n"
            + "  soie.catItem.id = cie.id AND\n"
            + "  soe.id NOT IN (SELECT nsoe.id FROM NonPENSalesOrderEntity nsoe) AND\n"
            + "  soe.id NOT IN (SELECT gsoe.id FROM GEDOrderEntity gsoe) AND\n"
            + "  soe.createdOn BETWEEN :" + PARAM_DATE_BEGAN + " AND :" + PARAM_DATE_ENDED + "\n"
            + "GROUP BY\n"
            + "  cie.deliveryMethod,\n"
            + "  YEAR(soe.createdOn) || '-' || MONTH(soe.createdOn)\n"
            + "ORDER BY\n"
            + "  CreatedYearMonth, DeliveryMethod";

    public final static String QUERY_NONPEN_USERS_ORDER_TOTALS_QUERY
            = "SELECT\n"
            + "  YEAR(soe.createdOn) || '-' || MONTH(soe.createdOn) AS CreatedYearMonth,\n"
            + "  cie.deliveryMethod AS DeliveryMethod,\n"
            + "  cie.code AS Code,\n"
            + "  SUM(soie.extPrice) AS TotalAmount,\n"
            + "  SUM(soie.quantity) AS TotalOrders\n"
            + "FROM\n"
            + "  SalesOrderEntity soe,\n"
            + "  SalesOrderItemEntity soie,\n"
            + "  CatalogueCategoryEntity cce,\n"
            + "  CatalogueItemEntity cie\n"
            + "WHERE\n"
            + "  soie.salesOrder.id = soe.id AND\n"
            + "  soe.status IN ('PAID','FULFILLMENT','COMPLETE') AND\n"
            + "  soie.catItem.id = cie.id AND\n"
            + "  cie.catalogueCategory.id = cce.id AND\n"
            + "  (soe.id IN (SELECT nsoe.id FROM NonPENSalesOrderEntity nsoe) OR\n"
            + "  soe.id IN (SELECT gsoe.id FROM GEDOrderEntity gsoe)) AND\n"
            + "  soe.createdOn BETWEEN :" + PARAM_DATE_BEGAN + " AND :" + PARAM_DATE_ENDED + "\n"
            + "GROUP BY\n"
            + "  cie.deliveryMethod,\n"
            + "  cie.code,\n"
            + "  YEAR(soe.createdOn) || '-' || MONTH(soe.createdOn)\n"
            + "ORDER BY\n"
            + "  CreatedYearMonth, DeliveryMethod, Code";

    /**
     * Returns all the Sales Order Items that PSI with Interim flag equals TRUE.
     * The information is used for fulfillment purposes.
     */
    public final static String QUERY_PSI_CHOICE_QUERY
            = "SELECT\n"
            + "  soie\n"
            + "FROM\n"
            + "  SalesOrderItemEntity soie,\n"
            + "  SalesOrderEntity soe\n"
            + "WHERE\n"
            + "  soie.salesOrder.id = soe.id AND\n"
            + "  soie.deliveryInfo.psiCode IS NOT NULL AND\n"
            + "  soie.deliveryInfo.interim = TRUE AND\n"
            + "  soie.status <> ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderItemStatusEnum.FILLED AND\n"
            + "  soe.status = ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderStatusEnum.FULFILLMENT AND\n"
            + "  soe.createdOn BETWEEN :" + PARAM_DATE_BEGAN + " AND :" + PARAM_DATE_ENDED;

    public final static String QUERY_FAILED_DOCUMENTS_QUERY
            = "SELECT\n"
            + "  soie\n"
            + "FROM\n"
            + "  SalesOrderItemEntity soie,\n"
            + "  SalesOrderEntity soe\n"
            + "WHERE\n"
            + "  soie.salesOrder.id = soe.id AND\n"
            + "  soie.document.sentOn IS NULL AND\n"
            + "  soe.orderNumber IS NOT NULL AND\n"
            + "  soie.status = 'PROCESSING'";

    public final static String QUERY_PROCESSING_ITEMS_QUERY
            = "SELECT\n"
            + "  soie\n"
            + "FROM\n"
            + "  SalesOrderItemEntity soie\n"
            + "WHERE\n"
            + "  soie.status = 'PROCESSING' AND\n"
            + "  soie.createdOn > :" + PARAM_DATE_BEGAN;
    /**
     * Do not group by month.
     */
    public final static String QUERY_PSI_CHOICE_TALLIES_QUERY
            = "SELECT\n"
            + "  '2017-01' AS CreatedYearMonth,\n"
            + "  pdie.psiCode AS Code,\n"
            + "  pdie.transmissionMode AS Mode,\n"
            + "  COUNT(soie.quantity) AS Tally\n"
            + "FROM\n"
            + "  SalesOrderItemEntity soie,\n"
            + "  SalesOrderEntity soe,\n"
            + "  PSIDeliveryInfoEntity pdie\n"
            + "WHERE\n"
            + "  soie.salesOrder.id = soe.id AND\n"
            + "  pdie.salesOrderItem.id = soie.id AND\n"
            + "  pdie.transmissionMode IS NOT NULL AND\n"
            + "  soe.createdOn BETWEEN :" + PARAM_DATE_BEGAN + " AND :" + PARAM_DATE_ENDED + "\n"
            + "GROUP BY\n"
            + "  pdie.psiCode,\n"
            + "  pdie.transmissionMode\n"
            + "ORDER BY\n"
            + "  Tally DESC";

}

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
 *  File:                $Id:: ServiceConstants.java 12336 2019-12-13 19:30:20#$
 *  Date of Last Commit: $Date:: 2019-12-13 11:30:20 -0800 (Fri, 13 Dec 2019)  $
 *  Revision Number:     $Rev:: 12336                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

import static ca.bc.gov.educ.isd.common.Constants.*;

/**
 * Defines global JNDI lookup strings used to inject EJB services via the EJB
 * annotation.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ServiceConstants {

    /**
     * Prevent instantiation.
     *
     * @see
     * https://jtechies.blogspot.ca/2012/07/item-19-use-interfaces-only-to-define.html
     */
    private ServiceConstants() {
    }

    public final static String JNDI_GLOBAL = "java:global/";

    public final static String BCMAIL_PLUS_SERVICE = "java:/eis/BCMailPlusConnectionFactory";

    /**
     * For student reports.
     */
    public final static String REPORT_SERVICE
            = JNDI_GLOBAL + REPORT_SERVICES_APP_NAME + "/" + REPORT_SERVICES_MODULE_NAME + "/ReportServiceImpl!ca.bc.gov.educ.isd.reports.ReportService";

    public final static String BCMP_SERVICE
            = JNDI_GLOBAL + REPORT_SERVICES_APP_NAME + "/" + BCMP_SERVICES_MODULE_NAME + "/BCMPBundleServiceImpl!ca.bc.gov.educ.isd.reports.bundle.service.BCMPBundleService";

    /**
     * For objects that must have values set (predominately receipt report).
     */
    public final static String REPORT_ADMIN_SERVICE
            = JNDI_GLOBAL + REPORT_SERVICES_APP_NAME + "/" + REPORT_SERVICES_MODULE_NAME + "/ReportAdminServiceImpl!ca.bc.gov.educ.isd.reports.ReportAdminService";

    /**
     * For administrative reports.
     */
    public final static String ADMIN_REPORT_SERVICE
            = JNDI_GLOBAL + REPORT_SERVICES_APP_NAME + "/" + REPORT_SERVICES_MODULE_NAME + "/AdminReportServiceImpl!ca.bc.gov.educ.isd.reports.admin.AdminReportService";

    public final static String CODESET_ADMIN_SERVICE
            = JNDI_GLOBAL + CODESET_SERVICES_MODULE_NAME + "/CodeSetAdminEntityBean!ca.bc.gov.educ.isd.codes.CodeSetAdminEntityService";

    public final static String CODESET_SERVICE
            = JNDI_GLOBAL + CODESET_SERVICES_MODULE_NAME + "/CodeSetEntityBean!ca.bc.gov.educ.isd.codes.CodeSetEntityService";

    public final static String CODE_ADMIN_SERVICE
            = JNDI_GLOBAL + CODESET_SERVICES_MODULE_NAME + "/CodeAdminEntityBean!ca.bc.gov.educ.isd.codes.CodeAdminEntityService";

    public final static String TASK_SERVICE
            = JNDI_GLOBAL + TASK_SERVICES_MODULE_NAME + "/TaskEntityBean!ca.bc.gov.educ.isd.task.TaskService";

    public final static String TASK_HISTORY_SERVICE
            = JNDI_GLOBAL + TASK_SERVICES_MODULE_NAME + "/TaskHistoryEntityBean!ca.bc.gov.educ.isd.task.TaskHistoryService";

    public final static String TASK_ADMIN_SERVICE
            = JNDI_GLOBAL + TASK_SERVICES_MODULE_NAME + "/TaskAdminEntityBean!ca.bc.gov.educ.isd.task.TaskAdminService";

    public final static String USER_PROFILE_ADMIN_SERVICE
            = JNDI_GLOBAL + USERPROFILE_SERVICES_MODULE_NAME + "/UserProfileAdminServiceImpl!ca.bc.gov.educ.isd.users.UserProfileAdminService";

    public final static String SSO_ACCOUNT_SERVICE
            = JNDI_GLOBAL + USERPROFILE_SERVICES_MODULE_NAME + "/SSOAccountServiceImpl!ca.bc.gov.educ.isd.users.SSOAccountService";

    public final static String SALES_ORDER_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/SalesOrderEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderEntityService";

    public final static String SELF_SERVE_ORDER_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/SelfServeOrderEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.SelfServeOrderEntityService";

    public final static String SALES_ORDER_ADMIN_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/SalesOrderAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderAdminEntityService";

    public final static String SALES_ORDER_ITEM_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/SalesOrderItemEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderItemEntityService";

    public final static String SALES_ORDER_ITEM_ADMIN_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/SalesOrderItemAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderItemAdminEntityService";

    public final static String GED_ORDER_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/GEDOrderEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.ged.GEDOrderEntityService";

    public final static String GED_ORDER_ADMIN_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/GEDOrderAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.ged.GEDOrderAdminEntityService";

    public final static String GED_PAYMENT_PROCESS_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/GEDOrderProcessBean!ca.bc.gov.educ.isd.ecommerce.sales.ged.GEDOrderProcess";

    public final static String NONPEN_DATA_CHANGE_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/NonPENSalesOrderDataChangeEventBean!ca.bc.gov.educ.isd.ecommerce.impl.fulfillment.internal.DataChangeEventService";

    public final static String GED_DATA_CHANGE_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/GEDSalesOrderDataChangeEventBean!ca.bc.gov.educ.isd.ecommerce.impl.fulfillment.internal.DataChangeEventService";

    public final static String DATA_CHANGE_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/SalesOrderCompletionBean!ca.bc.gov.educ.isd.ecommerce.impl.fulfillment.internal.DataChangeEventService";

    public final static String NONPEN_SALES_ORDER_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/NonPENSalesOrderEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.nonpen.NonPENSalesOrderEntityService";

    public final static String NONPEN_PAYMENT_PROCESSOR_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/NonPENOrderProcessBean!ca.bc.gov.educ.isd.ecommerce.sales.nonpen.NonPENOrderProcess";

    public final static String PAYMENT_RECEIPT_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/PaymentReceiptBean!ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentReceiptService";

    public final static String PURCHASED_DOCUMENT_ADMIN_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/PurchasedDocumentAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.sales.PurchasedDocumentAdminEntityService";

    public final static String ENTITLEMENT_ITEM_ADMIN_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/EntitlementItemAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.entitlements.EntitlementItemAdminEntityService";

    public final static String ENTITLEMENT_CATEGORY_ADMIN_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/EntitlementCategoryAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.entitlements.EntitlementCategoryAdminEntityService";

    public final static String CATALOGUE_ITEMS_ADMIN_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/CatalogueItemAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.catalogue.CatalogueItemAdminEntityService";

    public final static String DELIVERY_INFO_ADMIN_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/DeliveryInfoAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.delivery.DeliveryInfoAdminEntityService";

    public final static String PAYMENT_PROCESSOR_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/ShoppingCartBean!ca.bc.gov.educ.isd.ecommerce.sales.ShoppingCartProcess";

    public final static String PESC_TRANSMISSION_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/PESCTransmissionServiceBean!ca.bc.gov.educ.isd.ecommerce.pesc.PESCTransmissionService";

    /**
     * @deprecated Use PAYMENT_PROCESSOR_SERVICE.
     */
    public final static String SHOPPING_CART_SERVICE = PAYMENT_PROCESSOR_SERVICE;

    public final static String CATALOGUE_CATEGORY_ADMIN_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/CatalogueCategoryAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.catalogue.CatalogueCategoryAdminEntityService";

    public final static String DOCUMENT_DOWNLOAD_DELIVERY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/DocumentDownloadDeliveryBean!ca.bc.gov.educ.isd.ecommerce.fulfillment.DocumentDownloadDeliveryService";

    public final static String DELIVERI_INFORMATION_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/DeliveryInfoEntityBean!ca.bc.gov.educ.isd.ecommerce.delivery.DeliveryInfoEntityService";

    public final static String PSI_DELIVERY_BATCH_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/PSIDeliveryBatchEntityServiceBean!ca.bc.gov.educ.isd.ecommerce.impl.fulfillment.internal.PSITranscriptMailBatchAdminEntityService";

    public final static String STUDENT_X_REF_SERVICE
            = JNDI_GLOBAL + STUDENT_DEMOG_SERVICES_MODULE_NAME + "/StudentXRefEntityService!ca.bc.gov.educ.isd.student.StudentXRefService";

    public final static String STUDENT_X_REF_ADMIN_SERVICE
            = JNDI_GLOBAL + STUDENT_DEMOG_SERVICES_MODULE_NAME + "/StudentXRefEntityAdminService!ca.bc.gov.educ.isd.student.StudentXRefAdminService";

    public final static String USER_PROFILE_SERVICE
            = JNDI_GLOBAL + USERPROFILE_SERVICES_MODULE_NAME + "/UserProfileServiceImpl!ca.bc.gov.educ.isd.users.UserProfileService";

    public final static String ROLE_SERVICE
            = JNDI_GLOBAL + USERPROFILE_SERVICES_MODULE_NAME + "/RoleServiceImpl!ca.bc.gov.educ.isd.users.RoleService";

    public final static String ROLE_GROUP_SERVICE
            = JNDI_GLOBAL + USERPROFILE_SERVICES_MODULE_NAME + "/RoleGroupServiceImpl!ca.bc.gov.educ.isd.users.RoleGroupService";

    public final static String STUDENT_EXAM_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/StudentExamServiceImpl!ca.bc.gov.educ.isd.exam.StudentExamService";

    public final static String NUMERACY_ASSESSMENT_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/NumeracyAssessmentServiceImpl!ca.bc.gov.educ.isd.assessment.NumeracyAssessmentService";

    public final static String LITERACY_ASSESSMENT_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/LiteracyAssessmentServiceImpl!ca.bc.gov.educ.isd.assessment.LiteracyAssessmentService";

    public final static String STUDENT_TRANSCRIPT_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/StudentTranscriptServiceImpl!ca.bc.gov.educ.isd.transcript.StudentTranscriptService";

    public final static String PSI_SELECTION_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/PSISelectionServiceBean!ca.bc.gov.educ.isd.psiselections.PSISelectionService";

    public final static String GRAD_CERTIFICATES_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/GradCertificateServiceImpl!ca.bc.gov.educ.isd.grad.GradCertificateService";

    public final static String PSI_TRAX_COUNTRY_SERVICE
            = JNDI_GLOBAL + PSI_REGISTRY_SERVICES_MODULE_NAME + "/TRAXCountryServiceBean!ca.bc.gov.educ.isd.psi.TRAXCountryService";

    public final static String PSI_TRAX_REGION_SERVICE
            = JNDI_GLOBAL + PSI_REGISTRY_SERVICES_MODULE_NAME + "/TRAXRegionServiceBean!ca.bc.gov.educ.isd.psi.TRAXRegionService";

    public final static String PSI_CHOICES_ENTITY_SERVICE
            = JNDI_GLOBAL + PSI_REGISTRY_SERVICES_MODULE_NAME + "/PSIChoicesEntityServiceBean!ca.bc.gov.educ.isd.psi.PSIChoicesEntityService";

    public final static String PSI_REGISTRY_ENTITY_SERVICE
            = JNDI_GLOBAL + PSI_REGISTRY_SERVICES_MODULE_NAME + "/PSIRegistryEntityServiceBean!ca.bc.gov.educ.isd.psi.PSIRegistryEntityService";

    public final static String USERPROCESS_SERVICE
            = JNDI_GLOBAL + USERPROCESS_SERVICES_MODULE_NAME + "/UserRegistrationProcessImpl!ca.bc.gov.educ.isd.process.user.UserRegistrationProcess";

    public final static String TASKS_USEPROCESS_SERVICE
            = JNDI_GLOBAL + USERPROCESS_SERVICES_MODULE_NAME + "/AdminTaskProcessImpl!ca.bc.gov.educ.isd.process.admin.AdminTaskProcess";

    public final static String TRANSCRIPT_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/StudentTranscriptServiceImpl!ca.bc.gov.educ.isd.transcript.StudentTranscriptService";

    public final static String SCHOLARSHIP_SERVICE
            = JNDI_GLOBAL + TRANSCRIPT_SERVICES_MODULE_NAME + "/StudentScholarshipServiceImpl!ca.bc.gov.educ.isd.scholarship.StudentScholarshipService";

    public final static String NOTIFICATION_SERVICE
            = JNDI_GLOBAL + USERCOMM_SERVICES_MODULE_NAME + "/NotificationServiceImpl!ca.bc.gov.educ.isd.communication.NotificationService";

    public final static String GLOBAL_NOTIFICATION_ADMIN_SERVICE
            = JNDI_GLOBAL + USERCOMM_SERVICES_MODULE_NAME + "/GlobalNotificationAdminServiceImpl!ca.bc.gov.educ.isd.communication.GlobalNotificationAdminService";

    public final static String TRAX_ADAPTOR
            = JNDI_GLOBAL + TRAX_MODULE_NAME + "/TRAXAdapterBean!ca.bc.gov.educ.isd.eis.trax.db.TRAXAdapter";

    public final static String CATALOGUE_CATEGORY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/CatalogueCategoryEntityBean!ca.bc.gov.educ.isd.ecommerce.catalogue.CatalogueCategoryEntityService";

    public final static String ENTITLEMENT_CATEGORY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/EntitlementCategoryEntityBean!ca.bc.gov.educ.isd.ecommerce.entitlements.EntitlementCategoryEntityService";

    public final static String PAYMENT_ADAPTOR_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/BeanstreamPaymentServiceImpl!ca.bc.gov.educ.isd.eis.beanstream.PaymentAdaptor";

    public final static String PAYMENT_STATUS_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/PaymentStatusEntityBean!ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatusEntityService";

    public final static String PAYMENT_STATUS_ADMIN_ENTITY_SERVICE
            = JNDI_GLOBAL + PAYMENT_SERVICES_MODULE_NAME + "/PaymentStatusAdminEntityBean!ca.bc.gov.educ.isd.ecommerce.payment.PaymentStatusAdminEntityService";

    public final static String EVENT_SERVICE
            = JNDI_GLOBAL + EVENT_SERVICES_MODULE_NAME + "/EventBroadcastSupportImpl!ca.bc.gov.educ.isd.common.EventBroadcastSupport";

    public final static String TEMPLATE_SERVICE
            = JNDI_GLOBAL + USERCOMM_SERVICES_MODULE_NAME + "/TemplateServiceImpl!ca.bc.gov.educ.isd.communication.TemplateService";

    public final static String BCEID_SERVICES
            = JNDI_GLOBAL + BCEID_SERVICES_MODULE_NAME + "/BCeIDAdaptorImpl!ca.bc.gov.educ.isd.eis.idim.bc.BCeIDAdaptor";

    public static final String JMS_DATACHANGE_TOPIC_LOOKUP = "topic/ISD-EventServices-ejb/DataChangeEvent";
    public static final String JMS_CONNECTION_FACTORY = "ConnectionFactory";
    public static final String JMS_MESSAGE_COMPONENT_KEY = "COMPONENT_KEY";
    public static final String JMS_MESSAGE_SERVICENAME_KEY = "SERVICENAME_KEY";
    public static final String JMS_MESSAGE_ENTITYNAME_KEY = "ENTITYNAME_KEY";
    public static final String JMS_MESSAGE_STATUS_KEY = "STATUS_KEY";
    public static final String JMS_MESSAGE_OPERATION_KEY = "OPERATION_KEY";
    public static final String JMS_MESSAGE_ENTITY_ID_KEY = "ENTITY_ID_KEY";
    public static final String JMS_MESSAGE_LAST_UPDATEBY_KEY = "LAST_UPDATEBY_KEY";
    public static final String JMS_MESSAGE_LAST_UPDATEON_KEY = "LAST_UPDATEON_KEY";

    public static final String JMS_TEMPORALCHANGE_TOPIC_LOOKUP = "topic/ISD-EventServices-ejb/TemporalEvent";
    public static final String JMS_MESSAGE_YEAR_KEY = "YEAR_KEY";
    public static final String JMS_MESSAGE_MONTH_KEY = "MONTH_KEY";
    public static final String JMS_MESSAGE_DAY_KEY = "DAY_KEY";
    public static final String JMS_MESSAGE_HOUR_KEY = "HOUR_KEY";
    public static final String JMS_MESSAGE_MINUTE_KEY = "MINUTE_KEY";
    public static final String JMS_MESSAGE_SECOND_KEY = "SECOND_KEY";

    public static final String JMS_TOPIC = "javax.jms.Topic";
    public static final String JMS_SUBSCRIPTION_DURABLE = "Durable";

    public static final String JMS_SALES_ORDER_FULFULLMENT_MDB = "SalesOrderFulfillmentMDB";
    public static final String JMS_SALES_ORDER_CANCELLATION_MDB = "SalesOrderCancellationMDB";
    public static final String JMS_NONPEN_SALES_ORDER_FULFILLMENT_MDB = "NonPENSalesOrderFulfillmentMDB";
    public static final String JMS_GED_SALES_ORDER_FULFILLMENT_MDB = "GEDSalesOrderFulfillmentMDB";
    public static final String JMS_DATA_CHANGE_NONPEN_EVENT_MDB = "DataChangeNonPENEventMDB";
    public static final String JMS_DATA_CHANGE_GED_EVENT_MDB = "DataChangeGEDEventMDB";
}

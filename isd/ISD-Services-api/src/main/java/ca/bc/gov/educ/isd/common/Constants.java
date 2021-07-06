/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Constants.java 12336 2019-12-13 19:30:20Z cfunk $
 *  Date of Last Commit: $Date:: 2019-12-13 11:30:20 -0800 (Fri, 13 Dec 2019)  $
 *  Revision Number:     $Rev:: 12336                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class contains any constants required for ISD.
 *
 * FIXME: this is in the common package, so only constants that are common to
 * all components should be here. many of these are specific to a component and
 * should be in the service package for that component.
 *
 * FIXME: Remove duplication with ca.bc.gov.educ.isd.test.util.Constants.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class Constants {

    /**
     * Prevent instantiation.
     *
     * @see
     * https://jtechies.blogspot.ca/2012/07/item-19-use-interfaces-only-to-define.html
     */
    private Constants() {
    }

    public static final String BCEID_MODULE_NAME = "ISD-BCeIDAdaptor-EIS";
    public static final String BCMP_SERVICES_MODULE_NAME = "ISD-BCMPServices-ejb";
    public static final String CODESET_SERVICES_MODULE_NAME = "ISD-CodeSetServices-ejb";
    public static final String COMMON_SUPPORT_MODULE_NAME = "ISD-CommonSupport";
    public static final String COMMUNICATION_SERVICES_MODULE_NAME = "ISD-UserCommServices-ejb";
    public static final String EVENT_SERVICES_MODULE_NAME = "ISD-EventServices-ejb";
    public static final String PAYMENT_SERVICES_MODULE_NAME = "ISD-PaymentServices-ejb";
    public static final String PSI_REGISTRY_SERVICES_MODULE_NAME = "ISD-PSIRegistryServices-ejb";
    public static final String REPORT_SERVICES_APP_NAME = "ISD-ReportServices";
    public static final String REPORT_SERVICES_MODULE_NAME = "ISD-ReportServices-ejb";
    public static final String BCEID_SERVICES_MODULE_NAME = "ISD-BCeIDAdaptor-EIS";
    public static final String SERVICES_API_MODULE_NAME = "ISD-Services-api";
    public static final String STUDENT_DEMOG_SERVICES_MODULE_NAME = "ISD-StudentDemoServices-ejb";
    public static final String TASK_SERVICES_MODULE_NAME = "ISD-TaskServices-ejb";
    public static final String TEST_SUPPORT_MODULE_NAME = "ISD-TestSupport";
    public static final String TRANSCRIPT_SERVICES_MODULE_NAME = "ISD-TranscriptServices-ejb";
    public static final String TRAX_MODULE_NAME = "ISD-TRAXAdaptor-ejb";
    public static final String USERCOMM_SERVICES_MODULE_NAME = "ISD-UserCommServices-ejb";
    public static final String USERPROCESS_SERVICES_MODULE_NAME = "ISD-UserProcess-ejb";
    public static final String USERPROFILE_SERVICES_MODULE_NAME = "ISD-UserServices-ejb";
    public static final String WEB_SUPPORT_MODULE_NAME = "ISD-WebSupport";

    public static final String API_PREFIX = "API.";
    public static final String TASK_PREFIX = "TASK.";
    public static final String CODESET_PREFIX = "CODESET.";
    public static final String PAYMENT_PREFIX = "ECOMMERCE.";
    public static final String REPORTSERVICES_PREFIX = "REPORTSERVICES.";
    public static final String ADMINREPORTSERVICES_PREFIX = "ADMINREPORTSERVICES.";
    public static final String BCEID_PREFIX = "BCEID.";
    public static final String ADMIN_PREFIX = "ADMIN.";
    public static final String TRANSCRIPT_PREFIX = "TRANSCRIPT.";
    public static final String TRAX_PREFIX = "TRAX.";
    public static final String USERPROFILEADMIN_PREFIX = "USERPROFILEADMIN.";
    public static final String EXAM_PREFIX = "EXAM.";
    public static final String SCHOLARSHIP_PREFIX = "SCHOLARSHIP.";
    public static final String USER_PREFIX = "USER.";
    public static final String STUDENT_PREFIX = "STUD.";
    public static final String TESTUSER_PREFIX = "TESTUSER.";
    public static final String EVENT_SERVICES_PREFIX = "EVENTS.";
    public static final String CERTIFICATE_PREFIX = "CERTIFICATE.";
    public static final String USERCOMM_PREFIX = "USERCOMM.";
    public static final String PSIREGISTRY_PREFIX = "PSIREGISTRY.";

    public static final String CSF_FRENCH_DOGWOOD = "CSF French Dogwood";
    public static final String FRENCH_DOGWOOD = "French Dogwood";
    public static final String ENGLISH_DOGWOOD = "English Dogwood";
    public static final String ENGLISH_DOGWOOD_IND = "English Dogwood Independent School";
    public static final String ENGLISH_DOGWOOD_ADULT = "English Dogwood Adult Program";
    public static final String ENGLISH_DOGWOOD_ADULT_IND = "English Dogwood Adult Program Independent School";
    public static final String SCCP_CERTIFICATE = "SCCP Certificate";

    /**
     * @deprecated Use PaperType.CERTIFICATE_REGULAR
     */
    public static final String CERTIFICATE_YEDR_MEDIA_TYPE = "YEDR";
    /**
     * @deprecated Use PaperType.CERTIFICATE_ADULT
     */
    public static final String CERTIFICATE_YEDB_MEDIA_TYPE = "YEDB";
    /**
     * @deprecated Use PaperType.CERTIFICATE_SCCP
     */
    public static final String CERTIFICATE_SCCP_MEDIA_TYPE = "YED2";

    // Email Template Names
    public static final String USER_PROFILE_ACT_EXCEED_TEMP = "UserProfileActivationExceededTemplate";
    public static final String USER_PROFILE_INVALID_STATUS_TEMP = "UserProfileInvalidStatusTemplate";
    public static final String USER_PROFILE_ACT_REQUEST_TEMP = "UserProfileActivationRequestTemplate";
    public static final String USER_PROFILE_REBIND_TEMP = "UserProfileRebindTemplate";
    public static final String THIRD_PARTY_TRANSCRIPT_RECIPIENT_TEMP = "ThirdPartyTranscriptRecipientTemplate";
    public static final String ETRANSCRIPT_COLL_STUDENT_CONF_TEMP = "ETranscriptCollectedStudentConfirmationTemplate";
    public static final String ETRANSCRIPT_SUSPEND_FAILED_ATTEMPT_TEMP = "ETranscriptSuspendedFailedAttemptStudentTemplate";
    public static final String USER_PROFILE_ACT_NEW_EMAIL_TEMP = "UserProfileActivateNewEmailTemplate";
    public static final String USER_PROFILE_NOTIFY_OLD_EMAIL_TEMP = "UserProfileNotifyOldEmailTemplate";
    public static final String CHECKOUT_CHEQUE_MONEYORDER_TEMP = "CheckoutChequeMoneyOrderTemplate";
    public static final String CHECKOUT_BEANSTREAM_TEMP = "CheckoutBeanstreamTemplate";
    public static final String GED_ORDER_COMPLETED_TEMP = "GEDorderCompletedTemplate";
    public static final String GED_CREDIT_CARD_CANCELLED_TEMP = "GEDcreditCardOrderCancelledTemplate";
    public static final String GED_NON_CREDIT_CARD_CANCELLED_TEMP = "GEDnonCreditCardOrderCancelledTemplate";
    public static final String PEN_NON_CREDIT_CARD_TRANS_CERT_CANCELLED_TEMP = "PENtransCertNonCreditCardOrderCancelledTemplate";
    public static final String NONPEN_TRANS_CERT_ORDER_COMPLETED_TEMP = "NONPENtransCertOrderCompletedTemplate";
    public static final String NONPEN_TRANS_CERT_CREDIT_CARD_CANCELLED_TEMP = "NONPENtransCertCreditCardOrderCancelledTemplate";
    public static final String NONPEN_TRANS_CERT_NON_CREDIT_CARD_CANCELLED_TEMP = "NONPENtransCertNonCreditCardOrderCancelledTemplate";
    public static final String USER_PROFILE_REG_ISSUE_RESOLVED_TEMP = "UserProfileRegIssueResolvedTemplate";
    public static final String USER_PROFILE_RESTRICT_ACCESS_RESOLVED_TEMP = "UserProfileRestrictAccessIssueResolvedTemplate";
    public static final String NONPEN_GED_CREDIT_CARD_ORDER_SUBMITTED_TEMP = "NONPENorGEDCreditCardOrderSubmitted";
    public static final String EMAIL_NOTIFICATION_BASIC = "EmailNotificationBasicTemplate";
    public static final String BCCAMPUS_TRANSMISSION_ERROR = "BCcampusTransmissionErrorTemplate";
    public static final String EMAIL_NOTIFICATION_ORDER_CANCEL_TEMP = "EmailNotificationOrderCancelledTemplate";

    public static final String ANONYMOUS = "anonymous";

    /**
     * User Notification Template Names
     */
    public static final String USER_NOTIFICATION_BASIC = "UserNotificationBasicTemplate";
    public static final String MAILPLUS_NOTIFICATION_TEMP = "MailPlusNotificationBasicTemplate";
    public static final String BCMP_EMAIL_ADDRESS_JNDI = "ISD-BCMailPlusAdaptor-EIS-rar/emailAddress";

    /**
     * Parameter names used when getting and setting values for Maps (ie Task
     * Attributes, Email substitution variables)
     */
    public static final String EMAIL_NOTIFICATION_PARAM_NAME = "email";
    public static final String USER_NOTIFICATION_PARAM_NAME = "user";
    public static final String ACTIVATION_PARAM_NAME = "activationCode";
    public static final String PROFILE_EID = "profileEid";
    public static final String PEN = "pen";
    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String DOB = "dateOfBirth";
    public static final String LAST_THREE_SIN = "lastThreeSIN";
    public static final String FORMER_NAME = "formerName";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String PROVINCE = "province";
    public static final String COUNTRY = "country";
    public static final String POSTAL_CODE = "postalCode";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String PAYMENT_METHOD = "paymentMethod";
    public static final String NUMBER_OF_TRANSCRIPTS = "numberOfTranscripts";
    public static final String NUMBER_OF_CERTIFICATES = "numberOfCertificates";
    public static final String TASK_NON_PEN_DESCR = "Non-PEN Transcript and Certificate Order";
    public static final String TASK_GED_DESCR = "GED Order";
    public static final String TASK_PRIORITY = "5";
    public static final String SESSION_YEAR_GED = "sessionYearGed";
    public static final String TEST_CENTRE_NAME_GED = "testCentreNameGed";
    public static final String TASK_NOTES_NON_PEN_ORDER = "Task notes for Non-PEN Order";
    public static final String TASK_NOTES_GED_ORDER = "Task notes for GED Order";
    public static final String TRANSCRIPT_IDENTIFIER = "TRANSCRIPT";
    public static final String CERTIFICATE_IDENTIFIER = "CERTIFICATE";
    public static final String RECIPIENT_NAME = "recipientName";
    public static final String SECONDARY_SCHOOL_GRADE = "secondarySchoolGrade";
    public static final String SECONDARY_SCHOOL_NAME = "secondarySchoolName";
    public static final String SECONDARY_SCHOOL_YEAR = "secondarySchoolYear";
    public static final String ERROR_CAUSE_MSG = "errorCauseMsg";
    public static final String ERROR_RESPONSE_MSG = "errorResponseMsg";
    public static final String ONLINE_TRANSACTION_ID = "onlineTransactionId";
    public static final String PROCESSED_LINFILE_LIST = "processedLINFileList";
    public static final String DISCREPENCY_LIST = "discrepancyList";

    // ///////////////////////////////////////////////////////////////////
    //
    // E-Transcript Session Variable Key Name Constants
    //
    // ///////////////////////////////////////////////////////////////////
    public static final String ETRANSCRIPT_DOCUMENT_DOWNLOAD_ID = "downloadId";
    public static final String ETRANSCRIPT_CHALLENGE_RESPONSE = "challengeResponse";

    // ///////////////////////////////////////////////////////////////////
    //
    // Date Formats
    //
    // Common date formats used throughout the application.
    //
    // ///////////////////////////////////////////////////////////////////
    /**
     * 4-digit year
     */
    public static final String DATE_YEAR = "yyyy";
    /**
     * 4-digit year, 2-digit month
     */
    public static final String DATE_YEAR_MONTH = "yyyy-MM";
    /**
     * ISO 8601 full date
     */
    public static final String DATE_ISO_8601_FULL = "yyyyMMMdd_HH'h'mm'm'";
    /**
     * ISO 8601 short date (4-digit year, 2-digit month, 2-digit day)
     */
    public static final String DATE_ISO_8601_YMD = "yyyy-MM-dd";

    /**
     * @see https://tools.ietf.org/html/rfc7231#section-7.1.1.1
     */
    public static final String DATE_RFC_1123 = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * 4-digit year (yyyyMMdd)
     */
    public static final String DATE_TRAX_YMD = "yyyyMMdd";
    /**
     * 4-digit year, no day (yyyyMM)
     */
    public static final String DATE_TRAX_YM = "yyyyMM";

    /**
     * Used to set the filename as per business requirement BR095 and BR110
     * (inside the XPIF XML header as well as the file name used during
     * transmission to BCMP).
     */
    public static final String DATE_SAFE_FILENAME = "yyyyMMdd.hhmmss";

    /**
     * Full month, day, year.
     */
    public static final String DATE_NATURAL_1 = "MMMMM d, yyyy";

    /**
     * 3-letter month, day, year.
     */
    public static final String DATE_NATURAL_2 = "MMMM dd, yyyy";

    /**
     * Year, 3-letter month, day.
     */
    public static final String DATE_UNAMBIGUOUS_YMD = "yyyy-MMM-dd";

    /**
     * 2-digit month / day / year and time.
     */
    public static final String DATE_PAYMENT_MERCHANT = "MM/dd/yyyy hh:mm:ss aa";

    /**
     * 2-digit month, 2-digit day, 2-digit hour, minute, and second.
     */
    public static final String DATE_PSI = "YYddhhmmss";

    /**
     * Event log date format (4-digit year, 3-digit month, 2-digit day, hour,
     * minute).
     */
    public static final String DATE_EVENT_LOG = "yyyy-MMM-dd HH:mm";

    /**
     * Session date format used on transcript and exam reports.
     */
    public static final String DATE_REPORT_SESSION = "yyyy/MM";

    /**
     * TODO: Replace DATE_PESC_YMD references with DATE_ISO_8601_YMD.
     */
    public static final String DATE_PESC_YMD = DATE_ISO_8601_YMD;
    public static final String DATE_PESC_CREATED_ON = "yyyy-MM-dd'T'HH:mm:ss";

    // ///////////////////////////////////////////////////////////////////
    //
    // PESC-related constants
    //
    // Used to create PESC XML documents
    //
    // ///////////////////////////////////////////////////////////////////
    /**
     * TODO: Replace with DATE_PESC_YM references with DATE_YEAR_MONTH.
     */
    public static final String DATE_PESC_YM = DATE_YEAR_MONTH;

    public static final String GUID_PATTERN = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

    public static final Date DATE_UNKNOWN_BIRTHDATE = (new GregorianCalendar(1900, 00, 01)).getTime();

    public static final String PESC_HST_PREDICATE = "HighSchoolTranscript";
    public static final String PESC_TR_PREDICATE = "TranscriptResponse";

    /**
     * Delimiter used in XML doc creation
     */
    public static final String XML_DELIM = "/";

    // ///////////////////////////////////////////////////////////////////
    //
    // Sales Order Status Identifiers
    //
    // Used in admin reports and payment services
    //
    // ///////////////////////////////////////////////////////////////////
    public static final String FULFILLMENT_IDENTIFIER = "FULFILLMENT";
    public static final String PLACED_IDENTIFIER = "PLACED";
    public static final String PAID_IDENTIFIER = "PAID";
    public static final String COMPLETE_IDENTIFIER = "COMPLETE";
    public static final String CANCELLED_IDENTIFIER = "CANCELLED";

    // ///////////////////////////////////////////////////////////////////
    //
    // Account type Identifiers
    //
    // Used in Admin and Student transcripts
    //
    // ///////////////////////////////////////////////////////////////////
    public static final String INDIVIDUAL_IDENTIFIER = "Individual";
    public static final String BUSINESS_IDENTIFIER = "Business";
    public static final String INTERNAL_IDENTIFIER = "Internal";
    public static final String BCSERVICE_CARD_NONPHOTO_IDENTIFIER = "BCSERVICE_CARD_NONPHOTO";
    public static final String BCSERVICE_CARD_PHOTO_IDENTIFIER = "BCSERVICE_CARD_PHOTO";
    public static final String PERSONAL_BCEID_IDENTIFIER = "PERSONAL_BCEID";
    public static final String VERIFIED_INDIVIDUAL_IDENTIFIER = "VerifiedIndividual";

    // ///////////////////////////////////////////////////////////////////
    //
    // Account type Values
    //
    // Used in Admin and Student transcripts
    //
    // ///////////////////////////////////////////////////////////////////
    public static final String INDIVIDUAL_VALUE = "BCeID Individual";
    public static final String BUSINESS_VALUE = "BCeID Business";
    public static final String INTERNAL_VALUE = "IDIR";
    public static final String BCSERVICE_CARD_NONPHOTO_VALUE = "BCService Card Non-Photo";
    public static final String BCSERVICE_CARD_PHOTO_VALUE = "BCService Card Photo";
    public static final String PERSONAL_BCEID_VALUE = "Personal BCeID";

    // ///////////////////////////////////////////////////////////////////
    //
    // Account type issuing organization
    //
    // Used in Admin and Student transcripts
    //
    // ///////////////////////////////////////////////////////////////////
    public static final String BCEID = "BCeID";
    public static final String BC_SERVICES = "BC Services";
    public static final String IDIR = "IDIR";
    public static final String SDE_ROLEGROUP_PREFIX = "SDE_";
    // ///////////////////////////////////////////////////////////////////
    //
    // Payment Status identifiers
    //
    // Used by Receipt report and by Payment Receipt Beans.
    //
    // ///////////////////////////////////////////////////////////////////
    public static final String PAYMENT_APPROVED_STATUS = "Approved";
    public static final String PAYMENT_DECLINED_STATUS = "Declined";

    /**
     * FIXME: Move to EIS layer.
     *
     * FIXME: Make an integer.
     *
     * Merchant session expiry in minutes.
     */
    public static final double BEANSTREAM_EXPIRY_SESSION_MINS = 30.0;

    // ///////////////////////////////////////////////////////////////////
    //
    // Service Constants
    //
    // ///////////////////////////////////////////////////////////////////
    /**
     * Key created by Shopping Cart for Transaction Processor Servlet.
     */
    public static final String PAYMENT_SEQUENCE_ID = "PaymentSequenceId";

    /**
     * Parameter key created by the Transaction Processor Servlet.
     */
    public static final String SALES_ORDER_NUMBER = "salesOrderNumber";

    /**
     * Session key name for unofficial transcript binary data (PDF).
     */
    public static final String TRANSCRIPT_PREVIEW = "StudentTranscriptPreview";

    /**
     * Session key name for Provincial Exams and Assessments Report (PDF).
     */
    public static final String PEAREPORT_PREVIEW = "PEAReportPreview";

    /**
     * Session key name for General Numeracy Assessment Report (GNA).
     */
    public static final String NUMERACY_ASSESSMENT_PREVIEW = "GNAReportPreview";
    
    /**
     * Session key name for General Literacy Assessment Report (GLA).
     */
    public static final String LITERACY_ASSESSMENT_PREVIEW = "GLAReportPreview";

    /**
     * Constant for the session ID cookie name.
     */
    public static final String APPLICATION_COOKIE_NAME = "JSESSIONID";

    // ///////////////////////////////////////////////////////////////////
    //
    // TRAX
    //
    // Used TRAX adaptor and anything related with it.
    //
    // ///////////////////////////////////////////////////////////////////
//    public static final String CANADA_CODE_TRAX = "CN";
    public static final String CANADA_CODE_ISO = "CA";

    public static final String BRITISH_COLUMBIA_PROV = "BC";

    public static final String STUDENT_GRADE_12 = "12";
    public static final String STUDENT_GRADE_AD = "AD";
}

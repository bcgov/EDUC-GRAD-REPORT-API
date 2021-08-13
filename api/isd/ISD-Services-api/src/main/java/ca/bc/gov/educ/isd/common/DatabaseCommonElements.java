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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *  Revision Number:     $Rev:: 36                                             $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

/**
 * Database column names, sizes, and types based on the Common Education Data
 * Standard. (CDES) In addition to other common meta-data values.
 *
 * @see https://ceds.ed.gov/dataModelNDS.aspx
 * @author CGI Information Management Consultants Inc.
 */
public final class DatabaseCommonElements {

    // *******************************************************************
    // Default values
    // *******************************************************************
    public static final String DELIMITER = "_";

    public static final String COL_OTHER_DEFAULT_TOTAL = "TOTAL";

    public static final int COL_DEFAULT_YEAR_LEN = 4;

    public static final String COL_ORDER_DATE = "ORDER_DATE";

    public static final String COL_DEFAULT_PEN = "PEN";
    public static final int COL_DEFAULT_PEN_LEN = 9;

    public static final String COL_DEFAULT_TELEPHONE_NUMBER = "TELEPHONENUMBER";

    public static final int COL_DEFAULT_TELEPHONE_NUMBER_LEN = 24;

    public static final String COL_DEFAULT_NAME = "COL_DEFAULT_NAME";
    public static final int COL_DEFAULT_NAME_LEN = 35;

    public static final String COL_DEFAULT_CITY = "COL_DEFAULT_CITY";
    public static final int COL_DEFAULT_CITY_LEN = 30;

    // It is very hard to find a common standard that encompasses all the possible
    // forms taken by a postal code/area code/zip code.  Use the arbitrary values
    // specified below till further notice.
    public static final String COL_DEFAULT_POSTALCODE_MINIMUM = "COL_DEFAULT_POSTALCODE_MINIMUM";
    public static final int COL_DEFAULT_POSTALCODE_MINIMUM_LEN = 1;

    public static final String COL_DEFAULT_POSTALCODE_MAXIMUM = "COL_DEFAULT_POSTALCODE_MAXIMUM";
    public static final int COL_DEFAULT_POSTALCODE_MAXIMUM_LEN = 9;

    // *******************************************************************
    // CDES Person definitions
    // *******************************************************************
    public static final String COL_PERSON_FIRSTNAME = "FIRSTNAME";

    public static final int COL_PERSON_FIRSTNAME_LEN = COL_DEFAULT_NAME_LEN;

    public static final String COL_PERSON_MIDDLENAME = "MIDDLENAME";

    public static final int COL_PERSON_MIDDLENAME_LEN = COL_DEFAULT_NAME_LEN;

    public static final String COL_PERSON_LASTNAME = "LASTNAME";

    public static final int COL_PERSON_LASTNAME_LEN = COL_DEFAULT_NAME_LEN;

    public static final String COL_PERSON_FORMERNAME = "FORMERNAME";

    public static final int COL_PERSON_FORMERNAME_LEN = 150;

    public static final String COL_PERSON_BIRTHDATE = "BIRTHDATE";

    // *******************************************************************
    // CDES Person Address definitions
    // *******************************************************************
    public static final String COL_PERSONADDRESS_CITY = "CITY";

    public static final int COL_PERSONADDRESS_CITY_LEN = COL_DEFAULT_CITY_LEN;

    public static final int COL_PERSONADDRESS_LEN = 35;

    public static final int COL_PERSONADDRESS_APERTMENTROOMORSUITENUMBER_LEN = COL_PERSONADDRESS_LEN;

    public static final String COL_REFSTATE_STATENAME = "PROVINCE";

    public static final int COL_REFSTATE_STATENAME_LEN = 100;

    public static final String COL_COUNTRY = "COUNTRY";

    public static final int COL_COUNTRY_LEN = 70;

    public static final String COL_PERSONADDRESS_POSTALCODE = "POSTALCODE";

    public static final int COL_PERSONADDRESS_POSTALCODE_LEN = 71;

    public static final String COL_EMAIL_ADDRESS = "EMAILADDRESS";

    public static final int COL_EMAIL_ADDRESS_LEN = 128;

    public static final String COL_PERSON_TELEPHONE_NUMBER = COL_DEFAULT_TELEPHONE_NUMBER;

    public static final int COL_PERSON_TELEPHONE_NUMBER_LEN = COL_DEFAULT_TELEPHONE_NUMBER_LEN;

    // *******************************************************************
    // CDES Organisation definitions
    // *******************************************************************
    /**
     * The name or title of a non-person entity such as an organization,
     * institution, agency, business, program or course. [CEDS Elements:
     * Organization Name (000204), Name of Institution (000191), Program Name
     * (000626), Responsible Organization Name (000631), Course Title (000067),
     * Postsecondary Course Title (000068)]
     */
    public static final String COL_ORGANIZATION_NAME = "NAME";

    /**
     * Maximum length of the Organization Name.
     */
    public static final int COL_ORGANIZATION_NAME_LEN = 60;

    // *******************************************************************
    // CDES Country reference
    // *******************************************************************
    public static final String COL_REFCOUNTRY_CODE = "COUNTRY_CD";

    // seems a bit big, but needs to atleast contain the Canada Post Address Complete value.
    public static final int COL_REFCOUNTRY_CODE_LEN = 50;

    // *******************************************************************
    // Other data field names such as the local GED program and simple
    // non-normalized values.
    // *******************************************************************
    /**
     * A simple one line address that combines CDES
     * PersonAddress.StreetNameAndNumber with
     * PersonAddress.AppartmentRoomOrSuiteNumber.
     */
    public static final String COL_SIMPLEADDRESS = "ADDRESS";

    /**
     * Combined length of CDES PersonAddress.StreetNameAndNumber with
     * PersonAddress.AppartmentRoomOrSuiteNumber plus a space.
     */
    public static final int COL_SIMPLEADDRESS_LEN = COL_PERSONADDRESS_LEN + COL_PERSONADDRESS_APERTMENTROOMORSUITENUMBER_LEN + 1;

    public static final String COL_SIMPLEADDRESS_NAME = "NAME";

    public static final int COL_SIMPLEADDRESS_NAME_LEN = 50;

    public static final String COL_SIMPLEADDRESS_STREET_1 = "STREET_LINE_1";

    public static final int COL_SIMPLEADDRESS_STREET_1_LEN = COL_PERSONADDRESS_LEN;

    public static final String COL_SIMPLEADDRESS_STREET_2 = "STREET_LINE_2";

    public static final int COL_SIMPLEADDRESS_STREET_2_LEN = COL_SIMPLEADDRESS_STREET_1_LEN;

    public static final String COL_SIMPLEADDRESS_STREET_3 = "STREET_LINE_3";

    public static final int COL_SIMPLEADDRESS_STREET_3_LEN = COL_SIMPLEADDRESS_STREET_1_LEN;

    public static final String COL_SIMPLEADDRESS_CITY = COL_PERSONADDRESS_CITY;

    public static final int COL_SIMPLEADDRESS_CITY_LEN = COL_DEFAULT_CITY_LEN;

    public static final String COL_SIMPLEADDRESS_STATE = COL_REFSTATE_STATENAME;

    public static final int COL_SIMPLEADDRESS_STATE_LEN = COL_REFSTATE_STATENAME_LEN;

    public static final String COL_SIMPLEADDRESS_COUNTRY = COL_REFCOUNTRY_CODE;

    public static final int COL_SIMPLEADDRESS_COUNTRY_LEN = COL_REFCOUNTRY_CODE_LEN;

    public static final String COL_SIMPLEADDRESS_POSTALCODE = COL_PERSONADDRESS_POSTALCODE;

    public static final int COL_SIMPLEADDRESS_POSTALCODE_LEN = COL_PERSONADDRESS_POSTALCODE_LEN;

    public static final String COL_GRADUATED_YEAR = "GRADUATE_YEAR";
    public static final int COL_GRADUATED_YEAR_LEN = COL_DEFAULT_YEAR_LEN;

    public static final String COL_SECONDARYSCHOOL_YEAR = "SECONDARYSCHOOL_YEAR";
    public static final int COL_SECONDARYSCHOOL_YEAR_LEN = COL_DEFAULT_YEAR_LEN;

    public static final String COL_REGISTRATIONLOCKED_YEAR = "REGISTRATION_LOCKED_YEAR";
    public static final int COL_REGISTRATIONLOCKED_YEAR_LEN = COL_DEFAULT_YEAR_LEN;

    // *******************************************************************
    // CDES TRAX
    // *******************************************************************
    // Common PSI objects
    public static final String COL_STUD_NO = "STUD_NO";
    public static final int COL_STUD_NO_LEN = 10;

    public static final int PSI_ADDRESS_LEN = 40;
    public static final int PSI_NAME_LEN = 40;

    // PSI Registry
    public static final String COL_PSI_CODE = "PSI_CODE";
    public static final int COL_PSI_CODE_LEN = 3;

    public static final String COL_PSI_NAME = "PSI_NAME";
    public static final int COL_PSI_NAME_LEN = PSI_NAME_LEN;

    public static final String COL_PSI_CSL_CODE = "PSI_CSL_CODE";
    public static final int COL_PSI_CSL_CODE_LEN = 4;

    public static final String COL_PSI_ATTN_NAME = "PSI_ATTN_NAME";
    public static final int COL_PSI_ATTN_NAME_LEN = PSI_NAME_LEN;

    public static final String COL_PSI_ADDRESS1 = COL_SIMPLEADDRESS + "1";
    public static final int COL_PSI_ADDRESS1_LEN = PSI_ADDRESS_LEN;

    public static final String COL_PSI_ADDRESS2 = COL_SIMPLEADDRESS + "2";
    public static final int COL_PSI_ADDRESS2_LEN = PSI_ADDRESS_LEN;

    public static final String COL_PSI_ADDRESS3 = COL_SIMPLEADDRESS + "3";
    public static final int COL_PSI_ADDRESS3_LEN = PSI_ADDRESS_LEN;

    public static final String COL_PSI_CITY = COL_PERSONADDRESS_CITY;
    public static final int COL_PSI_CITY_LEN = COL_DEFAULT_CITY_LEN;

    public static final String COL_PSI_PROV_CODE = "PROV_CODE";
    public static final int COL_PSI_PROV_CODE_LEN = 2;

    public static final String COL_PSI_CNTRY_CODE = "CNTRY_CODE";
    public static final int COL_PSI_CNTRY_CODE_LEN = 2;

    public static final String COL_OPEN_FLAG = "OPEN_FLAG";
    public static final int COL_OPEN_FLAG_LEN = 1;

    public static final String COL_GLOBAL_SIGN_OFF = "GLOBAL_SIGN_OFF";
    public static final int COL_GLOBAL_SIGN_OFF_LEN = 1;

    public static final String COL_PSI_POSTAL = "PSI_POSTAL";
    public static final int COL_PSI_POSTAL_LEN = 9;

    public static final String COL_PSI_FAX = "FAX";
    public static final int COL_PSI_FAX_LEN = 12;

    public static final String COL_PSI_PHONE = "PHONE1";
    public static final int COL_PSI_PHONE_LEN = 12;

    public static final String COL_PSI_TRANSMISSION_MODE = "TRANSMISSION_MODE";
    public static final int COL_PSI_TRANSMISSION_MODE_LEN = 10;

    public static final String COL_PSIS_CODE = "PSIS_CODE";
    public static final int COL_PSIS_CODE_LEN = 8;

    public static final String COL_PSI_URL = "PSI_URL";
    public static final int COL_PSI_URL_LEN = 2000;

    public static final String COL_PSI_GROUPING = "PSI_GROUPING";
    public static final int COL_PSI_GROUPING_LEN = 3;

    // PSI Choices
    public static final String COL_PSI_PROCESS_DATE = "PROCESS_DATE";

    public static final String COL_PSI_STATUS = "STATUS";

    public static final String COL_PSI_SYNC_DATE = "SYNC_DATE";

    // *******************************************************************
    // Other data field names such as the local GED program and non-normalized
    // values.
    // *******************************************************************
    
    public static final String COL_SESSION_ID = "HTTPSESSIONID";
    public static final int COL_SESSION_ID_LEN = 30;
    /**
     * Local extension for the GED program.
     */
    public static final String COL_OTHER_GEDTESTCENTERNAME = "GED_TEST_CENTER_NAME";

    /**
     * Local extension for the GED program.
     */
    public static final int COL_OTHER_GEDTESTCENTERNAME_LEN = COL_ORGANIZATION_NAME_LEN;

    public static final String COL_GED_YEAR = "GED_SESSION_YEAR";
    public static final int COL_GED_YEAR_LEN = COL_DEFAULT_YEAR_LEN;

    public static final String COL_OTHER_PICONSENT_DATE = "PI_CONSENT";

    public static final String COL_OTHER_NUM_CERTS = "NUM_GRAD_CERT";

    public static final String COL_OTHER_NUM_XCRPT = "NUM_XCRPT";

    public static final String COL_OTHER_TOTALPRICE = COL_OTHER_DEFAULT_TOTAL + DELIMITER + "PRICE";

    public static final String COL_PAYMENT_TYPE = "PYMNT_TYPE";

    public static final String COL_PAYMENT_AMOUNT = "PYMNT_AMOUNT";

    public static final String COL_OTHER_ORDER_NUMBER = "ORDER_NUMBER";

    public static final String COL_OTHER_ORDER_STATUS = "ORDER_STATUS";

    public static final String COL_OTHER_ORDER_ITEM_STATUS = "ORDER_ITM_STATUS";

    public static final String COL_OTHER_SECURITY_QUESTION = "SEC_QUEST";

    public static final int COL_OTHER_SECURITY_QUESTION_LEN = 200;

    public static final String COL_OTHER_SECURITY_ANSWER = "SEC_ANSER";

    public static final String COL_RECIPIENT_NAME = "RECIP_NAME";

    public static final int COL_RECIPIENT_NAME_LEN = 128;

    public static final int COL_OTHER_SECURITY_ANSWER_LEN = 200;

    public static final String COL_OTHER_EXT_PRICE = "EXT_PRICE";

    public static final String COL_OTHER_QUANTITY = "QUANTITY";

    public static final String COL_ENT_QUANTITY = "ENT_QUANTITY";

    public static final String COL_OTHER_SENT_DATE = "SENT_DATE";

    /**
     * Local extension for the GED program, last 3 digits of the Social
     * Insurance Number.
     */
    public static final String COL_SINLAST3 = "SINLAST3";

    public static final int COL_SINLAST3_LEN = 3;

    public static final String COL_OTHER_GED_ORDER_DATE = COL_ORDER_DATE;

    public static final String COL_OTHER_EXPIRY_DATE = "EXP_DATE";

    public static final String COL_OTHER_NUM_ATTEMPTS = "ATMPTS";

    public static final String COL_OTHER_TRAX_PSI_CODE = "PSI_CODE";

    public static final int COL_OTHER_TRAX_PSI_CODE_LEN = 5;

    public static final String COL_PSI_PREF = "PSI_PREF";

    // TODO: https://jira.vic.cgi.com/browse/ST-806
    public static final int COL_PSI_PREF_LEN = 12;

    public static final String COL_OTHER_SEND_INTERIM = "INTERIM";

    public static final String COL_OTHER_DOWNLOADED = "DOWNLOADED";

    // System Notifications
    public static final String COL_SYSTEM_NOTIFICATION_CODE = "SEVERITY_CODE";

    public static final int COL_SYSTEM_NOTIFICATION_CODE_LEN = 20;

    public static final String COL_SYSTEM_NOTIFICATION_SUMMARY = "NOTIFICATION_SUMMARY";

    public static final int COL_SYSTEM_NOTIFICATION_SUMMARY_LEN = 250;

    public static final String COL_SYSTEM_NOTIFICATION_DETAIL = "NOTIFICATION_DETAIL";

    public static final int COL_SYSTEM_NOTIFICATION_DETAIL_LEN = 4000;

    // Automatic Notifications
    public static final String COL_AUTOMATIC_NOTIFICATION_SENDER_EMAIL = "SENDER_EMAIL";

    public static final int COL_AUTOMATIC_NOTIFICATION_SENDER_EMAIL_LEN = 100;

    public static final String COL_AUTOMATIC_NOTIFICATION_SUMMARY = "SUMMARY";

    public static final int COL_AUTOMATIC_NOTIFICATION_SUMMARY_LEN = 250;

    public static final String COL_AUTOMATIC_NOTIFICATION_DETAIL = "TEMPLATE";

    public static final int COL_AUTOMATIC_NOTIFICATION_DETAIL_LEN = 4000;

    /**
     * Extension for the SelfServeOrder view.
     */
    public static final String COL_HAS_TRANSCRIPTS = "HAS_TRANSCRIPTS";
    public static final String COL_HAS_CERTIFICATES = "HAS_CERTIFICATES";
    public static final String COL_DELIVERY_METHODS = "DELIVERY_METHODS";
    public static final String COL_CARD_TYPE = "CARD_TYPE";

    // *******************************************************************
    // User Profile
    // *******************************************************************
    public static final String COL_DISPLAY_NAME = "DISPLAYNAME";

    private DatabaseCommonElements() {
    }

}

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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.common;

/**
 * Non-instantiable utility class containing Constants being used in the EIS API
 * implementation. Note that these should directly align with the Constants
 * defined at the Services API level that call out to EIS services. This
 * duplication is required to enforce the separation between the layers.
 */
public class Constants {

    private Constants() {
    }

    /**
     * 4-digit year (yyyyMMdd)
     */
    public static final String DATE_TRAX_YMD = "yyyyMMdd";
    /**
     * 4-digit year, 2-digit month, no day (yyyyMM)
     */
    public static final String DATE_TRAX_YM = "yyyyMM";
    public static final String DATE_TRAX_YM_UPPER = DATE_TRAX_YM.toUpperCase();

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

    public static final String JMS_TEMPORALCHANGE_TOPIC_LOOKUP = "topic/ISD-EventServices-ejb/TemporalEvent";
    public static final String JMS_MESSAGE_YEAR_KEY = "YEAR_KEY";
    public static final String JMS_MESSAGE_MONTH_KEY = "MONTH_KEY";
    public static final String JMS_MESSAGE_DAY_KEY = "DAY_KEY";
    public static final String JMS_MESSAGE_HOUR_KEY = "HOUR_KEY";
    public static final String JMS_MESSAGE_MINUTE_KEY = "MINUTE_KEY";
    public static final String JMS_MESSAGE_SECOND_KEY = "SECOND_KEY";

    /**
     * Delimiter used in XML doc creation
     */
    public static final String XML_DELIM = "/";

    /**
     * A student PEN that is not in the system.
     */
    public static final String INVALID_STUDENT_PEN = "000000000";

    /**
     * Number of months after a student's most recent session that the student
     * can be considered a "former" student.
     */
    public static final int STUDENT_SESSION_EXPIRATION = 6;

    /**
     * Attributes of STUD_PROFILE_MASTER_JOINT_VW database view
     */
    public static final String COL_PEN = "PEN";
    public static final String COL_PROFILE_ID = "PROFILE_ID";
    public static final String COL_PROFILE_ENTITY_ID = "PROFILE_ENTITY_ID";
    public static final String COL_LAST_LOGIN_DATE = "LAST_LOGIN_DATE";
    public static final String COL_BIRTHDATE = "BIRTHDATE";
    public static final String COL_FIRST_NAME = "FIRST_NAME";
    public static final String COL_MIDDLE_NAME = "MIDDLE_NAME";
    public static final String COL_LAST_NAME = "LAST_NAME";

    public static final String ENTITY_STUDENT_PROFILE_MASTER_LITE = "STUD_PROFILE_MASTER_JOINT_VW";

    // for the file transfer with BC Mail Plus
    public static enum DocumentType {

        CERTIFICATE, TRANSCRIPT, RESPONSE_FILE, ANY;
    }
}

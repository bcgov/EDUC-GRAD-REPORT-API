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
package ca.bc.gov.educ.grad.model.common;

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
     */
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

    public static final String CSF_FRENCH_DOGWOOD = "CSF Program francophone Dogwood";
    public static final String FRENCH_DOGWOOD = "French Immersion Certificate";
    public static final String ENGLISH_DOGWOOD = "English Dogwood Public";
    public static final String ENGLISH_DOGWOOD_IND = "English Dogwood Independent School";
    public static final String ENGLISH_DOGWOOD_ADULT = "English Dogwood Adult Public";
    public static final String ENGLISH_DOGWOOD_ADULT_IND = "English Dogwood Adult Independent School";
    public static final String SCCP_CERTIFICATE = "School Completion Certificate English";

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

    public static final String PEN = "pen";

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
     */
    public static final String DATE_RFC_1123 = "EEE, dd MMM yyyy HH:mm:ss z";

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

    /**
     * Delimiter used in XML doc creation
     */
    public static final String XML_DELIM = "/";

}

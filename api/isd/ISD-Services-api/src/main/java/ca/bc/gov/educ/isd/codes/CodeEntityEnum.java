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
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.codes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ca.bc.gov.educ.isd.common.Constants.DATE_UNAMBIGUOUS_YMD;
import static java.lang.Boolean.TRUE;

/**
 * @author CGI Information Management Consultants Inc.
 */
public enum CodeEntityEnum {

    ProfileActivationLink("Profile Activation link", Integer.class),
    PdfAuthorizationLink("PDF Authorization Link", Integer.class),
    EmailActivationLink("Email Activation link", Integer.class),
    AllowableFailedSecurityAnswer("Security answer attempts for PDF download", Integer.class),
    AllowableFailedPenAttempts("PEN matches on profile creations", Integer.class),
    DaysAfterInitialSendDate("Default access window for XML PSIs (days)", Integer.class),
    RegisterPENifDOBAfter("Required to register with PEN if DOB after", Date.class),
    EmbargoMessageon("Turn embargo message on", Boolean.class),
    MessageForCurrentStudents("Message for Current students", String.class),
    SendLaterPSI("Send Later Options Available for Current Students", Boolean.class),
    AllowableSuccessfulDownloads("Allowable successful downloads", Integer.class),
    EnableUserProfileCache("Enable caching of user profiles", Boolean.class);

    private final String description;

    /**
     * Data type this enumerated value represents.
     */
    private final Class clazz;

    /**
     * Constructs a code entity using a given data type.
     *
     * @param description Human-friendly text to display for this option.
     * @param clazz Data type to use to indicate how to parse UI elements.
     */
    private CodeEntityEnum(final String description, final Class clazz) {
        this.description = description;
        this.clazz = clazz;
    }

    /**
     * Answers true if the given class is the same data type as the data type
     * that this enumerated type requires for data input conversion.
     *
     * @param clazz The data type to compare against.
     * @return false The data type differs.
     */
    public boolean isClass(final Class clazz) {
        return this.clazz == clazz;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Indicate that the code entity is active by default.
     *
     * @return "1.0"
     */
    public String getDefaultStatus() {
        return "1.0";
    }

    /**
     * Returns the default value for this code entity. Booleans are "true",
     * dates are the current date in DATE_UNAMBIGUOUS_YMD format, Strings are
     * empty, and Integers are 1.
     *
     * @return The default value, based on the enumeration's data type.
     */
    public String getDefaultValue() {
        final String result;

        if (isClass(Boolean.class)) {
            result = getDefaultBoolean();
        } else if (isClass(Date.class)) {
            result = getDefaultDate();
        } else if (isClass(Integer.class)) {
            result = getDefaultInteger();
        } else {
            result = getDefaultString();
        }

        return result;
    }

    private String getDefaultBoolean() {
        return TRUE.toString().toLowerCase();
    }

    private String getDefaultDate() {
        final Date date = new Date();
        final DateFormat df = new SimpleDateFormat(DATE_UNAMBIGUOUS_YMD);

        return df.format(date);
    }

    private String getDefaultInteger() {
        return "1";
    }

    private String getDefaultString() {
        return "";
    }

}

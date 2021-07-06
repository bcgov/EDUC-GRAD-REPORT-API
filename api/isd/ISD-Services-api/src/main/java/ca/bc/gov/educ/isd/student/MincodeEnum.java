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
package ca.bc.gov.educ.isd.student;

/**
 * @author CGI Information Management Consultants Inc.
 */
public enum MincodeEnum {

    CODE_098("098");

    private final String value;

    /**
     *
     * @param name
     */
    MincodeEnum(final String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param v
     * @return
     */
    public static MincodeEnum fromValue(final String v) {
        for (final MincodeEnum c : MincodeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }

    /**
     * If the code is more than 3 characters, this will use a substring compare
     * on the first three characters against the enumerated code value.
     *
     * @param code
     * @return
     */
    public boolean isCode(final String code) {
        boolean result = false;

        if (code != null && !code.isEmpty()) {
            final String check = code.length() > 3 ? code.substring(0, 3) : code;

            result = getValue().equals(check);
        }

        return result;
    }
}

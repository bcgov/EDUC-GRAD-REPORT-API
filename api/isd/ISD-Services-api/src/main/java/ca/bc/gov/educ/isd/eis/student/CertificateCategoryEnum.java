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
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.student;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum CertificateCategoryEnum {

    DOGWOOD("Dogwood"),
    SCCP("SCCP");

    private final String value;

    /**
     *
     * @param name
     */
    CertificateCategoryEnum(final String value) {
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
    public static CertificateCategoryEnum fromValue(final String v) {
        for (final CertificateCategoryEnum c : CertificateCategoryEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }
}

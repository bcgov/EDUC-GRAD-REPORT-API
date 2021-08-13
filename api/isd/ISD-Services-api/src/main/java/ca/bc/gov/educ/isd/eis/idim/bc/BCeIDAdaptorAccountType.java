/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        BCeIDAdaptorAccountType.java
 *  Date of Last Commit: $Date:: 2016-07-19 12:38:36 -0700 (Tue, 19 Jul 2016)  $
 *  Revision Number:     $Rev:: 2243                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.idim.bc;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum BCeIDAdaptorAccountType {

    VOID("Void"),
    INDIVIDUAL("Individual"),
    VERIFIED_INDIVIDUAL("VerifiedIndividual"),
    BUSINESS("Business"),
    INTERNAL("Internal"),
    LDB("LDB"),
    EDS("EDS"),
    THS("THS");

    private final String value;

    /**
     *
     * @return
     */
    BCeIDAdaptorAccountType(String v) {
        value = v;
    }

    /**
     *
     * @return
     */
    public String value() {
        return value;
    }

    /**
     *
     * @param v
     * @return
     */
    public static BCeIDAdaptorAccountType fromValue(String v) {
        for (BCeIDAdaptorAccountType c : BCeIDAdaptorAccountType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

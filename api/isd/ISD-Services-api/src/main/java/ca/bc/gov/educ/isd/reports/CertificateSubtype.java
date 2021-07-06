/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateSubtype.java 4160 2016-10-11 0#$
 *  Date of Last Commit: $Date:: 2016-10-10 17:23:03 -0700 (Mon, 10 Oct 2016)  $
 *  Revision Number:     $Rev:: 4160                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports;

/**
 * Represents the various subtypes of certificates. Note that francophone does
 * not mean French, but is a variation on the French certificates. Use setLocale
 * to produce a certificate written in French.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum CertificateSubtype {

    DEFAULT(""),
    INDEPENDENT("independent"),
    FRANCOPHONE("francophone");

    private final String subtype;

    /**
     * Constructs an enumerated value with a given subtype name.
     *
     * @param subtype The report subtype.
     */
    private CertificateSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * Returns this certificate report subtype.
     *
     * @return The report subtype.
     */
    @Override
    public String toString() {
        return this.subtype;
    }
}

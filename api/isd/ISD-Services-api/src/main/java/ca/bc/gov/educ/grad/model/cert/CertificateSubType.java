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
package ca.bc.gov.educ.grad.model.cert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * Represents the various subtypes of certificates. Note that francophone does
 * not mean French, but is a variation on the French certificates. Use setLocale
 * to produce a certificate written in French.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public enum CertificateSubType {

    REPRINT(""),
    BLANK("B"),
    ORIGINAL("O");

    private String subtype;

    CertificateSubType() {
    }

    /**
     * Constructs an enumerated value with a given subtype name.
     *
     * @param subtype The report subtype.
     */
    private CertificateSubType(String subtype) {
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

    @JsonCreator
    public static CertificateSubType forValue(@JsonProperty("subtype") final String subtype) {
        for (CertificateSubType certificateSubType : CertificateSubType.values()) {
            if (certificateSubType.name().equals(subtype)) {
                return certificateSubType;
            }
        }
        return null;
    }
}

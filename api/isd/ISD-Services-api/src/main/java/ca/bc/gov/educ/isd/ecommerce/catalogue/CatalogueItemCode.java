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
 *  File:                $Id:: CatalogueItemCode.java 6909 2017-04-25 22:55:49#$
 *  Date of Last Commit: $Date:: 2017-04-25 15:55:49 -0700 (Tue, 25 Apr 2017)  $
 *  Revision Number:     $Rev:: 6909                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.catalogue;

import ca.bc.gov.educ.isd.reports.CertificateType;
import ca.bc.gov.educ.isd.reports.TranscriptType;

import static ca.bc.gov.educ.isd.common.Constants.CERTIFICATE_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.TRANSCRIPT_IDENTIFIER;

/**
 * Represents item codes for catalog entities.
 *
 * @eee
 * ca.bc.gov.educ.isd.ecommerce.impl.fulfillment.internal.filter.CataloguePredicate
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum CatalogueItemCode {

    CERTIFICATE_ADULT(CERTIFICATE_IDENTIFIER + "_" + CertificateType.A.toString()),
    CERTIFICATE_REGULAR(CERTIFICATE_IDENTIFIER + "_" + CertificateType.E.toString()),
    CERTIFICATE_SCCP(CERTIFICATE_IDENTIFIER + "_" + CertificateType.SC.toString()),
    TRANSCRIPT_ALL(TRANSCRIPT_IDENTIFIER + "_" + TranscriptType.ALL.toString()),
    /**
     * Indicates a regular certificate that is printed in French (not
     * necessarily a Francophone certificate).
     */
    CERTIFICATE_REGULAR_FR(CERTIFICATE_IDENTIFIER + "_" + CertificateType.E.toString() + "_FR");

    private final String code;
    private boolean transcript;
    private boolean certificate;

    private CatalogueItemCode(final String code) {
        this.code = code;

        if (code.contains(TRANSCRIPT_IDENTIFIER)) {
            this.transcript = true;
        } else if (code.contains(CERTIFICATE_IDENTIFIER)) {
            this.certificate = true;
        }
    }

    /**
     * Represents a "TRANSCRIPT.*" code where the type (*) is determined by the
     * transcript type.
     *
     * @param tt The transcript type created.
     */
    private CatalogueItemCode(final TranscriptType tt) {
        this.code = TRANSCRIPT_IDENTIFIER + "_" + tt.toString();
        this.transcript = true;
    }

    /**
     * Represents a "CERTIFICATE.*" code where the type is determined by the
     * CertificateType (e.g., SCCP, Adult, Regular).
     *
     * @param ct The certificate type created.
     */
    private CatalogueItemCode(final CertificateType ct) {
        this.code = CERTIFICATE_IDENTIFIER + "_" + ct.toString();
        this.certificate = true;
    }

    /**
     * Convenience method to return the certificate type for this catalogue
     * item.
     *
     * @return The certificate type associated with the suffix.
     */
    public CertificateType getCertificateType() throws IllegalStateException {
        final String itemCode = toString().toUpperCase();
        final CertificateType ct;

        if (isCertificate()) {
            final String[] split = itemCode.split("_");
            ct = CertificateType.valueOf(split[1]);
        } else {
            throw new IllegalStateException("Not a certificate.");
        }

        return ct;
    }

    /**
     * Convenience method to answer whether this catalogue item is a
     * certificate.
     *
     * @return true Represents a certificate catalogue item.
     */
    public boolean isCertificate() {
        return this.certificate;
    }

    /**
     * Convenience method to answer whether this catalogue item is a transcript.
     *
     * @return true Represents a transcript catalogue item.
     */
    public boolean isTranscript() {
        return this.transcript;
    }

    @Override
    public String toString() {
        return this.code;
    }
}

/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateOrderTypeImpl.java 5479 2016-12-01 2#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.report.model.cert.CertificateOrderType;
import ca.bc.gov.educ.grad.report.model.cert.CertificateType;

/**
 * Responsible for creating order types that can print certificates on the
 * correct paper type.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class CertificateOrderTypeImpl extends OrderTypeImpl
        implements CertificateOrderType {

    private static final long serialVersionUID = 3L;

    private String name;
    private CertificateType certificateType;

    public CertificateOrderTypeImpl() {
    }

    /**
     * Constructs with paper type based on the certificate that was ordered.
     *
     * @param certificateType Type of certificate ordered.
     */
    public CertificateOrderTypeImpl(final CertificateType certificateType) {
        this.certificateType = certificateType;
        setPaperType(certificateType.getPaperType());
    }

    /**
     * Returns the human-readable name for certificates.
     *
     * @return "Certificates"
     */
    @Override
    public String getName() {
        return "Certificates";
    }

    public void setName(String name) {
        this.name = name;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
        this.setPaperType(this.certificateType.getPaperType());
    }

}

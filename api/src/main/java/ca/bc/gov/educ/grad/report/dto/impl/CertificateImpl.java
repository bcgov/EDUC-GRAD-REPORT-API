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
 *  File:                $Id:: CertificateImpl.java 6312 2017-02-25 00:29:23Z DAJA#$
 *  Date of Last Commit: $Date:: 2017-02-24 16:29:23 -0800 (Fri, 24 Feb 2017)  $
 *  Revision Number:     $Rev:: 6312                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.grad.report.model.cert.Certificate;
import ca.bc.gov.educ.grad.report.model.cert.CertificateType;
import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Mock certificate used for testing.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class CertificateImpl implements Certificate, Serializable {

    private static final long serialVersionUID = 2L;

    private Date issued;
    private String certStyle;
    private CertificateOrderTypeImpl orderType;
    private Map<String, SignatureBlockType> signatureBlockTypes;

    public CertificateImpl() {
    }

    public CertificateImpl(final Date issued) {
        this.issued = issued;
    }

    @JsonProperty("certStyle")
    public String getCertStyle() {
        return certStyle;
    }

    public void setCertStyle(String certStyle) {
        this.certStyle = certStyle;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getIssued() {
        return this.issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    @JsonDeserialize(as = CertificateOrderTypeImpl.class)
    public OrderType getOrderType() {
        return orderType == null ? new CertificateOrderTypeImpl(CertificateType.E) : orderType;
    }

    @Override
    public Map<String, SignatureBlockType> getSignatureBlockTypes() {
        return this.signatureBlockTypes;
    }

    public void setSignatureBlockTypes(Map<String, SignatureBlockType> signatureBlockTypes) {
        this.signatureBlockTypes = signatureBlockTypes;
    }

    public void setOrderType(OrderType orderType) {
        if(!(orderType instanceof CertificateOrderTypeImpl)) {
            final RuntimeException dse = new RuntimeException("Order Type must be instance of CertificateOrderType");
            throw dse;
        }
        this.orderType = (CertificateOrderTypeImpl)orderType;
    }

    public CertificateType getCertificateType() {
        return orderType.getCertificateType();
    }

}

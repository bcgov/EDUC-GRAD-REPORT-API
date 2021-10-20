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
package ca.bc.gov.educ.isd.grad.impl;

import ca.bc.gov.educ.isd.cert.Certificate;
import ca.bc.gov.educ.isd.reports.CertificateType;
import ca.bc.gov.educ.isd.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.isd.reports.bundle.service.OrderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Mock certificate used for testing.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class CertificateImpl implements Certificate, Serializable {

    private static final long serialVersionUID = 2L;

    private Date issued;
    private boolean isOrigin = false;
    private CertificateOrderTypeImpl orderType;

    public CertificateImpl() {
    }

    public CertificateImpl(final Date issued) {
        this.issued = issued;
    }

    @JsonProperty("isOrigin")
    public boolean getIsOrigin() {
        return isOrigin;
    }

    public void setIsOrigin(boolean origin) {
        isOrigin = origin;
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

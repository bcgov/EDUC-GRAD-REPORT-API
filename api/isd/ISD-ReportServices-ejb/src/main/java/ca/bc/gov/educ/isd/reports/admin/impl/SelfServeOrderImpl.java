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
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.SelfServeOrder;

import java.util.Date;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SelfServeOrderImpl implements SelfServeOrder {

    private static final long serialVersionUID = 6L;

    private Date ordered;
    private Long order;
    private String status;
    private Boolean transcript;
    private Boolean certificate;
    private String delivery;
    private String payment;
    private Double cost;

    @Override
    public void setOrdered(final Date ordered) {
        this.ordered = ordered;
    }

    @Override
    public Date getOrdered() {
        return this.ordered;
    }

    @Override
    public void setOrder(final Long order) {
        this.order = order;
    }

    @Override
    public Long getOrder() {
        return this.order;
    }

    @Override
    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setTranscript(final Boolean transcript) {
        this.transcript = transcript;
    }

    @Override
    public Boolean getTranscript() {
        return this.transcript;
    }

    @Override
    public void setCertificate(final Boolean certificate) {
        this.certificate = certificate;
    }

    @Override
    public Boolean getCertificate() {
        return this.certificate;
    }

    @Override
    public void setDelivery(final String delivery) {
        this.delivery = delivery;
    }

    @Override
    public String getDelivery() {
        return this.delivery;
    }

    @Override
    public void setPayment(final String payment) {
        this.payment = payment;
    }

    @Override
    public String getPayment() {
        return this.payment;
    }

    @Override
    public void setCost(final Double cost) {
        this.cost = cost;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public int compareTo(final Object that) {
        final Date thisDate = nullSafe(getOrdered());
        final Date thatDate = that instanceof SelfServeOrder
                ? nullSafe(((SelfServeOrder) that).getOrdered())
                : new Date();

        return thisDate.compareTo(thatDate);
    }
}

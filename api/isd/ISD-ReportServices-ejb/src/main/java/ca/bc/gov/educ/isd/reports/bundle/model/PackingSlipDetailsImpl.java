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
 *  File:                PackingSlipDetailsImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.bundle.model;

import ca.bc.gov.educ.isd.common.party.address.PostalAddress;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.ecommerce.delivery.PostalDeliveryInfo;
import ca.bc.gov.educ.isd.reports.packingslip.DestinationType;
import ca.bc.gov.educ.isd.reports.packingslip.PackingSlipDetails;

import java.util.Date;

/**
 * Implementation of PackingSlipDetails using to create BCMP packing slip
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PackingSlipDetailsImpl extends AbstractDomainEntity implements PackingSlipDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String recipient;
    private String orderedByName;
    private Integer documentsShipped;
    private String orderNumber;
    private DestinationType destinationType;
    private final PostalAddress address;

    public PackingSlipDetailsImpl(PostalDeliveryInfo info) {
        this.address = info;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public PostalAddress getAddress() {
        return address;
    }

    @Override
    public String getOrderedByName() {
        return orderedByName;
    }

    @Override
    public Integer getDocumentsShipped() {
        return documentsShipped;
    }

    @Override
    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public Date getOrderDate() {
        return getCreatedOn();
    }

    @Override
    public DestinationType getDestinationType() {
        return destinationType;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setOrderedByName(String orderedByName) {
        this.orderedByName = orderedByName;
    }

    public void setDocumentsShipped(Integer documentsShipped) {
        this.documentsShipped = documentsShipped;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }
}

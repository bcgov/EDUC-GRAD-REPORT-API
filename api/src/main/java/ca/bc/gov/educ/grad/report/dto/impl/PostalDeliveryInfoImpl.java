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
 *  File:                $Id:: PostalAddressImpl.java 3454 2016-09-07 21:58:53#$
 *  Date of Last Commit: $Date:: 2016-09-07 14:58:53 -0700 (Wed, 07 Sep 2016)  $
 *  Revision Number:     $Rev:: 3454                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.party.address.PostalDeliveryInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a general-purpose address. The address is used by both the Student
 * and the School, although not all fields are necessarily used by both in all
 * situations. This is re-used by PackingSlipDetails.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class PostalDeliveryInfoImpl extends PostalAddressImpl implements PostalDeliveryInfo, Serializable {

    private static final long serialVersionUID = 2L;

    private String name = "";
    private String attentionTo = "";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAttentionTo() {
        return attentionTo;
    }

    @Override
    public void setAttentionTo(String attentionTo) {
        this.attentionTo = attentionTo;
    }

    @Override
    public void setCountryCode(String countryCode) {
        super.setCountry(countryCode);
    }

    @Override
    public void setPostalCode(String code) {
        super.setCode(code);
    }

    @Override
    public String getEntityId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCreatedOn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCreatedBy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getLastUpdatedOn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLastUpdatedBy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

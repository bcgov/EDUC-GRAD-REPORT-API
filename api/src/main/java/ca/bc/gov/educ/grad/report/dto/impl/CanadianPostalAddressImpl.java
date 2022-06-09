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
 *  File:                CanadianPostalAddressImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.party.address.CanadianPostalAddress;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class CanadianPostalAddressImpl extends PostalAddressImpl implements CanadianPostalAddress, Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * Empty constructor to initialize an empty address instance.
     */
    public CanadianPostalAddressImpl() {
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

    @Override
    public String getProvince() {
        return getRegion();
    }

    /**
     * Set the province name.
     *
     * @param province
     */
    public void setProvince(String province) {
        setRegion(province);
    }

    public void setPostalCode(String postalCode) {
        setCode(postalCode);
    }
}

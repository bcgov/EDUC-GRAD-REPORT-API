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
 *  File:                CanadianPostalAddressImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.impl;

import ca.bc.gov.educ.grad.model.common.party.address.CanadianPostalAddress;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class CanadianPostalAddressImpl implements CanadianPostalAddress, Serializable {

    private static final long serialVersionUID = 2L;

    private String postalCode = "";
    private String street1 = "";
    private String street2 = "";
    private String street3 = "";
    private String city = "";
    private String province = "";
    private String countryCode = "";

    /**
     * Empty constructor to initialize an empty address instance.
     */
    public CanadianPostalAddressImpl() {
    }

    /**
     * Full constructor to initialize all the attributes during object creation.
     * <p>
     * @param street1
     * @param street2
     * @param street3
     * @param city
     * @param postalcode
     * @param province
     * @param country
     */
    public CanadianPostalAddressImpl(String street1, String street2, String street3, String city,
            String postalcode, String province, String country) {

        this.street1 = street1;
        this.street2 = street2;
        this.street3 = street3;
        this.city = city;
        this.postalCode = postalcode;
        this.province = province;
        this.countryCode = country;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getStreetLine1() {
        return street1;
    }

    @Override
    public String getStreetLine2() {
        return street2;
    }

    @Override
    public String getStreetLine3() {
        return street3;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getRegion() {
        return getProvince();
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setStreet3(String street3) {
        this.street2 = street3;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.setProvince(region);
    }

    public void setCountry(String country) {
        this.countryCode = country;
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
        return province;
    }

    /**
     * Set the province name.
     *
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }
}

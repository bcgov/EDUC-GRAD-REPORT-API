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
 *  File:                $Id:: PostalAddressImpl.java 3454 2016-09-07 21:58:53#$
 *  Date of Last Commit: $Date:: 2016-09-07 14:58:53 -0700 (Wed, 07 Sep 2016)  $
 *  Revision Number:     $Rev:: 3454                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.common.party.address.PostalAddress;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a general-purpose address. The address is used by both the Student
 * and the School, although not all fields are necessarily used by both in all
 * situations. This is re-used by PackingSlipDetails.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class PostalAddressImpl implements PostalAddress, Serializable {

    private static final long serialVersionUID = 2L;

    private String streetLine1 = "";
    private String streetLine2 = "";
    private String streetLine3 = "";
    private String city = "";
    private String region = "";
    private String code = "";
    private String country = "";

    @Override
    public synchronized String getStreetLine1() {
        return streetLine1;
    }

    @Override
    public synchronized String getStreetLine2() {
        return streetLine2;
    }

    @Override
    public synchronized String getStreetLine3() {
        return streetLine3;
    }

    @Override
    public synchronized String getCity() {
        return city;
    }

    @Override
    public synchronized String getRegion() {
        return region;
    }

    @Override
    public synchronized String getPostalCode() {
        return this.code;
    }

    @Override
    public synchronized String getCountryCode() {
        return country;
    }

    /**
     * set street.
     *
     * @param streetLine1
     */
    public void setStreetLine1(final String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    /**
     * set street.
     *
     * @param streetLine2
     */
    public void setStreetLine2(final String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    /**
     * set street.
     *
     * @param streetLine3
     */
    public void setStreetLine3(final String streetLine3) {
        this.streetLine3 = streetLine3;
    }

    /**
     * set city name.
     *
     * @param city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * set the Province name.
     *
     * @param region
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * set the country.
     *
     * @param country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * set the code (e.g., post, postal, zip).
     *
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    @Override
    public String getEntityId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getXactId() {
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

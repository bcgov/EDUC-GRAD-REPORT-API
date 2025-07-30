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

import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a general-purpose address. The address is used by both the Student
 * and the School, although not all fields are necessarily used by both in all
 * situations. This is re-used by PackingSlipDetails.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class PostalAddressImpl implements PostalAddress, Serializable {

    private static final long serialVersionUID = 2L;

    private String streetLine1 = "";
    private String streetLine2 = "";
    private String streetLine3 = "";
    private String city = "";
    private String region = "";
    private String code = "";
    private String country = "";

    private static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "Not supported yet.";
    private static final String COUNTRY_NAME_CANADA = "CANADA";

    @Override
    @JsonProperty("address1")
    public String getStreetLine1() {
        return streetLine1;
    }

    @Override
    @JsonProperty("address2")
    public String getStreetLine2() {
        return streetLine2;
    }

    @Override
    @JsonProperty("address3")
    public  String getStreetLine3() {
        return streetLine3;
    }

    @Override
    @JsonProperty("city")
    public  String getCity() {
        return city;
    }

    @Override
    @JsonProperty("provinceName")
    public  String getRegion() {
        return region;
    }

    @Override
    @JsonProperty("postal")
    public  String getPostalCode() {
        switch(StringUtils.trimToEmpty(getCountryCode()).toUpperCase()) {
            case "CA", COUNTRY_NAME_CANADA:
                if(StringUtils.isNotBlank(this.code) && this.code.length() == 6) {
                    return new StringBuilder(this.code).insert(3, " ").toString();
                } else {
                    return this.code;
                }
            case "US", "USA":
                if(StringUtils.isNotBlank(this.code) && this.code.length() == 9) {
                    return new StringBuilder(this.code).insert(5, "-").toString();
                } else {
                    return this.code;
                }
            default:
                return this.code;
        }
    }

    @Override
    @JsonProperty("countryCode")
    public  String getCountryCode() {
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
    public String getFormattedAddressForLabels() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStreetLine1());
        if(StringUtils.isNotBlank(getStreetLine2())) {
            sb.append("\n").append(getStreetLine2());
        }
        if(StringUtils.isNotBlank(getStreetLine3())) {
            sb.append("\n").append(getStreetLine3());
        }
        if(StringUtils.isNotBlank(getCity())) {
            sb.append("\n").append(getCity());
        }
        if(StringUtils.isNotBlank(getRegion())) {
            sb.append(" ").append(getRegion());
        }
        if(StringUtils.isNotBlank(getCountryCode()) && !"CA".equalsIgnoreCase(getCountryCode()) && !COUNTRY_NAME_CANADA.equalsIgnoreCase(getCountryCode())) {
                sb.append("\n").append(getCountryCode());
            }

        if(StringUtils.isNotBlank(getPostalCode())) {
            sb.append("CA".equalsIgnoreCase(getCountryCode()) || COUNTRY_NAME_CANADA.equalsIgnoreCase(getCountryCode()) || StringUtils.isBlank(getCountryCode()) ? "  " : " ").append(getPostalCode());
        }
        return sb.toString();
    }

    @Override
    public String getEntityId() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCreatedOn() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCreatedBy() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getLastUpdatedOn() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLastUpdatedBy() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

}

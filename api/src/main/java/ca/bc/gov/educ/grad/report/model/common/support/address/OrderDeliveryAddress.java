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
 *  File:                $Id:: OrderDeliveryAddress.java 11695 2019-02-27 18:5#$
 *  Date of Last Commit: $Date:: 2019-02-27 10:55:32 -0800 (Wed, 27 Feb 2019)  $
 *  Revision Number:     $Rev:: 11695                                          $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.support.address;

import ca.bc.gov.educ.grad.report.model.common.party.address.Address;
import ca.bc.gov.educ.grad.report.model.common.party.address.MailingAddress;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;

import static ca.bc.gov.educ.grad.report.model.common.support.VerifyUtils.nullSafe;

/**
 * Represents the delivery address for an order.
 *
 * FIXME: This should move out of common support and into appropriate service.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class OrderDeliveryAddress extends AbstractDomainEntity implements MailingAddress, Address {

    /**
     * Street address line 1.
     */
    private String streetLine1;

    /**
     * Street address line 2.
     */
    private String streetLine2;

    /**
     * Street address line 3.
     */
    private String streetLine3;

    /**
     * City within the region.
     */
    private String city;

    /**
     * Region (state, province, territory) within the country.
     */
    private String region;

    /**
     * Mailing address code (e.g., postal code, zip code, postcode).
     */
    private String postalCode;

    /**
     * Mailing address two-letter ISO country code.
     */
    private String countryCode;

    public OrderDeliveryAddress() {
    }

    /**
     * Adapts the given address information to this order delivery instance.
     *
     * @param delivery The address information to adapt.
     */
    public OrderDeliveryAddress(final PostalAddress delivery) {
        setStreetLine1(delivery.getStreetLine1());
        setStreetLine2(delivery.getStreetLine2());
        setStreetLine3(delivery.getStreetLine3());
        setCity(delivery.getCity());
        setRegion(delivery.getRegion());
        setCountryCode(delivery.getCountryCode());
        setPostalCode(delivery.getPostalCode());
    }

    /**
     * Returns the first of three street lines.
     *
     * @return A string (max length 40 chars) or empty, but never null.
     */
    @Override
    public synchronized String getStreetLine1() {
        return nullSafe(this.streetLine1);
    }

    /**
     * Returns the second of three street lines.
     *
     * @return A string (max length 40 chars) or empty, but never null.
     */
    @Override
    public synchronized String getStreetLine2() {
        return nullSafe(this.streetLine2);
    }

    /**
     * Returns the third of three street lines.
     *
     * @return A string (max length 40 chars) or empty, but never null.
     */
    @Override
    public synchronized String getStreetLine3() {
        return nullSafe(this.streetLine3);
    }

    /**
     * Returns the city name for this address (for example, "Sechelt Indian
     * Government District"). The client should not depend on any particular
     * case for the returned value.
     *
     * @return A non-null city name, possibly empty.
     */
    @Override
    public synchronized String getCity() {
        return nullSafe(this.city);
    }

    /**
     * The calling client must format the return value as necessary.
     *
     * @return The region, possibly empty.
     */
    @Override
    public synchronized String getRegion() {
        return nullSafe(this.region);
    }

    /**
     * Returns the six-letter postal code for this address, no spaces, all
     * uppercase (for example, "V1V1V1").
     *
     * @return The postal code, possibly empty.
     */
    @Override
    public synchronized String getPostalCode() {
        return nullSafe(this.postalCode);
    }

    /**
     * Returns the country name.
     *
     * @return The country name, possibly empty.
     */
    public synchronized String getCountryName() {
        return nullSafe(this.countryCode);
    }

    /**
     * Returns the two-letter ISO country code.
     *
     * @return An ISO code, possibly empty.
     */
    @Override
    public String getCountryCode() {
        return nullSafe(this.countryCode);
    }

    /**
     * Used by the builder to set the street line 1 value.
     *
     * @param streetLine1 Passed in by the builder.
     */
    public void setStreetLine1(final String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    /**
     * Used by the builder to set the street line 2 value.
     *
     * @param streetLine2 Passed in by the builder.
     */
    public void setStreetLine2(final String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    /**
     * Used by the builder to set the street line 3 value.
     *
     * @param streetLine3 Passed in by the builder.
     */
    public void setStreetLine3(final String streetLine3) {
        this.streetLine3 = streetLine3;
    }

    /**
     * Used by the builder to set the city name.
     *
     * @param city Passed in by the builder.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Used by the builder to set the Province name.
     *
     * @param region Passed in by the builder.
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * Used by the builder to set the country.
     *
     * @param countryCode Passed in by the builder.
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Used by the builder to set the code (e.g., post, postal, zip).
     *
     * @param postalCode Passed in by the builder.
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public void reset() {
        setStreetLine1("");
        setStreetLine2("");
        setStreetLine3("");
        setCity("");
        setRegion("");
        setCountryCode("");
        setPostalCode("");
    }

    /**
     * Returns the comma-delimited version of this address.
     *
     * @return A non-null String, but could be empty.
     */
    @Override
    public String toString() {
        return toCommaDelimitedString();
    }

    /**
     * Returns the first three address lines concatenated together with a space
     * followed by the city, region, country name, and postal code.
     *
     * @return A non-null string, possibly empty.
     */
    public String toCommaDelimitedString() {
        String address = getStreetLine1();

        address = (address + " " + getStreetLine2()).trim();
        address = (address + " " + getStreetLine3()).trim();

        final String cityName = getCity();

        if (!cityName.isEmpty()) {
            address += ", " + cityName;
        }

        final String regionName = getRegion();

        if (!regionName.isEmpty()) {
            address += ", " + regionName;
        }

        final String countryName = getCountryName();

        if (!countryName.isEmpty()) {
            address += ", " + countryName;
        }

        final String code = getPostalCode();

        if (!code.isEmpty()) {
            address += ", " + code;
        }

        return address;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

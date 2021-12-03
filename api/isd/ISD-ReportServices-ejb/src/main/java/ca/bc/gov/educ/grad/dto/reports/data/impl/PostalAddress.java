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
 *  File:                $Id:: PostalAddress.java 11116 2018-08-13 23:26:33Z D#$
 *  Date of Last Commit: $Date:: 2018-08-13 16:26:33 -0700 (Mon, 13 Aug 2018)  $
 *  Revision Number:     $Rev:: 11116                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports.data.impl;

import ca.bc.gov.educ.grad.dto.reports.data.BusinessEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Locale;

import static ca.bc.gov.educ.grad.model.common.party.address.MailingAddress.LINE_SEPARATOR;
import static java.util.Locale.ENGLISH;

/**
 * Represents a general-purpose address. The address is used by both the Student
 * and the School, although not all fields are necessarily used by both in all
 * situations. This is re-used by PackingSlipDetails.
 *
 * This class guards against null fields and extraneous whitespace.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class PostalAddress extends BusinessEntity {

    private static final long serialVersionUID = 4L;

    /**
     * Number of characters allowed per line of an address.
     */
    private static final int LINE_LENGTH = 40;

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
     * Destination country code.
     */
    @XmlTransient
    private String countryCode;

    /**
     * Returns the first of three street lines.
     *
     * @return A string (max length 40 chars) or empty, but never null.
     */
    public String getStreetLine1() {
        return trimSafe(this.streetLine1);
    }

    /**
     * Returns the second of three street lines.
     *
     * @return A string (max length 40 chars) or empty, but never null.
     */
    public String getStreetLine2() {
        return trimSafe(this.streetLine2);
    }

    /**
     * Returns the third of three street lines.
     *
     * @return A string (max length 40 chars) or empty, but never null.
     */
    public String getStreetLine3() {
        return trimSafe(this.streetLine3);
    }

    /**
     * Returns the city name for this address.
     *
     * @return A non-null, trimmed, possibly empty string instance.
     */
    public String getCity() {
        return trimSafe(this.city);
    }

    /**
     * Returns the two-letter Canada Post abbreviation for the address' Postal
     * Code. The calling client must format the return value as necessary.
     *
     * @return A non-null, trimmed, possibly empty string instance.
     */
    public String getRegion() {
        return trimSafe(this.region);
    }

    /**
     * Returns the two-letter ISO code for the country.
     *
     * @return A non-null, trimmed, possibly empty string instance.
     */
    public String getCountryCode() {
        return trimSafe(this.countryCode);
    }

    /**
     * Returns the six-letter postal code for this address, no spaces, all
     * uppercase.
     *
     * @return A non-null, trimmed, possibly empty string instance.
     */
    public String getPostalCode() {
        final String code = nullSafe(this.postalCode);
        final String result = code.replaceAll("\\s", "");

        return result;
    }

    /**
     * Returns the country name based on the ISO country code.
     *
     * @return A non-null, trimmed, possibly empty string instance.
     */
    public String getCountryName() {
        final String isoCode = getCountryCode();
        String country = "";

        if (!isoCode.isEmpty()) {
            country = new Locale("en", isoCode).getDisplayCountry();
        }

        return country.toUpperCase(ENGLISH);
    }

    /**
     * Used by the builder to set the street line 1 value.
     *
     * @param streetLine1 Passed in by the builder.
     */
    protected void setStreetLine1(final String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    /**
     * Used by the builder to set the street line 2 value.
     *
     * @param streetLine2 Passed in by the builder.
     */
    protected void setStreetLine2(final String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    /**
     * Used by the builder to set the street line 3 value.
     *
     * @param streetLine3 Passed in by the builder.
     */
    protected void setStreetLine3(final String streetLine3) {
        this.streetLine3 = streetLine3;
    }

    /**
     * Used by the builder to set the city name.
     *
     * @param city Passed in by the builder.
     */
    protected void setCity(final String city) {
        this.city = city;
    }

    /**
     * Used by the builder to set the region name.
     *
     * @param region Passed in by the builder.
     */
    protected void setRegion(final String region) {
        this.region = region;
    }

    /**
     * Used by the builder to set the country.
     *
     * @param countryCode Passed in by the builder.
     */
    protected void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Used by the builder to set the code (e.g., post, postal, zip).
     *
     * @param postalCode Passed in by the builder.
     */
    protected void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns a trimmed version of s. This will convert s to an empty string in
     * the case that s is null.
     *
     * @param s The string to trim.
     * @return The empty string or the trimmed version of s, never null.
     */
    private String trimSafe(final String s) {
        return s == null ? "" : s.trim();
    }

    /**
     * Returns a postal code with a space in the middle. This is used by the
     * getFormatted() method to format the postal code according to Canada Post
     * specifications.
     *
     * @return A space-formatted postal code.
     */
    public String getFormattedPostalCode() {
        String formatted = getPostalCode();

        if (formatted.length() > 3) {
            formatted = formatted.substring(0, 3) + " " + formatted.substring(3);
        }

        return formatted.toUpperCase(ENGLISH);
    }

    /**
     * Returns the formatted street name. This is used by the report to get the
     * single line used in the transcripts header for the school's street
     * address.
     *
     * @return A non-null, possibly empty String.
     */
    public String getFormattedStreet() {
        return getStreetLine1() + " " + getStreetLine2();
    }

    /**
     * Returns a pre-formatted text string that can be displayed verbatim. The
     * formatted version of the address must conform to published standard
     * specifications. Lines are separated by \n, regardless of platform (e.g.,
     * \r\n is not allowed even if the OS is Windows).
     *
     * @return A formatted version of the address, possibly empty, never null.
     */
    public String getFormatted() {
        // Allocate memory for five address lines.
        final StringBuilder formatted = new StringBuilder(5 * LINE_LENGTH);
        final String line
                = getCity() + " " + getRegion() + "  " + getFormattedPostalCode();

        formatted.append(newLine(left(getStreetLine1(), LINE_LENGTH)));
        formatted.append(newLine(left(getStreetLine2(), LINE_LENGTH)));
        formatted.append(newLine(left(getStreetLine3(), LINE_LENGTH)));

        // This might leave an extraneous newline if no country is added.
        formatted.append(newLine(left(line, LINE_LENGTH)));

        final String countryName = getCountryName();

        // Suppress the country name if it is Canada.
        if (!"CANADA".equalsIgnoreCase(countryName)) {
            formatted.append(newLine(left(countryName, LINE_LENGTH)));
        }

        // Trim any extraneous newline characters, if there is one.
        return formatted.toString().trim();
    }

    /**
     * Returns s with a newline appended, provided that s is not empty. This is
     * used by getFormatted() to prevent mailing addresses from having lines
     * between street line elements.
     *
     * @param s Must not be null.
     * @return s + LINE_SEPARATOR iff !s.isEmpty()
     */
    protected static String newLine(String s) {
        assert s != null;
        s = s.trim();
        return s.isEmpty() ? s : s + LINE_SEPARATOR;
    }

    /**
     * Gets the leftmost {@code n} characters of a String.
     *
     * @param str String to parse (can be null).
     * @param n Required substring length.
     * @return The leftmost n characters or null if str is null.
     */
    public static String left(final String str, final int n) {
        final String result;

        if (str == null) {
            result = null;
        } else if (n < 0) {
            result = "";
        } else if (str.length() <= n) {
            result = str;
        } else {
            result = str.substring(0, n);
        }

        return result;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder
            extends BusinessEntity.Builder<PostalAddress, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        /**
         * Returns an outer class instance without attributes initialized.
         *
         * @return New PostalAddress instance.
         */
        @Override
        protected PostalAddress createObject() {
            return new PostalAddress();
        }

        /**
         * Sets the street line 1.
         *
         * @param streetLine1 First of up to three street lines.
         * @return thisBuilder
         */
        public Builder withStreetLine1(final String streetLine1) {
            getObject().setStreetLine1(streetLine1);
            return thisBuilder();
        }

        /**
         * Sets the street line 2.
         *
         * @param streetLine2 Second of up to three street lines.
         * @return thisBuilder
         */
        public Builder withStreetLine2(final String streetLine2) {
            getObject().setStreetLine2(streetLine2);
            return thisBuilder();
        }

        /**
         * Sets the street line 3.
         *
         * @param streetLine3 Third of up to three street lines.
         * @return thisBuilder
         */
        public Builder withStreetLine3(final String streetLine3) {
            getObject().setStreetLine3(streetLine3);
            return thisBuilder();
        }

        /**
         * Sets the city.
         *
         * @param city The municipal area within a Province.
         * @return thisBuilder
         */
        public Builder withCity(final String city) {
            getObject().setCity(city);
            return thisBuilder();
        }

        /**
         * Sets the region (e.g., province).
         *
         * @param region The Province or Territory within the country.
         * @return thisBuilder
         */
        public Builder withRegion(final String region) {
            getObject().setRegion(region);
            return thisBuilder();
        }

        /**
         * Sets the address delivery code (postal, zip, postcode, etc.).
         *
         * @param postalCode For example, V1V1V1.
         * @return thisBuilder
         */
        public Builder withPostalCode(final String postalCode) {
            getObject().setPostalCode(postalCode);
            return thisBuilder();
        }

        /**
         * Sets the address delivery country.
         *
         * @param countryCode For example, "CA" for Canada.
         * @return thisBuilder
         */
        public Builder withCountryCode(final String countryCode) {
            getObject().setCountryCode(countryCode);
            return thisBuilder();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "streetLine1=" + streetLine1 + ", streetLine2=" + streetLine2 + ", streetLine3=" + streetLine3 + ", city=" + city + ", region=" + region + ", postalCode=" + postalCode + ", countryCode=" + countryCode + '}';
    }
}

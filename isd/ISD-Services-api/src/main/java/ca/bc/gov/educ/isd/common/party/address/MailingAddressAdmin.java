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
package ca.bc.gov.educ.isd.common.party.address;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface MailingAddressAdmin {

    /**
     * @param streetLine1 Line 1 of the street address.
     */
    void setStreetLine1(String streetLine1);

    /**
     * @param streetLine2 Line 2 of the street address.
     */
    void setStreetLine2(String streetLine2);

    /**
     * @param streetLine3 Line 3 of the street address.
     */
    void setStreetLine3(String streetLine3);

    /**
     * Sets the city name.
     *
     * @param city A non-null String instance, might be empty.
     */
    void setCity(String city);

    /**
     * Sets the Province, State, or other region.
     *
     * @param region A non-null String instance, might be empty.
     */
    void setRegion(String region);

    /**
     * Sets the destination country 2-letter ISO code.
     *
     * @param countryCode A non-null String instance, might be empty.
     */
    void setCountryCode(String countryCode);

    /**
     * Sets the postal code (zip code, postcode, etc.).
     *
     * @param postalCode A mostly unique identifier for the address.
     */
    void setPostalCode(String postalCode);
}

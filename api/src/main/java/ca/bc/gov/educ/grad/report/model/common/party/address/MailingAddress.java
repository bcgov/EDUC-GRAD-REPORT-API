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
 *  File:                $Id:: MailingAddress.java 5618 2016-12-12 20:47:02Z D#$
 *  Date of Last Commit: $Date:: 2016-12-12 12:47:02 -0800 (Mon, 12 Dec 2016)  $
 *  Revision Number:     $Rev:: 5618                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.party.address;

/**
 * Used to provide a consistent interface for mailing addresses throughout the
 * system.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface MailingAddress {

    /**
     * Used to terminate lines for formatted addresses.
     */
    String LINE_SEPARATOR = "\n";

    /**
     * Line 1 of the street address.
     *
     * @return A non-null String instance, should not be empty.
     */
    String getStreetLine1();

    /**
     * Line 2 of the street address.
     *
     * @return A non-null String instance, possibly empty.
     */
    String getStreetLine2();

    /**
     * Line 3 of the street address.
     *
     * @return A non-null String instance, possibly empty.
     */
    String getStreetLine3();

    /**
     * City name.
     *
     * @return A non-null String instance, should not be empty.
     */
    String getCity();

    /**
     * Province, State, or other region.
     *
     * @return A non-null String instance, might be empty.
     */
    String getRegion();

    /**
     * Destination country.
     *
     * @return A non-null String instance, might be empty.
     */
    String getCountryCode();

    /**
     * Returns the postal code (zip code, postcode, etc.).
     *
     * @return A mostly unique identifier for the address.
     */
    String getPostalCode();

    /**
     * Returns the formatted address depending on country formats.
     *
     * @return formatted country specific address
     */
    String getFormattedAddressForLabels();
}

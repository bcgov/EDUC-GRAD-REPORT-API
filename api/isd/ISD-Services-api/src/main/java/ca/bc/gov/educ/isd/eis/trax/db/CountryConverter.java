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
package ca.bc.gov.educ.isd.eis.trax.db;

import java.util.Map;

/**
 * A helper class to convert internal TRAX country codes to/from ISO 3166
 * alpha-2 (the standard used by Java).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CountryConverter extends TRAXData {

    /**
     * Convert from the passed in ISO 3166 Alpha-2 country code to the TRAX
     * country code.
     *
     * @param isoCountryCode
     * @return
     */
    public String isoToTRAX(final String isoCountryCode);

    /*
     * Convert from the passed in TRAX
     * country code to the ISO 3166 Alpha-2 country code .

     * @param traxCountryCode
     * @return isoCountryCode
     */
    public String traxToISO(final String traxCountryCode);

    /**
     * Should only be used for unit tests.
     *
     * @deprecated
     * @return
     */
    Map<String, String> getISOTRAXMap();

    /**
     * Should only be used for unit tests.
     *
     * @deprecated
     * @return
     */
    Map<String, String> getTRAXISOMap();

    /**
     * Map that contains the name of the country as key and iso code as value
     *
     * @return
     */
    Map<String, String> getISOCountryMap();

    /**
     * Map that contains the name of the country as key and TRAX code as value
     *
     * @return
     */
    Map<String, String> getTRAXCountryMap();

}

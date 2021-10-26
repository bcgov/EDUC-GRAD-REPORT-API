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
package ca.bc.gov.educ.isd.adaptor.dao.impl;

import java.io.Serializable;

/**
 * Entity to read from the TRAX to ISO country code mapping view.
 *
 * Unfortunately TRAX uses non-standard country codes. Fortunately it contains a
 * translation table to ISO 3166 alpha-2 codes.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TRAXCountryCodes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String traxCountryCode;
    private String countryName;
    private String isoCountryCode;

    public TRAXCountryCodes() {
    }

    public String getTRAXCountryCode() {
        return traxCountryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getISOCountryCode() {
        return isoCountryCode;
    }
}

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
package ca.bc.gov.educ.isd.ecommerce.pesc;

/**
 * Reusable container for building PESC format addresses for use with XML
 * transcript generation.
 *
 * @author CGI Information Management Consultants Inc.
 * @version 1.0
 * @updated 10-Nov-2015 4:44:17 PM
 */
public interface PESCAddress {

    String getAddressLine1();

    String getAddressLine2();

    String getCity();

    String getStateProvinceCode();

    String getPostalCode();

    String getStateProvince();

    String getCountryCode();

}

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
 *  File:                SaleOrderRecipient.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.party.address.PostalAddress;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SaleOrderRecipient extends PostalAddress {

    String getName();

    void setName(String name);

    void setStreetLine1(String streetLine1);

    void setStreetLine2(String streetLine2);

    void setStreetLine3(String streetLine3);

    void setCity(String city);

    void setRegion(String region);

    void setCountryCode(String countryCode);

    void setPostalCode(String code);

    Integer getNumGradCerts();

    void setNumGradCerts(Integer count);

    Integer getNumTranscripts();

    void setNumTranscripts(Integer count);

}

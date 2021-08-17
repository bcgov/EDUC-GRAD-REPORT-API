/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PostalDeliveryInfo.java 7004 2017-05-05 00:2#$
 *  Date of Last Commit: $Date:: 2017-05-04 17:29:41 -0700 (Thu, 04 May 2017)  $
 *  Revision Number:     $Rev:: 7004                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import ca.bc.gov.educ.isd.common.party.address.PostalAddress;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PostalDeliveryInfo extends DeliveryInfo, PostalAddress {

    String getName();

    void setName(String name);

    void setStreetLine1(String streetLine1);

    void setStreetLine2(String streetLine2);

    void setStreetLine3(String streetLine3);

    void setCity(String city);

    void setRegion(String region);

    void setCountryCode(String countryCode);

    void setPostalCode(String code);
}

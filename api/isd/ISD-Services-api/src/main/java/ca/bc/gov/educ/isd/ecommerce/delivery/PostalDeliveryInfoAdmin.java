
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 * 
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 * 
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                     $
 * 
 *  
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.delivery;

/**
 *
 * @author Ministry of Education, BC
 */
public interface PostalDeliveryInfoAdmin extends PostalDeliveryInfo, DeliveryInfoAdmin {

    @Override
    String getName();

    @Override
    void setName(String name);

    @Override
    void setStreetLine1(String streetLine1);

    @Override
    void setStreetLine2(String streetLine2);

    @Override
    void setStreetLine3(String streetLine3);

    @Override
    void setCity(String city);

    @Override
    void setRegion(String region);

    @Override
    void setCountryCode(String countryCode);

    @Override
    void setPostalCode(String code);

}

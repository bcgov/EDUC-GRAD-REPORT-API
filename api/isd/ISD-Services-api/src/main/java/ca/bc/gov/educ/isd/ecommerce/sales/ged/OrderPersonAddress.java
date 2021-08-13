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
 *  File:                $Id:: OrderPersonAddress.java 7364 2017-06-02 20:48:3#$
 *  Date of Last Commit: $Date:: 2017-06-02 13:48:32 -0700 (Fri, 02 Jun 2017)  $
 *  Revision Number:     $Rev:: 7364                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales.ged;

import java.io.Serializable;

/**
 * FIXME: cp - temporary until we can review the common.party packages.
 *
 * The basic address fields of a person, as used on the GED order forms.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface OrderPersonAddress extends Cloneable, Serializable {

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    String getProvince();

    void setProvince(String province);

    String getCountry();

    void setCountry(String country);

    String getPostalCode();

    void setPostalCode(String postalCode);

}

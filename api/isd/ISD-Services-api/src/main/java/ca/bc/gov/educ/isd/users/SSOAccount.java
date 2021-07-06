/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: SSOAccount.java 10324 2018-05-25 19:08:12Z DAJA#$
 *  Date of Last Commit: $Date:: 2018-05-25 12:08:12 -0700 (Fri, 25 May 2018)  $
 *  Revision Number:     $Rev:: 10324                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.users;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SSOAccount extends DomainEntity {

    String getAccountGuid();

    String getLastName();

    String getFirstName();

    String getDisplayName();

    String getBusinessGuid();

    String getBusinessLegalName();

    String getBusinessTelephoneNumber();

    String getBusinessEmailAddress();

    String getUserId();

    String getAddressLine1();

    String getAddressLine2();

    String getCountry();

    String getProvince();

    String getCity();

    String getPostalCode();

    String getFreeformAddress();

    Date getEffectiveStartDate();

    Date getEffectiveEndDate();

    void expire();

    String getNameCode();

    String getIssuedByOrganization();

    String getTypeCode();

    boolean isActive();

    UserProfile getUserProfile();

}

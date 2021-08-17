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
package ca.bc.gov.educ.isd.users;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SSOAccountAdmin extends SSOAccount {

    public void setAccountGuid(final String accountGuid);

    public void setLastName(final String lastName);

    public void setFirstName(final String firstName);

    public void setDisplayName(final String displayName);

    public void setBusinessGuid(final String businessGuid);

    public void setBusinessLegalName(String businessLegalName);

    public void setBusinessTelephoneNumber(final String businessTelephoneNumber);

    public void setBusinessEmailAddress(final String businessEmailAddress);

    public void setUserId(final String userId);

    public void setAddressLine1(final String addressLine1);

    public void setAddressLine2(final String addressLine2);

    public void setCountry(final String country);

    public void setProvince(final String province);

    public void setCity(final String city);

    public void setPostalCode(final String postalCode);

    public void setFreeformAddress(final String freeformAddress);

    public void setEffectiveStartDate(final Date effectiveStartDate);

    public void setEffectiveEndDate(final Date effectiveEndDate);

    public void setNameCode(final String nameCode);

    public void setTypeCode(final String typeCode);

    public void setIssuedByOrganization(final String issuedByOrganization);

}

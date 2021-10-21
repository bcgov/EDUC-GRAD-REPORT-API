/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        BCeIDAdaptor.java
 *  Date of Last Commit: $Date:: 2016-09-27 16:17:37 -0700 (Tue, 27 Sep 2016)  $
 *  Revision Number:     $Rev:: 3856                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.idim.bc;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.idim.IDIMAdaptor;

import java.io.Serializable;
import java.util.Map;

/**
 * Exposes the services that are offer by BCeIDAdaptor.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCeIDAdaptor extends IDIMAdaptor, Serializable {

    /**
     * Verifies if the connection to the SOAP service is available.
     *
     * @throws EISException
     */
    void testConnectionToBCeID() throws EISException;

    /**
     * Retrieves the details of a user account using the User's GUID. Because
     * this method returns a Map with all the possible values from BCeID SOAP
     * service, and there are many that has the same key on different
     * categories, in the map the key is inserting with a prefix that indicates
     * the origin of the key returned. Here all the possible keys that the
     * method returns:
     * <p>
     * guid, luid, dluid, bceidLuid, bceidDluid, userId, displayName, type,
     * accountState_expiryDateTime, accountState_isSuspended,
     * accountState_isLocked, accountState_passwordChangeRequired,
     * accountState_passwordHintsRequired, accountContact_preference,
     * accountContact_email, accountContact_telephone,
     * accountContact_addressLine1, accountContact_addressLine2,
     * accountContact_city, accountContact_postal, accountContact_province,
     * accountContact_country, accountContact_unstructuredAddress,
     * accountContact_preferredName, accountContact_department,
     * individualIdentity_firstname, individualIdentity_middleName,
     * individualIdentity_otherMiddleName, individualIdentity_surname,
     * individualIdentity_initials, individualIdentity_dateOfBirth,
     * individualIdentity_residential_addressLine1,
     * individualIdentity_residential_addressLine2,
     * individualIdentity_residential_city,
     * individualIdentity_residential_postal,
     * individualIdentity_residential_province,
     * individualIdentity_residential_country,
     * individualIdentity_residential_unstructuredAddress,
     * individualIdentity_mailing_addressLine1,
     * individualIdentity_mailing_addressLine2, individualIdentity_mailing_city,
     * individualIdentity_mailing_postal, individualIdentity_mailing_province,
     * individualIdentity_mailing_country,
     * individualIdentity_mailing_unstructuredAddress, business_guid,
     * business_luid, business_dluid, business_type, business_legalName,
     * business_businessNumber, business_businessNumberVerifiedFlag,
     * business_statementOfRegistrationNumber, business_incorporationNumber,
     * business_jurisdictionOfIncorporation, business_doingBusinessAs,
     * business_addressLine1, business_addressLine2, business_city,
     * business_postal, business_province, business_country,
     * business_unstructuredAddress, business_state, business_bnHubBusinessType,
     * business_businessTypeOther, internalIdentity_title,
     * internalIdentity_company, internalIdentity_organizationCode,
     * internalIdentity_department, internalIdentity_office,
     * internalIdentity_description, internalIdentity_employeeId
     * <p>
     * @param userGuid Searched user's GUID.
     * @param userAccountType Type of BCeID account being queried. This value is
     * required within the request; Not all BCeIDAdaptorAccountType values are
     * valid within the scope of the GetAccountDetail method.
     * @param requesterGuid requester GUID.
     * @param requesterAccountType Type of BCeID account being queried. This
     * value is required within the request; Not all BCeIDAdaptorAccountType
     * values are valid within the scope of the GetAccountDetail method.
     * @return a map that contains all the details of the user.
     * @throws EISException
     */
    Map<String, String> queryUserByGUID(final String userGuid, final BCeIDAdaptorAccountType userAccountType, final String requesterGuid, final BCeIDAdaptorAccountType requesterAccountType) throws EISException;

    /**
     * Retrieves the details of a user account using the User Id. Because this
     * method returns a Map with all the possible values from BCeID SOAP
     * service, and there are many that has the same key on different
     * categories, in the map the key is inserting with a prefix that indicates
     * the origin of the key returned. Here all the possible keys that the
     * method returns:
     * <p>
     * guid, luid, dluid, bceidLuid, bceidDluid, userId, displayName, type,
     * accountState_expiryDateTime, accountState_isSuspended,
     * accountState_isLocked, accountState_passwordChangeRequired,
     * accountState_passwordHintsRequired, accountContact_preference,
     * accountContact_email, accountContact_telephone,
     * accountContact_addressLine1, accountContact_addressLine2,
     * accountContact_city, accountContact_postal, accountContact_province,
     * accountContact_country, accountContact_unstructuredAddress,
     * accountContact_preferredName, accountContact_department,
     * individualIdentity_firstname, individualIdentity_middleName,
     * individualIdentity_otherMiddleName, individualIdentity_surname,
     * individualIdentity_initials, individualIdentity_dateOfBirth,
     * individualIdentity_residential_addressLine1,
     * individualIdentity_residential_addressLine2,
     * individualIdentity_residential_city,
     * individualIdentity_residential_postal,
     * individualIdentity_residential_province,
     * individualIdentity_residential_country,
     * individualIdentity_residential_unstructuredAddress,
     * individualIdentity_mailing_addressLine1,
     * individualIdentity_mailing_addressLine2, individualIdentity_mailing_city,
     * individualIdentity_mailing_postal, individualIdentity_mailing_province,
     * individualIdentity_mailing_country,
     * individualIdentity_mailing_unstructuredAddress, business_guid,
     * business_luid, business_dluid, business_type, business_legalName,
     * business_businessNumber, business_businessNumberVerifiedFlag,
     * business_statementOfRegistrationNumber, business_incorporationNumber,
     * business_jurisdictionOfIncorporation, business_doingBusinessAs,
     * business_addressLine1, business_addressLine2, business_city,
     * business_postal, business_province, business_country,
     * business_unstructuredAddress, business_state, business_bnHubBusinessType,
     * business_businessTypeOther, internalIdentity_title,
     * internalIdentity_company, internalIdentity_organizationCode,
     * internalIdentity_department, internalIdentity_office,
     * internalIdentity_description, internalIdentity_employeeId
     * <p>
     * @param userId Searched user's ID.
     * @param userAccountType Type of BCeID account being queried. This value is
     * required within the request; Not all BCeIDAdaptorAccountType values are
     * valid within the scope of the GetAccountDetail method.
     * @param requesterGuid requester GUID.
     * @param requesterAccountType Type of BCeID account being queried. This
     * value is required within the request; Not all BCeIDAdaptorAccountType
     * values are valid within the scope of the GetAccountDetail method.
     * @return a map that contains all the details of the user.
     * @throws EISException
     */
    Map<String, String> queryUserByID(final String userId, final BCeIDAdaptorAccountType userAccountType, final String requesterGuid, final BCeIDAdaptorAccountType requesterAccountType) throws EISException;
}

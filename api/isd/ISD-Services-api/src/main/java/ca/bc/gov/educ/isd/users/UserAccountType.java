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

import static ca.bc.gov.educ.isd.common.Constants.BCEID;
import static ca.bc.gov.educ.isd.common.Constants.BUSINESS_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.INDIVIDUAL_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.INTERNAL_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.BCSERVICE_CARD_NONPHOTO_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.BCSERVICE_CARD_NONPHOTO_VALUE;
import static ca.bc.gov.educ.isd.common.Constants.BCSERVICE_CARD_PHOTO_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.BCSERVICE_CARD_PHOTO_VALUE;
import static ca.bc.gov.educ.isd.common.Constants.BC_SERVICES;
import static ca.bc.gov.educ.isd.common.Constants.BUSINESS_VALUE;
import static ca.bc.gov.educ.isd.common.Constants.IDIR;
import static ca.bc.gov.educ.isd.common.Constants.INDIVIDUAL_VALUE;
import static ca.bc.gov.educ.isd.common.Constants.INTERNAL_VALUE;
import static ca.bc.gov.educ.isd.common.Constants.PERSONAL_BCEID_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.PERSONAL_BCEID_VALUE;
import static ca.bc.gov.educ.isd.common.Constants.VERIFIED_INDIVIDUAL_IDENTIFIER;

/**
 * BCeID account type identifiers.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum UserAccountType {

    INDIVIDUAL(INDIVIDUAL_IDENTIFIER, INDIVIDUAL_VALUE, BCEID),
    BUSINESS(BUSINESS_IDENTIFIER, BUSINESS_VALUE, BCEID),
    INTERNAL(INTERNAL_IDENTIFIER, INTERNAL_VALUE, IDIR),
    VERIFIED_INDIVIDUAL(VERIFIED_INDIVIDUAL_IDENTIFIER, "", ""),
    BCSERVICE_CARD_NONPHOTO(BCSERVICE_CARD_NONPHOTO_IDENTIFIER, BCSERVICE_CARD_NONPHOTO_VALUE, BC_SERVICES),
    BCSERVICE_CARD_PHOTO(BCSERVICE_CARD_PHOTO_IDENTIFIER, BCSERVICE_CARD_PHOTO_VALUE, BC_SERVICES),
    PERSONAL_BCEID(PERSONAL_BCEID_IDENTIFIER, PERSONAL_BCEID_VALUE, BCEID);

    private final static String ASSURANCE_LEVEL_2 = "2";
    private final static String ASSURANCE_LEVEL_PHOTO = "3";

    private final static String AUTHENTICATION_USER_ID = "UseridPassword";
    private final static String AUTHENTICATION_SMART_CARD = "SmartCardPasscode";

    private final String accountTypeCode;
    private final String accountTypeDesc;
    private final String credentialIssuingOrganization;

    private UserAccountType(final String code, final String description, final String cio) {
        this.accountTypeCode = code;
        this.accountTypeDesc = description;
        this.credentialIssuingOrganization = cio;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public String getAccountTypeDesc() {
        return accountTypeDesc;
    }

    public String getAccountTypeCIO() {
        return credentialIssuingOrganization;
    }

    private boolean isAccountTypeCode(final String typeCode) {
        return getAccountTypeCode().equalsIgnoreCase(typeCode);
    }

    /**
     * Convenience method to check if account type is BCEID Individual.
     *
     * @return true represents an account type of BCEID Individual.
     */
    public boolean isIndividual() {
        return isAccountTypeCode(INDIVIDUAL_IDENTIFIER);
    }

    /**
     * Convenience method to check if account type is BCEID Verified Individual.
     *
     * @return true represents an account type of BCEID Verified Individual.
     */
    public boolean isVerifiedIndividual() {
        return isAccountTypeCode(VERIFIED_INDIVIDUAL_IDENTIFIER);
    }

    /**
     * Convenience method to check if account type is BCService Card Non-Photo.
     *
     * @return true represents an account type of BCService Card Non-Photo.
     */
    public boolean isBcSericeCardNonPhoto() {
        return isAccountTypeCode(BCSERVICE_CARD_NONPHOTO_IDENTIFIER);
    }

    /**
     * Convenience method to check if account type is BCService Card Photo.
     *
     * @return true represents an account type of BCService Card Photo.
     */
    public boolean isBcSericeCardPhoto() {
        return isAccountTypeCode(BCSERVICE_CARD_PHOTO_IDENTIFIER);
    }

    /**
     * Convenience method to check if account type is Personal BCEID.
     *
     * @return true represents an account type of Personal BCEID.
     */
    public boolean isPersonalBecid() {
        return isAccountTypeCode(PERSONAL_BCEID_IDENTIFIER);
    }

    /**
     * Convenience method to check if account type is BCEID Business.
     *
     * @return true represents an account type of BCEID Business.
     */
    public boolean isBusiness() {
        return isAccountTypeCode(BUSINESS_IDENTIFIER);
    }

    /**
     * Convenience method to check if account type is BCEID Internal or IDIR.
     *
     * @return true represents an account type of BCEID Internal/IDIR.
     */
    public boolean isInternal() {
        return isAccountTypeCode(INTERNAL_IDENTIFIER);
    }

    /**
     * Converts the SiteMinder user type to an account type.
     *
     * @param userType The value from SiteMinder to convert to a UserAccountType
     * enum instance.
     * @return The user account type that corresponds to the given userType.
     * @throws IllegalArgumentException Could not determine the user type (did
     * not map to a known UserAccountType).
     */
    public static UserAccountType fromUserType(final String userType) {
        for (final UserAccountType type : values()) {
            if (type.isAccountTypeCode(userType)) {
                return type;
            }
        }

        throw new IllegalArgumentException(userType);
    }

    /**
     *
     * @param assuranceLevel
     * @return
     */
    public static UserAccountType fromAssuranceLevel(final String assuranceLevel, String authentication) {
        final UserAccountType accountType;

        switch (assuranceLevel) {
            case ASSURANCE_LEVEL_2:
                if (authentication.equals(AUTHENTICATION_USER_ID)) {
                    accountType = PERSONAL_BCEID;
                } else if (authentication.equals(AUTHENTICATION_SMART_CARD)) {
                    accountType = BCSERVICE_CARD_NONPHOTO;
                } else {
                    throw new IllegalArgumentException("User Type : <" + VERIFIED_INDIVIDUAL_IDENTIFIER + "> has incorrect authentication value : <" + authentication + ">");
                }
                break;
            case ASSURANCE_LEVEL_PHOTO:
                accountType = BCSERVICE_CARD_PHOTO;
                break;
            default:
                throw new IllegalArgumentException("User Type : <" + VERIFIED_INDIVIDUAL_IDENTIFIER + "> has incorrect assurance level value : <" + assuranceLevel + ">");

        }

        return accountType;
    }

    public static UserAccountType fromValues(final String userType, final String assuranceLevel, String authentication) throws IllegalArgumentException {
        return userType.equals("VerifiedIndividual") ? fromAssuranceLevel(assuranceLevel, authentication) : fromUserType(userType);
    }
}

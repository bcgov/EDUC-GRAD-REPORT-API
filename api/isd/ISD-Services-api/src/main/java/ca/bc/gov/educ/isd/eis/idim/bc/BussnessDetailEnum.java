/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

import static ca.bc.gov.educ.isd.eis.idim.bc.AccountDetailEnum.*;
import static ca.bc.gov.educ.isd.eis.idim.bc.IdentityTypesEnum.BUSINESS;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum BussnessDetailEnum {

    BUSINESS_GUID(BUSINESS.value() + "_" + GUID.value()),
    BUSINESS_LUID(BUSINESS.value() + "_" + LUID.value()),
    BUSINESS_DLUID(BUSINESS.value() + "_" + DLUID.value()),
    BUSINESS_TYPE(BUSINESS.value() + "_type"),
    BUSINESS_LEGAL_NAME(BUSINESS.value() + "_legalName"),
    BUSINESS_NUMBER(BUSINESS.value() + "_businessNumber"),
    BUSINESS_NUMBER_VERIFIED_FLAG(BUSINESS.value() + "_businessNumberVerifiedFlag"),
    BUSINESS_STATEMENT_REGISTRATION_NUMBER(BUSINESS.value() + "_statementOfRegistrationNumber"),
    BUSINESS_INCORPORATION_NUMBER(BUSINESS.value() + "_incorporationNumber"),
    BUSINESS_JURISDICTION_INCORPORATION(BUSINESS.value() + "_jurisdictionOfIncorporation"),
    DOING_BUSINESS_AS(BUSINESS.value() + "_doingBusinessAs"),
    BUSINESS_STATE(BUSINESS.value() + "_state"),
    HUB_BUSINESS_TYPE(BUSINESS.value() + "_bnHubBusinessType"),
    BUSINESS_TYPE_OTHER(BUSINESS.value() + "_businessTypeOther");

    private final String value;

    BussnessDetailEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BussnessDetailEnum fromValue(String v) {
        for (BussnessDetailEnum c : BussnessDetailEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);

    }
}

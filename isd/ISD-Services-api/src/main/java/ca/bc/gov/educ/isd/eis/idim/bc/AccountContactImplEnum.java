/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

import static ca.bc.gov.educ.isd.eis.idim.bc.IdentityTypesEnum.CONTACT_ACCOUNT;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum AccountContactImplEnum {

    PREFERENCE(CONTACT_ACCOUNT.value() + "_" + AccountContactEnum.PREFERENCE.value()),
    EMAIL(CONTACT_ACCOUNT.value() + "_" + AccountContactEnum.EMAIL.value()),
    TELEPHONE(CONTACT_ACCOUNT.value() + "_" + AccountContactEnum.TELEPHONE.value()),
    PREFERRED_NAME(CONTACT_ACCOUNT.value() + "_" + AccountContactEnum.PREFERRED_NAME.value()),
    DEPARTMENT(CONTACT_ACCOUNT.value() + "_" + AccountContactEnum.DEPARTMENT.value());

    private final String value;

    AccountContactImplEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountContactImplEnum fromValue(String v) {
        for (AccountContactImplEnum c : AccountContactImplEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

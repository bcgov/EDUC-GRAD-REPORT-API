/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum IdentityTypesEnum {

    INDIVIDUAL_IDENTITY("individualIdentity"),
    BUSINESS("business"),
    CONTACT_ACCOUNT("accountContact"),
    INTERNAL_IDENTITY("internalIdentity");

    private final String value;

    IdentityTypesEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentityTypesEnum fromValue(String v) {
        for (IdentityTypesEnum c : IdentityTypesEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);

    }
}

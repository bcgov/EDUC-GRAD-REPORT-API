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
public enum AddressTypeEnum {

    PREFIX_ACCOUNT_CONTACT("accountContact"),
    PREFIX_INDIVIDUAL_RESIDENTIAL("individualIdentity_residential"),
    PREFIX_INDIVIDUAL_MAILING("individualIdentity_mailing"),
    PREFIX_BUSINESS("business");

    private final String value;

    AddressTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AddressTypeEnum fromValue(String v) {
        for (AddressTypeEnum c : AddressTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

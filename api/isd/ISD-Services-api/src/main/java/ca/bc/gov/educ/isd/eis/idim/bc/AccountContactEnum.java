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
enum AccountContactEnum {

    PREFERENCE("preference"),
    EMAIL("email"),
    TELEPHONE("telephone"),
    PREFERRED_NAME("preferredName"),
    DEPARTMENT("department");

    private final String value;

    AccountContactEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountContactEnum fromValue(String v) {
        for (AccountContactEnum c : AccountContactEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

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
enum AccountStateEnum {

    EXPIRY_DATE_TIME("expiryDateTime"),
    IS_SUSPENDED("isSuspended"),
    IS_MANAGER_DISABLED("isManagerDisabled"),
    IS_LOCKED("isLocked"),
    PASSWORD_CHANGE_REQUIRED("passwordChangeRequired"),
    PASSWORD_HINT_REQUIRED("passwordHintsRequired");

    private final String value;

    AccountStateEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountStateImplEnum fromValue(String v) {
        for (AccountStateImplEnum c : AccountStateImplEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

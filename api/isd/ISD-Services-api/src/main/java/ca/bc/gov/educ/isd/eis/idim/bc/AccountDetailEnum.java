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
public enum AccountDetailEnum {

    GUID("guid"),
    LUID("luid"),
    DLUID("dluid"),
    BCEID_LUID("bceidLuid"),
    BCEID_DLUID("bceidDluid"),
    USER_ID("userId"),
    DISPLAY_NAME("displayName"),
    ACCOUNT_TYPE_CODE("type");

    private final String value;

    AccountDetailEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountDetailEnum fromValue(String v) {
        for (AccountDetailEnum c : AccountDetailEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

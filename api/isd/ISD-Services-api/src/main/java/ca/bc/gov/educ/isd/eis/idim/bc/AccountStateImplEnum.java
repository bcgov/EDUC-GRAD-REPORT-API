/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

import static ca.bc.gov.educ.isd.eis.idim.bc.AccountStateEnum.EXPIRY_DATE_TIME;
import static ca.bc.gov.educ.isd.eis.idim.bc.AccountStateEnum.IS_LOCKED;
import static ca.bc.gov.educ.isd.eis.idim.bc.AccountStateEnum.IS_MANAGER_DISABLED;
import static ca.bc.gov.educ.isd.eis.idim.bc.AccountStateEnum.IS_SUSPENDED;
import static ca.bc.gov.educ.isd.eis.idim.bc.AccountStateEnum.PASSWORD_CHANGE_REQUIRED;
import static ca.bc.gov.educ.isd.eis.idim.bc.AccountStateEnum.PASSWORD_HINT_REQUIRED;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum AccountStateImplEnum {

    ACCOUNT_EXPIRY_DATE_TIME(Constants.PREFIX + "_" + EXPIRY_DATE_TIME.value()),
    ACCOUNT_IS_SUSPENDED(Constants.PREFIX + "_" + IS_SUSPENDED.value()),
    ACCOUNT_IS_MANAGER_DISABLED(Constants.PREFIX + "_" + IS_MANAGER_DISABLED.value()),
    ACCOUNT_IS_LOCKED(Constants.PREFIX + "_" + IS_LOCKED.value()),
    ACCOUNT_PASSWORD_CHANGE_REQUIRED(Constants.PREFIX + "_" + PASSWORD_CHANGE_REQUIRED.value()),
    ACCOUNT_PASSWORD_HINT_REQUIRED(Constants.PREFIX + "_" + PASSWORD_HINT_REQUIRED.value());

    final String value;

    AccountStateImplEnum(String v) {
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

    private static class Constants {

        public static final String PREFIX = "accountState";
    }
}

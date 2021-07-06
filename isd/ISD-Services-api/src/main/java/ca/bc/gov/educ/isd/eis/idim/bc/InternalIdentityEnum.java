/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

import static ca.bc.gov.educ.isd.eis.idim.bc.IdentityTypesEnum.INTERNAL_IDENTITY;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum InternalIdentityEnum {

    INTERNAL_TITLE(INTERNAL_IDENTITY.value() + "_title"),
    INTERNAL_COMPANY(INTERNAL_IDENTITY.value() + "_company"),
    INTERNAL_ORGANIZATION_CODE(INTERNAL_IDENTITY.value() + "_organizationCode"),
    INTERNAL_DEPARTMENT(INTERNAL_IDENTITY.value() + "_department"),
    INTERNAL_OFFICE(INTERNAL_IDENTITY.value() + "_office"),
    INTERNAL_DESCRIPTION(INTERNAL_IDENTITY.value() + "_description"),
    INTERNAL_EMPLOYEE_ID(INTERNAL_IDENTITY.value() + "_employeeId");

    private final String value;

    InternalIdentityEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InternalIdentityEnum fromValue(String v) {
        for (InternalIdentityEnum c : InternalIdentityEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);

    }
}

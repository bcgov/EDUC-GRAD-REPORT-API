/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.idim.bc;

import static ca.bc.gov.educ.isd.eis.idim.bc.IdentityTypesEnum.INDIVIDUAL_IDENTITY;
import static ca.bc.gov.educ.isd.eis.idim.bc.PersonalInfoEnum.*;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum IndividualIdentity {

    INDIVIDUAL_DATE_OF_BIRTH(INDIVIDUAL_IDENTITY.value()),
    INDIVIDUAL_NAME(NAME.value()),
    INDIVIDUAL_FIRST_NAME(FIRST_NAME.value()),
    INDIVIDUAL_MIDDLE_NAME(MIDDLE_NAME.value()),
    INDIVIDUAL_OTHER_MIDDLE_NAME(OTHER_MIDDLE_NAME.value()),
    INDIVIDUAL_SURNAME(SURNAME.value()),
    INDIVIDUAL_INITIALS(INITIALS.value());

    private final String value;

    IndividualIdentity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IndividualIdentity fromValue(String v) {
        for (IndividualIdentity c : IndividualIdentity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);

    }
}

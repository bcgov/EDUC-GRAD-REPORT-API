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
enum PersonalInfoEnum {

    DATE_OF_BIRTH("dateOfBirth"),
    NAME("name"),
    FIRST_NAME("firstname"),
    MIDDLE_NAME("middleName"),
    OTHER_MIDDLE_NAME("otherMiddleName"),
    SURNAME("surname"),
    INITIALS("initials");

    private final String value;

    PersonalInfoEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PersonalInfoEnum fromValue(String v) {
        for (PersonalInfoEnum c : PersonalInfoEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);

    }
}

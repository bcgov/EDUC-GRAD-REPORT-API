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
public enum AddressEnum {

    ADDRESS_LINE_ONE("addressLine1"),
    ADDRESS_LINE_TWO("addressLine2"),
    CITY("city"),
    POSTAL_CODE("postal"),
    PROVINCE("province"),
    COUNTRY("country"),
    UNSTRUCTURED_ADDRESS("unstructuredAddress");

    private final String value;

    AddressEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AddressEnum fromValue(String v) {
        for (AddressEnum c : AddressEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

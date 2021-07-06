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
public enum BCeIDAdaptorAccountContactPreferenceType {

    VOID("Void"),
    EMAIL("Email"),
    TELEPHONE("Telephone"),
    MAILING_ADDRESS("Mailing Address");

    private final String value;

    /**
     *
     * @param name
     */
    BCeIDAdaptorAccountContactPreferenceType(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    String getValue() {
        return value;
    }

    /**
     *
     * @param v
     * @return
     */
    public static BCeIDAdaptorAccountContactPreferenceType fromValue(String v) {
        for (BCeIDAdaptorAccountContactPreferenceType c : BCeIDAdaptorAccountContactPreferenceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

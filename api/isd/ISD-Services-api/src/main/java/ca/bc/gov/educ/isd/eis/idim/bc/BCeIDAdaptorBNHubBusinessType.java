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
public enum BCeIDAdaptorBNHubBusinessType {

    VOID("Void"),
    PROPRIETORSHIP("Proprietorship"),
    GENERAL_PARTNERSHIP("GeneralPartnership"),
    CORPORATION("Corporation"),
    OTHER("Other");

    private final String value;

    /**
     *
     * @param v
     */
    BCeIDAdaptorBNHubBusinessType(String v) {
        this.value = v;
    }

    /**
     *
     * @return
     */
    public String value() {
        return this.value;
    }

    /**
     *
     * @param v
     * @return
     */
    public static BCeIDAdaptorBNHubBusinessType fromValue(String v) {
        for (BCeIDAdaptorBNHubBusinessType c : BCeIDAdaptorBNHubBusinessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

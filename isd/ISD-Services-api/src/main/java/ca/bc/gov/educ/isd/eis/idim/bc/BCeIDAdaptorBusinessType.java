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
public enum BCeIDAdaptorBusinessType {

    VOID("Void"),
    PROPRIETORSHIP("Proprietorship"),
    PARTNERSHIP("Partnership"),
    CORPORATION("Corporation"),
    EXTRA_PROVINCIALLY_REGISTERED_COMPANY("ExtraProvinciallyRegisteredCompany"),
    OTHER("Other");

    private final String value;

    /**
     *
     * @param v
     */
    BCeIDAdaptorBusinessType(String v) {
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
    public static BCeIDAdaptorBusinessType fromValue(String v) {
        for (BCeIDAdaptorBusinessType c : BCeIDAdaptorBusinessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

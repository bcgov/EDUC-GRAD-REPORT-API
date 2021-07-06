/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                TaskPaymentMethod.java
 *  Date of Last Commit: $Date:: 2017-12-08 16:52:46 -0800 (Fri, 08 Dec 2017)  $
 *  Revision Number:     $Rev:: 8986                                           $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum TaskPaymentMethod {

    CREDIT_CARD("Online"),
    CHEQUE_MONEYORDER("Offline");

    private final String value;

    /**
     *
     * @param name
     */
    TaskPaymentMethod(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param v
     * @return
     */
    public static TaskPaymentMethod fromValue(String v) {
        for (TaskPaymentMethod c : TaskPaymentMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

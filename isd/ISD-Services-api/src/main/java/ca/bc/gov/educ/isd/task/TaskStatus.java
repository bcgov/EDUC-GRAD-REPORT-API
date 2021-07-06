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
 *  File:                TaskStatus.java
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016)  $
 *  Revision Number:     $Rev:: 4514                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum TaskStatus {

    NEW("New"),
    COMPLETE("Complete"),
    IN_PROGRESS("In Progress"),
    CANCELLED("Cancelled"),
    RESOLVED("Resolved"),
    PENDING("Pending"),
    UNLOCKED("Unlocked"),
    LOCKED("Locked");

    private final String value;

    /**
     *
     * @param name
     */
    TaskStatus(String value) {
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
    public static TaskStatus fromValue(String v) {
        for (TaskStatus c : TaskStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

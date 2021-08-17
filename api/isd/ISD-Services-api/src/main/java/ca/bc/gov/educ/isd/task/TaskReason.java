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
 *  File:                TaskReason.java
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
public enum TaskReason {

    EXCEEDED_PEN_MATCH_ATTEMPTS("Exceeded PEN Match Attempts"),
    STATUS_MERGE("Status of Merged"),
    STATUS_DECEASED("Status Deceased"),
    STUDENT_GRADE_NOT12_OR_AD("Student Grade NOT 12 or AD");

    private final String value;

    /**
     *
     * @param name
     */
    TaskReason(String value) {
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
    public static TaskReason fromValue(String v) {
        for (TaskReason c : TaskReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

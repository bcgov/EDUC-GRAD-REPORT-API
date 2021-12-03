/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: ReportCourseType.java 11485 2018-11-14 23:48:54#$
 *  Date of Last Commit: $Date:: 2018-11-14 15:48:54 -0800 (Wed, 14 Nov 2018)  $
 *  Revision Number:     $Rev:: 11485                                          $
 *  Last Commit by:      $Author:: dajarvis                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.model.course;

/**
 * Represents the types of courses in the course registry (e.g., provincially
 * examinable, non-provincially examinable, assessments, and so forth).
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum ReportCourseType {

    /**
     * Report course type of 1.
     */
    PROVINCIALLY_EXAMINABLE("1", "Provincially Examinable"),
    /**
     * Report course type of 2.
     */
    NONPROVINCIALLY_EXAMINABLE("2", "Non-provincially Examinable"),
    /**
     * Report course type of 3.
     */
    ASSESSMENT("3", "Assessment");

    private final String code;
    private final String description;

    ReportCourseType(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Returns the enum associated with the given code.
     *
     * @param code The code to find the value of.
     * @return The course type code for the given code.
     */
    public static ReportCourseType valueFrom(final String code) {
        for (final ReportCourseType rct : values()) {
            if (rct.isCode(code)) {
                return rct;
            }
        }

        throw new IllegalArgumentException("No such course type code <" + code + ">.");
    }

    public boolean isCode(final String code) {
        final String thisCode = getCode();

        return thisCode.equals(code);
    }

    public String getCode() {
        return this.code;
    }

    private String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}

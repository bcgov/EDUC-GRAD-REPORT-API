/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.assessment;

/**
 * Represents all the course codes for "courses" that piggy-back on the
 * transcript and examination course codes.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum IncompleteAssessmentCode {

    AEG("AEG"),
    DSQ("DSQ"),
    NC("NC"),
    XMT("XMT");

    /**
     * Associated with student's assessment score result.
     */
    private final String code;

    private IncompleteAssessmentCode(final String code) {
        this.code = code;
    }

    /**
     * Returns the code associated with student's assessment score result.
     *
     * @return A non-null, non-empty String.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Answers whether the given code matches this enumerated instance's code.
     * This is case insensitive.
     *
     * @param code The code to compare.
     * @return false The code does not match.
     */
    private boolean isCode(final String code) {
        return getCode().equalsIgnoreCase(code);
    }

    /**
     * Determines the enumerated instance that matches the given code.
     *
     * @param code The enumerated code to look up.
     * @return The enumerated instance that matches the given code.
     * @throws IllegalArgumentException The code could not be found.
     */
    public static IncompleteAssessmentCode fromValue(final String code) {
        for (final IncompleteAssessmentCode ac : values()) {
            if (ac.isCode(code)) {
                return ac;
            }
        }

        throw new IllegalArgumentException(code);
    }
}

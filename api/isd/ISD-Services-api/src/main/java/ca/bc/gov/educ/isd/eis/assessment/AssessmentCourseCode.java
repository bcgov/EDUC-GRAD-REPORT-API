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
package ca.bc.gov.educ.isd.eis.assessment;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents all the course codes for "courses" that piggy-back on the
 * transcript and examination course codes. Note that this enum has a duplicate
 * in ISD-Services-api
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum AssessmentCourseCode {

    LITERACY_ENGLISH("LTE10"),
    LITERACY_FRENCH("LTP10"),
    NUMERACY_ENGLISH("NME"),
    NUMERACY_FRENCH("NMF"),
    NUMERACY_ENGLISH10("NME10"),
    NUMERACY_FRENCH10("NMF10");

    /**
     * Associated with student's assessment score result.
     */
    private final String code;

    private AssessmentCourseCode(final String code) {
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
     *
     * @param code The code to compare.
     * @return false The code does not match.
     */
    private boolean isCode(final String code) {
        return getCode().equals(code);
    }

    /**
     * Determines the enumerated instance that matches the given code.
     *
     * @param code The enumerated code to look up.
     * @return The enumerated instance that matches the given code.
     * @throws IllegalArgumentException The code could not be found.
     */
    public static AssessmentCourseCode fromValue(final String code) {
        for (final AssessmentCourseCode ac : values()) {
            if (ac.isCode(code)) {
                return ac;
            }
        }

        throw new IllegalArgumentException(code);
    }
    
    public static List<AssessmentCourseCode> getNumeracyCodes() {
        List<AssessmentCourseCode> numeracyCodes = new ArrayList();
        numeracyCodes.add(NUMERACY_ENGLISH);
        numeracyCodes.add(NUMERACY_ENGLISH10);
        numeracyCodes.add(NUMERACY_FRENCH);
        numeracyCodes.add(NUMERACY_FRENCH10);
        return numeracyCodes;
    }
    
    public static List<AssessmentCourseCode> getLiteracyCodes() {
        List<AssessmentCourseCode> literacyCodes = new ArrayList();
        literacyCodes.add(LITERACY_ENGLISH);
        literacyCodes.add(LITERACY_FRENCH);
        return literacyCodes;
    }
}

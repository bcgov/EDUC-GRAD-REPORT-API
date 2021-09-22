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
 *  File:                $Id:: GraduationProgramCode.java 8355 2017-10-06 20:2#$
 *  Date of Last Commit: $Date:: 2017-10-06 13:20:51 -0700 (Fri, 06 Oct 2017)  $
 *  Revision Number:     $Rev:: 8355                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.grad;

/**
 * Constants (and synonyms) for the various graduation program codes. These
 * values reflect the program codes from the TRAX database. The list is not
 * meant to be exhaustive (e.g., there is no 2017) as the transcripts will, in
 * time, all look the same, eliminating the need to differentiate based on these
 * values.
 *
 * With minor variations, the following reports are approximately equivalent:
 *
 * 1950 = 1986<br/>
 * 1995 = 2004 = 2018 = SCCP<br/>
 *
 * 1986 also known as course-based<br />
 * 1950 also known as adult<br />
 * 1995 also known as 1996
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum GraduationProgramCode {

    PROGRAM_1950("1950", "Adult Graduation Program", 20),
    PROGRAM_1986("1986", "Course-Based Graduation Program", 52),
    PROGRAM_1995("1995", "Graduation Program 1995", 52),
    PROGRAM_1996("1996", PROGRAM_1995.getDescription(), PROGRAM_1995.getCredits()),
    PROGRAM_2004("2004", "Graduation Program 2004", 80),
    PROGRAM_2018("2018", "Graduation Program 2018", 80),
    PROGRAM_2018_EN("2018-EN", "Graduation Program 2018", 80),
    PROGRAM_2018_PF("2018-PF", "2018 Programme Francophone", 80),
    PROGRAM_2004_EN("2004-EN", "Graduation Program 2004", 80),
    PROGRAM_2004_PF("2004-PF", "2004 Programme Francophone", 80),
    PROGRAM_NOPROG("NOPROG", "No Program Specified", 0),
    PROGRAM_SCCP("SCCP", "School Completion Certificate Program", 0);

    private final String code;
    private final String description;
    private final int credits;

    /**
     * Constructs a new graduation program code enumerated type.
     *
     * @param code The graduation program code (from TRAX).
     * @param description The human-readable description of the code.
     * @param credits The number of credits required to graduate from this
     * program.
     */
    private GraduationProgramCode(final String code, final String description, final int credits) {
        this.code = code;
        this.description = description;
        this.credits = credits;
    }

    /**
     * Returns the enum associated with the given code.
     *
     * @param code The code to find the value of.
     * @return The graduation program code for the given code.
     */
    public static GraduationProgramCode valueFrom(final String code) {
        for (final GraduationProgramCode gpc : values()) {
            if (gpc.isCode(code)) {
                return gpc;
            }
        }

        throw new IllegalArgumentException("No such program code: " + code);
    }

    /**
     * Answers whether this program code and the given program code are
     * identical (ignoring case).
     *
     * @param code The code to compare against.
     * @return true The codes are identical.
     */
    public boolean isCode(final String code) {
        return toString().equalsIgnoreCase(code);
    }

    /**
     * Returns the human-readable text for this program code.
     *
     * @return A textual description of the code, never null, never empty.
     */
    public String getDescription() {
        return this.description;
    }

    public String getCode() {
        return code;
    }

    /**
     * Returns the number of credits required to graduate from the program.
     *
     * @return A positive integer.
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Returns true if this code represents an adult graduation program.
     *
     * @return true This is an adult program.
     */
    public boolean isAdult() {
        return equals(PROGRAM_1950);
    }

    /**
     * Returns the string representation of this graduation program code.
     *
     * @return The graduation program code (without the description).
     */
    @Override
    public String toString() {
        return this.code;
    }
}

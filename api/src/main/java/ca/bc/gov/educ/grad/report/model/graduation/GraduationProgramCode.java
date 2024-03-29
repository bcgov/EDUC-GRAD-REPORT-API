/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: GraduationProgramCode.java 8329 2017-10-04 20:4#$
 *  Date of Last Commit: $Date:: 2017-10-04 13:42:43 -0700 (Wed, 04 Oct 2017)  $
 *  Revision Number:     $Rev:: 8329                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.graduation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

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
@XmlType
@XmlEnum(String.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum GraduationProgramCode implements Serializable {

    @XmlEnumValue("BLANK")
    @JsonProperty("BLANK")
    PROGRAM_BLANK("BLANK", "Used for blank transcript", 0),

    @XmlEnumValue("1950")
    @JsonProperty("1950")
    PROGRAM_1950("1950", "Adult Graduation Program", 20),

    @XmlEnumValue("1986")
    @JsonProperty("1986")
    PROGRAM_1986("1986", "Course-Based Graduation Program", 52),

    @XmlEnumValue("1986-EN")
    @JsonProperty("1986-EN")
    PROGRAM_1986_EN("1986-EN", "Course-Based Graduation Program", 52),

    @XmlEnumValue("1986-PF")
    @JsonProperty("1986-PF")
    PROGRAM_1986_PF("1986-PF", "Course-Based Graduation Program French", 52),

    @XmlEnumValue("1996")
    @JsonProperty("1996")
    PROGRAM_1996("1996", "Graduation Program 1995", 52),

    @XmlEnumValue("1996-EN")
    @JsonProperty("1996-EN")
    PROGRAM_1996_EN("1996-EN", "Graduation Program 1995", 52),

    @XmlEnumValue("1996-PF")
    @JsonProperty("1996-PF")
    PROGRAM_1996_PF("1996-PF", "1995 Programme Francophone", 52),

    @XmlEnumValue("2004")
    @JsonProperty("2004")
    PROGRAM_2004("2004", "Graduation Program 2004", 80),

    @XmlEnumValue("2018")
    @JsonProperty("2018")
    PROGRAM_2018("2018", "Graduation Program 2018", 80),

    @XmlEnumValue("2018-EN")
    @JsonProperty("2018-EN")
    PROGRAM_2018_EN("2018-EN", "Graduation Program 2018", 80),

    @XmlEnumValue("SCCP")
    @JsonProperty("SCCP")
    PROGRAM_SCCP("SCCP", "School Completion Certificate Program", 0),

    @XmlEnumValue("SCCP-PF")
    @JsonProperty("SCCP-PF")
    PROGRAM_SCCP_PF("SCCP-PF", "School Completion Certificate Program French", 0),

    @XmlEnumValue("2004-PF")
    @JsonProperty("2004-PF")
    PROGRAM_2004_PF("2004-PF", "2004 Programme Francophone", 80),

    @XmlEnumValue("2004-EN")
    @JsonProperty("2004-EN")
    PROGRAM_2004_EN("2004-EN", "Graduation Program 2004", 80),

    @XmlEnumValue("NOPROG")
    @JsonProperty("NOPROG")
    PROGRAM_NOPROG("NOPROG", "No Program Specified", 0),

    @XmlEnumValue("2018-PF")
    @JsonProperty("2018-PF")
    PROGRAM_2018_PF("2018-PF", "2018 Programme Francophone", 80),

    @XmlEnumValue("2023-EN")
    @JsonProperty("2023-EN")
    PROGRAM_2023_EN("2023-EN", "Graduation Program 2023", 0),

    @XmlEnumValue("2023-PF")
    @JsonProperty("2023-PF")
    PROGRAM_2023_PF("2023-PF", "Graduation Program 2023", 0);

    private String code;
    private String description;
    private int credits;

    /**
     * Constructs a new graduation program code enumerated type.
     *
     * @param code The graduation program code (from TRAX).
     * @param description The human-readable description of the code.
     * @param credits The number of credits required to graduate from this
     * program.
     */
    GraduationProgramCode(final String code, final String description, final int credits) {
        this.code = code;
        this.description = description;
        this.credits = credits;
    }

    @JsonCreator
    public static GraduationProgramCode forValue(@JsonProperty("code") final String code, @JsonProperty("description") final String description, @JsonProperty("credits") final int credits) {
        for (GraduationProgramCode graduationProgramCode : GraduationProgramCode.values()) {
            if (graduationProgramCode.code.equals(code)) {
                return graduationProgramCode;
            }
        }
        return null;
    }
    /**
     * Returns the enum associated with the given code.
     *
     * @param code The code to find the value of.
     * @return The graduation program code for the given code.
     */
    public static GraduationProgramCode valueFrom(final String code, final String description) {
        for (final GraduationProgramCode gpc : values()) {
            if (gpc.isCode(code)) {
                if(description != null) {
                    gpc.setDescription(description);
                }
                return gpc;
            }
            if (gpc.isCode(description)) {
                if(code != null) {
                    gpc.setDescription(code);
                }
                return gpc;
            }
        }

        throw new IllegalArgumentException("No such program code <" + code + ">.");
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @JsonValue
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the number of credits required to graduate from the program.
     *
     * @return A positive integer.
     */
    @JsonValue
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

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
package ca.bc.gov.educ.grad.report.model.transcript;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
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

public enum TranscriptTypeCode implements Serializable {

    @XmlEnumValue("BC1950-PUB")
    @JsonProperty("BC1950-PUB")
    BC1950_PUB("BC1950-PUB", "Adult Public School Transcript BC"),

    @XmlEnumValue("BC1950-IND")
    @JsonProperty("BC1950-IND")
    BC1950_IND("BC1950-IND", "Adult Independent School Transcript"),

    @XmlEnumValue("YU1950-PUB")
    @JsonProperty("YU1950-PUB")
    YU1950_PUB("YU1950-PUB", "Adult Public School Transcript Yukon"),

    @XmlEnumValue("BC1986-PUB")
    @JsonProperty("BC1986-PUB")
    BC1986_PUB("BC1986-PUB", "1986 Public School Transcript BC; including Programme Francophone (no offshore)"),

    @XmlEnumValue("BC1986-IND")
    @JsonProperty("BC1986-IND")
    BC1986_IND("BC1986-IND", "1986 Independent School Transcript"),

    @XmlEnumValue("YU1986-PUB")
    @JsonProperty("YU1986-PUB")
    YU1986_PUB("YU1986-PUB", "1986 Public School Transcript Yukon"),

    @XmlEnumValue("BC1995-PUB")
    @JsonProperty("BC1995-PUB")
    BC1995_PUB("BC1995-PUB", "1996 Public School Transcript BC; including Programme Francophone and Offshore"),

    @XmlEnumValue("BC1996-PUB")
    @JsonProperty("BC1996-PUB")
    BC1996_PUB("BC1996-PUB", BC1995_PUB.getDescription()),

    @XmlEnumValue("BC1996-IND")
    @JsonProperty("BC1996-IND")
    BC1996_IND("BC1996-IND", BC1995_PUB.getDescription()),

    @XmlEnumValue("YU1995-PUB")
    @JsonProperty("YU1995-PUB")
    YU1995_PUB("YU1995-PUB", "1996 Public School Transcript Yukon"),

    @XmlEnumValue("YU1996-PUB")
    @JsonProperty("YU1996-PUB")
    YU1996_PUB("YU1996-PUB", YU1995_PUB.getDescription()),

    @XmlEnumValue("SCCP-EN")
    @JsonProperty("SCCP-EN")
    SCCP_EN("SCCP-EN", "School Completion Certificate Program Transcript"),

    @XmlEnumValue("SCCP-FR")
    @JsonProperty("SCCP-FR")
    SCCP_FR("SCCP-FR", "School Completion Certificate Program French Transcript"),

    @XmlEnumValue("BC2004-PUB")
    @JsonProperty("BC2004-PUB")
    BC2004_PUB("BC2004-PUB", "2004 Public School Transcript BC; including Programme Francophone and Offshore"),

    @XmlEnumValue("BC2004-IND")
    @JsonProperty("BC2004-IND")
    BC2004_IND("BC2004-IND", "2004 Independent School Transcript"),

    @XmlEnumValue("YU2004-PUB")
    @JsonProperty("YU2004-PUB")
    YU2004_PUB("YU2004-PUB", "2004 Public School Transcript Yukon"),

    @XmlEnumValue("BC2018-PUB")
    @JsonProperty("BC2018-PUB")
    BC2018_PUB("BC2018-PUB", "2018 Public School Transcript BC"),

    @XmlEnumValue("BC2018-IND")
    @JsonProperty("BC2018-IND")
    BC2018_IND("BC2018-IND", "2018 Independent School Transcript"),

    @XmlEnumValue("BC2018-OFF")
    @JsonProperty("BC2018-OFF")
    BC2018_OFF("BC2018-OFF", "2018 Offshore Transcript"),

    @XmlEnumValue("YU2018-PUB")
    @JsonProperty("YU2018-PUB")
    YU2018_PUB("YU2018-PUB", "2018 Public School Transcript Yukon"),

    @XmlEnumValue("BC2018-PF")
    @JsonProperty("BC2018-PF")
    BC2018_PF("BC2018-PF", "2018 Programme Francophone Transcript"),

    @XmlEnumValue("NOPROG")
    @JsonProperty("NOPROG")
    NOPROG("NOPROG", "No Program Specified");

    private String code;
    private String description;

    /**
     * Constructs a new graduation program code enumerated type.
     *
     * @param code The graduation program code (from TRAX).
     * @param description The human-readable description of the code.
     * program.
     */
    TranscriptTypeCode(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static TranscriptTypeCode forValue(@JsonProperty("code") final String code, @JsonProperty("description") final String description) {
        for (TranscriptTypeCode graduationProgramCode : TranscriptTypeCode.values()) {
            if (graduationProgramCode.code.equals(code)) {
                graduationProgramCode.description = description;
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
    public static TranscriptTypeCode valueFrom(final String code) {
        for (final TranscriptTypeCode gpc : values()) {
            if (gpc.isCode(code)) {
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

    /**
     * Returns true if this code represents an adult graduation program.
     *
     * @return true This is an adult program.
     */
    public boolean isAdult() {
        return equals(BC1950_PUB);
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

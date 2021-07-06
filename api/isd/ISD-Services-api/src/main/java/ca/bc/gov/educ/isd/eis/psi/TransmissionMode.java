/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.psi;

/**
 * Transmission mode used to send information to a PSI. This is duplicated from
 * ca.bc.gov.educ.isd.psi.TransmissionMode because external systems must not be
 * tightly coupled.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum TransmissionMode {

    FTP("FTP", "Electronic"),
    PAPER("PAPER", "Printed"),
    XML("XML", "PESC");

    private final String code;
    private final String displayName;

    /**
     * Constructs an enumerated instance used to describe a PSI's transmission
     * mode.
     *
     * @param code The transmission mode code for a given PSI.
     * @param displayName The preferred on-screen display name.
     */
    private TransmissionMode(final String code, final String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * Returns the standard code for this transmission mode.
     *
     * @return A non-null String.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the transmission mode description.
     *
     * @return A non-null String.
     */
    public String getDescription() {
        return this.displayName;
    }

    /**
     * Answers whether the given code matches the internal code, case
     * insensitively. This method is null-safe.
     *
     * @param code The code to compare against this enumerated type's code.
     * @return true iff code equals the internal code, ignoring case.
     */
    public boolean isCode(final String code) {
        final String safeCode = code == null ? "" : code.trim();

        return getCode().equalsIgnoreCase(safeCode);
    }

    /**
     * Returns the enumerated type that corresponds to the given transmission
     * mode. This will return PAPER if the given code is null, empty, or does
     * not match any of the known modes.
     *
     * @param code The transmission mode code (can be null or empty).
     * @return The enumerated type that corresponds to the given code.
     */
    public static TransmissionMode fromCode(final String code) {
        TransmissionMode result = PAPER;

        for (final TransmissionMode mode : values()) {
            if (mode.isCode(code)) {
                result = mode;
                break;
            }
        }

        // Will return PAPER if there's no match.
        return result;
    }
}

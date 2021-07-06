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
 *  File:                $Id:: ScholarshipName.java 5701 2016-12-20 00:29:22Z #$
 *  Date of Last Commit: $Date:: 2016-12-19 16:29:22 -0800 (Mon, 19 Dec 2016)  $
 *  Revision Number:     $Rev:: 5701                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import static ca.bc.gov.educ.isd.eis.trax.Constants.DATE_TRAX_YMD;

/**
 * This defines: the scholarships, their relative expiration year (e.g., 5 years
 * from awarded), and when the scholarship name is no longer effective (e.g.,
 * 2016).
 *
 * TODO: Replace with a database table as this logic does not belong in a
 * hard-coded class.
 */
public enum ScholarshipName {

    // The order is important because the scholarship names with specific
    // effective dates (e.g., DISTRICT_AUTHORITY) must precede those with
    // no effective dates (e.g., DISTRICT_VOUCHER). This is because the
    // default effective date is a large negative number (Integer.MIN_VALUE).
    SECONDARY_APPRENTICE(
            "A", 0, "Secondary School Apprentice Scholarship"),
    ACHIEVEMENT(
            "C", 5, "BC Achievement Scholarship"),
    DISTRICT_AUTHORITY(
            "D", 5, "District/Authority Scholarship", 2016),
    DISTRICT_VOUCHER(
            "D", 5, "District/Authority Scholarship (voucher only)"),
    EXCELLENCE(
            "E", 5, "BC Excellence Scholarship"),
    GRAD_PROGRAM(
            "S", 5, "Graduation Program Examinations"),
    TEACHER_EDUCATION(
            "T", 7, "Pathway to Teacher Education Scholarship");

    private final static String SEPTEMBER_30 = "0930";

    private final String code;
    private final String name;
    private final int expiry;
    private final int effective;

    /**
     * @param code Unique code.
     * @param expiry Number of years from the award date that the award expires.
     * @param name Award name.
     * @param effective The date the award becomes effective.
     */
    private ScholarshipName(String code, int expiry, String name, int effective) {
        this.code = code;
        this.expiry = expiry;
        this.name = name;
        this.effective = effective;
    }

    /**
     * Constructs a scholarship award name that, By default, is effective until
     * the current year (i.e., always).
     *
     * @param code Unique code.
     * @param expiry Number of years from the award date that the award expires.
     * @param name Award name.
     */
    private ScholarshipName(String code, int expiry, String name) {
        this(code, expiry, name, Integer.MIN_VALUE);
    }

    private String getName() {
        return this.name;
    }

    private String getCode() {
        return this.code;
    }

    private int getExpiry() {
        return this.expiry;
    }

    private int getEffectiveYear() {
        return this.effective;
    }

    /**
     * Sums the number of years for this award to expire with year the
     * scholarship was awarded.
     *
     * @param awardYear Year the scholarship was awarded.
     * @return The date an awarded scholarship expires, or null if the award
     * never expires.
     * @throws Exception Could not parse the award date.
     */
    public Date toExpiryDate(final int awardYear) throws Exception {
        final int expires = getExpiry();

        return expires > 0
                ? new SimpleDateFormat(DATE_TRAX_YMD).parse((getExpiry() + awardYear) + SEPTEMBER_30)
                : null;
    }

    /**
     * Returns true iff the given code matches the constructed code.
     *
     * @param code
     * @return
     */
    private boolean isCode(String code) {
        return getCode().equalsIgnoreCase(code);
    }

    /**
     * Returns true iff the given year is greater than the effective year.
     *
     * @param year The year to compare against the effective year.
     * @return
     */
    public boolean isEffective(int year) {
        return getEffectiveYear() <= year;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Compares this scholarship's code against the given code; also compares
     * the effective year against the award year. This will return the
     * scholarship name for a matching code and its effective year. This relies
     * on http://docs.oracle.com/javase/specs/jls/se6/html/classes.html#8.9,
     * which states that list from calling values() is "in the order they're
     * declared".
     *
     * @param code The code to find.
     * @param awardYear Year the scholarship was awarded.
     * @return The scholarship name corresponding to the given code and award
     * year.
     */
    public static ScholarshipName getValue(final String code, final int awardYear) {
        for (ScholarshipName scholarship : values()) {
            if (scholarship.isCode(code) && scholarship.isEffective(awardYear)) {
                return scholarship;
            }
        }

        throw new IllegalArgumentException(code);
    }

    /**
     * Convenience method that ensures the awardCode is not null.
     *
     * @see getValue(String, awardYear)
     * @param awardCode The code to find.
     * @param awardYear Year the scholarship was awarded.
     * @return The scholarship name corresponding to the given code and award
     * year.
     */
    public static ScholarshipName getValue(
            final Character awardCode, final int awardYear) {
        if (awardCode == null) {
            throw new IllegalArgumentException(
                    "Award code must correspond to an enumerated ScholarshipName type (cannot be null).");
        }

        return getValue(awardCode.toString(), awardYear);
    }
}

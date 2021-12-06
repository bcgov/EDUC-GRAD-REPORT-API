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
package ca.bc.gov.educ.grad.dto.reports.impl;

import ca.bc.gov.educ.grad.model.common.DomainEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Established so that the ReportServices need not be coupled to the
 * AbstractDomainEntity from CommonSupport. This allows Jaspersoft Studio to use
 * the classes without needing AbstractDomainEntity from CommonSupport.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class AbstractReportDomainEntity implements DomainEntity {

    /**
     * Not implemented.
     *
     * @return Unused.
     */
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented.
     *
     * @return Unused.
     */
    @Override
    public String getEntityId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented.
     *
     * @return Unused.
     */
    @Override
    public Date getCreatedOn() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented.
     *
     * @return Unused.
     */
    @Override
    public String getCreatedBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented.
     *
     * @return Unused.
     */
    @Override
    public Date getLastUpdatedOn() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Not implemented.
     *
     * @return Unused.
     */
    @Override
    public String getLastUpdatedBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the current date if the given date is null.
     *
     * @param date The date to compare against null.
     * @return A valid date instance, never null.
     */
    public static Date nullSafe(final Date date) {
        return date == null ? new Date() : date;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param l The long to check against null.
     * @return l or 0, never null.
     */
    public static Long nullSafe(final Long l) {
        return l == null ? 0L : l;
    }

    /**
     * Returns an empty string if the given character sequence is null.
     *
     * @param s The character sequence to compare against null.
     * @return A non-null string.
     */
    public static String nullSafe(final String s) {
        return s == null ? "" : s;
    }

    /**
     * Returns an empty string if the given String is null, otherwise the
     * trimmed version of s.
     *
     * @param s The String to compare against null.
     * @return A non-null string, possibly empty.
     */
    public static String trimSafe(final String s) {
        return nullSafe(s).trim();
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param i The integer to check for null values.
     * @return i or 0, never null.
     */
    public static Integer nullSafe(final Integer i) {
        return i == null ? 0 : i;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param bd The number to check for null values.
     * @return bd or 0, never null.
     */
    public static BigDecimal nullSafe(final BigDecimal bd) {
        return bd == null ? new BigDecimal(0) : bd;
    }
}

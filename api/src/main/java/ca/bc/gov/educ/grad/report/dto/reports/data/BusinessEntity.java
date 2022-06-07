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
 *  File:                $Id:: BusinessEntity.java 10937 2018-07-20 22:43:12Z #$
 *  Date of Last Commit: $Date:: 2018-07-20 15:43:12 -0700 (Fri, 20 Jul 2018)  $
 *  Revision Number:     $Rev:: 10937                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_REPORT_SESSION;
import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_TRAX_YM;
import static java.util.logging.Level.WARNING;
import static java.util.logging.Logger.getLogger;

/**
 * Superclass for all classes in this package. The nullSafe methods are required
 * because the Jaspersoft Studio IDE cannot have a dependency on the
 * CommonSupport JAR file. If the dependency is created, Jaspersoft Studio will
 * lock the file, which prevents NetBeans from deleting it on a clean build.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class BusinessEntity implements Serializable {

    private static final long serialVersionUID = 3L;

    private static final String CLASSNAME = BusinessEntity.class.getName();
    private static final Logger LOG = getLogger(CLASSNAME);

    private Date createdOn;

    /**
     * Helper method to isolate what how to create an empty list.
     *
     * @param <T> The type of list to return.
     * @return Collections.EMPTY_LIST
     */
    protected <T extends Object> List<T> createEmptyList() {
        return new ArrayList<>();
    }

    /**
     * Sets the date this business entity instance was created.
     *
     * @param createdOn A valid date instance, never null.
     */
    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn == null
                ? new Date()
                : new Date(createdOn.getTime());
    }

    /**
     * Returns the date this business entity instance was created.
     *
     * @return A valid date instance, never null.
     */
    public Date getCreatedOn() {
        if (this.createdOn == null) {
            this.createdOn = new Date();
        }

        return new Date(this.createdOn.getTime());
    }

    /**
     * Fluent interface for building course grades and its subclasses, in
     * addition to other data implementation classes.
     *
     * @param <T> The object to be built.
     * @param <B> The sub-classed builder.
     */
    public static abstract class Builder<T extends BusinessEntity, B extends Builder<T, B>> {

        /**
         * The object to be built.
         */
        private final T object;

        /**
         * Creates a new object to be built (the outer class) and asks
         * subclasses for their sub-classed builder.
         */
        @SuppressWarnings("OverridableMethodCallInConstructor")
        public Builder() {
            this.object = createObject();
        }

        /**
         * Returns the object to be built.
         *
         * @return The outer object instance.
         */
        protected abstract T createObject();

        /**
         * Returns a reference to the builder.
         *
         * @return The inner object instance.
         */
        protected abstract B thisBuilder();

        /**
         * Returns the object to be built with its instance variables set via
         * the fluent interface.
         *
         * @return The initialized outer object.
         */
        public T build() {
            return getObject();
        }

        /**
         * Returns the object to be built.
         *
         * @return The object initialized using the fluent interface.
         */
        protected T getObject() {
            return this.object;
        }

        /**
         * Used by subclasses to create a Date instance from human-readable
         * values.
         *
         * @param year The date's year.
         * @param month The date's month.
         * @param day The date's day.
         * @return A Date instance set to the date supplied by the parameters.
         */
        protected Date createdDate(final int year, final int month, final int day) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            return calendar.getTime();
        }

        /**
         * Used by subclasses to create a Date instance from human-readable
         * values.
         *
         * @param year The date's year.
         * @param month The date's month.
         * @param day The date's day.
         * @return A Date instance set to the date supplied by the parameters.
         */
        public B withCreatedOn(final int year, final int month, final int day) {
            getObject().setCreatedOn(createdDate(year, month, day));
            return thisBuilder();
        }

        /**
         * Used by subclasses to create a Date instance from human-readable
         * values.
         *
         * @param date createdOn date
         * @return A Date instance set to the date supplied by the parameters.
         */
        public B withCreatedOn(Date date) {
            getObject().setCreatedOn(date);
            return thisBuilder();
        }
    }

    /**
     * Returns the current date if the given date is null.
     *
     * @param date The date to compare against null.
     * @return A valid date instance, never null.
     */
    public static Date nullSafe(Date date) {
        return date == null ? new Date() : date;
    }

    /**
     * Returns an empty string if the given string is null.
     *
     * @param string The string to compare against null.
     * @return A non-null String instance.
     */
    public static String nullSafe(String string) {
        return string == null ? "" : string;
    }

    /**
     * Ensures the list is never null.
     *
     * @param list The list to check against null.
     * @return A new ArrayList iff the given list is null, otherwise the given
     * list.
     */
    public static List<?> nullSafe(List<?> list) {
        return list == null ? new ArrayList<>() : list;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param d The double to check for null values.
     * @return d or 0, never null.
     */
    public static Double nullSafe(final Double d) {
        Double result = d;

        if (result == null) {
            result = 0d;
        }

        return result;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param l The long to check against null.
     * @return l or 0, never null.
     */
    public static Long nullSafe(final Long l) {
        Long result = l;

        if (result == null) {
            result = 0L;
        }

        return result;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param i The integer to check for null values.
     * @param d The default value to return if i == null.
     * @return i or d, never null.
     */
    public static Integer nullSafe(final Integer i, final Integer d) {
        Integer result = i;

        if (result == null) {
            result = d;
        }

        return result;
    }

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param i The integer to check for null values.
     * @return i or 0, never null.
     */
    public static Integer nullSafe(final Integer i) {
        return nullSafe(i, 0);
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

    /**
     * Used to ensure null safe values are returned from accessors.
     *
     * @param b The boolean to check for null values.
     * @return false iff b == null, otherwise b.
     */
    public static Boolean nullSafe(final Boolean b) {
        return b == null ? Boolean.FALSE : b;
    }

    /**
     * Converts a session date in TRAX format (yyyyMM) to a date for display on
     * reports (yyyy/MM).
     *
     * @param sessionDate TRAX-formatted date (can be null or empty).
     * @return Reformatted date, or sessionDate if parsing failed.
     */
    public String formatSessionDate(final String sessionDate) {
        String result;

        try {
            final DateFormat traxFormat = new SimpleDateFormat(DATE_TRAX_YM);
            final Date parsedDate = traxFormat.parse(sessionDate);
            final DateFormat reportFormat = new SimpleDateFormat(DATE_REPORT_SESSION);

            result = reportFormat.format(parsedDate);
        } catch (final ParseException ex) {
            LOG.log(WARNING, "Could not parse session date <{0}>: {1}",
                    new Object[]{sessionDate, ex.getMessage()});
            result = sessionDate;
        }

        return result;
    }
}

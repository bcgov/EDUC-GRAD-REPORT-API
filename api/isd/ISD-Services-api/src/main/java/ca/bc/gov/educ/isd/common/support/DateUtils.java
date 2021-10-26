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
 *  File:                DateUtils.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class DateUtils {

    /**
     * Sets the time for a given date.
     *
     * @param date The date to coerce to a specific time.
     * @param h The date's hour.
     * @param m The date's minute.
     * @param s The date's second.
     * @param ms The date's milliseconds.
     * @return A new date instance with the day of the given date and the time
     * according to the given parameters.
     */
    public static Date withTime(final Date date, final int h, final int m, final int s, final int ms) {
        Date verifiedDate = nullSafe(date);

        verifiedDate = setHours(verifiedDate, h);
        verifiedDate = setMinutes(verifiedDate, m);
        verifiedDate = setSeconds(verifiedDate, s);
        verifiedDate = setMilliseconds(verifiedDate, ms);

        return verifiedDate;
    }

    /**
     * Extracts year from a DATE in yyyy format.
     *
     * @param date
     * @return
     */
    public static String yearYYYY(final Date date) {
        return extractFromDate(date, "yyyy");
    }

    /**
     * Extracts month from a DATE in MM format.
     *
     * @param date
     * @return
     */
    public static String monthMM(final Date date) {
        return extractFromDate(date, "MM");
    }

    /**
     * Extracts month from a DATE in MMM format.
     *
     * @param date
     * @return
     */
    public static String monthMMM(final Date date) {
        return extractFromDate(date, "MMM");
    }

    /**
     * Extracts days from a DATE.
     *
     * @param date
     * @return
     */
    public static String dayDD(final Date date) {
        return extractFromDate(date, "dd");
    }

    /**
     * Extracts time plus time zone from a DATE.
     *
     * @param date
     * @return
     */
    public static String timeHHMMssSSZ(final Date date) {
        return extractFromDate(date, "HH:mm:ss.SSSZ");
    }

    /**
     * Extracts time from a DATE.
     *
     * @param date
     * @return
     */
    public static String timeHHMMssSS(final Date date) {
        return extractFromDate(date, "HH:mm:ss.SSS");
    }

    /**
     * Extracts a specific format from a DATE.
     *
     * @param date
     * @param format
     * @return
     */
    public static String extractFromDate(final Date date, final String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * Sets the date time to the end of the say.
     *
     * @param date
     * @return
     */
    public static Date endOfDay(final Date date) {
        return withTime(date, 23, 59, 59, 999);
    }

    /**
     * Sets the date time to the start of the say.
     *
     * @param date
     * @return
     */
    public static Date startOfDay(final Date date) {
        return withTime(date, 0, 0, 0, 0);
    }

    /**
     * Adds month to a Date.
     *
     * @param date
     * @param amountToAdd
     * @return
     */
    public static Date addYears(final Date date, final int amountToAdd) {
        return add(date, Calendar.YEAR, amountToAdd);
    }

    /**
     * Adds years to a Date.
     *
     * @param date
     * @param amountToAdd
     * @return
     */
    public static Date addMonth(final Date date, final int amountToAdd) {
        return add(date, Calendar.MONTH, amountToAdd);
    }

    /**
     * Adds days to a Date.
     *
     * @param date
     * @param amountToAdd
     * @return
     */
    public static Date addDays(final Date date, final int amountToAdd) {
        return add(date, Calendar.DAY_OF_MONTH, amountToAdd);
    }

    /**
     * Adds weeks to a Date.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * Adds minutes to a Date.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * Adds hours to a Date.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Adds seconds to a Date.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * Adds milliseconds to a Date.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliseconds(final Date date, final int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    public static Date setMonth(final Date date, final int amount) {
        return set(date, Calendar.MONTH, amount);
    }

    public static Date setDay(final Date date, final int amount) {
        return set(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date setDateToLastMonthDay(final Date date) {
        Date verifiedDate = nullSafe(date);
        final Calendar cal = Calendar.getInstance();

        cal.setLenient(false);
        cal.setTime(verifiedDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));

        return cal.getTime();
    }

    public static int getLastDayOfDate(final Date date) {
        return Integer.parseInt(dayDD(setDateToLastMonthDay(date)));
    }

    /**
     * Sets Date Hours.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date setHours(final Date date, final int amount) {
        return set(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * Sets Date minutes.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date setMinutes(final Date date, final int amount) {
        return set(date, Calendar.MINUTE, amount);
    }

    /**
     * Sets Date seconds.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date setSeconds(final Date date, final int amount) {
        return set(date, Calendar.SECOND, amount);
    }

    /**
     * Sets Date milliseconds.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date setMilliseconds(final Date date, final int amount) {
        return set(date, Calendar.MILLISECOND, amount);
    }

    private static Date add(final Date date, final int calendarField, final int amount) {
        Date verifiedDate = nullSafe(date);
        final Calendar cal = Calendar.getInstance();

        cal.setTime(verifiedDate);
        cal.add(calendarField, amount);
        Date newDate = cal.getTime();

        return newDate;
    }

    private static Date set(final Date date, final int calendarField, final int amount) {
        Date verifiedDate = nullSafe(date);
        final Calendar cal = Calendar.getInstance();

        cal.setLenient(false);
        cal.setTime(verifiedDate);
        cal.set(calendarField, amount);
        return cal.getTime();
    }

    /**
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date formateDate(final Date date, final String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String dateStr = extractFromDate(date, format);
        return df.parse(dateStr);
    }
}

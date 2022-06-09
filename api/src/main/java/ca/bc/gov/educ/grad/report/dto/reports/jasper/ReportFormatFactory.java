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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.jasper;

import net.sf.jasperreports.engine.util.DefaultFormatFactory;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Delegates creation of date and number formatters to JasperReports' default
 * formatters. This class ensures that dates are formatted consistently across
 * all reports.
 */
public class ReportFormatFactory extends DefaultFormatFactory {

    private final static String DATE_FORMAT = "date.format";
    private final static String DATE_FORMAT_DEFAULT = "dd-MMM-yyyy";

    private final static String SHORT_MONTH_NAMES[] = new String[]{
        "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT",
        "NOV", "DEC"
    };

    private ResourceBundle resourceBundle;

    /**
     * Constructs a new instance using custom properties read from an internal
     * resource map.
     */
    public ReportFormatFactory() {
    }

    /**
     * Constructs a new instance using a given resource bundle.
     *
     * @param resourceBundle The resource bundle to use for reading custom
     * properties.
     */
    public ReportFormatFactory(final ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Returns a DateFormat instance that creates dates in a common format.
     *
     * @param pattern Unused (see DATE_FORMAT_DEFAULT).
     * @param locale Passed to the DefaultFormatFactory instance.
     * @param timezone Passed to the DefaultFormatFactory instance.
     *
     * @return An object that can format dates.
     */
    @Override
    public DateFormat createDateFormat(
            final String pattern,
            final Locale locale,
            final TimeZone timezone) {
        String dateFormat = DATE_FORMAT_DEFAULT;

        try {
            final ResourceBundle rb = getResourceBundle();
            dateFormat = rb.getString(DATE_FORMAT);
        } catch (Exception ioe) {
            throw new RuntimeException(ioe);
        }

        final DateFormat df = super.createDateFormat(dateFormat, locale, timezone);

        // Uppercase month names, currently English-only.
        if (df instanceof SimpleDateFormat) {
            DateFormatSymbols symbols = new DateFormatSymbols(locale);
            symbols.setShortMonths(getShortMonthNames());
            ((SimpleDateFormat) df).setDateFormatSymbols(symbols);
        }

        return df;
    }

    private String[] getShortMonthNames() {
        return SHORT_MONTH_NAMES;
    }

    /**
     * Returns a resource bundle identified by the given resource name.
     *
     * @return A non-null ResourceBundle instance.
     */
    private synchronized ResourceBundle getResourceBundle() {
        if (this.resourceBundle == null) {
            this.resourceBundle = new ReportResourcesBundle();
        }

        return this.resourceBundle;
    }

    /**
     * Container for resource bundle properties in the situation when the
     * resources cannot be obtained on the CLASSPATH.
     */
    private static class ReportResourcesBundle extends ListResourceBundle {

        @Override
        protected Object[][] getContents() {
            return new Object[][]{
                {DATE_FORMAT, DATE_FORMAT_DEFAULT},};
        }
    }
}

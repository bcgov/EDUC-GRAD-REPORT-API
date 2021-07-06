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
 *  File:                $Id:: TupleSumScriplet.java 10675 2018-06-25 19:50:03#$
 *  Date of Last Commit: $Date:: 2018-06-25 12:50:03 -0700 (Mon, 25 Jun 2018)  $
 *  Revision Number:     $Rev:: 10675                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.jasper.impl;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.Serializable;
import java.util.*;

/**
 * Calculates grand sums for non-groupable values where the keys and values are
 * derived from separate, but correlated, columns. For example, on the PSI
 * Choice report, the columns might be the transmission mode and the tally. The
 * values for transmission mode (XML, Print, Electronic, etc.) become the keys
 * while the values for the tally become the mapped values.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TupleSumScriplet extends JRDefaultScriptlet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Used to obtain the key column name from the report parameters.
     */
    private final static String REPORT_KEY_COLUMN_NAME
            = "SCRIPTLET_KEY_COLUMN_NAME";

    /**
     * Used to obtain the key's value column name from the report parameters.
     */
    private final static String REPORT_VALUE_COLUMN_NAME
            = "SCRIPTLET_VALUE_COLUMN_NAME";

    /**
     * Maps distinct values from a given column to correlated values (obtained
     * from a different column). The keys are distinct.
     */
    private final Map<String, Long> sums = new HashMap<>();

    // Sometimes the last record is added twice...
    private int lastRecordNumber = 0;

    /**
     * Default (empty) constructor.
     */
    public TupleSumScriplet() {
    }

    /**
     * Called after each column in every row is filled in the detail band.
     *
     * @throws JRScriptletException Could not retrieve the field value.
     */
    @Override
    public synchronized void afterDetailEval() throws JRScriptletException {
        final int recordNumber = (Integer) getVariableValue("REPORT_COUNT");

        // Work around a bug in JasperReports where this method is called twice
        // for the last record.
        if (this.lastRecordNumber != recordNumber && hasTupleTotals()) {
            this.lastRecordNumber = recordNumber;

            final String keyColumnName = getKeyColumnName();
            final String key = (String) getFieldValue(keyColumnName);

            final String valueColumnName = getValueColumnName();
            final long value = Long.parseLong(getFieldValue(valueColumnName).toString());

            final Map<String, Long> totals = getSums();
            final long sum = totals.containsKey(key) ? totals.get(key) : 0;
            totals.put(key, sum + value);
        }
    }

    /**
     * Returns the map of sums for each name.
     *
     * @return A sorted collection of key names and values.
     */
    public JRDataSource getDataSource() {
        return new JRBeanCollectionDataSource(sort(getSums()));
    }

    /**
     * Returns the map of key/value pairs where the keys are the list of
     * category column names and values are the sum of value column name values.
     *
     * @return
     */
    public Map<String, Long> getSums() {
        return this.sums;
    }

    /**
     * Returns the name of the column containing categories to sum. The values
     * are reflected by looking up the value form the value column name.
     *
     * @throws JRScriptletException Could not obtain the column name for summing
     * keys.
     * @see #getValueColumnName()
     * @return A non-null string.
     */
    private String getKeyColumnName() throws JRScriptletException {
        return (String) getParameterValue(REPORT_KEY_COLUMN_NAME);
    }

    /**
     * Returns the name of the column containing values for categories to sum.
     *
     * @throws JRScriptletException Could not obtain the column name for summing
     * values.
     * @see #getKeyColumnName()
     * @return A non-null string.
     */
    protected String getValueColumnName() throws JRScriptletException {
        return (String) getParameterValue(REPORT_VALUE_COLUMN_NAME);
    }

    /**
     * Answers whether the report is a subreport or master report.
     *
     * @return true This is a subreport.
     * @throws JRScriptletException Could not determine report name.
     */
    private synchronized boolean hasTupleTotals() {
        boolean result;

        try {
            // If the subreport has no scriptlet parameter, then this will
            // cast an exception.
            getFieldValue(getKeyColumnName());
            getFieldValue(getValueColumnName());

            result = true;
        } catch (Exception e) {
            // There is no scriptlet parameter for the key column, so don't
            // try to read the field names and values.
            result = false;
        }

        return result;
    }

    /**
     * Sorts a map by its keys.
     *
     * @param <K> Data type for the map's keys, must implement Comparable.
     * @param <V> Data type for the map's values.
     * @param map Map data to sort.
     *
     * @return The given map, sorted into a list of Map.Entry values.
     */
    public static
            <K extends Comparable<? super K>, V> Collection<Map.Entry<K, V>>
            sort(Map<K, V> map) {
        final List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(
                    final Map.Entry<K, V> o1,
                    final Map.Entry<K, V> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        return list;
    }
}

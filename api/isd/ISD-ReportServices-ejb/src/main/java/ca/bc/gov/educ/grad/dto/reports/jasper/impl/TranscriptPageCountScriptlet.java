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
 *  File:                $Id:: TranscriptPageCountScriptlet.java 5784 2017-01-#$
 *  Date of Last Commit: $Date:: 2017-01-11 10:28:01 -0800 (Wed, 11 Jan 2017)  $
 *  Revision Number:     $Rev:: 5784                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.dto.reports.data.impl.Student;
import ca.bc.gov.educ.grad.dto.reports.data.impl.TranscriptResult;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sets the P_PAGE_COUNT parameter before the report runs based on the number of
 * courses on the transcript and whether the student has graduated. The
 * functionality in this class could be added to TranscriptReportImpl, but then
 * it would not be available through Jaspersoft Reports IDE. Exposing it as a
 * scriptlet allows the code to run using the IDE and the Report Service.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptPageCountScriptlet extends JRDefaultScriptlet {

    private static final String CLASSNAME = TranscriptPageCountScriptlet.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String V_TOTAL_PAGE_COUNT = "V_TOTAL_PAGE_COUNT";

    @Override
    public void afterReportInit() throws JRScriptletException {
        // Calculate the total number of pages required to create the report
        // in advance so that the correct first page background and suppressing
        // the page number can be determined. This is only used for the
        // non-Adult transcripts.
        final int rowsPerPage = getRowsPerPage();
        final int pageCount = getPageCount(rowsPerPage);
        setVariableValue(V_TOTAL_PAGE_COUNT, pageCount);
    }

    /**
     * Calculates the number of pages the transcript requires to show all course
     * rows and blank separator rows.
     *
     * @param rowsPerPage The number of rows that can fit on a page.
     * @return The number of pages required to fit all course entries.
     */
    private int getPageCount(final int rowsPerPage)
            throws JRScriptletException {
        // If the number of rows in any successive combination of groups meets
        // a page boundary, then a blank row is suppressed (i.e., there are no
        // blank rows at the end of a page, nor any blank rows as the first
        // entry). Otherwise, the number of blank rows increases by 1 for each
        // course level group.
        int blankRows = 0;
        int courses = 0;

        final Map<String, Integer> courseGroups = getCourseGroupTallies();

        for (final String group : courseGroups.keySet()) {
            courses += courseGroups.get(group);

            // Blank rows are inserted to neither the top nor bottom of pages.
            if (courses % rowsPerPage != 0) {
                blankRows++;
            }

            LOG.log(Level.FINE, "Grade {0} cumulative tally = {1}",
                    new Object[]{group, courses});
            LOG.log(Level.FINE, "Blank rows = {0}", blankRows);
        }

        // The last blank row is never included on the report.
        if (blankRows > 0) {
            blankRows--;
        }

        final int rows = getTranscriptResults().size();
        return ((rows + blankRows) / (rowsPerPage + 1)) + 1;
    }

    /**
     * Tabulates the number of courses per level (e.g., the student might have
     * taken 7 grade 12 courses).
     *
     * @return A set of tallies associated with course levels.
     * @throws JRScriptletException Could not retrieve the transcript result
     * list.
     */
    private Map<String, Integer> getCourseGroupTallies()
            throws JRScriptletException {
        final Map<String, Integer> result = new HashMap<>();

        for (final TranscriptResult tr : getTranscriptResults()) {
            final String group = tr.getCourseLevelGroup();
            final Integer tally = result.get(group);

            // Track the number of items in each course level group.
            result.put(group, tally == null ? 1 : tally + 1);
        }

        return result;
    }

    /**
     * Returns the list of transcript results for the student.
     *
     * @return The student's transcript results.
     */
    private List<TranscriptResult> getTranscriptResults()
            throws JRScriptletException {
        return getStudent().getTranscriptResults();
    }

    /**
     * This method contains knowledge of the number of rows that can fit on a
     * transcript page. This is coupling can only be removed if the layout of
     * the transcript changes. This value changes depending on whether the
     * student has graduated. If the page layout changes, so too must this
     * method.
     *
     * These magic numbers correspond to numbers defined in the Transcript.jrxml
     * file for the V_ROWS_PER_PAGE variable. They are 3 more than those numbers
     * to account for a maximum of 3 possible blank rows (assuming blank rows
     * between grades 10, 11, 12, and other).
     *
     * @return The number of rows that can completely fit on a page, including
     * blank rows.
     */
    private int getRowsPerPage() throws JRScriptletException {
        return getStudent().hasGraduated() ? 34 : 26;
    }

    /**
     * Returns a reference to the student instance that was passed into the
     * reports (as the main field).
     *
     * @return The student passed into the report, never null.
     * @throws JRScriptletException Could not retrieve the student field.
     */
    private Student getStudent() throws JRScriptletException {
        return (Student) getFieldValue("student");
    }
}

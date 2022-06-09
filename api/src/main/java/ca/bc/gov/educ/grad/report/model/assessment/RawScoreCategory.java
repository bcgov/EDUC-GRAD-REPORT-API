/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education and Child Care, BC.
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
package ca.bc.gov.educ.grad.report.model.assessment;

/**
 * Provides a determinate for the sorting order of raw score categories.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum RawScoreCategory {

    ONLINE("Online Questions", 1),
    WRITTEN_RESPONSE("Extended Written Response Questions", 2),
    TASK("Raw Scores by Task", 3),
    PART("Raw Scores by Part", 4),
    TASK_FR("Résultat brut pour chaque tâche", 5),
    PART_FR("Résultat brut pour chaque partie", 6);
    

    /**
     * Raw score category title displayed on the GNA report.
     */
    private final String title;

    /**
     * Sort order determinate (e.g., so that online questions can precede
     * written responses).
     */
    private final int sortOrder;

    RawScoreCategory(final String title, final int sortOrder) {
        this.title = title;
        this.sortOrder = sortOrder;
    }

    /**
     * Returns the raw score category title.
     *
     * @return A non-null, non-empty String.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the raw score sort order.
     *
     * @return A value that can be used to sort the categories prior to passing
     * data into the GNA report.
     */
    public int getSortOrder() {
        return this.sortOrder;
    }
}

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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.service;

import ca.bc.gov.educ.isd.eis.trax.db.StudentInfo;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;
import ca.bc.gov.educ.isd.eis.trax.db.TranscriptCourse;

import java.util.List;

/**
 * An interface to the transcript data access object providing create
 * functionality for <code>StudentInfo</code> and <code>TranscriptCourse</code>.
 * The data is source is TRAX, it is searched using the provided PEN.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TranscriptData extends TRAXData {

    /**
     * Create a list of <code>StudentInfo</code> objects containing the student
     * information used on student transcripts.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul>
     * <li><code>lastUpdateDt</code></li>
     * </ul>
     *
     * @param PEN
     * @return List of 1 StudentInfo object, or NULL if student data was not
     * found.
     */
    List<? extends StudentInfo> findStudentByPEN(String PEN);

    /**
     * Create a list of <code>TranscriptCourse</code> objects containing course
     * information used for student transcripts.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul>
     * <li><code>relatedCourse</code></li>
     * <li><code>relatedLevel</code></li>
     * <li><code>usedForGrad</code></li>
     * </ul>
     *
     * @param PEN
     * @return List of TranscriptCourse objects
     */
    List<? extends TranscriptCourse> findCoursesByPEN(String PEN);

    /**
     * Create a list of <code>TranscriptCourse</code> objects containing course
     * information used for student transcripts.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul>
     * <li><code>relatedCourse</code></li>
     * <li><code>relatedLevel</code></li>
     * <li><code>usedForGrad</code></li>
     * </ul>
     *
     * @param PEN
     * @return List of TranscriptCourse objects
     */
    List<? extends TranscriptCourse> findInterimCoursesByPEN(String PEN);

    /**
     * Counts the number of transcript courses the student with the passed PEN
     * has.
     *
     * @param PEN
     * @return
     */
    Integer countCoursesByPEN(String PEN);
}

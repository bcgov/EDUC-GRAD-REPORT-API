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

import ca.bc.gov.educ.isd.eis.trax.db.ExamResult;
import ca.bc.gov.educ.isd.eis.trax.db.ExamStudent;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;

import java.util.List;

/**
 * An interface to the exam data access object providing create functionality
 * for <code>ExamResult</code> and <code>ExamStudent</code>. The data is source
 * is TRAX, it is searched using the provided PEN.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ExamData extends TRAXData {

    /**
     * Create an <code>ExamStudent</code> object with student information
     * associated with exam results.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul><li>None</li></ul>
     *
     * @param PEN
     * @return List of 1 ExamStudent object, or NULL if student information was
     * not found.
     */
    List<? extends ExamStudent> findStudentByPEN(String PEN);

    /**
     * Create <code>ExamResult</code> objects with course examination results
     * associated with a PEN.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul><li>None</li></ul>
     *
     * @param PEN
     * @return List of ExamResult objects or NULL if no examinable course
     * results are found.
     */
    List<? extends ExamResult> findResultsByPEN(String PEN);

}

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

import ca.bc.gov.educ.isd.eis.trax.db.Scholarship;
import ca.bc.gov.educ.isd.eis.trax.db.ScholarshipStudent;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;

import java.util.List;

/**
 * An interface to the scholarship data access object providing create
 * functionality for <code>Scholarships</code>. The data is source is TRAX, it
 * is searched using the provided PEN.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ScholarshipData extends TRAXData {

    /**
     * Create a <code>ScholarshipStudent</code> object containing student
     * information associated with scholarships.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul><li>None</li></ul>
     *
     * @param PEN
     * @return List of 1 ScholarshipStudent, or NULL if student does not have
     * any scholarship information.
     */
    List<? extends ScholarshipStudent> findStudentByPEN(String PEN);

    /**
     * Create <code>Scholarship</code> objects containing scholarship
     * information for the given PEN.
     * <br>Calculated or derived attributes not populated by the constructor
     * expression:
     * <ul><li></li></ul>
     *
     * @param PEN
     * @return List of Scholarships awarded to the student
     */
    List<? extends Scholarship> findScholarshipsByPEN(String PEN);
}

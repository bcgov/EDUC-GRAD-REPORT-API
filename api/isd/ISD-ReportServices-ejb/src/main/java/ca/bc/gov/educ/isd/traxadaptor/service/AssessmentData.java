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
package ca.bc.gov.educ.isd.traxadaptor.service;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode;
import ca.bc.gov.educ.isd.eis.trax.db.LitAssessmentResult;
import ca.bc.gov.educ.isd.eis.trax.db.NumAssessmentResult;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;

import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface AssessmentData extends TRAXData {

    /**
     * Create <code>AssessmentResult</code> objects with assessment examination
     * results associated with a PEN.
     *
     * @param pen Student PEN
     * @param sessionDate When the student completed the assessment tasks
     * @param code Numeracy or literacy course code
     * @return A non-null, non-empty list.
     * @throws EISException Couldn't find any assessments for the given pen,
     * sessionDate, and course code.
     */
    List<? extends NumAssessmentResult> findAssessments(
            String pen,
            String sessionDate,
            AssessmentCourseCode code) throws EISException;
    
    /**
     * Create <code>AssessmentResult</code> objects with assessment examination
     * results associated with a PEN.
     *
     * @param pen Student PEN
     * @param sessionDate When the student completed the assessment tasks
     * @param code Numeracy or literacy course code
     * @return A non-null, non-empty list.
     * @throws EISException Couldn't find any assessments for the given pen,
     * sessionDate, and course code.
     */
    List<? extends LitAssessmentResult> findLiteracyAssessments(
            String pen,
            String sessionDate,
            AssessmentCourseCode code) throws EISException;

}

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
package ca.bc.gov.educ.isd.eis.trax.db;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode;
import ca.bc.gov.educ.isd.eis.common.DomainServiceException;

import java.util.Date;
import java.util.List;

/**
 * methods which external services can use to call the TRAX EJB service
 *
 * The different service calls may respond with a different type of object but
 * they all need to be defined as TRAXData.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TRAXAdapter extends TRAXData {

    /**
     * Provides student information as defined by xml transcript and text
     * transcript.
     *
     * @param pen Personal Education Number of the student
     * @return StudentInfo object
     * @throws EISException Could not find any matching students.
     */
    StudentInfo readStudent_Transcript(String pen) throws EISException;

    /**
     * Provides a list of courses which the student has taken.
     *
     * @param pen Personal Education Number of the student
     * @return List of TranscriptCourse objects
     * @throws EISException Could not find any matching students.
     */
    List<TranscriptCourse> readCourses_Transcript(String pen) throws EISException;

    /**
     * Provides a list of courses, completed and in progress which the student
     * has taken.
     *
     * @param pen Personal Education Number of the student
     * @return List of TranscriptCourse objects
     * @throws EISException Could not find any matching students.
     */
    List<TranscriptCourse> readCourses_InterimTranscript(String pen) throws EISException;

    /**
     * Provides a count of courses which the student has taken.
     *
     * @param pen Personal Education Number of the student
     * @return List of TranscriptCourse objects
     * @throws EISException Could not find any matching students.
     */
    Integer countCourses_Transcript(String pen) throws EISException;

    /**
     * Provides student information as defined by student demographics and the
     * grad certificate services.
     *
     * @param pen Personal Education Number of the student
     * @return StudentDemographic object
     * @throws EISException Could not find any matching students.
     */
    StudentDemographic readStudent_Demographic(String pen) throws EISException;

    /**
     * Searches for student information using fields filled from the given
     * student demographic data.
     *
     * @param student Partial student information.
     * @return A non-null instance with all data filled.
     * @throws EISException Could not find any matching students.
     */
    StudentDemographic findStudentDemographic(StudentDemographic student) throws EISException;

    /**
     * Provides student information as defined by exam service.
     *
     * @param pen Personal Education Number of the student
     * @return ExamStudent object
     * @throws EISException Could not find any matching students.
     */
    ExamStudent readStudent_Exam(String pen) throws EISException;

    /**
     * Provides student information as defined by TRAX service.
     *
     * @param pen Student's Personal Education Number
     * @param sessionDate The assessment business key
     * @param code The assessment business key (e.g., LTE, NME, NMF).
     * @return AssessmentResult Student assessment data
     * @throws EISException Could not find any matching students.
     */
    AssessmentResult readStudent_Assessment(
            final String pen,
            final String sessionDate,
            final AssessmentCourseCode code) throws EISException;

    /**
     * Provides a list of exam results for courses a student has taken.
     *
     * @param pen Personal Education Number of the student
     * @return List of ExamResult objects
     * @throws EISException Could not find any matching students.
     */
    List<ExamResult> readCourses_Exam(String pen) throws EISException;

    /**
     * Provides a list of scholarships a student has received.
     *
     * @param pen Personal Education Number of the student
     * @return List of Scholarship objects
     * @throws EISException Could not find any matching students.
     */
    List<Scholarship> readScholarships(String pen) throws EISException;

    /**
     * Provides student information associated with scholarships.
     *
     * @param pen Personal Education Number of the student
     * @return ScholarshipStudent object
     * @throws EISException Could not find any matching students.
     */
    ScholarshipStudent readStudent_Scholarship(String pen) throws EISException;

    /**
     * Provides PSI Choices information associated with students (TRAX
     * Database).
     *
     * @param pen Personal Education Number of the student
     * @return List of PIS choices associated with the student
     * @throws EISException Could not find any matching students.
     */
    List<PSIChoice> readStudentChoices(final String pen) throws EISException;

    /**
     * Provides PSI Choices information associated with students (TRAX
     * Database).
     *
     * @param pen Personal Education Number of the student
     * @param psiCode TRAX unique identifier for PSI
     * @param psiYear Year in which PSI Choices requested to be sent
     * @return List of PIS choices associated with the student
     * @throws EISException Could not find any matching students.
     */
    List<PSIChoice> readStudentChoices(final String pen, final String psiCode, final String psiYear) throws EISException;

    /**
     * Provides TX PSI information (TRAX Database).
     *
     * @param pen Personal Education Number of the student
     * @return List of TX PSI associated with the student.
     * @throws EISException
     */
    List<TSWTxPSI> readTxPSI(final String pen) throws EISException;

    List<TSWTxPSI> readTxPSI(final String pen, final String psiCode) throws EISException;

    List<TSWTxPSI> readTxPSI(final String pen, final String psiCode, final Date processDate) throws EISException;

    /**
     * Provides TX PSI information (TRAX Database).
     *
     * @param pen Personal Education Number of the student
     * @param txId TRAX ID for PSI
     * @param psiCode TRAX unique identifier for PSI
     * @return List of TX PSI associated with the student.
     * @throws EISException
     */
    List<TSWTxPSI> readTxPSI(final String pen, final String txId, final String psiCode) throws EISException;

    List<TSWTxPSI> readTxPSI(final String pen, final String txId, final String psiCode, final Date processDate) throws EISException;

    /**
     * Updates TX PSI information in the TRAX Database.
     *
     * @param txId TRAX ID for PSI
     * @param studNo Personal Education Number of the student
     * @param psiCode TRAX unique identifier for PSI
     * @param status indicates new records for processing
     * @throws EISException
     */
    void updateTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException;

    /**
     * Inserts TX PSI information in the TRAX Database.
     *
     * @param txId TRAX ID for PSI
     * @param studNo Personal Education Number of the student
     * @param psiCode TRAX unique identifier for PSI
     * @param status indicates new records for processing
     * @throws EISException
     */
    void insertTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException;

    /**
     * Deletes TX PSI information in the TRAX Database.
     *
     * @param txId TRAX ID for PSI
     * @param studNo Personal Education Number of the student
     * @param psiCode TRAX unique identifier for PSI
     * @throws EISException
     */
    void deleteTxPSI(final String txId, final String studNo, final String psiCode) throws EISException;

    void mergeTxPSI(final String studNo, final String psiCode, final Character status) throws EISException;

    void mergeTxPSI(final String txId, final String studNo, final String psiCode, final Character status) throws EISException;

    List<? extends TSWStud> searchTswStudPSI(final String studNo, final String psiCode, final String psiYear) throws EISException;

    void mergeTswStudPSI(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException;

    void insertTswStudPSI(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException;

    void updateTswStudPSIStatus(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException;

    void deleteTswStudPSI(final String studNo, final String psiCode, final String psiYear, final Character status) throws EISException;

    TSWRegistry createTswRegistry(String psiCode) throws EISException;

    /**
     * Returns a list with all the regions that exist in TRAX
     *
     * @return region Lists
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    List<TabProvince> readAllRegions() throws EISException;

    /**
     * Search for students by their partial PEN and last name, only return the
     * students meet all given criteria.
     *
     * @param tokens List of pen or last name.
     * @return list of <code>StudentProfileMasterLite</code> who match all given
     * PENs and last names.
     * @throws ca.bc.gov.educ.isd.eis.common.DomainServiceException
     */
    List<? extends StudentProfileMasterLite> searchStudentPartialMatchAll(List<String> tokens)
            throws DomainServiceException;
}

/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: StudentAchievementService.java 8921 2017-12-07 1#$
 *  Date of Last Commit: $Date:: 2017-12-07 11:16:33 -0800 (Thu, 07 Dec 2017)  $
 *  Revision Number:     $Rev:: 8921                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.achievement;

import ca.bc.gov.educ.grad.report.model.common.BusinessService;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;
import ca.bc.gov.educ.grad.report.model.student.PersonalEducationNumber;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Provides an interface for generating official an unofficial student
 * transcripts.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentAchievementService extends BusinessService {

    /**
     * FIXME: Rename this to buildReport and make method shared between this
     * interface and StudentExamService via a common super-interface.
     *
     * Generate the student transcripts report. Reports can be generated for
     * different MIME types such as HTML or PDF. If the current user does not
     * have an entry in the Student XRef or if the current user is not a student
     * (no PEN) then a DomainServiceExcepton is thrown. If the current user is a
     * student but has no exam results in TRAX then a DomainServiceException is
     * thrown. If there is an error generating the report then a
     * DomainServiceException is thrown.
     *
     * @param format The final format for the filled report.
     *
     * @return Report data for consumption by the GUI.
     *
     * @throws DomainServiceException
     * @throws IOException
     * @throws DataException
     */
    StudentAchievementReport buildAchievementReport(ReportFormat format)
            throws DomainServiceException, IOException, DataException;

    /**
     * Generate the student transcripts report. Reports can be generated for
     * different MIME types such as HTML or PDF. If the current user does not
     * have an entry in the Student XRef or if the current user is not a student
     * (no PEN) then a DomainServiceExcepton is thrown. If the current user is a
     * student but has no exam results in TRAX then a DomainServiceException is
     * thrown. If there is an error generating the report then a
     * DomainServiceException is thrown.
     *
     * @param format The final format for the filled report.
     * @param interim true Indicates extra columns to display on the report
     * showing the student's mid-course marks.
     *
     * @return Report data for consumption by the GUI.
     *
     * @throws DomainServiceException
     * @throws IOException
     * @throws DataException
     */
    StudentAchievementReport buildAchievementReport(ReportFormat format, boolean interim)
            throws DomainServiceException, IOException, DataException;

    /**
     * Generate the student transcripts report. Reports can be generated for
     * different MIME types such as HTML or PDF. This is to be called by
     * services that require reports for different students. If the current user
     * does not have an entry in the Student XRef or if the current user is not
     * a student (no PEN) then a DomainServiceExcepton is thrown. If the current
     * user is a student but has no exam results in TRAX then a
     * DomainServiceException is thrown. If there is an error generating the
     * report then a DomainServiceException is thrown.
     *
     * @param format The final format for the filled report.
     * @param pen The Personal Education Number of the student to build the
     * report.
     * @param parameters The transmission header data to be included if
     * required.
     * @param interim The courses are being read from the in progress table.
     *
     * @return Report data for consumption by the PESCTransmissionService.
     *
     * @throws DomainServiceException
     * @throws IOException
     * @throws DataException
     */
    StudentAchievementReport buildAchievementReport(ReportFormat format, PersonalEducationNumber pen, Parameters parameters, boolean interim)
            throws DomainServiceException, IOException, DataException;

    /**
     * Performs an asynchronous call to
     * <code>buildAchievementReport(ReportFormat)</code>.
     *
     * This is intended to be used when loading the transcript report should be
     * started and can continue asynchronously.
     *
     * @param format The final format for the filled report.
     * @return Report data for consumption by the GUI.
     * @throws DomainServiceException
     * @throws IOException
     * @throws DataException
     */
    Future<StudentAchievementReport> buildAchievementReportAsync(ReportFormat format)
            throws DomainServiceException, IOException, DataException;

    /**
     * Builds the official transcript report in PDF format.
     *
     * @return
     * @throws DomainServiceException
     * @throws IOException
     * @throws DataException
     */
    StudentAchievementReport buildOfficialAchievementReport()
            throws DomainServiceException, IOException, DataException;

    /**
     * Retrieves a transcript that contains course results and the report date.
     *
     * @return A transcript for the currently logged in user.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Achievement getAchievement() throws DomainServiceException;

    /**
     * Retrieves a transcript that contains course results and the report date.
     *
     * @param pen The PEN of the student of which transcript to fetch.
     * @param interim Should the transcript include interim marks.
     * @return A transcript for the queried user.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Achievement getAchievement(String pen, boolean interim) throws DomainServiceException;

    /**
     * Retrieves a new instance of Linked Parameters to be used when preserving
     * insertion order of data is required.
     *
     * @return new instance of Linked Parameters.
     */
    Parameters createParameters();

    /**
     * Sorts the transcript results according to business rules determined by
     * graduation program code. The results must be sorted prior to passing them
     * into the report because of the logic the report uses to insert blank rows
     * that delineate different courses.
     *
     * @deprecated Use report services to sort.
     * @param transcriptResults The list of results to sort.
     * @param programCode The program code that influences sorting behaviour.
     * @return A sorted list for suitable for the given program code.
     */
    List<AchievementResult> sort(
            List<AchievementResult> transcriptResults,
            GraduationProgramCode programCode);
}

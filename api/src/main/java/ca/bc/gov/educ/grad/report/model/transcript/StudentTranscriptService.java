/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: StudentTranscriptService.java 8921 2017-12-07 1#$
 *  Date of Last Commit: $Date:: 2017-12-07 11:16:33 -0800 (Thu, 07 Dec 2017)  $
 *  Revision Number:     $Rev:: 8921                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.common.BusinessService;
import ca.bc.gov.educ.grad.report.model.common.DataException;
import ca.bc.gov.educ.grad.report.model.common.DomainServiceException;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Provides an interface for generating official an unofficial student
 * transcripts.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentTranscriptService extends BusinessService {

    /**
     * Performs an asynchronous call to
     * <code>buildTranscriptReport(ReportFormat)</code>.
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
    Future<StudentTranscriptReport> buildTranscriptReportAsync(ReportFormat format)
            throws DomainServiceException, IOException, DataException;

    /**
     * Builds the official transcript report in PDF format.
     *
     * @return
     * @throws DomainServiceException
     * @throws IOException
     * @throws DataException
     */
    StudentTranscriptReport buildOfficialTranscriptReport()
            throws DomainServiceException, IOException, DataException;

    /**
     * Retrieves a transcript that contains course results and the report date.
     *
     * @return A transcript for the currently logged in user.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Transcript getTranscript() throws DomainServiceException;

    /**
     * Retrieves a transcript that contains course results and the report date.
     *
     * @param pen The PEN of the student of which transcript to fetch.
     * @return A transcript for the queried user.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Transcript getTranscript(String pen) throws DomainServiceException;

    /**
     * Retrieves basic transcript information for the given PEN. This is for
     * determining whether the student with the given PEN has any transcript
     * results and what the issue date is. It does NOT contain the list of
     * transcriptResults and will throw an exception if this method is called.
     *
     *
     * @param pen Student number for which information is desired.
     * @return A lightweight transcript object for the passed PEN.
     * @throws DomainServiceException Could not read data from TRAX.
     */
    Transcript getTranscriptInformation(String pen) throws DomainServiceException;

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
    List<TranscriptResult> sort(
            List<TranscriptResult> transcriptResults,
            GraduationProgramCode programCode);
}

/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Transcript.java 8921 2017-12-07 19:16:33Z DAJAR#$
 *  Date of Last Commit: $Date:: 2017-12-07 11:16:33 -0800 (Thu, 07 Dec 2017)  $
 *  Revision Number:     $Rev:: 8921                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.transcript;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;

import java.util.Date;
import java.util.List;

/**
 * A transcript is comprised of a number of transcript results.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Transcript extends DomainEntity {

    /**
     * Returns a list of results that contains courses and associated marks a
     * student achieved.
     *
     * @return The list of marks for a student transcript.
     */
    List<TranscriptResult> getResults();

    /**
     * Sorts the transcript results.
     *
     * @param graduationProgramCode The student's graduation program code used
     * to determine how to sort the results.
     * @return The list of results, sorted according to the program code
     * requirements.
     */
    List<TranscriptResult> getResults(GraduationProgramCode graduationProgramCode);

    /**
     * Returns the date that the transcript was issued. On the transcript
     * report, this is the report date.
     *
     * @return Date that the transcript was issued.
     */
    Date getIssueDate();

    /**
     * Convenience method that answers whether the transcript has any transcript
     * results.
     *
     * @return true iff getResults().isEmpty() == true.
     */
    boolean isEmpty();

    /**
     * Returns the type of the transcript
     *
     * @return transcript type
     */
    TranscriptTypeCode getTranscriptTypeCode();

}

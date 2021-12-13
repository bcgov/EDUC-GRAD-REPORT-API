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
 *  File:                $Id:: TranscriptType.java 4517 2016-10-20 20:09:34Z D#$
 *  Date of Last Commit: $Date:: 2016-10-20 13:09:34 -0700 (Thu, 20 Oct 2016)  $
 *  Revision Number:     $Rev:: 4517                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.transcript;

/**
 * Represents the type of transcript. This one transcript type is used for all
 * different transcripts (2004, SCCP, and Adult).
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum TranscriptType {

    ALL("All");

    private final String name;

    private TranscriptType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

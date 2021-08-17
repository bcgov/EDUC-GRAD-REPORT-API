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
 *  File:                $Id:: TranscriptOrderTypeImpl.java 5479 2016-12-01 23#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.bundle.decorator;

import ca.bc.gov.educ.isd.reports.bundle.service.TranscriptOrderType;

import static ca.bc.gov.educ.isd.reports.PaperType.TRANSCRIPT;

/**
 * Responsible for creating order types that can print transcripts on the
 * correct paper type.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptOrderTypeImpl extends OrderTypeImpl
        implements TranscriptOrderType {

    private static final long serialVersionUID = 3L;

    /**
     * Constructs with paper type YED4.
     */
    public TranscriptOrderTypeImpl() {
        setPaperType(TRANSCRIPT);
    }

    /**
     * Returns the human-readable name for transcripts.
     *
     * @return "Transcripts"
     */
    @Override
    public String getName() {
        return "Transcripts";
    }
}

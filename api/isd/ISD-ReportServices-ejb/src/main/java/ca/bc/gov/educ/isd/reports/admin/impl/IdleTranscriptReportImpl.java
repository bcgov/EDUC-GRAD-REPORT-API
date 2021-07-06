/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.IdleTranscript;
import ca.bc.gov.educ.isd.reports.jasper.impl.AdminReportImpl;

/**
 * Responsible for exporting IdleTranscript reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class IdleTranscriptReportImpl
        extends AdminReportImpl<IdleTranscript> {

    private static final long serialVersionUID = 1L;

    /**
     * Default (empty) constructor.
     */
    public IdleTranscriptReportImpl() {
        super("IdleTranscriptReport");
    }
}

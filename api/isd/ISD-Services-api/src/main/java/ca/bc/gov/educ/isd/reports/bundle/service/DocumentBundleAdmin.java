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
 *  File:                DocumentBundleAdmin.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.bundle.service;

import ca.bc.gov.educ.isd.reports.ReportDocument;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DocumentBundleAdmin extends ReportDocument {

    /**
     * Changes the print job recipient name to include in the XPIF.
     *
     * @param jobRecipientName "BCMAIL PLANNERS TEST" (default) or "BCMAIL
     * PLANNERS PROD"
     */
    public void setJobRecipientName(final String jobRecipientName);

    /**
     * Sets the filename used for this bundle.
     *
     * @param filename Filename that can be used for saving the document and
     * filling the XPIF information.
     */
    public void setFilename(String filename);
}

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
 *  File:                $Id:: ReportDocument.java 8342 2017-10-05 22:19:16Z D#$
 *  Date of Last Commit: $Date:: 2017-10-05 15:19:16 -0700 (Thu, 05 Oct 2017)  $
 *  Revision Number:     $Rev:: 8342                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.reports;

import java.io.Serializable;

/**
 * Represents the final output for a report generated using the report service.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ReportDocument extends Serializable {

    /**
     * Returns the document as an array of bytes. The caller is then responsible
     * for streaming the bytes.
     *
     * @return An array of bytes that represents the final report document,
     * never null.
     */
    public byte[] asBytes();
}

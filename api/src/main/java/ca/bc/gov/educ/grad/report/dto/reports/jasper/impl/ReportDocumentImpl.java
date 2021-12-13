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
package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Represents a filled report. This class does not contain knowledge of what
 * report type was created (presumably the client class to the report service
 * has that information). Rather, this class contains a series of bytes that
 * could be in any report format (PDF, HTML, CSV, etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ReportDocumentImpl implements ReportDocument {

    private static final long serialVersionUID = 1L;

    /**
     * Data that constitutes the filled report.
     */
    private final byte[] reportContent;

    /**
     * Constructs a new instance with a set of bytes that contains the report's
     * data in its final output format.
     *
     * @param content The report content.
     */
    public ReportDocumentImpl(final byte[] content) {
        this.reportContent = content;
    }

    /**
     * Returns the final, filled report as a format- and engine-agnostic series
     * of bytes.
     *
     * @return Bytes that represent the final, filled report, never null,
     * possibly empty.
     */
    @Override
    public byte[] asBytes() {
        return this.reportContent == null ? new byte[0] : this.reportContent;
    }

    /**
     * Helper method to save the document to a file. This will overwrite the
     * given file.
     *
     * @param file The path and filename to contain the report document content.
     * @throws IOException Could not write the report document.
     */
    public void save(final File file) throws IOException {
        // Write the bytes and close the stream on either success or error.
        try (final FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(reportContent);
        }
    }
}

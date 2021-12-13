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
package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.report.model.reports.Report;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSmartCopy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;

/**
 * Responsible for interleaving report summary pages for the student transcript
 * report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptJasperReportImpl extends JasperReportImpl {

    /**
     * Passes the report to the superclass.
     *
     * @param report
     */
    public TranscriptJasperReportImpl(final Report report) {
        super(report);
    }

    /**
     * Ensures the report date is set before exporting the report.
     *
     * @return super.export()
     * @throws IOException The report could not be filled.
     */
    @Override
    public ReportDocument export() throws IOException {
        final Report report = getReport();

        // Create the transcript.
        ReportDocument transcript = super.export();

        // Only the PDF version has summary pages.
        if (report.isFormat(PDF)) {
            // Change report name for summary page (for grad program code).
            report.preprocessReportName();

            // Create a new parameter map for summary report, to avoid modifying
            // an unmodifiable map.
            report.setParameters(null);

            // Get the summary page for the student's graduation program.
            final ReportDocument summary = super.export();

            // Inject the summary page after every transcript page for PDF only.
            transcript = interleave(transcript, summary);
        }

        return transcript;
    }

    /**
     * Given a source document, this will insert the alternate document after
     * every page.
     *
     * @param source The document to interleave with the alternate.
     * @param alternate The document to insert into the source document every n
     * pages, where n is the number of pages in the source document.
     * @return The source document interleaved with the alternate.
     */
    private ReportDocument interleave(
            final ReportDocument source, final ReportDocument alternate) {

        // iText writes the interleaved PDF to this stream.
        try (final ByteArrayOutputStream baos = createByteArrayOutputStream()) {
            final Document document = new Document();
            final PdfCopy copy = new PdfSmartCopy(document, baos);
            document.open();

            final byte[] sourceBytes = source.asBytes();
            final byte[] alternateBytes = alternate.asBytes();

            final PdfReader srcReader = new PdfReader(sourceBytes);
            final PdfReader altReader = new PdfReader(alternateBytes);

            final int pages = srcReader.getNumberOfPages();

            // Copy all the pages from the list into a new document.
            for (int i = 1; i <= pages; i++) {
                copy.addPage(copy.getImportedPage(srcReader, i));

                // Interleave the alternate page. There is only one page:
                // the transcript back. A generalized solution would iterate
                // and append all the alternate pages.
                copy.addPage(copy.getImportedPage(altReader, 1));
            }

            altReader.close();
            srcReader.close();
            document.close();

            // Return the source document with the alternate document
            // interleaved.
            return new ReportDocumentImpl(baos.toByteArray());
        } catch (final DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

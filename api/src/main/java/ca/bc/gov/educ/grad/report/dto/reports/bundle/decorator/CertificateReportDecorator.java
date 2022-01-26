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
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.ReportDocumentImpl;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.itextpdf.text.pdf.PdfName.ROTATE;

/**
 * Responsible for bundling certificate reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class CertificateReportDecorator extends DocumentBundleDecorator {

    private static final long serialVersionUID = 1L;

    /**
     * Constructed using the superclass.
     *
     * @param bundle
     */
    public CertificateReportDecorator(final DocumentBundle bundle) {
        super(bundle);
    }

    /**
     * Only write page numbers on the first page of a certificate bundle (i.e.,
     * the packing slip).
     *
     * @param pageNumber The page number to check against.
     * @return true iff pageNumber == 1
     */
    @Override
    protected boolean isEnumerable(final int pageNumber) {
        return pageNumber == 1;
    }

    /**
     * Rotates the given document by 90 degrees.
     *
     * @param report The document to be rotated.
     * @return A rotated version of the given document.
     * @throws IOException Could not process the report.
     */
    @Override
    protected ReportDocument process(final ReportDocument report)
            throws IOException {
        return rotate(report, 90);
    }

    /**
     * Rotates a document by a given number of degrees. A certificate only has
     * one page, but this is a general-purpose method that will process all the
     * pages in a PDF.
     *
     * @see
     * http://developers.itextpdf.com/examples/stamping-content-existing-pdfs-itext5/scaling-and-rotating-pages#1048-rotate90degrees.java
     *
     * @param document The document to process.
     * @param degrees The number of degrees to process the document by (should
     * probably be zero or a multiple of 90).
     * @return The rotated document.
     * @throws IOException Could not process the document.
     */
    private ReportDocument rotate(
            final ReportDocument document, final int degrees)
            throws IOException {
        final byte[] bytes = document.asBytes();
        final PdfReader reader = new PdfReader(bytes);
        final int pages = reader.getNumberOfPages();

        for (int i = 1; i <= pages; i++) {
            final PdfDictionary page = reader.getPageN(i);
            final PdfNumber rotate = page.getAsNumber(ROTATE);

            // If the PDF page does not have a /Rotate key in the dictionary,
            // then add one. If the PDF has a /Rotate key, then update the
            // value to use the new rotation.
            final int rotation = rotate == null
                    ? degrees
                    : (rotate.intValue() + degrees) % 360;

            page.put(ROTATE, new PdfNumber(rotation));
        }

        // Export the rotated document and save its contents in the result
        // document.
        try (final ByteArrayOutputStream baos = createByteArrayOutputStream()) {
            final PdfStamper stamper = new PdfStamper(reader, baos);

            // Write the rotated document to the stream.
            stamper.close();
            reader.close();

            // Extract the bytes for the rotated document.
            return new ReportDocumentImpl(baos.toByteArray());
        } catch (final DocumentException de) {
            throw new IOException(de);
        }
    }

    /**
     * Returns print settings template name.
     *
     * @return A non-null string.
     */
    @Override
    protected String getXpifResourceName() {
        return "xpif_certificate.xml";
    }

    /**
     * Returns an indicator prefix to the filename.
     *
     * @return "CERT"
     */
    @Override
    public String getFilenamePrefix() {
        return "CERT";
    }
}

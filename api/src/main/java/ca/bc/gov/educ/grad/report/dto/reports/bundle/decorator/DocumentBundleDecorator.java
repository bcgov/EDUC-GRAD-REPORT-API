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
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.itextpdf.text.pdf.PdfName.ROTATE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.replaceEach;

/**
 * The class implements common logic for adding reports into a PDF bundle.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
public abstract class DocumentBundleDecorator implements Serializable {

    private static final String CLASSNAME = DocumentBundleDecorator.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final long serialVersionUID = 2L;

    private static final int BUFFER_SIZE = 250000;

    private final DocumentBundle bundle;

    public DocumentBundleDecorator(final DocumentBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Concatenate the document bundle and the given report PDF content together
     * and return the result. This will process the the given document if it is
     * not the first document to be appended and it is for certificates.
     *
     * @return A PDF with the bundle and report content appended together.
     * @param report The document to append to the bundle.
     * @throws IOException Could not append the given document to the bundle.
     */
    public byte[] append(final ReportDocument report) throws IOException {
        return appendReportDocument(Arrays.asList(report));
    }

    public byte[] append(final BusinessReport report) throws IOException {
        return appendBusinessReport(Arrays.asList(report));
    }

    /**
     * Concatenate the document bundle and the given report PDF content together
     * and return the result. This will process the the given document if it is
     * not the first document to be appended and it is for certificates.
     *
     * @return A PDF with the bundle and report content appended together.
     *
     * @param reports The documents to append to the bundle.
     * @throws IOException Could not append the given document to the bundle.
     */
    public byte[] appendReportDocument(final List<ReportDocument> reports) throws IOException {
        final String methodName = "append(List<ReportDocument>)";
        log.trace("Entering {}", methodName);

        final List<byte[]> pdfs = new ArrayList<>();
        final byte[] bundleContent = getDocumentBundleBytes();
        boolean firstPage = bundleContent.length == 0;

        // If the bundle length is zero, then this is the first time a
        // packing slip and/or transcripts/certificates are being appended
        // to the document. In this situation, do not rotate the first
        // document in the reports list because it is a packing slip.
        if (!firstPage) {
            pdfs.add(bundleContent);
        }

        for (final ReportDocument report : reports) {
            // All pages are processed by bundle-specific decorators, except
            // for the packing slip page.
            ReportDocument processedReport = report;

            if (!firstPage) {
                processedReport = process(report);
            }

            // Load the document to append into the list of documents.
            pdfs.add(processedReport.asBytes());
            firstPage = false;
        }

        final byte[] result = writeBundle(pdfs);

        log.trace("Exiting {}", methodName);
        return result;
    }

    public byte[] appendBusinessReport(final List<BusinessReport> reports) throws IOException {
        final String methodName = "appendBytes(BusinessReport report)";
        log.trace("Entering {}", methodName);

        final List<byte[]> pdfs = new ArrayList<>();
        final byte[] bundleContent = getDocumentBundleBytes();
        boolean packingSlipPage = bundleContent.length == 0;

        // If the bundle length is zero, then this is the first time a
        // packing slip and/or transcripts/certificates are being appended
        // to the document. In this situation, do not rotate the first
        // document in the reports list because it is a packing slip.
        if (!packingSlipPage) {
            pdfs.add(bundleContent);
        }

        for (final BusinessReport report : reports) {
            // All pages are processed by bundle-specific decorators, except
            // for the packing slip page.
            BusinessReport processedReport = report;

            if (!packingSlipPage) {
                processedReport = process(report);
            }

            // Load the document to append into the list of documents.
            pdfs.add(processedReport.getReportData());
            packingSlipPage = false;
        }

        final byte[] result = writeBundle(pdfs);

        log.trace("Exiting {}", methodName);
        return result;
    }

    /**
     * Rotates the given document by 90 degrees.
     *
     * @param report The document to be rotated.
     * @return A rotated version of the given document.
     * @throws IOException Could not process the report.
     */
    protected ReportDocument process(final ReportDocument report)
            throws IOException {
        return rotate(report, getRotateDegree());
    }

    private byte[] writeBundle(List<byte[]> pdfs) throws IOException {
        final byte[] result;

        // Create a place to write the new bundle.
        try (final ByteArrayOutputStream out = createByteArrayOutputStream()) {
            final Document document = new Document();
            final PdfCopy copy = new PdfSmartCopy(document, out);
            document.open();

            byte[] previousPdf = pdfs.get(0);
            PdfReader reader = new PdfReader(previousPdf);

            // Concatenate the PDF documents.
            for (final byte[] pdf : pdfs) {
                // Interpret a PDF only when it is different to the previous one
                if (previousPdf != pdf) {
                    previousPdf = pdf;
                    reader = new PdfReader(pdf);
                }

                final int pages = reader.getNumberOfPages();

                // Copy all the pages from the list into a new document.
                for (int i = 1; i <= pages; i++) {
                    copy.addPage(copy.getImportedPage(reader, i));
                }

                reader.close();
            }

            document.close();
            result = out.toByteArray();
        } catch (final DocumentException e) {
            throw new IOException(e);
        }
        return result;
    }

    /**
     * Rotates a document by a given number of degrees. A certificate only has
     * one page, but this is a general-purpose method that will process all the
     * pages in a PDF.
     *
     * @see
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
     * Overlays page number on the report.
     *
     * @return A copy of the bundle with page numbers overlaid.
     * @throws IOException Could not overlay page numbers.
     */
    public byte[] enumeratePages() throws IOException {
        final String methodName = "enumeratePages()";
        log.trace("Entering {}", methodName);

        final byte[] pdf = getDocumentBundleBytes();
        final byte[] result;

        // Create a place to write the new bundle.
        try (final ByteArrayOutputStream out = createByteArrayOutputStream()) {
            final PdfReader reader = new PdfReader(pdf);
            final PdfStamper stamper = new PdfStamper(reader, out);
            final int pages = reader.getNumberOfPages();

            for (int i = 1; i <= pages; i++) {
                final PdfContentByte pcb = stamper.getOverContent(i);

                if (isEnumerable(i)) {
                    final NumberLabel countLabel = createPageCountLabel(i);
                    final NumberLabel imageLabel = createImageCountLabel(i);

                    if(countLabel != null) {
                        countLabel.write(pcb);
                    }
                    if(imageLabel != null) {
                        imageLabel.write(pcb);
                    }
                }
            }

            stamper.close();
            reader.close();
            result = out.toByteArray();
        } catch (final DocumentException e) {
            throw new IOException(e);
        }

        log.trace("Exiting {}", methodName);
        return result;
    }

    /**
     * Answers whether the image and page counts should be overlaid onto the
     * page.
     *
     * @param pageNumber The current page number.
     * @return true Write the page number labels to the page.
     */
    protected abstract boolean isEnumerable(final int pageNumber);

    /**
     * Returns the bundle with XPIF information added. After this method is
     * called, the resulting PDF will be deemed corrupt by most PDF reading
     * software, even though it is suitable for transmission to BC Mail.
     *
     * @return The PDF bytes with XPIF information prepended.
     * @throws IOException Could not prepend XPIF information.
     */
    public byte[] xpif() throws IOException {
        final String methodName = "xpif()";
        log.trace("Entering {}", methodName);

        final byte[] result;

        try (
                final StringWriter writer = new StringWriter();
                final InputStream xpifStream = openXpifStream();
                final ByteArrayOutputStream out = createByteArrayOutputStream()) {
            IOUtils.copy(xpifStream, writer, UTF_8);
            final String xpif = fillXpif(writer.toString());
            final byte[] bytes = getDocumentBundleBytes();

            // Prepend the XPIF to the PDF.
            out.write(xpif.getBytes());
            out.write(bytes);

            result = out.toByteArray();
        } catch (final Exception ex) {
            LOG.log(Level.SEVERE, methodName, ex);
            throw new IOException(ex);
        }

        log.trace("Exiting {}", methodName);
        return result;
    }

    /**
     * Returns the label that can overlay a page number.
     *
     * @param count The incremental count to overlay.
     * @return A label that can write to a PDF document.
     */
    protected NumberLabel createPageCountLabel(final int count) {
        return new PageNumberLabel(new Point2D.Float(602.8f, 640), count);
    }

    /**
     * Returns the label that can overlay an image number.
     *
     * @param count The incremental count to overlay.
     * @return A label that can write to a PDF document.
     */
    protected NumberLabel createImageCountLabel(final int count) {
        return new ImageNumberLabel(new Point2D.Float(602.8f, 710), count);
    }

    /**
     * Getter of Document Bundle object attached to this decorator.
     *
     * @return
     */
    public DocumentBundle getDocumentBundle() {
        return this.bundle;
    }

    /**
     * Subclasses must return the prefix to prepend to the filename. This is
     * used by DocumentBumdleImpl to create a filename for BC Mail Plus.
     *
     * @see #getFilename()
     * @return A prefix that indicates the document bundle type (transcript or
     * certificate).
     */
    public abstract String getFilenamePrefix();

    /**
     * Formats and applies custom tokens to the print template.
     *
     * @param xml The XPIF document to prepend to the bundle.
     * @return The XML with its media colour, media type, and job name variable
     * filled with values from this instance.
     */
    protected String fillXpif(final String xml) {
        final String methodName = "fillXpif(String)";
        log.trace("Entering {}", methodName);

        final String result = replaceEach(xml, new String[]{
            "${JOB_RECIPIENT_NAME}",
            "${MEDIA_COLOR}",
            "${MEDIA_TYPE}",
            "${JOB_NAME}"
        }, new String[]{
            getJobRecipientName(),
            getMediaColour(),
            getMediaType(),
            getFilename()
        });

        log.trace("Exiting {}", methodName);
        return result;
    }

    protected abstract int getRotateDegree();

    /**
     * Returns the recipient name for the print job.
     *
     * @return A non-null string (e.g., "BCMAIL PLANNERS TEST").
     */
    protected String getJobRecipientName() {
        return getDocumentBundle().getJobRecipientName();
    }

    /**
     * Returns the paper type media colour that corresponds to the order.
     *
     * @return Paper type media colour.
     */
    protected String getMediaColour() {
        return getOrderType().getMediaColour();
    }

    /**
     * Returns the paper type media type that corresponds to the order.
     *
     * @return Paper type media colour.
     */
    protected String getMediaType() {
        return getOrderType().getMediaType();
    }

    /**
     * Returns the document bundle's filename.
     *
     * @return A filename, never null, never empty.
     */
    protected String getFilename() {
        return getDocumentBundle().getFilename();
    }

    /**
     * Returns the order type (subclass) that defines the paper type and packing
     * slip order type name (displayed verbatim).
     *
     * @return An order type, never null.
     */
    private OrderType getOrderType() {
        return getDocumentBundle().getOrderType();
    }


    /**
     * Subclasses can override this to process the document before it is written
     * to the bundle packet.
     *
     * @param report The document to process.
     * @return The given report, not rotated.
     * @throws IOException Could not process the report.
     */
    protected BusinessReport process(final BusinessReport report)
            throws IOException {
        return report;
    }

    /**
     * Returns the resource name for XPIF XML data.
     *
     * @return A resource name.
     */
    protected abstract String getXpifResourceName();

    /**
     * Returns XPIF print settings template as an XML stream.
     *
     * @return null by default (subclasses should implement this if required)
     */
    private InputStream openXpifStream() {
        return getResourceAsStream(getXpifResourceName());
    }

    /**
     * Uses the Class to load a resource.
     *
     * @param resourceName Opens a stream to the resource with this name.
     * @return An open stream for reading the resource contents, or null if the
     * resource cannot be read.
     */
    protected InputStream getResourceAsStream(final String resourceName) {
        return getClass().getResourceAsStream(resourceName);
    }

    protected ByteArrayOutputStream createByteArrayOutputStream() {
        return new ByteArrayOutputStream(BUFFER_SIZE);
    }

    /**
     * Returns the bytes from the document bundle, or an empty array.
     *
     * @return A non-null array, possibly empty.
     */
    protected byte[] getDocumentBundleBytes() {
        final DocumentBundle db = getDocumentBundle();

        // All implementations return a non-null array.
        final byte[] bytes = db.asBytes();

        return bytes;
    }
}

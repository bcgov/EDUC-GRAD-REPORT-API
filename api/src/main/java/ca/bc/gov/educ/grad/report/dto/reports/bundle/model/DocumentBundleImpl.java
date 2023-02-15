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
package ca.bc.gov.educ.grad.report.dto.reports.bundle.model;

import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.*;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementOrderType;
import ca.bc.gov.educ.grad.report.model.cert.CertificateOrderType;
import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.school.SchoolReportOrderType;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptOrderType;

import javax.naming.NamingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_SAFE_FILENAME;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.PDF;

/**
 * Defines common attributes and behavior of all types of Bundled Documents
 *
 * @author CGI Information Management Consultants Inc.
 */
public class DocumentBundleImpl implements DocumentBundle {

    private static final String CLASSNAME = DocumentBundleImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private final static long serialVersionUID = 8L;

    /**
     * PDF data (with or without the XPIF header).
     */
    private byte[] bundleContent = new byte[0];
    private String entityId;
    private OrderType orderType;
    private String filename;

    /**
     * Set via JNDI.
     */
    private String jobRecipientName;

    /**
     * Lazily initialized based on order type.
     */
    private DocumentBundleDecorator decorator;

    /**
     * Creates empty bundle document.
     *
     * @param orderType Set to a certificate type or transcript.
     * @param suffix 64-character UUID value.
     * @throws NamingException Could not look up JNDI value for job recipient
     * name.
     */
    public DocumentBundleImpl(final OrderType orderType, final String suffix) {
        setJobRecipientName(("BCMP-Service"));
        setOrderType(orderType);
        setEntityId(suffix);
        initFilename();
    }

    /**
     * Appends a report document to the list of documents in this bundle.
     *
     * @param report The filled report document to append to the end of this
     * bundle of report documents.
     * @throws IOException Could not append the report to this bundle.
     */
    @Override
    public void append(final ReportDocument report)
            throws IOException {
        setBytes(getDecorator().append(report));
    }

    @Override
    public void append(final BusinessReport report)
            throws IOException {
        setBytes(getDecorator().append(report));
    }

    /**
     * Append multiple documents to the bundle by their order in the list.
     *
     * @param reports A list of Reports to append to the bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    public void appendReportDocument(final List<ReportDocument> reports)
            throws IOException {
        setBytes(getDecorator().appendReportDocument(reports));
    }

    /**
     * Append multiple documents to the bundle by their order in the list.
     *
     * @param reports A list of Reports to append to the bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    public void appendBusinessReport(final List<BusinessReport> reports)
            throws IOException {
        setBytes(getDecorator().appendBusinessReport(reports));
    }

    /**
     * Overlay page numbers throughout the PDF. If this is a certificate, only
     * the first page (the packing slip) receives page numbers.
     *
     * @throws IOException Could not overlay page numbers.
     */
    @Override
    public void enumeratePages() throws IOException {
        setBytes(getDecorator().enumeratePages());
    }

    /**
     * Prepends paper type print instructions to this bundle.
     *
     * @throws IOException Could not prepend XPIF data.
     */
    @Override
    public void xpif() throws IOException {
        setBytes(getDecorator().xpif());
    }

    /**
     * Helper method to save the document to a file. This will overwrite the
     * given file if it already exists.
     *
     * @param file The path and filename to contain the report document content.
     * @throws IOException Could not write the report document.
     */
    public void save(final File file) throws IOException {
        try (final FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(asBytes());
        } catch (final IOException e) {
            LOG.log(Level.SEVERE, "save", e);
            throw new IOException(
                    "Export bundle failed: " + file.getAbsolutePath(), e);
        }
    }

    /**
     * Returns the common part of a bundle's filename. Subclasses must override
     * this method to prepend the bundle-specific prefix, which happens prior to
     * filling out the XPIF template.
     *
     * @return A filename suitable for saving and including in the XPIF header.
     */
    private void initFilename() {
        final String f = String.format(
                "EDTPMA.%s.%s",
                getFilenamePrefix(),
                getFilenameSuffix()
        );

        setFilename(f);
    }

    /**
     * Asks the decorator for the filename prefix, which varies depending on the
     * report type (certificate/transcript).
     *
     * @return The decorated filename prefix (e.g., "TRANS" or "CERT").
     */
    private String getFilenamePrefix() {
        return getDecorator().getFilenamePrefix();
    }

    /**
     * Returns the suffix used for the bundles, specified by BC Mail Plus.
     *
     * @return A filename that is compatible with the BC Mail Plus system.
     */
    private String getFilenameSuffix() {
        return (new SimpleDateFormat(DATE_SAFE_FILENAME).format(new Date())
                + "." + getEntityId()
                + "." + PDF.getFilenameExtension()).toUpperCase();
    }

    /**
     * Returns the print job recipient name to include in the XPIF.
     *
     * @return "BCMAIL PLANNERS TEST" by default.
     */
    @Override
    public  String getJobRecipientName() {
        if (this.jobRecipientName == null) {
            this.jobRecipientName = "BCMP-Service";
        }

        return this.jobRecipientName;
    }

    private void setJobRecipientName(final Object jobRecipientName) {
        if (jobRecipientName != null) {
            setJobRecipientName(jobRecipientName.toString());
        }
    }

    /**
     * Changes the print job recipient name to include in the XPIF.
     *
     * @param jobRecipientName "BCMAIL PLANNERS TEST" (default) or "BCMAIL
     * PLANNERS PROD"
     */
    public void setJobRecipientName(final String jobRecipientName) {
        if (jobRecipientName != null) {
            this.jobRecipientName = jobRecipientName;
        }
    }

    /**
     * Returns a recommended filename. The filename is inserted into the XPIF
     * and is used by the BC Mail Plus file transmission service.
     *
     * @return A file name for the bundle file.
     */
    @Override
    public String getFilename() {
        return this.filename;
    }

    /**
     * Sets the filename used for this bundle.
     *
     * @param filename Filename that can be used for saving the document and
     * filling the XPIF information.
     */
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    /**
     * Changes the four digit character sequence used to help uniquely identify
     * this bundle.
     *
     * @param entityId Unique code.
     */
    private void setEntityId(final String entityId) {
        assert entityId != null;

        this.entityId = entityId;
    }

    /**
     * Returns the entity ID used within the suggested bundle filename.
     *
     * @return
     */
    private String getEntityId() {
        return this.entityId;
    }

    /**
     * Answers whether this bundle should package up transcripts or
     * certificates.
     */
    @Override
    public OrderType getOrderType() {
        return this.orderType;
    }

    /**
     * Sets whether this bundle should package up transcripts or certificates.
     */
    private void setOrderType(final OrderType orderType) {
        this.orderType = orderType;
    }

    /**
     * Replaces the PDF content using new PDF content.
     *
     * @param src The content to replace the current content.
     */
    private void setBytes(final byte[] src) {
        if (src != null && src.length > 0) {
            this.bundleContent = src;
        }
    }

    /**
     * Returns the entire document. This will return a valid PDF content only
     * until the xpif method is called.
     *
     * @return The PDF with all appended requests concatenated, never null,
     * possibly an empty (zero-length) array.
     */
    @Override
    public byte[] asBytes() {
        return this.bundleContent == null ? new byte[0] : this.bundleContent;
    }

    /**
     * Lazily initialized decorator.
     *
     * @return The decorator used to modify the bundle.
     */
    private  DocumentBundleDecorator getDecorator() {
        if (this.decorator == null) {
            this.decorator = createDecorator();
        }

        return this.decorator;
    }

    /**
     * Returns an appender that corresponds to the given order type.
     *
     * @return An appender for the given order type.
     */
    private DocumentBundleDecorator createDecorator() {
        final String methodName = "createDecorator";
        LOG.entering(CLASSNAME, methodName);

        final DocumentBundleDecorator dbd;
        final OrderType ot = getOrderType();

        if (ot instanceof TranscriptOrderType) {
            dbd = new TranscriptReportDecorator(this);
        } else if (ot instanceof CertificateOrderType) {
            dbd = new CertificateReportDecorator(this);
        } else if (ot instanceof AchievementOrderType) {
            dbd = new AchievementReportDecorator(this);
        } else if (ot instanceof SchoolReportOrderType) {
            dbd = new SchoolReportDecorator(this);
        } else {
            throw new IllegalArgumentException("Unexpected order type: " + ot);
        }

        LOG.exiting(CLASSNAME, methodName);
        return dbd;
    }
}

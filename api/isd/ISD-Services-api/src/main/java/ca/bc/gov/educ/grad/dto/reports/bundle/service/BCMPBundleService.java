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
package ca.bc.gov.educ.grad.dto.reports.bundle.service;

import ca.bc.gov.educ.grad.dto.reports.ReportDocument;
import ca.bc.gov.educ.grad.dto.reports.ReportExportService;
import ca.bc.gov.educ.grad.model.cert.CertificateType;
import ca.bc.gov.educ.grad.model.common.BusinessReport;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Bundle multiply documents into a single unit.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMPBundleService extends ReportExportService, Serializable {

    /**
     * Creates empty document to be populated with report document instances.
     *
     * @param orderType The type of bundle to create (certificate or transcript)
     * @return An empty document.
     */
    DocumentBundle createDocumentBundle(OrderType orderType);

    /**
     * Appends a report document (transcript or certificate) to the given
     * bundle.
     *
     * @param bundle The bundle to receive another document.
     * @param report The document to append to the bundle.
     * @return A bundle concatenated with the bundle contents and the report.
     * @throws IOException Could not append to the bundle.
     */
    DocumentBundle appendReportDocument(
            DocumentBundle bundle,
            ReportDocument report) throws IOException;

    /**
     * Appends a list of reports (transcript or certificate) to the given bundle.
     *
     * @param bundle The bundle to receive another document.
     * @param reports The documents to append to the bundle.
     * @return A bundle concatenated with the bundle contents and the report.
     * @throws IOException Could not append to the bundle.
     */
    DocumentBundle appendReportDocument(
            DocumentBundle bundle,
            List<ReportDocument> reports) throws IOException;

    /**
     * Appends a report document (transcript or certificate) to the given
     * bundle.
     *
     * @param bundle The bundle to receive another document.
     * @param report The document to append to the bundle.
     * @return A bundle concatenated with the bundle contents and the report.
     * @throws IOException Could not append to the bundle.
     */
    DocumentBundle appendBusinessReport(
            DocumentBundle bundle,
            BusinessReport report) throws IOException;

    /**
     * Appends a list of reports (transcript or certificate) to the given bundle.
     *
     * @param bundle The bundle to receive another document.
     * @param reports The documents to append to the bundle.
     * @return A bundle concatenated with the bundle contents and the report.
     * @throws IOException Could not append to the bundle.
     */
    DocumentBundle appendBusinessReport(
            DocumentBundle bundle,
            List<BusinessReport> reports) throws IOException;


    /**
     * Adds page numbers to a document. This must be called before prepending
     * the XPIF information. Note that these page numbers (required by BC Mail)
     * are different than the transcript page numbers (required by business).
     *
     * @param bundle The bundle to paginate.
     * @return The given bundle with page numbers.
     * @throws IOException Could not add page numbers to the bundle.
     */
    DocumentBundle enumeratePages(DocumentBundle bundle)
            throws IOException;

    /**
     * Creates an order type for transcripts.
     *
     * @return A non-null instance.
     */
    OrderType createTranscriptOrderType();

    /**
     * Creates an order type for transcripts.
     *
     * @param ct The certificate type that dictates the paper to use when
     * printing.
     * @return A non-null instance.
     */
    OrderType createCertificateOrderType(CertificateType ct);

    /**
     * Prepend a bundle with paper type print instructions.
     *
     * @param bundle Prepends the XPIF instructions to the given bundle.
     * @return The document bundle with XPIF information prepended.
     * @throws IOException Could not add XPIF information to the bundle.
     */
    DocumentBundle xpif(DocumentBundle bundle)
            throws IOException;
    
    /**
     * Decorate a bundle for BCMP.
     * @throws java.io.IOException
     * @precondition No XPIF
     * @precondition No enumerated pages.
     * @postcondition XPIF is prepended to document.
     * @postcondition Pages are enumerated for BCMP.
     * @param documentBundle the source bundle
     * @return the decorated bundle
     */
    DocumentBundle decorate(DocumentBundle documentBundle) throws IOException;
}

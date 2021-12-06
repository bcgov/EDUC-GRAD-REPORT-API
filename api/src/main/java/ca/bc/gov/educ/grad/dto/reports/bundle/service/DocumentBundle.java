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

import ca.bc.gov.educ.grad.model.common.BusinessReport;
import ca.bc.gov.educ.grad.model.order.OrderType;
import ca.bc.gov.educ.grad.model.reports.ReportDocument;

import java.io.IOException;
import java.util.List;

/**
 * Represent bundle (packet) of appended/merged documents
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DocumentBundle extends ReportDocument {

    /**
     * Append a document to the bundle.
     *
     * @param report Report contents to append to the bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    void append(final ReportDocument report) throws IOException;

    /**
     * Append multiple documents to the bundle by their order in the list.
     *
     * @param reports A list of Reports to append to the bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    void appendReportDocument(final List<ReportDocument> reports) throws IOException;

    /**
     * Append multiple reports to the bundle by their order in the list.
     *
     * @param reports A list of Reports to append to the bundle.
     * @throws IOException Could not append the report to the bundle.
     */
    void appendBusinessReport(final List<BusinessReport> reports) throws IOException;
    /**
     * Append multiple documents to the bundle by their order in the list.
     *
     * @param report the report
     */
    void append(final BusinessReport report) throws IOException;

    /**
     * Overlay page numbers to the bundle. For a transcript, this will only
     * change the first page (assumed to be the packing slip).
     *
     * @throws IOException Could not overlay page numbers.
     */
    void enumeratePages() throws IOException;

    /**
     * Part of the package that is sent to BC Mail for printing is the XPIF
     * printing instructions. This XML has a job-recipient-name element. In the
     * PROD environment the value of this element should be:
     * <code>BCMAIL PLANNERS PROD</code>. In all other environments the value of
     * this element should be: <code>BCMAIL PLANNERS TEST</code>
     *
     * @return A non-null name to insert into the XPIF.
     */
    String getJobRecipientName();

    /**
     * Returns the recommended filename as per business requirements.
     *
     * @return A suggested filename, never null, never empty.
     */
    String getFilename();

    /**
     * Prepends paper type print instructions to this bundle.
     *
     * @throws IOException Could not prepend XPIF data.
     */
    void xpif() throws IOException;

    /**
     * Returns the order type (certificate/transcript) for this bundle.
     *
     * @return A non-null instance.
     */
    public OrderType getOrderType();
}

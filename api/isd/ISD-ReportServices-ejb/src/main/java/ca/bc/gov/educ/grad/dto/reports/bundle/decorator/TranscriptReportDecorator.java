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
 *  File:                CertificateReportAppender.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.dto.reports.bundle.service.DocumentBundle;

/**
 * Responsible for bundling transcript reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptReportDecorator extends DocumentBundleDecorator {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs using the superclass.
     *
     * @param bundle The bundle to manipulate.
     */
    public TranscriptReportDecorator(final DocumentBundle bundle) {
        super(bundle);
    }

    /**
     * Answers whether the page/image number can be written to the page.
     *
     * @param pageNumber The current page number.
     * @return true for odd pages, false for even pages.
     */
    @Override
    protected boolean isEnumerable(final int pageNumber) {
        return pageNumber % 2 == 1;
    }

    /**
     * Returns the label that can overlay an image number.
     *
     * @param count The incremental count to overlay.
     * @return A label that can write to a PDF document.
     */
    @Override
    protected NumberLabel createPageCountLabel(int count) {
        return super.createPageCountLabel((count + 1) / 2);
    }

    /**
     * Returns print settings template name.
     *
     * @return A non-null string.
     */
    @Override
    protected String getXpifResourceName() {
        return "/xpif_transcript.xml";
    }

    /**
     * Returns an indicator prefix to the filename.
     *
     * @return "TRANS"
     */
    @Override
    public String getFilenamePrefix() {
        return "TRANS";
    }
}

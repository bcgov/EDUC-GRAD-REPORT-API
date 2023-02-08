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
 *  File:                CertificateReportAppender.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.report.dto.reports.bundle.service.DocumentBundle;

import java.awt.geom.Point2D;
import java.util.logging.Logger;

/**
 * Responsible for bundling transcript reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SchoolReportDecorator extends DocumentBundleDecorator {

    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = SchoolReportDecorator.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Constructs using the superclass.
     *
     * @param bundle The bundle to manipulate.
     */
    public SchoolReportDecorator(final DocumentBundle bundle) {
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
        return pageNumber > 1;
    }

    /**
     * Returns the label that can overlay an image number.
     *
     * @param count The incremental count to overlay.
     * @return A label that can write to a PDF document.
     */
    @Override
    protected NumberLabel createPageCountLabel(int count) {
        NumberLabel label = new PageNumberLabel(new Point2D.Float(495, 810), count, 0);
        label.setNumberFormat("Page: %s%s");
        label.setLabelPrefix("");
        return label;
    }

    /**
     * Returns the label that can overlay an image number.
     *
     * @param count The incremental count to overlay.
     * @return A label that can write to a PDF document.
     */
    protected NumberLabel createImageCountLabel(final int count) {
        return null;
    }

    /**
     * Returns print settings template name.
     *
     * @return A non-null string.
     */
    @Override
    protected String getXpifResourceName() {
        return "xpif_school.xml";
    }

    /**
     * Returns an indicator prefix to the filename.
     *
     * @return "TRANS"
     */
    @Override
    public String getFilenamePrefix() {
        return "SCHOOL";
    }

    @Override
    protected int getRotateDegree() {
        return 0;
    }

}

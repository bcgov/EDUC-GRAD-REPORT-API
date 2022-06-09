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
/**
 * Responsible for bundling certificate reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AchievementReportDecorator extends DocumentBundleDecorator {

    private static final long serialVersionUID = 1L;

    /**
     * Constructed using the superclass.
     *
     * @param bundle
     */
    public AchievementReportDecorator(final DocumentBundle bundle) {
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
     * Returns print settings template name.
     *
     * @return A non-null string.
     */
    @Override
    protected String getXpifResourceName() {
        return "xpif_achievement.xml";
    }

    /**
     * Returns an indicator prefix to the filename.
     *
     * @return "CERT"
     */
    @Override
    public String getFilenamePrefix() {
        return "ACHIV";
    }
}

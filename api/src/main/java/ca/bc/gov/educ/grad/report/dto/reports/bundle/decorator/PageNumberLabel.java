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
 *  File:                $Id:: PageNumberLabel.java 4099 2016-10-07 18:56:49Z #$
 *  Date of Last Commit: $Date:: 2016-10-07 11:56:49 -0700 (Fri, 07 Oct 2016)  $
 *  Revision Number:     $Rev:: 4099                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import java.awt.geom.Point2D;

/**
 * Overlays page numbers on a PDF.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PageNumberLabel extends NumberLabel {

    /**
     * Constructs using the superclass.
     *
     * @param coordinate Passed to superclass.
     * @param number Passed to superclass.
     */
    public PageNumberLabel(final Point2D coordinate, final int number) {
        super(coordinate, number);
        setLabelPrefix("P");
    }

    /**
     * Constructs using the superclass.
     *
     * @param coordinate Passed to superclass.
     * @param number Passed to superclass.
     */
    public PageNumberLabel(final Point2D coordinate, final int number, final int degree) {
        super(coordinate, number, degree);
        setLabelPrefix("P");
    }

}

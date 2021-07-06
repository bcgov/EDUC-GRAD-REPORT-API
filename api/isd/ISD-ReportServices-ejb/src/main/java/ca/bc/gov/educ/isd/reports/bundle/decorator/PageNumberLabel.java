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
 *  File:                $Id:: PageNumberLabel.java 4099 2016-10-07 18:56:49Z #$
 *  Date of Last Commit: $Date:: 2016-10-07 11:56:49 -0700 (Fri, 07 Oct 2016)  $
 *  Revision Number:     $Rev:: 4099                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.bundle.decorator;

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
    }

    /**
     * Sheet number of each physical page. On the packing slip, this value
     * should always be "P0000001"; there may be page numbers on other pages of
     * the BC Mail Print File the details of which are described in the business
     * requirements document (BR095, BR110).
     *
     * @return 'P'
     */
    @Override
    protected char getLabelPrefix() {
        return 'P';
    }
}

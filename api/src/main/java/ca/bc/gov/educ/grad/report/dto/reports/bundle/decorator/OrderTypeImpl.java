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
 *  File:                $Id:: OrderTypeImpl.java 6785 2017-04-06 18:22:51Z ts#$
 *  Date of Last Commit: $Date:: 2017-04-06 11:22:51 -0700 (Thu, 06 Apr 2017)  $
 *  Revision Number:     $Rev:: 6785                                           $
 *  Last Commit by:      $Author:: tsalomon                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.reports.PaperType;

/**
 * Responsible for determining the paper type to use for XPIF print instructions
 * used by BC Mail Plus.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class OrderTypeImpl implements OrderType {

    private static final long serialVersionUID = 4L;

    /**
     * Paper used for printing.
     */
    private PaperType paperType;

    /**
     * Returns a value used for both media type and media colour.
     *
     * @return A non-null, non-empty string.
     */
    public PaperType getPaperType() {
        return this.paperType;
    }

    /**
     * Sets paper type used for printing this order.
     *
     * @param paperType The paper used for printing the order.
     */
    public void setPaperType(final PaperType paperType) {
        this.paperType = paperType;
    }

    /**
     * Returns the paper's media type to use for setting the print instructions.
     *
     * @return A non-null string, never empty.
     */
    @Override
    public String getMediaType() {
        return getPaperType().toString();
    }

    /**
     * Returns the paper's colour type to use for setting the print
     * instructions.
     *
     * @return A non-null string, never empty.
     */
    @Override
    public String getMediaColour() {
        return getPaperType().toString();
    }
}

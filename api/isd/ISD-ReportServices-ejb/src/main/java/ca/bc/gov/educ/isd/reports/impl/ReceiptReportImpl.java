/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
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
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Receipt;
import ca.bc.gov.educ.isd.reports.ReceiptReport;
import ca.bc.gov.educ.isd.reports.jasper.impl.ReportImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a receipts report for a sales order, which can include multiple
 * receipts, each receipt having multiple line items.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ReceiptReportImpl extends ReportImpl implements ReceiptReport {

    private static final long serialVersionUID = 2L;

    public static final String REPORT_NAME = "Receipt";

    /**
     * The data objects to pass into the report.
     */
    private List<Receipt> receipts;

    /**
     * Constructs a new report using the default report template for receipts.
     *
     * @param receipts
     */
    public ReceiptReportImpl(final List<Receipt> receipts) {
        super(REPORT_NAME);
        setReceipts(receipts);
    }

    /**
     * Sets the list of receipts, which includes a set of line items per
     * receipt, to display on the report. If the list of receipts is null, this
     * will use an empty list.
     *
     * @param receipts Receipt list with line items, can be null.
     */
    private void setReceipts(final List<Receipt> receipts) {
        this.receipts = receipts == null ? new ArrayList<Receipt>() : receipts;
    }

    /**
     * Returns the list of receipts (and corresponding line items) to display on
     * the report.
     *
     * @return Receipt list with line items, possibly empty, never null.
     */
    private List<Receipt> getReceipts() {
        return this.receipts;
    }

    /**
     * Returns receipt list.
     *
     * @return The receipt list to render on the report.
     */
    @Override
    public Object getDataSource() {
        return getReceipts();
    }
}

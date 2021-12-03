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
package ca.bc.gov.educ.grad.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.model.reports.Report;
import ca.bc.gov.educ.grad.model.reports.ReportDocument;

import java.io.IOException;

/**
 * Admin reports differ from student reports in that a list is provided as the
 * data source, rather than a single object wrapped in a list.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AdminJasperReportImpl extends JasperReportImpl {

    private static final String PARAMETER_DATA_SOURCE = "P_DATA_SOURCE";

    public AdminJasperReportImpl(final Report report) {
        super(report);
    }

    /**
     *
     * @return @throws IOException
     */
    @Override
    public ReportDocument export() throws IOException {
        final Report report = getReport();
        final Object o = report.getDataSource();

        report.setParameter(PARAMETER_DATA_SOURCE, createBeanDataSource(o));
        return super.export();
    }
}

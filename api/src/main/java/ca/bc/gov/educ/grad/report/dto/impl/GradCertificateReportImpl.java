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
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.support.report.BusinessReportEntity;
import ca.bc.gov.educ.grad.report.model.graduation.GradCertificateReport;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;

public class GradCertificateReportImpl extends BusinessReportEntity
        implements GradCertificateReport {

    private static final long serialVersionUID = 2L;

    /**
     * Class Constructor populates all the object attributes during
     * construction.
     *
     * @param reportData
     * @param reportFormat
     * @param filename
     * @param reportName
     */
    public GradCertificateReportImpl(
            final byte[] reportData,
            final ReportFormat reportFormat,
            final String filename,
            final String reportName) {
        super(reportData, reportFormat, filename, reportName);
    }
}

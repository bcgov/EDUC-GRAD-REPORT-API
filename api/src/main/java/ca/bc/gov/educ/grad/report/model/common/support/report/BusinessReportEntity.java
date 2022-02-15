/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
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
package ca.bc.gov.educ.grad.report.model.common.support.report;

import ca.bc.gov.educ.grad.report.model.common.BusinessReport;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;

import java.util.Objects;

/**
 * Contains common behaviours and attributes for all the student reports.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class BusinessReportEntity extends AbstractDomainEntity implements BusinessReport, ReportDocument {

    private static final long serialVersionUID = 1L;

    private final byte[] reportData;
    private final ReportFormat repotFormat;
    private final String reportName;
    private final String filename;

    /**
     * Class Constructor populates all the object attributes during
     * construction. Uses an empty report name.
     *
     * @param reportData
     * @param reportFormat
     * @param filename
     */
    public BusinessReportEntity(
            final byte[] reportData,
            final ReportFormat reportFormat,
            final String filename) {
        this(reportData, reportFormat, filename, "");
    }

    /**
     * Class Constructor populates all the object attributes during
     * construction.
     *
     * @param reportData
     * @param reportFormat
     * @param filename
     * @param reportName
     */
    public BusinessReportEntity(
            final byte[] reportData,
            final ReportFormat reportFormat,
            final String filename,
            final String reportName) {
        this.reportData = reportData;
        this.repotFormat = reportFormat;
        this.reportName = reportName;
        this.filename = filename;
    }

    @Override
    public byte[] getReportData() {
        return this.reportData;
    }

    @Override
    public String getReportTypeName() {
        return this.reportName;
    }

    @Override
    public ReportFormat getReportFormat() {
        return this.repotFormat;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public byte[] asBytes() {
        return getReportData();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BusinessReportEntity that = (BusinessReportEntity) o;
        return reportName.equals(that.reportName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reportName);
    }
}

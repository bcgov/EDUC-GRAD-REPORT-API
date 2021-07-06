/*
 * ***********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                           $
 *  Date of Last Commit: $Date::                                         $
 *  Revision Number:     $Rev::                                          $
 *  Last Commit by:      $Author::                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.common.impl;

import ca.bc.gov.educ.isd.reports.Report;
import ca.bc.gov.educ.isd.reports.ReportDocument;
import ca.bc.gov.educ.isd.reports.jasper.impl.JasperReportImpl;
import ca.bc.gov.educ.isd.reports.jasper.impl.ReportDocumentImpl;

import java.io.IOException;

import static ca.bc.gov.educ.isd.reports.ReportFormat.HTML;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Creates a JasperReport implementation and exports the given report in a
 * particular report format.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class AbstractReportService {

    /**
     * Creates a JasperReport instance and returns the report document generated
     * by filling and exporting.
     *
     * @return A non-null report document in the format requested by the report
     * parameter.
     *
     * @throws IOException Could not read resources required for filling the
     * report (e.g., resource bundle or report template).
     * @param report The report to fill and export.
     */
    public ReportDocument export(final Report report) throws IOException {
        final JasperReportImpl jasperReport = createJasperReportImpl(report);
        ReportDocument result = jasperReport.export();

        if (report.isFormat(HTML)) {
            result = postprocess(result);
        }

        return result;
    }

    /**
     * Provides subclasses the opportunity to change the HTML before it is
     * presented to the end user. By default this replaces the fixed-width
     * inline style for the jrPage table element with a 100% width.
     *
     * @see
     * https://github.com/TIBCOSoftware/jasperreports/blob/master/jasperreports/src/net/sf/jasperreports/engine/export/HtmlExporter.java#L532
     *
     * @param report The HTML report document to process.
     * @return The HTML report document after processing.
     */
    private ReportDocument postprocess(final ReportDocument report) throws IOException {
        final byte[] bytes = report.asBytes();
        final String result = new String(bytes, UTF_8);

        // Match any fixed-width style and convert to 100%. This ensures that
        // HTML reports expand to fill their container element.
        final String replaced = result.replaceFirst("width: .*p.;", "width: 100%;");

        return new ReportDocumentImpl(replaced.getBytes(UTF_8));
    }

    /**
     * Subclasses can override this to provide a different type of report
     * implementation for Jasper.
     *
     * @param report
     * @return
     */
    protected JasperReportImpl createJasperReportImpl(final Report report) {
        final JasperReportImpl jasperReport = new JasperReportImpl(report);
        return jasperReport;
    }
}

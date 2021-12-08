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
package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.report.dto.reports.jasper.ReportFormatFactory;
import ca.bc.gov.educ.grad.report.model.common.Predicate;
import ca.bc.gov.educ.grad.report.model.reports.Parameters;
import ca.bc.gov.educ.grad.report.model.reports.Report;
import ca.bc.gov.educ.grad.report.model.reports.ReportDocument;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.sf.jasperreports.export.type.HtmlSizeUnitEnum.POINT;

/**
 * Abstracts away the JasperReports-specific API calls so that the report
 * interface implementations do not get passed to the client side. This prevents
 * marshaling of JasperReports objects, which would lead to class not found
 * issues.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class JasperReportImpl {

    private static final String CLASSNAME = JasperReportImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    /**
     * Controls scaling images written to the browser.
     */
    public static final float HTML_SCALE_FACTOR = 1.5f;

    /**
     * HTML interim transcript is over 300k.
     */
    private final static int REPORT_OUTPUT_BUFFER_SIZE = 250000;
    private Report report;

    public JasperReportImpl(final Report report) {
        setReport(report);
    }

    public ReportDocument export() throws IOException {
        final String _m = "export()";
        LOG.entering(CLASSNAME, _m);

        final ReportFormat format = getFormat();
        final Exporter exporter = createExporter();
        final byte[] bytes;

        try (final ByteArrayOutputStream out = createByteArrayOutputStream()) {
            //final JasperPrint print = format.equals(XML) ? createEmptyReport() : createFilledReport();

            final JasperPrint print = createFilledReport();

            switch (format) {
                case CSV:
                    exporter.setConfiguration(createCsvExporterConfiguration());
                    exporter.setExporterOutput(
                            new SimpleWriterExporterOutput(out)
                    );
                    break;

                case HTML:
                    exporter.setConfiguration(createHtmlReportConfiguration());
                    exporter.setConfiguration(createHtmlExporterConfiguration());
                    exporter.setExporterOutput(
                            new SimpleHtmlExporterOutput(out));
                    break;

                case XML:
                    exporter.setConfiguration(createXmlExporterConfiguration());
                    exporter.setReportContext(createXmlReportContext());
                case PDF:
                default:
                    exporter.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(out));
                    break;
            }

            final ExporterInput exporterInput = new SimpleExporterInput(print);
            exporter.setExporterInput(exporterInput);
            exporter.exportReport();
            bytes = out.toByteArray();
        } catch (final Exception ex) {
            final String msg = "Could not export report";
            LOG.log(Level.SEVERE, msg, ex);
            throw new IOException(ex);
        }

        final ReportDocumentImpl result = new ReportDocumentImpl(bytes);

        LOG.exiting(CLASSNAME, _m);
        return result;
    }

    /**
     * Returns a new exporter instance for the given output format.
     *
     * @return An exporter that can generate a report in the given format.
     */
    private Exporter createExporter() {
        final Exporter exporter;
        final Report toExport = getReport();
        final ReportFormat format = toExport.getFormat();

        switch (format) {
            case CSV:
                exporter = new JRCsvExporter();
                break;
            case HTML:
                exporter = new HtmlExporter();
                break;
            case PDF:
                exporter = new JRPdfExporter();
                break;
            case XML:
                exporter = new XmlExporter();
                break;
            default:
                throw new IllegalArgumentException("No exporter defined for the following format: " + format);
        }

        return exporter;
    }

    /**
     * Creates a filled Jasper report ready for exporting.
     *
     * @return A print object that can be exported.
     * @throws Exception Failed to open the report template, or failed to fill
     * the report.
     */
    private JasperPrint createFilledReport() throws Exception {
        final Report filledReport = getReport();

        // Automatically close the report template after filling the report,
        // regardless of any exceptions.
        try (final InputStream is = filledReport.openReportTemplate()) {
            return fillReport(filledReport, is);
        }
    }

    /**
     * Creates a Jasper report that should not be exported.
     *
     * @return A print object that should not be used.
     */
    private JasperPrint createEmptyReport() {
        return new JasperPrint();
    }

    /**
     * Fills the report using the parameters and JR data source.
     *
     * @param is The report template to fill.
     * @return The filled report template.
     * @throws Exception Could not read the report template.
     */
    private JasperPrint fillReport(final Report report, final InputStream is) throws Exception {
        report.processParameters();

        final Parameters parameters = report.getParameters();

        // Format factory for dates.
        parameters.put(JRParameter.REPORT_FORMAT_FACTORY,
                new ReportFormatFactory(report.getResourceBundle()));

        return JasperFillManager.fillReport(
                is, report.getParameters(), getJRDataSource(report));
    }

    /**
     * Returns the source of the data used to fill the report, wrapped in a
     * collection that JasperReports can use.
     *
     * @param report The report with a data source to retrieve.
     * @return Null if no data source has been set.
     */
    protected JRDataSource getJRDataSource(final Report report) {
        return createBeanDataSource(report.getDataSource());
    }

    /**
     * Wraps the data source in a bean for the report iterator.
     *
     * @param data The list to wrap in a JR collection data source.
     * @return A JRBeanCollectionDataSource instance, never null.
     */
    protected JRBeanCollectionDataSource createBeanDataSource(final Object data) {
        final Collection<?> list = (Collection<?>) (data instanceof Collection
                ? data : Arrays.asList(data));

        return new JRBeanCollectionDataSource(list);
    }

    /**
     * Creates a SimpleHtmlExporterConfiguration instance with a header and
     * footer read from HTML resources located on the CLASSPATH.
     *
     * @return An HtmlExporterConfiguration with its HTML header and HTML footer
     * filled in with contents from header.html and footer.html resources
     * located on the CLASSPATH.
     * @throws IOException The HTML resources could not be read.
     */
    private HtmlExporterConfiguration createHtmlExporterConfiguration()
            throws IOException {
        final Report htmlReport = getReport();
        final SimpleHtmlExporterConfiguration configuration
                = new SimpleHtmlExporterConfiguration();

        if (htmlReport.getWrapHTML()) {
            configuration.setHtmlHeader(htmlReport.getHTMLHeader());
            configuration.setHtmlFooter(htmlReport.getHTMLFooter());
        } else {
            configuration.setHtmlHeader("");
            configuration.setHtmlFooter("");
        }

        return configuration;
    }

    /**
     * Creates an HTML report configuration that eliminates page margins and
     * sets a zoom factor.
     *
     * @return A non-null instance for configuring HTML reports.
     */
    private HtmlReportConfiguration createHtmlReportConfiguration() {
        final SimpleHtmlReportConfiguration configuration
                = new SimpleHtmlReportConfiguration();

        configuration.setIgnorePageMargins(true);
        configuration.setSizeUnit(POINT);

        return configuration;
    }

    /**
     * Creates a XmlExporterConfiguration that can filter on a predicate.
     *
     * @return
     */
    private XmlExporterConfiguration createXmlExporterConfiguration() {
        final Report xmlReport = getReport();
        final Predicate<String> predicate = xmlReport.getParameterPredicate();

        return new XmlExporterConfiguration(predicate);
    }

    /**
     * Creates a SimpleCsvExporterConfiguration instance.
     *
     * @return A new SimpleCsvExporterConfiguration instance, never null.
     */
    private SimpleCsvExporterConfiguration createCsvExporterConfiguration() {
        return new SimpleCsvExporterConfiguration();
    }

    /**
     * Creates a data source outside of the parameter map used in marshaling.
     *
     * @return A JasperReports ReportContext instance.
     */
    private ReportContext createXmlReportContext() {
        final Report xmlReport = getReport();
        final XmlReportContext context = new XmlReportContext();
        final Map<String, Object> params = xmlReport.getParameters();

        for (final Map.Entry<String, Object> entry : params.entrySet()) {
            final String key = entry.getKey();
            final Object value = entry.getValue();

            context.setParameterValue(key, value);
        }

        context.setDataSource(xmlReport.getDataSource());

        return context;
    }

    protected ReportFormat getFormat() {
        return getReport().getFormat();
    }

    protected Report getReport() {
        return this.report;
    }

    private void setReport(final Report report) {
        this.report = report;
    }

    protected ByteArrayOutputStream createByteArrayOutputStream() {
        return new ByteArrayOutputStream(REPORT_OUTPUT_BUFFER_SIZE);
    }
}

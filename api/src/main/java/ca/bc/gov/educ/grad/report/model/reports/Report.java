/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Report.java 11149 2018-08-21 23:04:25Z DAJARVIS $
 *  Date of Last Commit: $Date:: 2018-08-21 16:04:25 -0700 (Tue, 21 Aug 2018)  $
 *  Revision Number:     $Rev:: 11149                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.reports;

import ca.bc.gov.educ.grad.report.model.common.Predicate;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provides a report-engine agnostic mechanism to produce reports. That is, the
 * reporting engine is decoupled from the service architecture.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Report extends Serializable {

    /**
     * Sets the name of the report to run. This should be set once in the class
     * constructor.
     *
     * @param name The name of the report to run.
     */
    void setName(String name);

    /**
     * Sets the format to generate for this report class.
     * <ul>
     * <li><a href="http://www.iana.org/assignments/media-types/">IANA Media
     * Types</a></li>
     * <li><a href="http://www.ietf.org/rfc/rfc2046.txt?number=2046">RFC
     * 2046</a></li>
     * </ul>
     *
     * @param format The report file format to export.
     */
    void setFormat(ReportFormat format);

    /**
     * Returns the report format (the media type).
     *
     * @return The report's media type (e.g., HTML, PDF, CSV).
     */
    ReportFormat getFormat();

    /**
     * Sets the report parameters that will eventually be converted into the
     * data types required by the reporting tool.
     *
     * @param parameters The system, user input, and constant parameter values
     * (usually as string values).
     */
    void setParameters(Parameters<String, Object> parameters);

    /**
     * Adds a parameter to the parameter map that will be passed into the
     * report. If the report does not currently have a parameter map, one will
     * be instantiated automatically.
     *
     * @param key The name of the parameter to give to the report.
     * @param value The value of the parameter to give to the report.
     */
    void setParameter(String key, Object value);

    /**
     * Returns the source of data that the report uses to fill itself. There are
     * two types of data: static and tabular. The static data is often a single
     * row of data that populates headers, information boxes, etc. Tabular data
     * is iterated over to fill out report rows. How the data source is used
     * varies between report engines and must therefore be abstracted to a
     * generic Object.
     *
     * @return The source of data (static and tabular) for the report.
     */
    Object getDataSource();

    /**
     * Answers whether the given parameter name has a value assigned.
     *
     * @param key The parameter name to check.
     * @return false No key/value pair for the key exists in the parameter map.
     */
    boolean hasParameter(String key);

    /**
     * Answers whether the given report format is the same as this report's
     * format.
     *
     * @param format The format to compare.
     * @return true The internal format matches the given format.
     */
    boolean isFormat(ReportFormat format);

    /**
     * Returns a filename for saving the report. This should return a unique
     * filename so that browser caching is avoided. For example,
     *
     * @return A filename (no path).
     */
    String getFilename();

    /**
     * Returns the path used by the reporting engine to locate the given
     * resource.
     *
     * @param resource The resource to get the path of.
     * @return The path to the given resource.
     */
    String toReportResourcePath(String resource);

    /**
     * Changes the behaviour for exporting HTML. When set to true (default), the
     * resources header.html and footer.html are prepended and appended to the
     * exported HTML, respectively. When set to false, neither header.html or
     * footer.html are applied.
     *
     * @param wrapHtml true Wrap the exported HTML in head/body tags.
     */
    void setWrapHtml(boolean wrapHtml);

    /**
     * Dictates whether a unique suffix should be added to the filename.
     *
     * @param appendUniqueSuffix false Do not append a timestamp to the
     * filename.
     */
    void setAppendUniqueSuffix(boolean appendUniqueSuffix);

    /**
     * Set the language prior to generating a report.
     *
     * @param locale The resource bundle locale to use for report text.
     */
    void setLocale(Locale locale);

    /**
     * Returns the report template opened and ready for reading by JasperReports
     * API.
     *
     * @return The input stream for this report.
     * @throws IOException Could not open the report template resource.
     */
    InputStream openReportTemplate() throws IOException;

    /**
     * Assigns report parameters based on configuration settings. The parameters
     * are put into a map that is passed into JasperReports. The parameters are
     * then used by the report template when filling the report.
     */
    void processParameters();

    /**
     * Returns the map of name/value pairs used to configure the report.
     *
     * @return A non-null map of name/value pairs.
     */
    Parameters getParameters();

    /**
     * Returns the resource bundle for the report template in a particular
     * locale.
     *
     * @return A non-null resource bundle.
     */
    ResourceBundle getResourceBundle();

    /**
     * Answers whether the report should be wrapped in a custom HTML header and
     * footer.
     *
     * @return TRUE Write the custom HTML header and footer files to the start
     * and end of the report.
     * @deprecated
     */
    Boolean getWrapHTML();

    /**
     * Returns the HTML file content for the footer to write to the report data
     * during the report fill process.
     *
     * @return A non-null string with HTML content.
     * @throws IOException Could not read the footer resource.
     * @deprecated Unused
     */
    String getHTMLFooter() throws IOException;

    /**
     * Returns the HTML file content for the header to write to the report data
     * during the report fill process.
     *
     * @return A non-null string with HTML content.
     * @throws IOException Could not read the header resource.
     * @deprecated Unused
     */
    String getHTMLHeader() throws IOException;

    /**
     * Returns the predicate used to filter parameters. This is used by the XML
     * report configuration to filter XML elements.
     *
     * @return A non-null instance.
     */
    Predicate<String> getParameterPredicate();

    /**
     * Used to change the report template name based on arbitrary criteria. This
     * can be used, for example, to change the report template name when
     * multiple reports must be run consecutively to build the final report
     * document. The default behaviour is a no-op.
     */
    void preprocessReportName();
}

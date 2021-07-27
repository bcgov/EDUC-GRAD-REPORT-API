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
package ca.bc.gov.educ.isd.reports.jasper.impl;

import ca.bc.gov.educ.grad.utils.EducGradSignatureImageApiConstants;
import ca.bc.gov.educ.isd.common.Predicate;
import ca.bc.gov.educ.isd.reports.Parameters;
import ca.bc.gov.educ.isd.reports.Report;
import ca.bc.gov.educ.isd.reports.ReportFormat;
import ca.bc.gov.educ.isd.reports.impl.ParametersImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;
import static ca.bc.gov.educ.isd.reports.ReportFormat.PDF;
import static ca.bc.gov.educ.isd.reports.util.InheritableResourceBundle.getBundle;
import static java.util.Locale.CANADA;
import static net.sf.jasperreports.engine.JRParameter.IS_IGNORE_PAGINATION;

/**
 * Provides an interface to the JasperReports reporting tool. This isolates
 * knowledge of the reporting tool being used from the application.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class ReportImpl implements Report {

    private static final long serialVersionUID = 9L;

    private static final String CLASSNAME = ReportImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String TEMPLATE_EXTENSION = ".jasper";

    private static final String DIR_REPORT_BASE = "/reports/";
    private static final String DIR_JASPER_BASE = "reports/";
    private static final String DIR_RESOURCES = "resources/";
    private static final String DIR_INTERNATIONALIZATION
            = DIR_JASPER_BASE + DIR_RESOURCES + "i18n";

    @Autowired
    EducGradSignatureImageApiConstants signatureImageApiConstants;

    /**
     * Canadian English locale by default.
     */
    private Locale locale = CANADA;

    /**
     * Set false to disable the HTML header and footer (i.e., head/body tags).
     * This is used when the HTML version of the report is embedded inside of a
     * web page.
     */
    private boolean wrapHtml = true;

    /**
     * Set false to prevent a timestamp from being added to report filenames.
     */
    private boolean appendUniqueSuffix = true;

    /**
     * Predicate value used in filtering, such as filtering parameter values
     * when constructing an XML header.
     */
    private Predicate<String> parameterPredicate;

    /**
     * Name/value pairs to pass into the report.
     */
    private Parameters parameters;

    /**
     * Type of report format to generate (default is PDF).
     */
    private ReportFormat reportFormat;

    /**
     * Report template name to run.
     */
    private String name;

    /**
     * Constructs a report implementation based with a report template name.
     *
     * @param name Report template name to run.
     */
    public ReportImpl(final String name) {
        setName(name);
    }

    /**
     * Subclasses can override this method to inject their own parameters before
     * the report runs.
     */
    protected void preprocessParameters() {
    }

    /**
     * Subclasses can override this method to change existing parameters before
     * the report runs.
     */
    protected void postprocessParameters() {
        for (final Map.Entry<String, Object> entry : getParameters().entrySet()) {
            LOG.log(Level.FINE, "Report parameter {0}={1}",
                    new Object[]{entry.getKey(), entry.getValue()});
        }
    }

    /**
     * This method opens the report template specified by the report template
     * name.
     *
     * @return The report resource as a stream to read and pass into the report
     * engine.
     * @throws IOException Could not open the report template.
     */
    @Override
    public InputStream openReportTemplate() throws IOException {
        return openResource(getName() + TEMPLATE_EXTENSION);
    }

    /**
     * Returns the HTML header to prepend to the start of every report. This
     * should open the HTML header elements that are closed by getHtmlFooter().
     *
     * @return HTML elements suitable for writing to the start of an HTML file.
     * @throws IOException Could not find or read the HTML resource.
     */
    @Override
    public String getHTMLHeader() throws IOException {
        return readHtmlResource("header");
    }

    /**
     * Returns the HTML footer to append to the end of every report. This should
     * close off the HTML header elements returned from getHtmlHeader().
     *
     * @return HTML elements suitable for writing to the end of an HTML file.
     * @throws IOException Could not find or read the HTML resource.
     */
    @Override
    public String getHTMLFooter() throws IOException {
        return readHtmlResource("footer");
    }

    /**
     * Reads the entire content of the given path to a resource into a String.
     * This tries to open a resource named "html/" + resource + ".html".
     *
     * @param resource The path to an HTML resource, without a leading slash
     * (path should be relative to top of CLASSPATH) and without a filename
     * extension.
     * @return The contents of the resource (an unbalanced HTML document
     * fragment).
     * @throws IOException Could not open or read the resource.
     */
    private String readHtmlResource(final String resource) throws IOException {
        final String html;

        try (final InputStream input = openResource(
                DIR_RESOURCES + "html/" + resource + ".html")) {
            html = IOUtils.toString(input, getDefaultEncoding());
        }

        return html == null ? "" : html;
    }

    /**
     * Returns the platform's character encoding.
     *
     * @return A non-null String that represents a character encoding (e.g.,
     * "UTF-8").
     */
    private String getDefaultEncoding() {
        return Charset.defaultCharset().displayName();
    }

    /**
     * Convenience method to open a resource that exists on the CLASSPATH.
     *
     * @param resource The path to the resource to open.
     * @return The URL for the resource opened as a stream.
     * @throws IOException Could not open the resource.
     */
    private InputStream openResource(final String resource) throws IOException {
        //final URL url = getReportResource(resource);
        URL url = this.getClass().getResource(DIR_REPORT_BASE + resource);
        LOG.info("url == " + url.getFile());
        return url.openStream();
    }

    /**
     * Scans the CLASSPATH for a resource (e.g., compiled report template) and
     * returns the URL for the resource. This is used by the JasperFillManager
     * to produce a final report.
     *
     * This is a helper method to abstract the META-INF/ directory.
     *
     * @param resource The path (without META-INF/) to a file.
     * @return A URL that represents the path to the given resource or null if
     * no such resource could be found.
     */
    private URL getReportResource(final String resource) {
        //final String resourcePath = toReportResourcePath(resource);
        //final URL url = getResource(resourcePath);

        final URL url = ClassLoader.getSystemResource(DIR_REPORT_BASE + resource);

        if (url == null) {
            throw new RuntimeException(
                    "Missing resource path: '" + resource
                    + "' (for " + resource + ")");
        }

        return url;
    }

    /**
     * Helper method to obtain a stream-able resource from the class loader.
     *
     * @param resource The resource to obtain from the class loader.
     * @return The resource obtained from the class loader that can be streamed.
     */
    private URL getResource(final String resource) {
        return ReportImpl.class.getResource(resource);
    }

    /**
     * Returns the fully qualified path name used to find resources required by
     * the reports.
     *
     * @param resource The resource to qualify.
     * @return The resource name with a report package prefix prepended.
     */
    @Override
    public String toReportResourcePath(final String resource) {
        return getReportPackage() + resource;
    }

    /**
     * Returns the package name that is the root folder for all report-related
     * resources.
     *
     * @return "/reports/"
     */
    protected String getReportPackage() {
        return DIR_REPORT_BASE;
    }

    /**
     * Returns the data source used to fill the report. If this returns a list,
     * then the list is passed into the JRDataSource instance without first
     * being wrapped by Arrays.asList. Otherwise, a collection of 1 object is
     * passed into the report.
     *
     * @return A non-null object set using setDataSource.
     */
    @Override
    public abstract Object getDataSource();

    /**
     * Sets the name/value pairs to pass into the report. These are usually
     * values set by the user or system that control what data fills the report.
     * This method is null-safe because any subsequent call to getParameters
     * will return an empty (non-null) set of parameters.
     *
     * @param parameters The set of name/value pairs.
     */
    @Override
    public void setParameters(final Parameters parameters) {
        this.parameters = parameters;
    }

    /**
     * Sets the report file format to generate.
     *
     * @param format The output format for the report.
     */
    @Override
    public void setFormat(final ReportFormat format) {
        this.reportFormat = format;
    }

    /**
     * Returns the file format of the final report.
     *
     * @return The file format for the report that the engine should create.
     */
    @Override
    public ReportFormat getFormat() {
        final ReportFormat result = this.reportFormat;

        return result == null ? getDefaultReportFormat() : result;
    }

    /**
     * Answers whether the given report format matches this report's format.
     *
     * @param format The report format to compare against.
     * @return true The formats are the same.
     */
    @Override
    public boolean isFormat(final ReportFormat format) {
        return getFormat().equals(format);
    }

    /**
     * Returns the default file format to use when generating reports. Called by
     * getFormat when no report format has been set. Subclasses can override to
     * provide a different default, if necessary. Subclasses must not return
     * null.
     *
     * @return ReportFormat.PDF
     */
    protected ReportFormat getDefaultReportFormat() {
        return PDF;
    }

    /**
     * Sets a new or replaces an existing report parameter. If the parameter
     * already exists, the value will be updated. Otherwise, a new key is added
     * to the map.
     *
     * @param key The parameter name to set or replace.
     * @param value The parameter value to pass to the report.
     */
    @Override
    public void setParameter(final String key, final Object value) {
        getParameters().put(key, value);
    }

    /**
     * Returns the value of a report parameter.
     *
     * @param key The report parameter name to retrieve.
     * @return A value that will be passed into the report.
     */
    protected Object getParameter(final String key) {
        return getParameters().get(key);
    }

    /**
     * Returns the name/value pairs to pass into the report. If the parameters
     * are null, this will return an empty parameter map and set the map to use
     * the newly created map instance.
     *
     * @return A non-null map of name/value pairs (can be empty).
     */
    @Override
    public Parameters<String, Object> getParameters() {
        if (this.parameters == null) {
            this.parameters = createParameters();
        }

        return this.parameters;
    }

    /**
     * Returns an empty set of parameters to pass into the report should no
     * parameters be provided prior to generating a report.
     *
     * @return An empty parameter map.
     */
    protected Parameters createParameters() {
        return new ParametersImpl();
    }

    /**
     * Sets the name of the report template to fill. The report should exist
     * somewhere in the CLASSPATH where the ClassLoader for the currently
     * running thread can find it.
     *
     * @param name The report template name.
     */
    @Override
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the name of the report to load and run.
     *
     * @return The report template name to run.
     */
    protected String getName() {
        return nullSafe(this.name);
    }

    /**
     * Answers whether the given key exists in the report parameter map.
     *
     * @param key The key to find.
     * @return true The key has a value in the parameter map.
     */
    @Override
    public boolean hasParameter(final String key) {
        return getParameters().containsKey(key);
    }

    /**
     * Returns a filename suitable for saving this report.
     *
     * @return A non-null file instance.
     */
    @Override
    public String getFilename() {
        return getFilenamePrefix()
                + getName()
                + getFilenameSuffix()
                + (getAppendUniqueSuffix() ? getUniqueSuffix() : "")
                + getFilenameExtension();
    }

    /**
     * Allows subclasses to inject a prefix into the filename.
     *
     * @return ""
     */
    protected String getFilenamePrefix() {
        return "";
    }

    /**
     * Allows subclasses to inject a suffix onto the filename.
     *
     * @return ""
     */
    protected String getFilenameSuffix() {
        return "";
    }

    /**
     * Helps to ensure that filenames are unique so as to avoid caching issues
     * with MSIE. No HTTP headers to control caching are required if the
     * filename is unique.
     *
     * @return A unique number.
     */
    protected String getUniqueSuffix() {
        final AtomicLong counter = new AtomicLong(System.currentTimeMillis());
        return new StringBuilder().append('_').append(counter.toString()).toString();
    }

    /**
     * Answers whether a unique suffix should be added to the filename.
     *
     * @return true Append a timestamp to the filename.
     */
    public boolean getAppendUniqueSuffix() {
        return this.appendUniqueSuffix;
    }

    /**
     * Dictates whether a unique suffix should be added to the filename.
     *
     * @param appendUniqueSuffix false Do not append a timestamp to the
     * filename.
     */
    @Override
    public void setAppendUniqueSuffix(final boolean appendUniqueSuffix) {
        this.appendUniqueSuffix = appendUniqueSuffix;
    }

    /**
     * Returns the filename extension for this report based on the report's
     * media type.
     *
     * @return A filename extension suitable for the report format, with the dot
     * separator.
     */
    protected String getFilenameExtension() {
        return '.' + getFormat().getFilenameExtension();
    }

    /**
     * Answers the state of wrapping the HTML exported content.
     *
     * @return true - Use the header.html and footer.html resources when
     * exporting HTML.
     */
    @Override
    public Boolean getWrapHTML() {
        return this.wrapHtml;
    }

    /**
     * Controls whether header.html and footer.html are included in the HTML
     * export of the report.
     *
     * @param wrapHtml true - Wrap the HTML with head/body tags.
     */
    @Override
    public void setWrapHtml(final boolean wrapHtml) {
        this.wrapHtml = wrapHtml;
    }

    /**
     * Changes the locale to use for language settings.
     *
     * @param locale Either Canadian English (default) or Canadian French.
     */
    @Override
    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns the locale to use for language settings.
     *
     * @return English (default) or French locale.
     */
    protected Locale getLocale() {
        return this.locale;
    }

    @Override
    public void setParameterPredicate(final Predicate<String> parameterPredicate) {
        this.parameterPredicate = parameterPredicate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Predicate<String> getParameterPredicate() {
        return this.parameterPredicate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void processParameters() {
        final boolean isPaginated = getFormat().isPaginated();
        final Locale reportLocale = getLocale();

        // One-to-one relationship with resource bundle names.
        final ResourceBundle bundle = getResourceBundle();

        preprocessParameters();

        // Localization.
        setParameter("REPORT_RESOURCE_BUNDLE", bundle);
        setParameter("REPORT_LOCALE", reportLocale);

        // Relative paths for subreports, images, etc.
        setParameter("P_REPORT_BASE", DIR_JASPER_BASE);

        // Relative paths for signatures.
        setParameter("P_REPORT_SIGNATURES_PATH", signatureImageApiConstants.getSignatureImageBaseUrl());

        // Toggle pagination for HTML reports.
        setParameter(IS_IGNORE_PAGINATION, !isPaginated);

        // Allow the reports to alter output based on report format.
        setParameter("P_REPORT_FORMAT", getFormat().name());

        postprocessParameters();
    }

    /**
     * Returns the resource bundle for the report's locale. The locale is based
     * on the report template name.
     *
     * @return A non-null instance that corresponds to the locale.
     */
    @Override
    public ResourceBundle getResourceBundle() {
        final Locale reportLocale = getLocale();
        final String directory = getResourceBundleDirectory();
        final String resourceBundleName = getResourceBundleName();

        final ResourceBundle result = getBundle(
                directory,
                resourceBundleName,
                reportLocale
        );

        return result;
    }

    /**
     * Returns the name to use for resource bundle lookups.
     *
     * @return The report name.
     */
    protected String getResourceBundleName() {
        return getName();
    }

    /**
     * Returns the directory to use for resource bundle lookups.
     *
     * @return The i18n directory located in the report's resources directory.
     */
    protected String getResourceBundleDirectory() {
        return DIR_INTERNATIONALIZATION;
    }

    /**
     * Does nothing. Subclasses override to change the report name when
     * necessary (prior to export).
     */
    @Override
    public void preprocessReportName() {
    }
}

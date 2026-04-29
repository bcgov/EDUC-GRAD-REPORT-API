/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
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
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.PdfGlyphRenderer;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.FontInfo;
import net.sf.jasperreports.engine.fonts.FontUtil;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.JRTextAttribute;
import net.sf.jasperreports.export.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_EXITING;
import static net.sf.jasperreports.export.type.HtmlSizeUnitEnum.POINT;

/**
 * Abstracts away the JasperReports-specific API calls so that the report
 * interface implementations do not get passed to the client side. This prevents
 * marshaling of JasperReports objects, which would lead to class not found
 * issues.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
public class JasperReportImpl {

    private static final String CLASSNAME = JasperReportImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);
    private static final String FONT_FAMILIES_RESOURCE = "fonts/fontsfamily.xml";
    private static final String PDF_GLYPH_RENDERER_BLOCKS_PROPERTY =
            PdfReportConfiguration.PROPERTY_PREFIX_GLYPH_RENDERER_BLOCKS + "indigenous";
    private static final String PDF_GLYPH_RENDERER_BLOCKS =
            "COMBINING_DIACRITICAL_MARKS,IPA_EXTENSIONS,SPACING_MODIFIER_LETTERS,PHONETIC_EXTENSIONS";
    private static final Set<Character.UnicodeBlock> PDF_GLYPH_RENDERER_UNICODE_BLOCKS =
            new LinkedHashSet<>(Arrays.asList(
                    Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS,
                    Character.UnicodeBlock.IPA_EXTENSIONS,
                    Character.UnicodeBlock.SPACING_MODIFIER_LETTERS,
                    Character.UnicodeBlock.PHONETIC_EXTENSIONS
            ));
    private static final JasperReportsContext JASPER_REPORTS_CONTEXT = createJasperReportsContext();

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
        final String methodName = "export()";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final ReportFormat format = getFormat();
        final Exporter exporter = createExporter();
        final byte[] bytes;

        try (final ByteArrayOutputStream out = createByteArrayOutputStream()) {
            //final JasperPrint print = format.equals(XML) ? createEmptyReport() : createFilledReport();

            final JasperPrint print = createFilledReport();
            if (ReportFormat.PDF.equals(format)) {
                configurePdfGlyphRendering(print);
            }

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
                    exporter.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(out));
                    break;
                case PDF:
                    exporter.setConfiguration(createPdfExporterConfiguration());
                    exporter.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(out));
                    break;
                default:
                    exporter.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(out));
                    break;
            }

            final ExporterInput exporterInput = new SimpleExporterInput(print);
            exporter.setExporterInput(exporterInput);
            exporter.exportReport();
            bytes = out.toByteArray();
            if (ReportFormat.PDF.equals(format)) {
                logPdfActualTextDiagnostic(bytes);
            }
        } catch (final Exception ex) {
            final String msg = "Could not export report";
            LOG.log(Level.SEVERE, msg, ex);
            throw new IOException(ex);
        }

        final ReportDocumentImpl result = new ReportDocumentImpl(bytes);

        log.trace(LOG_TRACE_EXITING, methodName);
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
                exporter = new JRPdfExporter(JASPER_REPORTS_CONTEXT);
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

    private void configurePdfGlyphRendering(final JasperPrint print) {
        logPdfGlyphRenderingSupport();
        logPdfFontExtensionDiagnostics();
        print.setProperty(PDF_GLYPH_RENDERER_BLOCKS_PROPERTY, PDF_GLYPH_RENDERER_BLOCKS);
        print.setProperty(PdfReportConfiguration.PROPERTY_GLYPH_RENDERER_ADD_ACTUAL_TEXT, Boolean.TRUE.toString());
        logPdfGlyphRendererCandidates(print);
    }

    private static JasperReportsContext createJasperReportsContext() {
        final SimpleJasperReportsContext context =
                new SimpleJasperReportsContext(DefaultJasperReportsContext.getInstance());

        try (final InputStream inputStream = JasperReportImpl.class.getClassLoader()
                .getResourceAsStream(FONT_FAMILIES_RESOURCE)) {
            if (inputStream == null) {
                LOG.warning("Unable to load Jasper font families because the resource is missing: "
                        + FONT_FAMILIES_RESOURCE);
                return context;
            }

            final List<FontFamily> fontFamilies = SimpleFontExtensionHelper.getInstance()
                    .loadFontFamilies(context, inputStream);
            context.setExtensions(FontFamily.class, fontFamilies);
        } catch (final IOException | RuntimeException ex) {
            LOG.warning("Unable to load Jasper font families from " + FONT_FAMILIES_RESOURCE + ": " + ex.getMessage());
        }

        return context;
    }

    private void logPdfGlyphRenderingSupport() {
        try {
            com.lowagie.text.pdf.PdfContentByte.class.getMethod("showText", GlyphVector.class);
            final URL source = getPdfContentByteSource();
            LOG.info("PDF glyph rendering support detected. Jasper supported="
                    + PdfGlyphRenderer.supported()
                    + ", iText source=" + source);
        } catch (final NoSuchMethodException ex) {
            LOG.warning("PDF glyph rendering is disabled because the loaded iText jar does not support "
                    + "PdfContentByte.showText(GlyphVector): " + getPdfContentByteSource());
        }
    }

    private void logBundledPdfFontDiagnostics() {
        final List<String> diagnostics = new ArrayList<>();
        addBundledFontDiagnostic("BCSans-Regular", "fonts/BCSans/BCSans-Regular.ttf", diagnostics);
        addBundledFontDiagnostic("BCSans-Bold", "fonts/BCSans/BCSans-Bold.ttf", diagnostics);
        addBundledFontDiagnostic("BCSans-Italic", "fonts/BCSans/BCSans-Italic.ttf", diagnostics);
        addBundledFontDiagnostic("BCSans-BoldItalic", "fonts/BCSans/BCSans-BoldItalic.ttf", diagnostics);
        LOG.info("PDF bundled font diagnostics=" + diagnostics);
    }

    private void addBundledFontDiagnostic(final String label,
                                          final String resource,
                                          final List<String> diagnostics) {
        try (final InputStream inputStream = JasperReportImpl.class.getClassLoader().getResourceAsStream(resource)) {
            if (inputStream == null) {
                diagnostics.add(label + " resourceMissing=" + resource);
                return;
            }

            final Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            diagnostics.add(label
                    + " family=" + font.getFamily()
                    + ", fontName=" + font.getFontName()
                    + ", psName=" + font.getPSName());
        } catch (final FontFormatException | IOException ex) {
            diagnostics.add(label + " error=" + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }

    private void logPdfFontExtensionDiagnostics() {
        final List<String> fontFamilyNames = new ArrayList<>();
        final List<FontFamily> fontFamilies = JASPER_REPORTS_CONTEXT.getExtensions(FontFamily.class);
        if (fontFamilies != null) {
            for (final FontFamily fontFamily : fontFamilies) {
                fontFamilyNames.add(fontFamily.getName());
            }
        }

        final FontInfo bcSansFontInfo = FontUtil.getInstance(JASPER_REPORTS_CONTEXT)
                .getFontInfo("BCSans", null);
        LOG.info("PDF Jasper font extension diagnostics: BCSans found="
                + (bcSansFontInfo != null)
                + ", fontFamilies=" + fontFamilyNames);
    }

    private URL getPdfContentByteSource() {
        return com.lowagie.text.pdf.PdfContentByte.class.getProtectionDomain().getCodeSource() == null
                ? null
                : com.lowagie.text.pdf.PdfContentByte.class.getProtectionDomain().getCodeSource().getLocation();
    }

    private void logPdfGlyphRendererCandidates(final JasperPrint print) {
        final List<String> candidates = new ArrayList<>();
        final List<JRPrintPage> pages = print.getPages();

        for (int pageIndex = 0; pageIndex < pages.size(); pageIndex++) {
            collectPdfGlyphRendererCandidates(pages.get(pageIndex).getElements(), pageIndex, candidates);
        }

        if (candidates.isEmpty()) {
            LOG.info("PDF glyph renderer candidate text elements=0");
        } else {
            logBundledPdfFontDiagnostics();
            LOG.info("PDF glyph renderer candidate text elements=" + candidates.size() + "; " + candidates);
        }
    }

    private void collectPdfGlyphRendererCandidates(final List<JRPrintElement> elements,
                                                  final int pageIndex,
                                                  final List<String> candidates) {
        for (final JRPrintElement element : elements) {
            if (element instanceof JRPrintText) {
                final JRPrintText text = (JRPrintText) element;
                final String fullText = text.getFullText();
                if (containsPdfGlyphRendererCandidate(fullText)) {
                    candidates.add("page=" + pageIndex
                            + ", key=" + element.getKey()
                            + ", font=" + text.getFontName()
                            + ", bold=" + text.isBold()
                            + ", italic=" + text.isItalic()
                            + ", codepoints=" + getPdfGlyphRendererCandidateCodepoints(fullText)
                            + ", styledRuns=" + getStyledTextFontDiagnostics(text));
                }
            } else if (element instanceof JRPrintFrame) {
                collectPdfGlyphRendererCandidates(((JRPrintFrame) element).getElements(), pageIndex, candidates);
            }
        }
    }

    private List<String> getStyledTextFontDiagnostics(final JRPrintText text) {
        final List<String> diagnostics = new ArrayList<>();

        try {
            final JRStyledText styledText = text.getFullStyledText(
                    JRStyledTextAttributeSelector.getAllSelector(JASPER_REPORTS_CONTEXT));
            if (styledText == null) {
                diagnostics.add("styledText=null");
                return diagnostics;
            }

            final AttributedCharacterIterator iterator = styledText.getAttributedString().getIterator();
            int run = 0;
            int index = iterator.getBeginIndex();
            iterator.setIndex(index);

            while (index < iterator.getEndIndex()) {
                final Map<AttributedCharacterIterator.Attribute, Object> attributes = iterator.getAttributes();
                diagnostics.add("run=" + run
                        + ", range=" + index + "-" + iterator.getRunLimit()
                        + ", family=" + attributes.get(TextAttribute.FAMILY)
                        + ", fontInfo=" + sanitizeAttributeValue(attributes.get(JRTextAttribute.FONT_INFO))
                        + ", pdfFontName=" + attributes.get(JRTextAttribute.PDF_FONT_NAME)
                        + ", pdfEncoding=" + attributes.get(JRTextAttribute.PDF_ENCODING)
                        + ", pdfEmbedded=" + attributes.get(JRTextAttribute.IS_PDF_EMBEDDED)
                        + ", weight=" + attributes.get(TextAttribute.WEIGHT)
                        + ", posture=" + attributes.get(TextAttribute.POSTURE)
                        + ", size=" + attributes.get(TextAttribute.SIZE));
                index = iterator.getRunLimit();
                iterator.setIndex(index);
                run++;
            }
        } catch (final Exception ex) {
            diagnostics.add("error=" + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }

        return diagnostics;
    }

    private String sanitizeAttributeValue(final Object value) {
        if (value == null) {
            return null;
        }
        return value.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(value));
    }

    private boolean containsPdfGlyphRendererCandidate(final String text) {
        if (text == null) {
            return false;
        }

        for (int offset = 0; offset < text.length(); ) {
            final int codePoint = text.codePointAt(offset);
            if (PDF_GLYPH_RENDERER_UNICODE_BLOCKS.contains(Character.UnicodeBlock.of(codePoint))) {
                return true;
            }
            offset += Character.charCount(codePoint);
        }

        return false;
    }

    private Set<String> getPdfGlyphRendererCandidateCodepoints(final String text) {
        final Set<String> codepoints = new LinkedHashSet<>();
        for (int offset = 0; offset < text.length(); ) {
            final int codePoint = text.codePointAt(offset);
            if (PDF_GLYPH_RENDERER_UNICODE_BLOCKS.contains(Character.UnicodeBlock.of(codePoint))) {
                codepoints.add(String.format("U+%04X", codePoint));
            }
            offset += Character.charCount(codePoint);
        }
        return codepoints;
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

        return JasperFillManager.getInstance(JASPER_REPORTS_CONTEXT).fill(
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

    private SimplePdfExporterConfiguration createPdfExporterConfiguration() {
        final SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(false);
        return configuration;
    }

    private void logPdfActualTextDiagnostic(final byte[] bytes) {
        final String pdfContent = new String(bytes, StandardCharsets.ISO_8859_1);
        LOG.info("PDF ActualText marker present=" + pdfContent.contains("/ActualText"));
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

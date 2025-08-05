/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
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

import ca.bc.gov.educ.grad.report.dto.reports.data.impl.Student;
import ca.bc.gov.educ.grad.report.model.common.Predicate;
import ca.bc.gov.educ.grad.report.model.common.support.xml.XmlBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.export.*;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_ENTERING;
import static ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants.LOG_TRACE_EXITING;

/**
 * This exporter is used in the creation of XML documents that are to be used in
 * communication with PSIs. The purpose is to parse reports and document data in
 * order to create the proper element hierarchy.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
public class XmlExporter implements Exporter {

    private static final String CLASSNAME = XmlExporter.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private ReportContext reportContext;
    private Document document;
    private final Map<String, Element> elementMap = new LinkedHashMap<>();
    private ExporterOutput exporterOutput;
    private ExporterInput exporterInput;
    private XmlExporterConfiguration xmlExporterConfiguration;

    private final XmlBuilder xmlBuilder = new XmlBuilder();

    public XmlExporter() {
    }

    /**
     * This method takes an existing report and adds the Element structure based
     * on the parameter map associated with the document. On first pass the root
     * element is created and then the boolean is flipped to false.
     *
     * @throws JRException
     */
    @Override
    public void exportReport() throws JRException {
        final String methodName = "exportReport()";
        log.trace(LOG_TRACE_ENTERING, methodName);

        try {
            final Element docRoot = createElement("root");
            LOG.fine("Document root element created.");

            getDocument().appendChild(docRoot);
            LOG.fine("Document root element added to document.");

            final Element parameterElement = createParameterElement();

            if (parameterElement != null) {
                LOG.fine("Parameter elements created.");

                final Node importParameters = getDocument().importNode(parameterElement, true);
                LOG.fine("Parameter elements imported to current document.");

                docRoot.appendChild(importParameters);
                LOG.fine("Root element appended with imported parameter elements.");
            } else {
                LOG.fine("No parameter elements created.");
            }

            final Element dataSourceElement = createDataSourceElement();
            LOG.fine("Data source elements created.");

            docRoot.appendChild(dataSourceElement);
            LOG.fine("Root element appended with data source element.");

            exportDocument();
        } catch (final ParserConfigurationException | DOMException | JAXBException | IOException | SAXException ex) {
            final Throwable t = getRootCause(ex.getCause());
            log.info(ex.getMessage());
            throw new JRException(ex);
        }

        log.trace(LOG_TRACE_EXITING, methodName);
    }

    public Throwable getRootCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    private Element createElement(final String key) throws ParserConfigurationException {
        final Element element = getDocument().createElement(key);
        LOG.log(Level.FINE, "Element created with name {0}", key);

        return element;
    }

    private Document createDocument() throws ParserConfigurationException {
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        final Document result = docBuilder.newDocument();
        LOG.fine("Document created.");

        return result;
    }

    private Element createParameterElement() throws ParserConfigurationException {
        final Map<String, Object> parameterMap = getReportContext().getParameterValues();
        LOG.fine("Element parameters fetched.");

        final Predicate<String> predicate = getParameterPredicate();
        final Element parameterRootElement = getXmlBuilder().buildElementFromMap(parameterMap, predicate);

        return parameterRootElement;
    }

    private Element createDataSourceElement() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        final String methodName = "createDataSourceElement()";
        log.trace(LOG_TRACE_ENTERING, methodName);

        final Student student = getStudentDataSource();
        student.setCreatedOn(new Date());
        final String result;

        try (final StringWriter sw = new StringWriter()) {
            final JAXBContext jc = JAXBContext.newInstance(Student.class);
            final Marshaller marshaller = jc.createMarshaller();

            marshaller.marshal(student, sw);
            sw.flush();
            result = sw.toString();
        }

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(result));

        final Document dataSourceDocument = builder.parse(is);
        final Element dataSourceElement = dataSourceDocument.getDocumentElement();

        // createParameterElement uses its own DOM that cannot directly
        // add child nodes from a different DOM. Instead, the data source
        // element that was just created needs to be coerced into an element
        // that can be added.
        final Element imported = (Element) getDocument().importNode(
                dataSourceElement, true);

        return imported;
    }

    @Override
    public void setExporterInput(final ExporterInput input) {
        this.exporterInput = input;
    }

    @Override
    public void setExporterOutput(final ExporterOutput output) {
        this.exporterOutput = output;
    }

    @Override
    public void setConfiguration(final ReportExportConfiguration configuration) {
        final String methodName = "setConfiguration(ReportExportConfiguration)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        log.trace(LOG_TRACE_EXITING, methodName);
    }

    @Override
    public void setConfiguration(final ExporterConfiguration configuration) {
        final String methodName = "setConfiguration(ExporterConfiguration)";
        log.trace(LOG_TRACE_ENTERING, methodName);

        this.xmlExporterConfiguration = (XmlExporterConfiguration) configuration;

        log.trace(LOG_TRACE_EXITING, methodName);
    }

    private XmlExporterConfiguration getXmlExporterConfiguration() {
        return this.xmlExporterConfiguration;
    }

    private Student getStudentDataSource() {
        final Object o = getDataSource();

        return o == null ? new Student() : (Student) o;
    }

    private Object getDataSource() {
        final XmlReportContext xmlReportContext = (XmlReportContext) getReportContext();
        final Object dataSource = xmlReportContext.getDataSource();
        return dataSource;
    }

    private void exportDocument() throws IOException, ParserConfigurationException {
        final Document xmlDocument = getDocument();
        final String xml = getXmlBuilder().convertDocumentToXml(xmlDocument);
        getExporterOutput().getOutputStream().write(xml.getBytes());
    }

    private SimpleOutputStreamExporterOutput getExporterOutput() {
        return (SimpleOutputStreamExporterOutput) this.exporterOutput;
    }

    @Override
    public void setReportContext(final ReportContext reportContext) {
        this.reportContext = reportContext;
    }

    @Override
    public ReportContext getReportContext() {
        return this.reportContext;
    }

    private Document getDocument() throws ParserConfigurationException {
        if (this.document == null) {
            this.document = createDocument();
        }

        return this.document;
    }

    public Map<String, Element> getElementMap() {
        return this.elementMap;
    }

    private Predicate<String> getParameterPredicate() {
        return getXmlExporterConfiguration().getPredicate();
    }

    private XmlBuilder getXmlBuilder() {
        return this.xmlBuilder;
    }
}

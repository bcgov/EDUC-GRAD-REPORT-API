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
 *  File:                XmlBuilder.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.common.support.xml;

import ca.bc.gov.educ.grad.report.model.common.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.grad.report.model.common.Constants.DATE_ISO_8601_YMD;
import static ca.bc.gov.educ.grad.report.model.common.Constants.XML_DELIM;

/**
 * This class is a utility class used in the creation of XML. The methods take
 * linked hash maps and parses them to create a WC3 Document.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Slf4j
public class XmlBuilder {

    private static final String CLASSNAME = XmlBuilder.class.getName();
    protected static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private Document document;
    private final Map<String, Element> elementMap = new LinkedHashMap<>();

    private static final String DATE_FORMAT = DATE_ISO_8601_YMD;

    private Document getDocument() throws ParserConfigurationException {
        if (this.document == null) {
            this.document = createDocument();
        }

        return this.document;
    }

    private Map<String, Element> getElementMap() {
        return this.elementMap;
    }

    /**
     * Method creates an Element and assigns a value based on the key and value
     * from the parameter map.
     *
     */
    private void toXml(final String key, final String value)
            throws ParserConfigurationException {
        final String methodName = "toXml(final String key, final String value)";
        log.trace("Entering {}", methodName);

        String[] tokens = key.split(XML_DELIM);
        List<String> tokenList = new ArrayList(Arrays.asList(tokens));
        tokenList.remove(tokenList.size() - 1);
        LOG.fine("Split the key and removed the last token.");

        String parentKey = buildString(tokenList);
        LOG.fine("Set parent key to key without the last token.");

        Element parentElement = getElementMap().get(parentKey);
        if (parentElement == null) {
            parentElement = createElementHierarchy(parentKey);
        }
        LOG.fine("Parent element created.");

        String childElementKey = tokens[tokens.length - 1];
        Element childElement = createElement(childElementKey);
        LOG.fine("Child element created for the child key.");

        childElement.setTextContent(value);
        LOG.fine("Child element assigned value.");

        parentElement.appendChild(childElement);
        LOG.fine("Child element added to parent element.");

        log.trace("Exiting {}", methodName);
    }

    /**
     * Method takes the key and creates the element structure if it is not yet
     * created.
     *
     * @param key name structure of the elements to be created if required.
     * @return the parent that has been created.
     */
    private Element createElementHierarchy(final String key) throws ParserConfigurationException {
        final String methodName = "createElementHierarchy(final String key)";
        log.trace("Entering {}", methodName);

        final String[] tokens = key.split(XML_DELIM);
        final String lastToken = tokens[tokens.length - 1];
        LOG.fine("Key split into tokens. Last key assigned from last token ");

        final List<String> tokenList = new ArrayList(Arrays.asList(tokens));
        tokenList.remove(tokenList.size() - 1);
        String parentKey = buildString(tokenList);

        LOG.fine("Parent key set to key without the last token.");

        Element parentElement = getElementMap().get(parentKey);
        LOG.fine("Parent node fetched using the parent key from element map.");

        //if parent node == null then parent key = createElementHierarchy( parentKey )
        if (parentElement == null) {
            parentElement = createElementHierarchy(parentKey);
        }

        final Element childElement = createElement(lastToken);
        LOG.fine("Child element created for last token.");

        parentElement.appendChild(childElement);
        LOG.fine("Child element appended to the parent.");

        getElementMap().put(key, childElement);
        LOG.fine("Element map updated with created child element.");

        log.trace("Exiting {}", methodName);

        return childElement;
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

    private String buildString(final List<String> inList) {
        final StringBuilder builder = new StringBuilder(256);
        for (final String string : inList) {
            if (builder.length() > 0) {
                builder.append(XML_DELIM);
            }
            builder.append(string);
        }
        return builder.toString();
    }

    /**
     * This is used to take a completed DOM and convert it to a XML document.
     *
     * @param document the complete WC3 Document object.
     * @return the string representation of a converted XML documentation.
     */
    public String convertDocumentToXml(final Document document) {
        final String methodName = "convertDocumentToXml(Document)";
        log.trace("Entering {}", methodName);

        String result = null;

        try {
            final DOMSource domSource = new DOMSource(document);
            final TransformerFactory factory = TransformerFactory.newInstance();
            final Transformer transformer = factory.newTransformer();

            final StringWriter sw = new StringWriter();

            final StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            result = sw.toString();

        } catch (final TransformerConfigurationException ex) {
            LOG.throwing(CLASSNAME, methodName, ex);
        } catch (final TransformerException ex) {
            LOG.throwing(CLASSNAME, methodName, ex);
        }

        return result;
    }

    public Element buildElementFromMap(final Map<String, Object> parameterMap, Predicate<String> predicate) throws ParserConfigurationException {
        final String methodName = "buildElementFromMap(Map<String, Object>, Predicate<String>)";
        log.trace("Entering {}", methodName);

        final Set<String> paramKeys = parameterMap.keySet();
        final Element result;

        if (predicate == null) {
            result = parseMap(parameterMap, paramKeys);
        } else {
            final Collection<String> filteredParamKeys = predicate.filter(paramKeys);
            result = parseMap(parameterMap, filteredParamKeys);
        }

        LOG.fine("Parameter map parsed.");

        log.trace("Exiting {}", methodName);
        return result;
    }

    private String convertObjectType(final Object value) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        final String retVal;

        if (value instanceof Date) {
            retVal = sdf.format(value);
        } else {
            retVal = String.valueOf(value);
        }

        return retVal;
    }

    private Element parseMap(
            final Map<String, Object> parameterMap,
            final Collection<String> filteredParamKeys) throws ParserConfigurationException {
        boolean isFirst = true;
        Element parameterRootElement = null;

        for (final String thisKey : filteredParamKeys) {
            final String key = thisKey;
            final String value = convertObjectType(parameterMap.get(key));

            if (isFirst) {
                final String[] tokens = key.split(XML_DELIM);
                LOG.finest("Split key into tokens.");

                // Obtain the root element that all subsequent mapped values
                // are attached to either directly or indirectly.
                final String root = tokens[0];
                LOG.finest("Root element token name extracted.");

                // All subsequent nodes become children of this node. In the
                // XML Exporter Unit Test, this is "ParamMap".
                parameterRootElement = createElement(root);
                LOG.finest("Root node created from token.");

                // Provide the ability to look up the root node by forthcoming
                // child nodes.
                getElementMap().put(root, parameterRootElement);
                LOG.finest("Added root element to element map.");

                isFirst = false;
            }

            toXml(key, value);
            LOG.finer("Key and value parameter elements added to element map.");
        }

        return parameterRootElement;
    }
}

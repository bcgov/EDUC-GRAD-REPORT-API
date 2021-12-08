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
package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.impl.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

/**
 * This converter class is meant to take a Student object and marshal it to an
 * XML string.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class XmlConverterUtil {

    private static final String CLASSNAME = XmlConverterUtil.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    public XmlConverterUtil() {
    }

    public Element marshalStudentToElement(final Student student) throws JAXBException, ParserConfigurationException, SAXException, IOException {
        final String _m = "marshalStudentToElement(Student)";
        LOG.entering(CLASSNAME, _m);

        Element dataSourceRootElement = null;

        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (final XMLEncoder xmlEncoder = new XMLEncoder(baos)) {
                xmlEncoder.writeObject(student);
            }

            final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            final InputSource is = new InputSource();
            final String xml = baos.toString();
            is.setCharacterStream(new StringReader(xml));

            final Document doc = db.parse(is);
            dataSourceRootElement = (Element) doc.getFirstChild();
        } catch (final ParserConfigurationException | SAXException | IOException ex) {
            LOG.throwing(CLASSNAME, CLASSNAME, ex);
            throw ex;
        }

        LOG.entering(CLASSNAME, _m);
        return dataSourceRootElement;
    }
}

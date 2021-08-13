/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.bccampus;

import java.io.InputStream;
import javax.xml.stream.XMLStreamException;

/**
 * The document fragment parser takes an input stream and checks start, end and
 * element values.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DocumentFragmentParser {

    /**
     * Resets and initializes this instance with a new XML data source. This is
     * used primarily for parsing PESC XML documents in order to generate
     * XML response documents.
     *
     * @param xml The XML document containing fragments to parse.
     * @throws XMLStreamException Could not read the input stream.
     */
    void initialize(InputStream xml) throws XMLStreamException;

    /**
     * Gets the next element processor that represents a document fragment.
     *
     * @return Element Processor
     * @throws XMLStreamException
     */
    ElementProcessor getNext() throws XMLStreamException;

    /**
     * Are there more elements to be parsed in the stream?
     *
     * @return
     * @throws javax.xml.stream.XMLStreamException
     */
    boolean hasNext() throws XMLStreamException;

    /**
     * This handles whether a valid batch was fetched by the adaptor. It should
     * only be called once. Calling this method twice in succession or during a
     * single stream fetch session can lead to an element being omitted.
     *
     * @return
     */
    boolean documentsReturned();
}

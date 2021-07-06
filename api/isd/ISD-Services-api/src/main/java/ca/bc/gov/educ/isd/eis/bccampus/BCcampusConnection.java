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
package ca.bc.gov.educ.isd.eis.bccampus;

import ca.bc.gov.educ.isd.eis.EISException;
import java.io.IOException;
import java.io.Serializable;
import javax.resource.cci.Connection;

/**
 * Methods to interact with the BCcampusConnection once it is established.
 *
 * The methods represent different actions that are HTTP REST calls to the BC
 * campus API.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCcampusConnection extends Connection, Serializable {

    /**
     * POST a transcript to BCcampus
     *
     * @param content Transcript to be sent to BCcampus.
     * @return ID string of acknowledgment
     * @throws java.io.IOException
     */
    String doPost(byte[] content) throws IOException;

    /**
     * GET a batch of requests and/or acknowledgments from BCcampus.
     *
     * @param uuid Unique identifier used to fetch a batch of requests.
     * @return Batch of requests from BCcampus if available.
     * @throws java.io.IOException
     * @throws ca.bc.gov.educ.isd.eis.EISException
     * @throws javax.xml.stream.XMLStreamException
     */
    DocumentFragmentParser getRequests(String uuid) throws Exception;

    /**
     * GET the status of a previously sent transcript.
     *
     * @param documentID Unique identifier of the transcript previously sent.
     * @return command results
     * @throws java.io.IOException
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    String getDocumentStatus(String documentID) throws IOException, EISException;

    /**
     * Verify if BCcampus is accepting incoming connections.
     *
     * @return Boolean if the connection is receiving.
     */
    boolean verifyConnection();

    /**
     * Close the connection.
     */
    @Override
    void close();
}

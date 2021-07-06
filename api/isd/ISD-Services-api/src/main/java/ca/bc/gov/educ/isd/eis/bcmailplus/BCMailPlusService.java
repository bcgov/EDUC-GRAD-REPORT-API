/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *  This information contained herein may not be used in whole
 *  or in part without the express written consent of the
 *  Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        BCMailPlusService.java
 *  Date of Last Commit: $Date:: 2016-JUL-10                                   $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author:: cagomez                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.bcmailplus;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.common.Constants.DocumentType;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMailPlusService extends Serializable {

    // ------------------ METHOD(S)
    String validateServerSetup() throws IOException, Exception;

    /**
     * Acts as factory for documents that are sent to the remote server.
     * Documents are always sent to the <i>inbox</i> folder and their name is
     * always auto-generated. The <i>inbox</i> folder path is specified in the
     * Application Server as a JNDI.
     *
     * @param content content of the document in a byte array format.
     * @param documentId Id of the document to send.
     * @param documentType type of the document to send (i.e.,
     * {@link ISD-BCMailPlusAdaptor-EIS-rar.BCMailPlusDocumentImpl#CERTIFICATE}
     * and
     * {@link ISD-BCMailPlusAdaptor-EIS-rar.BCMailPlusDocumentImpl#TRANSCRIPT}).
     * @return the document to be sent to BC Mail Plus
     * @throws EISException
     */
    boolean createDocumentTOSend(byte[] content, String documentId, DocumentType documentType) throws EISException;

    /**
     * <p>
     * Sends a document to a remote server, renaming the file in the process
     * using an auto-generated file name.</p>
     *
     * <p>
     * The method connect itself to the server (if there is not an available
     * connection). The method retries a specific number of time to send the
     * file to the remote server waiting a few milliseconds between each
     * attempt.</p>
     *
     * <p>
     * The files are sent to the <i>inbox</i> folder. The <i>inbox</i> folder
     * location is specified in the Application Server.</p>
     *
     * @param content Document to send to BC Mail Plus.
     * @return ID of the document transferred. If something goes wrong it
     * returns null.
     * @throws java.io.IOException
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    String mailDocument(byte[] content) throws IOException, EISException;

    /**
     * Test the ability of the system to retry connection if a minor connection
     * problem happen.
     *
     * @param content Document to send to BC Mail Plus.
     * @return ID of the document transferred. If something goes wrong it
     * returns null.
     * @throws IOException
     * @throws EISException
     */
    String retryToMailDocument(byte[] content) throws IOException, EISException;

    /**
     * <p>
     * Gets all the Status document which represents a confirmation of a
     * previously sent documents. Additionally, it moves the read Status files
     * to a different folder in the remote serve, renaming the files in the
     * process.</p>
     *
     * <p>
     * The method connect itself to the server, if there is not an available
     * connection. The method retries a specific number of time to retrieve the
     * files from the remote server waiting a few milliseconds between each
     * attempt.</p>
     *
     * <p>
     * The Status files are read from the <i>outbox</i> folder, and later, moved
     * to the <i>response</i> folder. If the <i>outbox</i> folder and
     * <i>response</i> folder are the same, then the file is just renamed. Both
     * location are specified in the Application Server.</p>
     *
     * <p>
     * This method is meant to be called by a JMS client.</p>
     *
     * @return a map of documents id and dates.
     * @throws java.io.IOException
     * @throws ca.bc.gov.educ.isd.eis.EISException acts as an exception wrapper
     * for underlying classes or methods.
     */
    List<BCMailPlusResponseDocument> retrieveStatusDocuments() throws IOException, Exception;

    /**
     * <p>
     * Deletes an array of files in a remote server.</p>
     *
     * <p>
     * The method connect itself to the server (if there is not an available
     * connection). The method retries a specific number of time to delete the
     * files from the remote server waiting a few milliseconds between each
     * attempt.</p>
     *
     * <p>
     * The files are deleted from the <i>outbox</i> folder. The <i>outbox</i>
     * folder location is specified in the Application Server.</p>
     *
     * @param filesToDelete string array with the id of the documents to be
     * deleted.
     * @return the number of files deleted.
     * @throws Exception
     */
    boolean deleteFiles(final String[] filesToDelete) throws Exception;

}

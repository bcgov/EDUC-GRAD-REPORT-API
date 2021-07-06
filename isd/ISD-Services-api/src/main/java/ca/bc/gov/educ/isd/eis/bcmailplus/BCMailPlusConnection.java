/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date:: 2016-JUL-10                                   $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author:: cagomez                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.bcmailplus;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.common.Constants.DocumentType;
import java.io.Closeable;
import java.io.Serializable;
import java.util.List;
import javax.resource.cci.Connection;

/**
 * Methods to interact with the BCMailPlusConnection once it is established. The
 * methods represent common operations that can be execute over SSH and SFTP
 * connections
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMailPlusConnection extends Connection, Serializable, BCMailPlusClient, Closeable {

    /**
     * Sends a document to a remote server.
     *
     * @param doc document to be sent to BC Mail Plus
     * @return correlation id for the document submitted
     * @throws ca.bc.gov.educ.isd.eis.EISException acts as a wrapper for other
     * exception.
     * @throws java.lang.InterruptedException thrown when a thread is waiting,
     * sleeping, or otherwise occupied, and the thread is interrupted, either
     * before or during the activity.
     */
    String mailDocument(final BCMailPlusSentDocument doc) throws EISException, InterruptedException;

    /**
     * <p>
     * Gets all the Status document which represents a confirmation of a
     * previously sent documents. </p>
     *
     * <p>
     * The method connect itself to the server, if there is not an available
     * connection. The method retries a specific number of time to retrieve the
     * files from the remote server waiting a few milliseconds between each
     * attempt.</p>
     *
     * <p>
     * The Status files are read from the <i>outbox</i> folder.
     *
     * <p>
     * This method is meant to be called by a JMS client.</p>
     *
     * @return BCMailPlusResponseDocument list.
     * @throws ca.bc.gov.educ.isd.eis.EISException acts as an exception wrapper
     * for underlying classes or methods.
     * @throws java.lang.InterruptedException thrown when a thread is waiting,
     * sleeping, or otherwise occupied, and the thread is interrupted, either
     * before or during the activity.
     */
    public List<BCMailPlusResponseDocument> retrieveMultipleResponseDocuments() throws EISException, InterruptedException;

    /**
     * Acts as factory for documents that are going to be sent to the remote
     * server. Documents are always sent to the inbox folder and their name is
     * always auto-generated.
     *
     * @param content content of the document in a byte array format.
     * @param documentId Id of the document to send.
     * @param documentType type of the document to send (see
     * {@link BCMailPlusResponseDocument BCMailPlusResponseDocument}).
     * @return the document to be sent to BC Mail Plus
     * @throws EISException
     */
    BCMailPlusSentDocument
            createDocumentTOSend(final byte[] content, final String documentId, final DocumentType documentType) throws EISException;

    /**
     * Acts as factory for documents to be sent. Documents are always sent to
     * the <i>inbox</i> folder. The <i>inbox</i> folder path is specified in the
     * Application Server as a JNDI.
     *
     * @param content content of the document in a byte array format.
     * @param fileName Name of the file that is going to be sent.
     * @return the document to be sent to the remote server.
     * @throws EISException acts as a wrapper for other exception.
     */
    BCMailPlusSentDocument createDocumentTOSend(final byte[] content, final String fileName) throws EISException;

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
     * @param filesToDelete Ids of the files that want to be deleted.
     * @return the number of files deleted.
     * @throws EISException acts as a wrapper for other exception.
     * @throws java.lang.InterruptedException
     */
    int deleteFiles(final String[] filesToDelete) throws EISException, InterruptedException;

    /**
     * Close the connection.
     */
    @Override
    void close();
}

/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                BCMailPlusClient.java
 *  Date of Last Commit: $Date:: 2016-JUL-10                                   $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author:: cagomez                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.bcmailplus;

/**
 * Represents a generic SSH client (e.g., SFTP, SCP, or SSH). The methods
 * represent common operations that can be execute over SFTP, SCP, and SSH
 * connections
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMailPlusClient {

    /**
     * Gets the host name to connect to.
     *
     * @return String that contains the hostname to connect to.
     */
    String getHostname();

    /**
     * Gets the user name used for login.
     *
     * @return String that contains the username used for login.
     */
    String getUsername();

    /**
     * Gets the port number used for the SFTP connection.
     *
     * @return Integer the port at the remote host we will connect (or already
     * connected) to.
     */
    Integer getPort();

    /**
     * Sets the port number used for the SFTP connection.
     *
     * @param port the port at the remote host used for the connection.
     */
    void setPort(int port);

    /**
     * Gets the default folder that contains the files to send.
     *
     * @return Path to the folder from which files are going to be read.
     */
    String getInbox();

    /**
     * Gets the default folder where files are going to be (or are) put once
     * transmitted to the remote host.
     *
     * @return Path to the folder used as a dropbox in the remote host.
     */
    String getOutbox();

    /**
     * Gets the default folder where the response files are going to be (or are)
     * moved once transmitted locally.
     *
     * @return Path to the folder use to store response files in the remote
     * host.
     */
    String getResponseFileFolder();

    /**
     * Gets the time to wait in milliseconds before breaking off.
     *
     * @return the time to wait in milliseconds before breaking off.
     */
    Integer getResponseTimeout();

    /**
     * Sets the time to wait in milliseconds before breaking off. Defaults to
     * 60000 milliseconds.
     *
     * @param responseTimeout time in milliseconds for timeout response.
     */
    void setResponseTimeout(int responseTimeout);

    /**
     * Gets the transfer status of the last file sent.
     *
     * @return the transfer status of the last file sent.
     */
    boolean getLastTransferStatus();

    /**
     * Gets the number of times a sending process is going to retry to send the
     * document to a remote server.
     *
     * @return the number of times a sending process is going to be retried.
     */
    int getRetriesTimes();

    /**
     * Sets the number of times a sending process is going to retry to send the
     * document to a remote server. Default 5 times.
     *
     * @param retriesTimes the number of times a sending process is going to
     * retry to send the document.
     */
    void setRetriesTimes(int retriesTimes);

    /**
     * Gets the waiting time (milliseconds) before trying to send/get a document
     * to/from a remote server.
     *
     * @return waiting time (milliseconds) before trying to send/get a document
     * to/from a remote server.
     */
    int getWaitingTimeBeforeRetry();

    /**
     * Sets the waiting time (milliseconds) before trying to send/get a document
     * to/from a remote server. Default 5 times.
     *
     * @param waitingTimeBeforeRetry waiting time (milliseconds) before trying
     * to send/get a document to/from a remote server.
     */
    void setWaitingTimeBeforeRetry(int waitingTimeBeforeRetry);

}

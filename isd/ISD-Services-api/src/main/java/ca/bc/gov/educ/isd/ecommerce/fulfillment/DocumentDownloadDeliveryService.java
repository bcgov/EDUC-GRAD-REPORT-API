/*
 * *********************************************************************
 *   Copyright (c) 2017, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id::                                                $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015) $
 *   Revision Number:     $Rev:: 36                                            $
 *   Last Commit by:      $Author:: bbates                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.fulfillment;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.ecommerce.sales.PurchasedDocument;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException;

/**
 * Support delivery of transcripts and certificates by download.
 *
 * The Document Download Delivery Service provide access to a document for a
 * given download key. Additionally the service manages the download key expiry
 * and the lockout in the event of to many failed challenge responses.
 *
 * @author CGI Information Management Consultants, Inc.
 */
public interface DocumentDownloadDeliveryService extends BusinessService {

    /**
     * Determine if a download Id represents a Third Party Document Download
     * Delivery Item.
     *
     * @param downloadId The identifier sent in the download email.
     * @return TRUE, if the identifier parameter is a Third Party Document
     * Download Delivery Item.
     * @throws DomainServiceException If the request cannot be processed.
     * @throws SalesOrderLifeCycleException If the id does not represent a Third
     * Party Download Delivery Item.
     */
    Boolean checkValid(final String downloadId)
            throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Determines if a document download has expired.
     *
     * @param downloadId The document identifier sent in the download email.
     * @return TRUE, if the document download window has expired.
     * @throws DomainServiceException If the request cannot be processed.
     * @throws SalesOrderLifeCycleException If the id does not represent a Third
     * Party Download Delivery Item.
     */
    Boolean isExpired(final String downloadId)
            throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Answers TRUE if the given challenge response equals the challenge answer
     * for a given document (by way of its download ID). If the response is
     * incorrect, this will increment the number of attempts associated with the
     * delivery information for the given download ID.
     *
     * @param downloadId The document identifier sent in the download email.
     * @param challengeResponse
     * @return TRUE iff the answer to the challenge question was correct for an
     * existing document identified by documentId.
     * @throws DomainServiceException
     * @throws SalesOrderLifeCycleException
     */
    Boolean checkChallengeResponse(final String downloadId, final String challengeResponse)
            throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Retrieve the challenge question, which must be answered to download the
     * document.
     *
     * @param downloadId The document identifier sent in the download email.
     * @return The challenge question to be answered by the when retrieving the
     * document
     * @throws DomainServiceException If the request cannot be processed.
     * @throws SalesOrderLifeCycleException If the id does not represent a Third
     * Party Download Delivery Item.
     */
    String retrieveChallenge(final String downloadId)
            throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Retrieves the document to be downloaded. If the challenge response is
     * correct, this will mark the document as downloaded, as well, so that
     * subsequent attempts are not permitted. This can be called any number of
     * times without triggering a maximum attempt failure.
     *
     * @param downloadId The document identifier sent in the download email.
     * @param challengeResponse The answer to the challenge question.
     * @return The purchased document.
     * @throws DomainServiceException If the request cannot be processed.
     * @throws SalesOrderLifeCycleException If the id does not represent a Third
     * Party Download Delivery Item.
     */
    PurchasedDocument retrieveDocument(final String downloadId, final String challengeResponse)
            throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Answer whether the document may be downloaded.
     *
     * @param downloadId The document identifier sent in the download email.
     * @return Boolean if the number of attempts have been exceeded.
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     * @throws DomainServiceException If the request cannot be processed.
     */
    Boolean isChallengeAttemptsExceeded(final String downloadId)
            throws DomainServiceException, SalesOrderLifeCycleException;

    /**
     * Reset the download challenge to 0.
     * 
     * @param downloadId The document identifier sent in the download email.
     * @throws DomainServiceException If the request cannot be processed.
     */
    void resetChallengeAttempts(final String downloadId) throws DomainServiceException;
    
    /**
     * Return if the document has been downloaded.
     *
     *
     * @param downloadId The document identifier sent in the download email.
     * @return Boolean if the number of successful downloads was exceeded.
     * @throws DomainServiceException If the request cannot be processed.
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     */
    Boolean isSuccessfulDownloadsExceeded(final String downloadId)
            throws DomainServiceException, SalesOrderLifeCycleException;
    
    /**
     * Increase the number of successful Downloads for the document associated
     * with the downloadId.
     * 
     * @param downloadId The document identifier sent in the download email.
     * @throws DomainServiceException If the request cannot be processed.
     */
    void increaseSuccessfulDownloads(final String downloadId) throws DomainServiceException;

    /**
     * Return the name of the student who is associated with the download
     * authorization.
     *
     *
     * @param downloadId The document identifier sent in the download email.
     * @return The name of the student.
     * @throws ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderLifeCycleException
     * @throws DomainServiceException If the request cannot be processed.
     */
    String getStudentName(final String downloadId)
            throws DomainServiceException, SalesOrderLifeCycleException;
}

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
package ca.bc.gov.educ.isd.ecommerce.pesc;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderItem;
import ca.bc.gov.educ.isd.users.UserProfile;
import java.util.Date;
import java.util.Map;

/**
 * Manage transmissions to PESC Institutions.
 * <p>
 * Students (and potentially other parties) can order transcripts. Some of the
 * PSIs are compatible with sending PESC XML transcripts. This service
 * communicates with the appropriate EIS adaptor to deliver transcripts to these
 * PSI. The conversion from the Transcript domain entity and and a temporary
 * transcript conversion object occurs within the service.
 * <p>
 * The service will perform follow up actions when the external system is polled
 * to update the status and act on placed transmissions.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PESCTransmissionService extends CommonEntityService<PESCTransmission, SearchResult> {

    /**
     * Called to create a PESCTransmission prior to sending in PESC XML format.
     * This is used to test the XML document that is created.
     *
     * @param salesOrderItem An item that was purchased (should have a
     * transcript associated with it).
     * @return Transmission data including XML document to transmit.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    PESCTransmission createPESCTransmission(SalesOrderItem salesOrderItem) throws DomainServiceException;

    /**
     * Called when a transcript is to be transmitted in the PESC XML format when
     * in PSI order fulfillment.
     *
     * @param salesOrderItem
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void forwardTranscript(SalesOrderItem salesOrderItem) throws DomainServiceException;

    /**
     * Used to check the transcript status at BCcampus. Null indicates that
     * there was an error in getting the status from BCcampus. This is used so
     * that throwing an error can be avoided if the HUB is unavailable which
     * would cause the fulfillment thread to immediately try again and
     * potentially throw another.
     *
     * @param pt
     * @return
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    PESCTransmissionStatusEnum checkTranscriptStatus(PESCTransmission pt) throws DomainServiceException;

    /**
     * Called when a transcript requires a resend due to the external receiver
     * being unavailable (ie BCcampus)
     *
     * @param pescTransmission
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    public void resendTranscript(PESCTransmission pescTransmission) throws DomainServiceException;

    /**
     * Called when a transcript requires a resend due to bad formatting or
     * schema validation failure.
     *
     * @param pescTransmission
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    public void resendFailedTranscript(PESCTransmission pescTransmission) throws DomainServiceException;

    /**
     * When the temporal bean queries to update the status of a PESC
     * transmission there is the possibility that NOT_FOUND can be returned.
     * This method is responsible for sending the an email to notify a defined
     * user.
     *
     * @param userEntityId
     * @param documentId
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void sendNotFoundNotificationEmail(final String userEntityId, final String documentId) throws DomainServiceException;

    /**
     * Used to find the active PESCTransmission given a user entity ID and a PSI
     * code which is taken from the Sales Order Item. There should only be one
     * active PESC transmission per student per PSI at any given time. Even if
     * the Sales order item being passed in is not associated with the current
     * PESC transmission the proper active sales order item linked to the active
     * PESC transmission will be returned.
     *
     * @param soi
     * @param updates Should the result only be a Sales order item linked to an
     * 'UPDATES_TX' type PESCTransmission.
     * @return
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    SalesOrderItem findExistingTransmissionsBySalesOrderItem(SalesOrderItem soi, Boolean updates) throws DomainServiceException;

    /**
     * Method will return the existing authorization date the currently
     * authenticated user has granted to a PSI to request transcript updates.
     *
     * @param psiCode
     * @return The date that updates can be requested until.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    Date findExistingAuthDateByPSICode(String psiCode) throws DomainServiceException;

    /**
     * The functional acknowledgment data is previously extracted from an XML
     * document fetched from the hub. Processing this data results in a
     * PESCTransmission having an updated timestamp indicating that it has been
     * imported into in a PSI's student information staging area.
     *
     * @param elementDataMap
     * @param batchId
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void processFunctionalAcknowledgements(Map elementDataMap, String batchId) throws DomainServiceException;

    /**
     * The transcript acknowledgment data is previously extracted from an XML
     * document fetched from the hub. Processing this data results in a
     * PESCTransmission having an updated timestamp indicating that it has been
     * verified in a PSI's student information staging area. Request Track ID is
     * what is used in the transcript acknowledgment to reference the document
     * ID of the transcript that is acknowledged.
     *
     * @param elementDataMap
     * @param batchId
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void processTranscriptAcknowledgements(Map elementDataMap, String batchId) throws DomainServiceException;

    /**
     * The Transcript Response will contain the following information: Tag:
     * ResponseStatus will contain either "TranscriptRequestReceived" or
     * "NoRecord"
     * <ul>
     * <li>"TranscriptRequestReceived" indicates that a match was found on PEN
     * and Birthdate</li>
     * <li> "NoRecord" indicates that a match was not found on PEN and
     * Birthdate</li>
     * </ul>
     *
     * Tag: Notemessage will contain the following valid values as reasons for
     * not sending a Transcript: "BC: Authorization is Expired", "BC: Not
     * Authorized", "BC: Invalid Student Identifier", or "BC: Mismatched
     * Birthdate for Student Identifier".
     * <ul>
     * <li> "BC: Authorization is Expired" indicates an authorization exists for
     * the PSIS Code, PEN and Birthdate but the authorization date is in the
     * past. </li>
     * <li> "BC: Not Authorized" indicates that a match was not found for the
     * PSIS Code, PEN and Birthdate
     * </li>
     * <li> "BC: Invalid Student Identifier" indicates that the PEN is not valid
     * or no student authorization exists for this PEN </li>
     * <li> "BC: Mismatched Birthdate for Student Identifier" indicates that the
     * PEN and Birthdate do not match any of the authorizations</li>
     * </ul>
     *
     * @param elementDataMap
     * @param batchId
     * @throws DomainServiceException
     */
    void processTranscriptRequests(Map elementDataMap, String batchId) throws DomainServiceException;

    void sendEmail(final UserProfile profile, final String cause, final String response) throws DomainServiceException;
}

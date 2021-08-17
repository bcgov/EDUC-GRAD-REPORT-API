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
package ca.bc.gov.educ.isd.ecommerce.pesc;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.ecommerce.delivery.PSIDeliveryInfo;
import java.util.Date;

/**
 * A Transcript Transmission Record entity. This entity is managed by the
 * PESCTransmissionService. These entities will record the life cycle of a
 * transcript transmission to a PSI. This will include functional and transcript
 * acknowledgments.
 */
public interface PESCTransmission extends DomainEntity {

    /**
     * Retrieve the entity identifier of the user that placed this order.
     *
     * @return EID / uuid of the user that placed this order.
     */
    String getUserEntityId();

    /**
     * Retrieve the entity identifier of the sales order item associated with
     * the transcript.
     *
     * @return EID / uuid of the user that placed this order.
     */
    String getSalesOrderItemEntityId();

    /**
     * Retrieve the status of the transmission.
     *
     * @return Status of the transcript transmission.
     */
    PESCTransmissionStatusEnum getStatus();

    /**
     * Retrieve the sentTranscriptBatchId ID. This is returned by BCcampus when
     * a received transcript is accepted and structurally well formed.
     *
     * @return The batch id of the transmission.
     */
    String getSentTranscriptBatchId();

    /**
     * Retrieve the Document ID. This is the internal ID assigned by ISD for the
     * transmitted document. This acts as a reference outside the system when
     * corresponding with BCcampus.
     *
     * @return The ISD assigned document ID.
     */
    String getDocumentId();

    /**
     * Retrieve the XML Transcript
     *
     * @return the XML Transcript
     */
    String getTranscriptData();

    /**
     * Retrieve the ID of the functional acknowledgment.
     *
     * @return the XML Transcript
     */
    String getFunctionalAckId();

    /**
     * Retrieve the Date of the functional acknowledgment.
     *
     * @return the XML Transcript
     */
    Date getFunctionalAckDate();

    /**
     * Retrieve the ID of the transcript acknowledgment.
     *
     * @return the XML Transcript
     */
    String getTranscriptAckId();

    /**
     * Retrieve the Date of the transcript acknowledgment.
     *
     * @return the XML Transcript
     */
    Date getTranscriptAckDate();

    /**
     * Retrieve the ID of the transcript request.
     *
     * @return the XML Transcript
     */
    String getTranscriptRequestId();

    /**
     * Retrieve the Date of the transcript request.
     *
     * @return the XML Transcript
     */
    Date getTranscriptRequestDate();

    /**
     * Retrieve the type of transcript transmission relating the academic state
     * of the marks to be sent.
     *
     * @return the phase of marks sent in the transmission.
     */
    PESCTransmissionTypeEnum getType();

    /**
     * Retrieve the code of the destination PSI
     *
     * @return
     */
    String getPsiCode();

    /**
     * Returns the (transient) PSI to which the data shall be transmitted.
     *
     * @return A non-null instance.
     */
    PSIDeliveryInfo getPSIDeliveryInfo();
}

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

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum PESCTransmissionStatusEnum {

    //medium retry
    UNSENT_NO_RCVR("Unsent, no receiver available"),
    //long retry
    UNACCEPTED_BAD_FORMAT("Unaccepted by receiver, bad format"),
    FAILED_VALIDATION("Failed validation"),
    //short retry
    SENT("Sent"),
    PENDING("Pending"),
    //good to go
    VERIFIED("Verified, passed validation"),
    DELIVERED("Delivered"),
    ACKNOWLEDGED("Acknowledge"),
    UPDATE_TRANSCRIPT_DELIVERED("Update Transcript Delivered"),
    UPDATE_TRANSCRIPT_ACKNOWLEDGED("Update Transcript Acknowledged"),
    FAILED_BAD_SOURCE("Failed Bad Source Organization"),
    FAILED_UNKNOWN_DESTINATION("Failed Unknown Destination"),
    FAILED_UNKNOWN_CAUSE("Failed Unknown Cause"),
    //do nothing
    NOT_FOUND("DocumentId not found");

    private String description;

    private PESCTransmissionStatusEnum(final String description) {
        setDescription(description);
    }

    public String getDescription() {
        return this.description;
    }

    private void setDescription(final String description) {
        this.description = description;
    }
}

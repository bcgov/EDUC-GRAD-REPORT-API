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
package ca.bc.gov.educ.isd.reports.admin.data;

import java.util.Date;

/**
 * Defines the accessors for admin report data.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface IdleTranscript extends AdminReportDAO {

    /**
     * Sets the document ID that was used in XML transmission.
     *
     * @param id The transmission's document ID.
     */
    void setDocumentId(String id);

    /**
     * Returns the document ID that was used in XML transmission.
     *
     * @return The transmission's document ID.
     */
    String getDocumentId();

    /**
     * Returns the order number corresponding to the document that was ordered.
     *
     * @param orderNumber The document order number.
     */
    void setOrderNumber(String orderNumber);

    /**
     * Returns the order number corresponding to the document that was ordered.
     *
     * @return The document order number.
     */
    String getOrderNumber();

    /**
     * Sets the name of the recipient to which the transcript was sent (this
     * will typically be the PSI).
     *
     * @param recipient The recipient name.
     */
    void setRecipient(String recipient);

    /**
     * Returns the name of the recipient to which the transcript was sent.
     *
     * @return The recipient name.
     */
    String getRecipient();

    /**
     * Sets the identifier for the student who ordered the transcript.
     *
     * @param pen Student's personalized education number (PEN).
     */
    void setPen(String pen);

    /**
     * Returns the identifier for the student who ordered the transcript.
     *
     * @return The student identifier (PEN).
     */
    String getPen();

    /**
     * Sets when the transcript was ordered.
     *
     * @param orderedDate Date transcript was ordered.
     */
    void setOrderedDate(Date orderedDate);

    /**
     * Returns the date the transcript was ordered.
     *
     * @return The transcript ordered date.
     */
    Date getOrderedDate();
}

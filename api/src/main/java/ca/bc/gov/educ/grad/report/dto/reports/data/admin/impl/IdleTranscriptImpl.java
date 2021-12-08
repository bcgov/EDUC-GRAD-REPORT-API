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
package ca.bc.gov.educ.grad.report.dto.reports.data.admin.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.BusinessEntity;

import java.util.Date;

/**
 * @author CGI Information Management Consultants Inc.
 */
public class IdleTranscriptImpl extends BusinessEntity {

    private static final long serialVersionUID = 1L;

    private String documentId;
    private String orderNumber;
    private String recipient;
    private String pen;
    private Date orderedDate;

    /**
     * Default (empty) constructor.
     */
    public IdleTranscriptImpl() {
    }

    public void setDocumentId(final String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setRecipient(final String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setPen(final String pen) {
        this.pen = pen;
    }

    public String getPen() {
        return pen;
    }

    public void setOrderedDate(final Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    /**
     * Used to create instances of the outer class.
     */
    public static final class Builder extends BusinessEntity.Builder<IdleTranscriptImpl, Builder> {

        /**
         * Returns the builder used to construct outer class instances.
         *
         * @return this
         */
        @Override
        protected Builder thisBuilder() {
            return this;
        }

        @Override
        protected IdleTranscriptImpl createObject() {
            return new IdleTranscriptImpl();
        }

        /**
         * Transcript document ID.
         *
         * @param documentId Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public IdleTranscriptImpl.Builder withDocumentId(final String documentId) {
            getObject().setDocumentId(documentId);
            return thisBuilder();
        }

        /**
         * Transcript order number.
         *
         * @param orderNumber Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public IdleTranscriptImpl.Builder withOrderNumber(final String orderNumber) {
            getObject().setOrderNumber(orderNumber);
            return thisBuilder();
        }

        /**
         * Transcript recipient.
         *
         * @param recipient Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public IdleTranscriptImpl.Builder withRecipient(final String recipient) {
            getObject().setRecipient(recipient);
            return thisBuilder();
        }

        /**
         * Student PEN.
         *
         * @param pen Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public IdleTranscriptImpl.Builder withPen(final String pen) {
            getObject().setPen(pen);
            return thisBuilder();
        }

        /**
         * Transcript ordered date.
         *
         * @param orderedDate Value for the built instance.
         * @return thisBuilder Builder for populating the outer instance.
         */
        public IdleTranscriptImpl.Builder withOrderedDate(final Date orderedDate) {
            getObject().setOrderedDate(orderedDate);
            return thisBuilder();
        }
    }
}

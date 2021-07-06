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
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.IdleTranscript;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author CGI Information Management Consultants Inc.
 */
public class IdleTranscriptImpl implements IdleTranscript {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = IdleTranscriptImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private String documentId;
    private String orderNumber;
    private String recipient;
    private String pen;
    private Date orderedDate;

    @Override
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public String getDocumentId() {
        return this.documentId;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String getOrderNumber() {
        return this.orderNumber;
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public void setPen(String pen) {
        this.pen = pen;
    }

    @Override
    public String getPen() {
        return pen;
    }

    @Override
    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    @Override
    public Date getOrderedDate() {
        return orderedDate;
    }
}

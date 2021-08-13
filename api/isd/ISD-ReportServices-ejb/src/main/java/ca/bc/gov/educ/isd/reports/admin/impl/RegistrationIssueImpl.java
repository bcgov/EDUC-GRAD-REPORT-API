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

import ca.bc.gov.educ.isd.reports.admin.data.RegistrationIssue;

import java.util.Date;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class RegistrationIssueImpl implements RegistrationIssue {

    private static final long serialVersionUID = 5L;

    private static final String CLASSNAME = RegistrationIssueImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private Date registered;
    private String reference;
    private String status;
    private String queued;

    @Override
    public void setRegistered(final Date registered) {
        this.registered = registered;
    }

    @Override
    public Date getRegistered() {
        return this.registered;
    }

    @Override
    public void setReference(final String reference) {
        this.reference = reference;
    }

    @Override
    public String getReference() {
        return this.reference;
    }

    @Override
    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setQueued(final String queued) {
        this.queued = queued;
    }

    @Override
    public String getQueued() {
        return this.queued;
    }

    @Override
    public int compareTo(final Object that) {
        final Date thisDate = nullSafe(getRegistered());
        final Date thatDate = that instanceof RegistrationIssue
                ? nullSafe(((RegistrationIssue) that).getRegistered())
                : new Date();

        return thisDate.compareTo(thatDate);
    }
}

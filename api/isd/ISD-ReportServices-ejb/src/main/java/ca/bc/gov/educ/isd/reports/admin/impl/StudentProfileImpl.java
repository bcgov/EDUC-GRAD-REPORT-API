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

import ca.bc.gov.educ.isd.reports.admin.data.StudentProfile;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class StudentProfileImpl implements StudentProfile {

    private static final long serialVersionUID = 5L;

    private static final String CLASSNAME = StudentProfileImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private Date registered;
    private Integer bceId;
    private Integer bcServices;

    @Override
    public void setRegistered(final Date registered) {
        this.registered = registered;
        LOG.log(Level.FINE, "setting registered: {0}.", new Object[]{registered});
    }

    @Override
    public Date getRegistered() {
        return this.registered;
    }

    @Override
    public void setBceId(final Integer bceId) {
        this.bceId = bceId;
    }

    @Override
    public Integer getBceId() {
        return this.bceId;
    }

    @Override
    public void setBcServices(final Integer bcServices) {
        this.bcServices = bcServices;
    }

    @Override
    public Integer getBcServices() {
        return this.bcServices;
    }

    @Override
    public int compareTo(final Object that) {
        final Date thisDate = nullSafe(getRegistered());
        Date thatDate = new Date();
        if (that instanceof StudentProfile) {
            thatDate = nullSafe(((StudentProfile) that).getRegistered());
        }

        return thisDate.compareTo(thatDate);
    }

    @Override
    public String toString() {
        return CLASSNAME + "{registered=" + registered + ", bceId=" + bceId + ", bcServices=" + bcServices + '}';
    }
}

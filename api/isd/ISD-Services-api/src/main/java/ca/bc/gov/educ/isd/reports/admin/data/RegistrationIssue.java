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
 *  File:                $Id:: RegistrationIssue.java 7208 2017-05-23 23:25:1#$
 *  Date of Last Commit: $Date:: 2017-05-23 16:25:16 -0700 (Tue, 23 May 2017) $
 *  Revision Number:     $Rev:: 7208                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                   $
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
public interface RegistrationIssue extends AdminReportDAO, Comparable {

    /**
     * Returns the value for registered.
     *
     * @return Profile activation date.
     */
    public Date getRegistered();

    /**
     * Sets the value for registered.
     *
     * @param registered Profile activation date.
     */
    void setRegistered(Date registered);

    /**
     * Returns the value for reference.
     *
     * @return STs-assigned unique number.
     */
    String getReference();

    /**
     * Sets the value for reference.
     *
     * @param reference STs-assigned unique number.
     */
    void setReference(String reference);

    /**
     * Returns the value for status.
     *
     * @return Intervention status.
     */
    String getStatus();

    /**
     * Sets the value for status.
     *
     * @param status Intervention status.
     */
    void setStatus(String status);

    /**
     * Returns the value for queued.
     *
     * @return Preformatted time (dd:hh:mm) that the orders have been in queue.
     */
    String getQueued();

    /**
     * Sets the value for queued.
     *
     * @param queued Preformatted time (dd:hh:mm) that the orders have been in
     * queue.
     */
    void setQueued(String queued);
}

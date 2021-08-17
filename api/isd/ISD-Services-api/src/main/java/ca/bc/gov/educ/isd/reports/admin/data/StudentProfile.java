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
 *  File:                $Id:: StudentProfile.java 10409 2018-05-31 19:46:43Z#$
 *  Date of Last Commit: $Date:: 2018-05-31 12:46:43 -0700 (Thu, 31 May 2018) $
 *  Revision Number:     $Rev:: 10409                                         $
 *  Last Commit by:      $Author:: bbates                                     $
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
public interface StudentProfile extends AdminReportDAO, Comparable {

    /**
     * Returns the date the profile was registered.
     *
     * @return Profile activation date.
     */
    public Date getRegistered();

    /**
     * Sets the date the profile was registered.
     *
     * @param registered Profile activation date.
     */
    void setRegistered(Date registered);

    /**
     * Returns the value for bceId.
     *
     * @return Has associated BCeID credentials.
     */
    public Integer getBceId();

    /**
     * Sets the value for bceId.
     *
     * @param bceId Has associated BCeID credentials.
     */
    void setBceId(Integer bceId);

    /**
     * Returns the value for bcServices.
     *
     * @return Has associated BC Services credentials.
     */
    public Integer getBcServices();

    /**
     * Sets the value for bcServices.
     *
     * @param bcServices Has associated BC Services credentials.
     */
    void setBcServices(Integer bcServices);
}

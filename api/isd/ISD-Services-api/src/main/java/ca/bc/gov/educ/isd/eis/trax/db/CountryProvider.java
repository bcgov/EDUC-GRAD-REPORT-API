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
package ca.bc.gov.educ.isd.eis.trax.db;

/**
 * Interface for the CountryProviderBean used for TRAX County Code conversion.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CountryProvider extends TRAXData {

    /**
     * Check if the data has expired and if so reset.
     *
     * @return true if the data has expired, false otherwise
     */
    public boolean checkExpiredAndProcess();

    /**
     * Update the existing files to new values.
     */
    public void reset();

    /**
     * Get the expiry interval in seconds.
     *
     * @return
     */
    public long getIntervalSeconds();

    /**
     * Set the expiry interval in seconds.
     *
     * @param interSeconds
     */
    public void setIntervalSeconds(final long interSeconds);

}

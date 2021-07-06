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
 *  File:                Scholarship.java
 *  Date of Last Commit: $Date::                   $
 *  Revision Number:     $Rev::                    $
 *  Last Commit by:      $Author::                 $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax.db;

import java.util.Date;

/**
 * Defines the public methods to access the Scholarship object containing
 * information on the scholarships which the student received
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Scholarship extends TRAXData {

    /**
     * get the PEN which the data corresponds to
     *
     * @return PEN
     */
    String getPen();

    /**
     * get the scholarship name
     *
     * @return scholarship name
     */
    String getScholarshipName();

    /**
     * get the date on which the scholarship expires. Format YYYY
     *
     * @return expiry date
     */
    Date getScholarshipExpiry();

    /**
     * get indicator showing if the scholarship has been redeemed if applicable
     *
     * @return Y if scholarship has been redeemed, N if not or N/A if not
     * applicable
     */
    String getRedeemedFlag();

    /**
     * get the monetary amount of the scholarship.
     *
     * @return scholarship amount
     */
    Integer getAmount();

    /**
     * get the code for the scholarship type.
     * <p>
     * @return A, S, T, D, etc.
     */
    Character getCode();

    /**
     * get the year in which the scholarship was awarded.
     * <p>
     * @return
     */
    Integer getAwardYear();
}

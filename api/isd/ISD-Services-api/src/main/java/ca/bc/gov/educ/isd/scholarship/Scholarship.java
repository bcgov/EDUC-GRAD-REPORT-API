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
 *  File:                $Id:: Scholarship.java 3760 2016-09-22 14:53:27Z cpri#$
 *  Date of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016)  $
 *  Revision Number:     $Rev:: 3760                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.scholarship;

import ca.bc.gov.educ.isd.common.DomainEntity;
import java.util.Date;

/**
 * An award or scholarship.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Scholarship extends DomainEntity {

    /**
     * Returns the award/scholarship name.
     *
     * @return The award/scholarship name.
     */
    String getName();

    /**
     * Returns the date after which the award/scholarship can no longer be
     * redeemed.
     *
     * @return The date that the award/scholarship is set to expire.
     */
     Date getExpiry();

    /**
     * Answers whether the award/scholarship has been redeemed.
     *
     * @return "Y", "N", or "N/A" (uppercase), possibly empty, never null.
     */
    String getRedeemed();

    /**
     * Returns the monetary value of the award.
     *
     * @return A whole number.
     */
     int getAmount();

    /**
     * Returns the code for the scholarship type.
     *
     * @return "A", "D", "S", or "T".
     */
    String getCode();

    /**
     * Returns the date when the scholarship was awarded.
     *
     * @return format YYYY
     */
     Date getYearAwarded();
}

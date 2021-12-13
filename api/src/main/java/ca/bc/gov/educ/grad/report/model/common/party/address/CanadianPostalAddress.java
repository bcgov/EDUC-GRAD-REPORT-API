/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CanadianPostalAddress.java 3155 2016-08-26 23:2#$
 *  Date of Last Commit: $Date:: 2016-08-26 16:20:04 -0700 (Fri, 26 Aug 2016)  $
 *  Revision Number:     $Rev:: 3155                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.common.party.address;

/**
 * Represents a mailing address for postal deliveries in Canada.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface CanadianPostalAddress extends PostalAddress {

    /**
     * Returns the two-letter province code, uppercase, with no spaces.
     *
     * @return Example, "BC"
     */
    String getProvince();
}

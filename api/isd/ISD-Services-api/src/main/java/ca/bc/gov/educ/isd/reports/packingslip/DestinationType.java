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
 *  File:                $Id:: DestinationType.java 3100 2016-08-25 17:55:55Z #$
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $
 *  Revision Number:     $Rev:: 3100                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.packingslip;

/**
 * Represents the destination for the print materials so that the packing slip
 * report can make minor variations in layout.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum DestinationType {

    /**
     * Post-Secondary Institution.
     */
    PSI;

    private DestinationType() {
    }
}

/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PackingSlipReport.java 4514 2016-10-20 18:38:12#$
 *  Date of Last Commit: $Date:: 2016-10-20 11:38:12 -0700 (Thu, 20 Oct 2016)  $
 *  Revision Number:     $Rev:: 4514                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports;

import ca.bc.gov.educ.isd.reports.packingslip.DestinationType;
import ca.bc.gov.educ.isd.reports.bundle.service.OrderType;
import ca.bc.gov.educ.isd.reports.packingslip.PackingSlipDetails;

/**
 * Represents information required to generate a packing slip for printed
 * student reports (e.g., transcripts, certificates, etc.).
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PackingSlipReport extends Report {

    /**
     * Mandatory call to set the packing slip delivery details (such as
     * application ID, student records name, locator code, transmitted date,
     * etc.).
     *
     * @param details The details to include on the packing slip.
     */
    void setPackingSlipDetails(PackingSlipDetails details);

    /**
     * Mandatory call to set the type of packing slip to generate (such as
     * Certificate or Transcript). This can only be called after
     * setPackingSlipDetails is called.
     *
     * @param orderType The packing slip order type.
     */
    void setOrderType(OrderType orderType);

    /**
     * Call when sending print materials to a Post-Secondary Institution. This
     * can be null, and is an optional call.
     *
     * @param destinationType Where to send the print materials.
     */
    void setDestinationType(DestinationType destinationType);
}

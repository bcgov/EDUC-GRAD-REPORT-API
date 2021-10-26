/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: PackingSlipDetails.java 4160 2016-10-11 00:23:0#$
 *  Date of Last Commit: $Date:: 2016-10-10 17:23:03 -0700 (Mon, 10 Oct 2016)  $
 *  Revision Number:     $Rev:: 4160                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports.packingslip;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.party.address.PostalAddress;

import java.util.Date;

/**
 * Represents the information required to fill out a packing slip.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PackingSlipDetails extends DomainEntity {

    /**
     * Returns the name of the student who has transcript and certificate
     * records that comprise the package to which this packing slip applies.
     *
     * @return A non-null String, possibly empty.
     */
    String getRecipient();

    /**
     * Returns the delivery address for mailing the transcripts and
     * certificates.
     *
     * @return A mailing address instance suitable for directing postal mail,
     * never null.
     */
    PostalAddress getAddress();

    /**
     * Returns the name of the person who ordered the transcripts and
     * certificates on behalf of the student whose records are sought.
     *
     * @return A non-null String, possibly empty.
     */
    String getOrderedByName();

    /**
     * Returns the number of documents included in the package.
     *
     * @return A whole number, never null.
     */
    Integer getDocumentsShipped();

    /**
     * Returns the STs order number.
     *
     * @return A non-null String, possibly empty.
     */
    String getOrderNumber();

    /**
     * Returns the date the packing slip was transmitted to the postal mail
     * handler. The transmission date should not be null when constructing an
     * instance of this class.
     *
     * @return The transmission date or the current date in the event that the
     * transmission date is null. This must not return null.
     */
    Date getOrderDate();

    /**
     * Indicates whether this packing slip is destined for a post-secondary
     * institution.
     *
     * @return PSI or null.
     */
    DestinationType getDestinationType();
}

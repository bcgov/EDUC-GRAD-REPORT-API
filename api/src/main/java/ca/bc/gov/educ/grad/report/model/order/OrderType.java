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
 *  File:                $Id:: OrderType.java 6786 2017-04-06 18:23:40Z tsalom#$
 *  Date of Last Commit: $Date:: 2017-04-06 11:23:40 -0700 (Thu, 06 Apr 2017)  $
 *  Revision Number:     $Rev:: 6786                                           $
 *  Last Commit by:      $Author:: tsalomon                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.order;

import java.io.Serializable;

/**
 * Represents the type of order so that the packing slip report can make minor
 * variations in layout. Should probably be an enumeration because
 * PostalMailFillServiceBean must filter sales orders based on the order type.
 * Without using an enumeration, the PostMailFillServiceBean must have code to
 * filter on order type of certificate and of transcript, rather than looping
 * through the available order types.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface OrderType extends Serializable {

    /**
     * Returns the name of the order type suitable for viewing by humans. The
     * return value is displayed on the packing slips verbatim.
     *
     * @return A human-readable text string ("Certificates" or "Transcripts").
     */
    String getName();

    /**
     * Returns the media colour defined by BC Mail Plus, which will vary
     * depending on the type of item being printed.
     *
     * @return A non-null, non-empty string.
     */
    String getMediaColour();

    /**
     * Returns the media type defined by BC Mail Plus, which will vary depending
     * on the type of item being printed.
     *
     * @return A non-null, non-empty string.
     */
    String getMediaType();
}

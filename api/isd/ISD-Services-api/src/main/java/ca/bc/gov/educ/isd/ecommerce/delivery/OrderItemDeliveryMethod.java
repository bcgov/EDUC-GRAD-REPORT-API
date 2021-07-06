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
package ca.bc.gov.educ.isd.ecommerce.delivery;

/**
 * Represents the various ways an item can be delivered from point A to point B.
 * This class acts as a translation layer between DeliveryMethodEnum and the
 * TransmissionMode enum. This allows the catalogue item description to be
 * determined based on various criteria, without resorting to hard-coded string
 * values.
 *
 * The enumerated names (POSTAL, DOWNLOAD, UPLOAD, etc.) are used to generate
 * resource bundle keys that are then used to get catalogue item descriptions.
 * For example in "postal.catalogue.item.description.interim.false", the POSTAL
 * prefix comes directly from "POSTAL.name()" where POSTAL is defined by this
 * enum. This allows the shopping cart to display a custom catalogue item
 * description depending on the delivery method.
 *
 * <pre>
 * TransmissionMode.PAPER = POSTAL
 * TransmissionMode.XML = UPLOAD
 * TransmissionMode.ELECTRONIC = UPLOAD
 * DeliveryMethod.POSTAL_MAIL = POSTAL
 * DeliveryMethod.PSI_PREF = [convert from TransmissionMode]
 * DeliveryMethod.DOWNLOAD = DOWNLOAD
 * DeliveryMethod.MANUAL = n/a
 * </pre>
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum OrderItemDeliveryMethod {

    /**
     * Printed and sent via postal mail.
     */
    POSTAL,
    /**
     * Downloaded from the server using a web browser.
     */
    DOWNLOAD,
    /**
     * Uploaded from the server to another server.
     */
    UPLOAD;
}

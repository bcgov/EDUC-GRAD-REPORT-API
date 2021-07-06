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
 * The method of delivery used by a sales order item.
 *
 * @author Ministry of Education, BC
 */
public enum DeliveryMethodEnum {

    /**
     * Send to a recipient by postal mail.
     */
    POSTAL_MAIL("POSTAL_MAIL", "Send to a recipient by postal mail."),
    /**
     * Send to a PSI by their preferred method of delivery (ie. Batch download,
     * Batch Mail, PESC XML, etc.).
     */
    PSI_PREF("PSI_PREF", "Send to a PSI by their preferred method of delivery (ie. Batch download, Batch Mail, PESC XML, etc.)."),
    /**
     * Recipient will download the item from the web.
     */
    DOWNLOAD("DOWNLOAD", "Recipient will download the item from the web."),
    /**
     * An administrator will manually fill the order item and mark the item as
     * complete.
     */
    MANUAL("MANUAL", "An administrator will manually fill the order item and mark the item as complete.");

    private String code;
    private String description;

    private DeliveryMethodEnum(final String code, final String description) {
        setCode(code);
        setDescription(description);
    }

    String getCode() {
        return this.code;
    }

    void setCode(final String code) {
        this.code = code;
    }

    String getDescription() {
        return this.description;
    }

    void setDescription(final String description) {
        this.description = description;
    }
}

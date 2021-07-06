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
package ca.bc.gov.educ.isd.ecommerce.sales;

/**
 * Fulfillment status of individual sales order items.
 *
 * @author Ministry of Education, BC
 */
public enum SalesOrderItemStatusEnum {

    NEW("NEW", "A new item awaiting fulfillment."),
    PROCESSING("PROCESSING", "The order item fulfillment is in progress."),
    FILLED("FILLED", "The order item fulfillment is complete.");

    private String code;

    private String description;

    private SalesOrderItemStatusEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}

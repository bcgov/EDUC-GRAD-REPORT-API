
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                     $
 *
 *
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales;

import static ca.bc.gov.educ.isd.common.Constants.CANCELLED_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.COMPLETE_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.FULFILLMENT_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.PAID_IDENTIFIER;
import static ca.bc.gov.educ.isd.common.Constants.PLACED_IDENTIFIER;

/**
 * Life-cycle status of an order.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum SalesOrderStatusEnum {

    NEW("NEW", "NEW"),
    CHECKEDOUT("CHECKEDOUT", "CHECKED OUT"),
    PLACED("PLACED", "PLACED"),
    PAID("PAID", "PAID"),
    FULFILLMENT("FULFILLMENT", "IN PROGRESS"),
    COMPLETE("COMPLETE", "COMPLETE"),
    CANCELLED("CANCELLED", "CANCELLED");

    private String code;
    private String description;

    // FIXME: These variables are not necessary.
    // TODO chini Removed those variables.
    private SalesOrderStatusEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    /**
     * Convenience method to check if this sales order status is FULFILLMENT.
     *
     * @return true represents a sales order status of FULFILLMENT.
     */
    public boolean isFulfillment() {
        return FULFILLMENT_IDENTIFIER.equalsIgnoreCase(this.code);

    }

    /**
     * Convenience method to check if this sales order status is PLACED.
     *
     * @return true represents a sales order status of PLACED.
     */
    public boolean isPlaced() {

        return PLACED_IDENTIFIER.equalsIgnoreCase(this.code);
    }

    /**
     * Convenience method to check if this sales order status is PAID.
     *
     * @return true represents a sales order status of PAID.
     */
    public boolean isPaid() {

        return PAID_IDENTIFIER.equalsIgnoreCase(this.code);
    }

    /**
     * Convenience method to check if this sales order status is COMPLETE.
     *
     * @return true represents a sales order status of COMPLETE.
     */
    public boolean isComplete() {

        return COMPLETE_IDENTIFIER.equalsIgnoreCase(this.code);
    }

    /**
     * Convenience method to check if this sales order status is CANCELLED.
     *
     * @return true represents a sales order status of CANCELLED.
     */
    public boolean isCancelled() {

        return CANCELLED_IDENTIFIER.equalsIgnoreCase(this.code);
    }

}
